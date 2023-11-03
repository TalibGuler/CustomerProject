
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Web.Mvc;

namespace CustomerProject.Models
{
    public class CustomerAddVM
    {
        public SelectList cityList { get; set; }
        public CustomerInsert customerInsert { get; set; }
    }

}