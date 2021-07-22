package service;

import domain.User;
import repository.UserRepository;

import javax.swing.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class UserService {

    public static void register(Connection connection) throws SQLException {
        User user = new User();
        user.setUserName(JOptionPane.showInputDialog("Enter your UserName :"));
        user.setNationalCode(JOptionPane.showInputDialog("Enter your NationalCode :"));

        String birthday = JOptionPane.showInputDialog("Enter the Birthday date in the following format :\n" +
                "year-month-day");
        user.setBirthDay(Date.valueOf(birthday));
        user.setPassword(user.getNationalCode());


        UserRepository.setUser(connection, user);

        JOptionPane.showMessageDialog(null, "Wellcome to System !");

    }


    public static User logIn(Connection connection) throws SQLException {
        String username;
        while (true) {

            username = JOptionPane.showInputDialog("please Enter your username : ");

            if (UserRepository.userIsExist(connection, username)) {

                while (true) {
                    String password = JOptionPane.showInputDialog("please Enter your password : ");
                    if (UserRepository.passwordIsExist(connection, username, password)) {
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, "password is Wrong!!!!\n" +
                                "Please try agine...", "password", JOptionPane.ERROR_MESSAGE);

                    }
                }

                JOptionPane.showMessageDialog(null, "The log in was successful !");
                break;

            } else
                JOptionPane.showMessageDialog(null, "username is Wrong!!!!\n" +
                        "Please try agine...", "UserName", JOptionPane.ERROR_MESSAGE);

        }

        return UserRepository.getUser(connection, username);


    }


    public static void IncreaseAccountBalance(Connection connection) throws SQLException {


        String username = JOptionPane.showInputDialog("please Enter your username : ");
        String balance = JOptionPane.showInputDialog("Enter the amount you want to top up your account : ");
        User user=UserRepository.getUser(connection,username);
        user.setBalance(balance);
        UserRepository.updateUser(connection,user);
        JOptionPane.showMessageDialog(null,"The operation was successful ");

    }
}
