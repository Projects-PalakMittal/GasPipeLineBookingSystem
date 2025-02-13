namespace AdminModule.Models
{
    public class User
    {
        public int user_id { get; set; }
        public string first_name { get; set; }
        public string last_name { get; set; }
         public string email { get; set; }
        public string password { get; set; }
        public string phone_no { get; set; }

        public DateTime created_on { get; set; }
        public DateTime updated_on { get; set; }
        public string aadhar_card { get; set; }

        public DateTime dob { get; set; }

        public string pan_card { get; set; }

        public string user_name { get; set; }
        public string user_role { get; set; }
        public string user_status { get; set; }

        public int address_id { get; set; }


    }
}
