using AdminModule.Models;

using MySql.Data.MySqlClient;



namespace AdminModule.DAL
{
    public class DalAdmin
    {
        
            #region View all users
            public List<User> ViewAllUsers()
            {
                MySqlConnection connection = new MySqlConnection("server=localhost;uid=root;pwd=manager;database=gaspipeline");

                string queryText = "Select * from user where user_role = 'USER' ";
                //string query = string.Format(queryText, user.full_name, user.email, user.password, user.phone_no, user.created_time);

                MySqlCommand command = new MySqlCommand(queryText, connection);
                connection.Open();

               MySqlDataReader userList =  command.ExecuteReader();
                List<User> users = new List<User>();
            while (userList.Read())
            {
                User user = new User();
                user.user_id = Convert.ToInt32(userList[0]);
                user.created_on=Convert.ToDateTime(userList[1]);
                user.updated_on = Convert.ToDateTime(userList[2]);
                user.aadhar_card=userList[3].ToString();
                user.dob= Convert.ToDateTime(userList[4]);
                user.email = userList[5].ToString();
                user.first_name = userList[6].ToString();
                user.last_name = userList[7].ToString();
                user.pan_card = userList[8].ToString();
                user.password = userList[9].ToString();
                user.phone_no = userList[10].ToString();
                user.user_name = userList[11].ToString();
                user.user_role = userList[12].ToString();
                user.user_status= userList[13].ToString();
                users.Add(user);
            }

                connection.Close();
            return users;
            }

        #endregion

        #region View all vendors

        public List<User> ViewAllVendors()
        {
            MySqlConnection connection = new MySqlConnection("server=localhost;uid=root;pwd=manager;database=gaspipeline");

            string queryText = "Select * from user where user_role = 'VENDOR' ";
            //string query = string.Format(queryText, user.full_name, user.email, user.password, user.phone_no, user.created_time);

            MySqlCommand command = new MySqlCommand(queryText, connection);
            connection.Open();

            MySqlDataReader userList = command.ExecuteReader();
            List<User> vendors = new List<User>();
            while (userList.Read())
            {
                User user = new User();
                user.user_id = Convert.ToInt32(userList[0]);
                user.created_on = Convert.ToDateTime(userList[1]);
                user.updated_on = Convert.ToDateTime(userList[2]);
                user.aadhar_card = userList[3].ToString();
                user.dob = Convert.ToDateTime(userList[4]);
                user.email = userList[5].ToString();
                user.first_name = userList[6].ToString();
                user.last_name = userList[7].ToString();
                user.pan_card = userList[8].ToString();
                user.password = userList[9].ToString();
                user.phone_no = userList[10].ToString();
                user.user_name = userList[11].ToString();
                user.user_role = userList[12].ToString();
                user.user_status = userList[13].ToString();
                vendors.Add(user);
            }

            connection.Close();
            return vendors;
        }

        #endregion

        #region Delete user
        public void deleteUser(int id)
        {
            MySqlConnection connection = new MySqlConnection("server=localhost;uid=root;pwd=manager;database=gaspipeline");
            string queryText = "Update user set user_status='INACTIVE' where user_id = '{0}' ";
            string query = string.Format(queryText, id);

            MySqlCommand command = new MySqlCommand(query, connection);
            connection.Open();
            command.ExecuteNonQuery();
            connection.Close();
        }
        #endregion


    }
}
