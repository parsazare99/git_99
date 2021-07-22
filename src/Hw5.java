import domain.Article;
import repository.*;

import domain.User;
import service.ArticleService;
import service.UserService;

import javax.swing.*;
import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class Hw5 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {


        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hw6", "root", "Parsa99099@");


        while (true) {

            String in = JOptionPane.showInputDialog("1 : If you want to Register\n" +
                    "2 :If you want to Log In\n" +
                    "3 :If you want to view articles \n" +
                    "4 : Increase account balance \n" +
                    "5 :Display articles by value (Free -- Non Free)\n" +
                    "6 : Exit ...");

            int n = Integer.parseInt(in);
//--------------------------------------------------------------------------------------------------------------
            if (n == 1) {


                UserService.register(connection);


            }

//---------------------------------------------------------------------------------------------------------------

            else if (n == 2) {
                User user = UserService.logIn(connection);


                while (true) {
                    String userAnswer;
                    int a;
                    if (user.isAdmin()) {
                        userAnswer = JOptionPane.showInputDialog("0 : Confirm or block other users' accounts \n" +
                                "1 : View your articles\n " +
                                "2 : edit your article\n" +
                                "3 : create new article\n" +
                                "4 : Change Password \n " +
                                "5 : Exit...");
                        a = Integer.parseInt(userAnswer);


                    } else {
                        if (!(user.isApprovedByAdmin())) {
                            JOptionPane.showMessageDialog(null, "There is a problem!\n" +
                                    "Your account has not been verified\n" +
                                    " or blocked by the admin ");
                            break;
                        }
                        userAnswer = JOptionPane.showInputDialog("1 : View your articles\n " +
                                "2 : edit your article\n" +
                                "3 : create new article\n" +
                                "4 : Change Password \n " +
                                "5 : Exit...");
                        a = Integer.parseInt(userAnswer);


                    }
                    if (a == 0) {
                        AdminRepository.confirmUser(connection);

                    } else if (a == 1) {

                        ArticleRepository.showUserArticles(connection, user.getUserId());


                    } else if (a == 2) {

                        ArticleService.editArticle(connection, user.getUserId());


                    } else if (a == 3) {
                        ArticleService.createArticle(connection, user.getUserId());


                    } else if (a == 4) {


                        String newPassword = JOptionPane.showInputDialog(" Enter your New Password : ");
                        UserRepository.changhPassword(connection, user.getUserId(), newPassword);


                    } else if (a == 5) {
                        break;
                    }

                    String u = JOptionPane.showInputDialog("1 : Return to the previous menu\n " +
                            "2 : Exit ");
                    if (u.equals("2")) {
                        break;
                    }


                }


            }


//-------------------------------------------------------------------------------------------------------------------------

            else if (n == 3) {

                ArticleRepository.showPublishedArticlesMenu(connection);
                int input = Integer.parseInt(JOptionPane.showInputDialog("Enter the ID of the article you want to view : "));
                Article ar = ArticleRepository.getArticle(connection, input);
                System.out.println(ar.toString());
            }

//-------------------------------------------------------------------------------------------------------------------------
            else if (n == 4) {
                UserService.IncreaseAccountBalance(connection);


            }

//--------------------------------------------------------------------------------------------------------------------------
            else if (n == 5) {

                ArticleService.showArticlesByPrice(connection);


            }

//--------------------------------------------------------------------------------------------------------------------------

            else {
                break;
            }

        }
    }
}

