package repository;

import domain.*;

import java.sql.*;

public class TagRepository {


    public static void setArticle(Connection connection, Article article) throws SQLException {

        PreparedStatement pre = connection.prepareStatement("insert into" +
                " users (userName,nationalCode,birthDay,passWord) values (?,?,?,?)");

        pre.setString(1, article.getTitle());
        pre.setString(2, article.getBrief());
       // pre.setString(3, article.getCreateDate());
        //  pre.setBoolean(4, article.get);

        pre.executeUpdate();


    }


    public static User getArticle(Connection connection, String username) throws SQLException {
        User user = new User();
        String x = "select * from users where userName= '" + username + "'";
        Statement stm = connection.createStatement();
        ResultSet result = stm.executeQuery(x);

        while (result.next()) {

            user.setUserId(result.getInt(1));
            user.setUserName(result.getString(2));
            user.setNationalCode(result.getString(3));
         //   user.setBirthDay(result.getString(4));
            user.setPassword(result.getString(5));

        }

        stm.close();
        result.close();

        return user;
    }


















}
