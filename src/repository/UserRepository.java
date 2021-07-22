package repository;

import domain.*;

import java.sql.*;

public class UserRepository implements BaseRepository {

    @Override
    public void showAll(Connection connection) throws SQLException {

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select * from users");

        while (result.next()) {

            System.out.println("user ID : " + result.getInt(1));
            System.out.println("user name : " + result.getString(2));
            System.out.println("national code : " + result.getString(3));
            System.out.println("birth day :" + result.getDate(4));
            System.out.println("password: " + result.getString(5));
            System.out.println("---------------------------------------------\n");


        }


    }


    public static void setUser(Connection connection, User user) throws SQLException {

        PreparedStatement pre = connection.prepareStatement("insert into" +
                " users (userName,nationalCode,birthDay,passWord) values (?,?,?,?)");

        pre.setString(1, user.getUserName());
        pre.setString(2, user.getNationalCode());
        pre.setDate(3, user.getBirthDay());
        pre.setString(4, user.getPassword());

        pre.executeUpdate();

    }

    public static User getUser(Connection connection, String username) throws SQLException {
        User user = new User();

        Statement stm = connection.createStatement();
        ResultSet result = stm.executeQuery("select * from users where userName= '" + username + "'");

        while (result.next()) {

            user.setUserId(result.getInt(1));
            user.setUserName(result.getString(2));
            user.setNationalCode(result.getString(3));
            user.setBirthDay(result.getDate(4));
            user.setPassword(result.getString(5));
            user.setAdmin(result.getBoolean(6));
            user.setApprovedByAdmin(result.getBoolean(7));
            user.setBalance(result.getString(8));


        }

        stm.close();
        result.close();

        return user;
    }

    public static void updateUser(Connection connection, User user) throws SQLException {


        PreparedStatement pre = connection.prepareStatement
                ("UPDATE `hw6`.`users` SET " +
                        "`userName` =?, `nationalCode` = ?, `birthDay` = ?, `passWord` = ? ,`admin`=?,`Approved by admin`=?,`balance`=? WHERE userId =" + user.getUserId());

        pre.setString(1, user.getUserName());
        pre.setString(2, user.getNationalCode());
        pre.setDate(3, user.getBirthDay());
        pre.setString(4, user.getPassword());
        pre.setBoolean(5,user.isAdmin());
        pre.setBoolean(6,user.isApprovedByAdmin());
        pre.setString(7,user.getBalance());


        pre.executeUpdate();

    }

    public static boolean userIsExist(Connection connection, String username) throws SQLException {
        Statement stb = connection.createStatement();
        ResultSet re = stb.executeQuery("select userName from users where userName= '" + username + "'");

        String ans = "";
        while (re.next()) {
            ans = re.getString(1);

        }
        if (ans.equals(username))
            return true;
        else
            return false;
    }

    public static boolean passwordIsExist(Connection connection, String username, String password) throws SQLException {
        Statement stb = connection.createStatement();
        ResultSet re = stb.executeQuery("select passWord from users where userName= '" + username + "'");

        String ans = "";
        while (re.next()) {
            ans = re.getString(1);

        }
        if (ans.equals(password))
            return true;
        else
            return false;

    }

    public static void changhPassword(Connection connection, int userId, String newPassword) throws SQLException {

        PreparedStatement pre = connection.prepareStatement(
                "update users set passWord =? where userId = " + userId + "");
        pre.setString(1, newPassword);
        pre.executeUpdate();
    }

}