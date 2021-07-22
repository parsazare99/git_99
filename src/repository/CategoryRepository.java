package repository;

import domain.*;

import java.sql.*;
import java.util.Random;

public class CategoryRepository implements BaseRepository {

    @Override
    public void showAll(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select * from categories");

        while (result.next()) {

            System.out.println("category ID : " + result.getInt(1));
            System.out.println("category Title : " + result.getString(2));
            System.out.println("---------------------------------------------\n");


        }

    }

    public static void createCategory(Connection connection, String category) throws SQLException {

        PreparedStatement pre = connection.prepareStatement(" INSERT INTO `hw6`.`categories` " +
                "( `categoryTitle`) VALUES (?)");

        pre.setString(1, category);
        pre.executeUpdate();

    }

    public static void showAllCategories(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select * from categories");

        while (result.next()) {


            System.out.println("category ID  : " + result.getInt(1));
            System.out.println("category Title : " + result.getString(2));

            System.out.println("----------------------------------\n");


        }

    }

    public static int getCategoryId(Connection connection, String categoryTitle) throws SQLException {

        Statement st = connection.createStatement();
        ResultSet resultSet = st.executeQuery("select categoryId from categories where categoryTitle='" + categoryTitle + "'");
        int n = 0;

        while (resultSet.next()) {
            n = resultSet.getInt(1);
        }
        return n;

    }

}
