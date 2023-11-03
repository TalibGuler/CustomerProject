using System;

namespace CustomerProject.Models
{
    public class CustomerInsert
    {
        public string customerName { get; set; }
        public string customerSurname { get; set; }
        public string gender { get; set; }
        public DateTime birthdate  { get; set; }
        public string address { get; set; }
        public string isDisabled { get; set; }
        public string phoneNumber { get; set; }
        public string email { get; set; }
        public City city { get; set; }
    }
}