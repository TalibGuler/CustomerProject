using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Threading.Tasks;
using System.Web;
using System.Web.Helpers;
using System.Web.Mvc;
using System.Web.UI.WebControls;
using CustomerProject.Models;
using Newtonsoft.Json;

namespace CustomerProject.Controllers
{
    public class HomeController : Controller
    {
        string Baseurl = "http://localhost:8080/";

        public async Task<ActionResult> Index()
        {
            List<Customer> customerInfo = new List<Customer>();



            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Baseurl);
                client.DefaultRequestHeaders.Clear();   
                client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
               
                try
                {
                    HttpResponseMessage Res = await client.GetAsync("api/musteri/musteri-listesi");
                    if (Res.IsSuccessStatusCode)
                    {
                        var customerResponse = Res.Content.ReadAsStringAsync().Result;
                        customerInfo = JsonConvert.DeserializeObject<List<Customer>>(customerResponse);
                    }
                }
                catch (Exception e)
                {
                    Console.WriteLine(e);
                }

                return View(customerInfo);
            }
        }

        [HttpPost]
        public JsonResult CustomerDetail(Customer customer)
        {
            return Json(customer);
        }

        public ActionResult CustomerDetails(Customer customer)
        {
            return View(customer);
        }

        public async Task<ActionResult> CustomerAdd()
        {
            CustomerAddVM customerAddVM = new CustomerAddVM();
            List<City> cList = new List<City>();

            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Baseurl);
                client.DefaultRequestHeaders.Clear();
               
                client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
                HttpResponseMessage Res = await client.GetAsync("api/musteri/hizmet-verilen-iller");
               
                if (Res.IsSuccessStatusCode)
                {
                    var cResponse = Res.Content.ReadAsStringAsync().Result;
                    cList = JsonConvert.DeserializeObject<List<City>>(cResponse);
                }

                customerAddVM.cityList = new SelectList(cList, "id", "name");

                ViewBag.Message = "Your application description page.";
                return View(customerAddVM);
            }
        }

        [HttpPost]
        public async Task<ActionResult> CustomerAdd(CustomerAddVM customerAddVM)
        {

            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Baseurl);
                client.DefaultRequestHeaders.Clear();
               
                client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));

                var buffer = System.Text.Encoding.UTF8.GetBytes(JsonConvert.SerializeObject(customerAddVM.customerInsert));
                var byteContent = new ByteArrayContent(buffer);
                byteContent.Headers.ContentType = new MediaTypeHeaderValue("application/json");

                try
                {
                    HttpResponseMessage response = await client.PostAsync("api/musteri/musteri-ekle", byteContent);
                    string responseStr = response.Content.ReadAsStringAsync().Result;
                }
                catch (Exception)
                {
                    throw;
                }

                return RedirectToAction("Index");
            }

        }

        [HttpPost]
        public async Task<JsonResult> CustomerDogrulama(Customer customer)
        {
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Baseurl);
                client.DefaultRequestHeaders.Clear();
               
                client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));

                var buffer = System.Text.Encoding.UTF8.GetBytes(JsonConvert.SerializeObject(customer));
                var byteContent = new ByteArrayContent(buffer);
                byteContent.Headers.ContentType = new MediaTypeHeaderValue("application/json");

                try
                {
                    HttpResponseMessage response = await client.PostAsync("api/musteri/musteri-dogrula", byteContent);
                    string responseStr = response.Content.ReadAsStringAsync().Result;
                    return Json(responseStr);
                }
                catch (Exception)
                {
                    return Json("Sunucu Hatası");
                }

            }
        }
    }
}