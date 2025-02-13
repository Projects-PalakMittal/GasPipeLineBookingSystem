using System.Text.Encodings.Web;
using System.Web.Mvc;
using AdminModule.DAL;
using AdminModule.Models;
using Microsoft.AspNetCore.Mvc;
using Controller = Microsoft.AspNetCore.Mvc.Controller;


namespace AdminModule.Controllers
{
    public class AdminController : Controller
    {
        DalAdmin dalObject = new DalAdmin();
        public IActionResult Admin()
        {
            List<User> users = dalObject.ViewAllUsers();
            return View("Admin" , users);
        }

        public IActionResult VendorDetails()
        {
            List<User> vendors = dalObject.ViewAllVendors();
            return View();
        }

        public IActionResult DeleteUser(int id)
        {
            dalObject.deleteUser(id);
            return Redirect("/Admin/Admin");
        }
        }
}
