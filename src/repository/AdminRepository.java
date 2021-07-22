package repository;

import domain.User;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminRepository {


    public static void showAllUser(Connection connection) throws SQLException {


        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select * from users where not userId=14");

        while (result.next()) {

            System.out.println("user ID : " + result.getInt(1));
            System.out.println("user name : " + result.getString(2));
            System.out.println("national code : " + result.getString(3));
            System.out.println("birth day :" + result.getDate(4));
            // System.out.println("password: " + result.getString(5));

            System.out.println("Approved by admin :" + result.getDate(7));

            System.out.println("---------------------------------------------\n");


        }


    }


    public static void confirmUser(Connection connection) throws SQLException {

        showAllUser(connection);


        String username = JOptionPane.showInputDialog("Enter username of user that you\n want confirm or block his/her account : ");

        User user = UserRepository.getUser(connection, username);

        if (user.isApprovedByAdmin()) {
            user.setApprovedByAdmin(false);
        } else {
            user.setApprovedByAdmin(true);
        }


        UserRepository.updateUser(connection, user);


    }


}
