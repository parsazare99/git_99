package repository;

import domain.*;

import java.sql.*;
import java.util.Random;

public class ArticleRepository implements BaseRepository {

    @Override
    public void showAll(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select * from articles ");

        while (result.next()) {

            System.out.println("Article ID : " + result.getInt(1));
            System.out.println("article Title : " + result.getString(2));
            System.out.println("Brief : " + result.getString(3));
            System.out.println("Create Date :" + result.getString(4));
            System.out.println("is Published : " + result.getString(5));
            System.out.println("Last Update Date : " + result.getString(6));
            System.out.println("Publish Date : " + result.getString(7));
            System.out.println("User ID : " + result.getInt(8));
            System.out.println("CategoryRepository ID : " + result.getInt(9));
            System.out.println("Article is Free : " + result.getBoolean(10));
            System.out.println("price : " + result.getString(11));

            System.out.println("---------------------------------------------\n");


        }

    }


    public static void setArticle(Connection connection, Article article, int userId) throws SQLException {

        PreparedStatement pre = connection.prepareStatement("INSERT INTO articles " +
                "(`articleTitle`, `articleBrief`, `CreateDate`, `isPublished` , `publishDate`," +
                " `userId`, `categoryId` ,`isFree` , `price`) " +
                "VALUES (?,?,?,?,?,?,?,?,?)");

        pre.setString(1, article.getTitle());
        pre.setString(2, article.getBrief());
        pre.setDate(3, article.getCreateDate());
        pre.setBoolean(4, article.isPublished());
        pre.setDate(5, article.getPublishDate());

        pre.setInt(6, userId);
        pre.setInt(7, article.getAricleCategoryId());
        pre.setBoolean(8, article.isFree());
        pre.setString(9, article.getPrice());

        pre.executeUpdate();

    }


    public static Article getArticle(Connection connection, int articleId) throws SQLException {
        Article article = new Article();
        Statement stm = connection.createStatement();
        ResultSet result = stm.executeQuery("select * from articles where articleId= '" + articleId + "'");

        while (result.next()) {

            article.setArticleId(result.getInt(1));
            article.setTitle(result.getString(2));
            article.setBrief(result.getString(3));
            article.setCreateDate(result.getDate(4));
            article.setPublished(result.getBoolean(5));
            article.setLastUpdateDate(result.getDate(6));
            article.setPublishDate(result.getDate(7));
            article.setAricleCategoryId(result.getInt(9));
            article.setFree(result.getBoolean(10));
            article.setPrice(result.getString(11));

        }


        return article;
    }


    public static void updateArticle(Connection connection, Article article) throws SQLException {


        PreparedStatement pre = connection.prepareStatement
                ("UPDATE `hw6`.`articles` SET " +
                        "`articleTitle` =?, `articleBrief` = ?, `isPublished` = ?," +
                        " `lastUpdateDate` = ?, `publishDate` = ?, `categoryId` = ? ,`isFree` =? , `price`=?  WHERE articleId =" + article.getArticleId());

        pre.setString(1, article.getTitle());
        pre.setString(2, article.getBrief());
        pre.setBoolean(3, article.isPublished());
        pre.setDate(4, article.getLastUpdateDate());
        pre.setDate(5, article.getPublishDate());
        pre.setInt(6, article.getAricleCategoryId());
        pre.setBoolean(7, article.isFree());
        pre.setString(8, article.getPrice());

        pre.executeUpdate();

    }


    public static void showUserArticles(Connection connection, int userId) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select * from articles where userId=" + userId);

        while (result.next()) {

            System.out.println("Article ID : " + result.getInt(1));
            System.out.println("article Title : " + result.getString(2));
            System.out.println("Brief : " + result.getString(3));
            System.out.println("Create Date :" + result.getString(4));
            System.out.println("is Published : " + result.getString(5));
            System.out.println("Last Update Date : " + result.getString(6));
            System.out.println("Publish Date : " + result.getString(7));
            System.out.println("User ID : " + result.getInt(8));
            System.out.println("CategoryRepository ID : " + result.getInt(9));
            System.out.println("Article is Free : " + result.getBoolean(10));
            System.out.println("price : " + result.getString(11));

            System.out.println("---------------------------------------------\n");


        }

    }


    public static void showPublishedArticlesMenu(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select articleId,articleTitle from articles where isPublished=true");

        while (result.next()) {

            System.out.println("Article ID : " + result.getInt(1));
            System.out.println("article Title : " + result.getString(2));
            System.out.println("---------------------------------------------\n");


        }
        statement.close();

    }


    public static void showByQuery(Connection connection, String query) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);

        while (result.next()) {

            System.out.println("Article ID : " + result.getInt(1));
            System.out.println("article Title : " + result.getString(2));
            System.out.println("Brief : " + result.getString(3));
            System.out.println("Create Date :" + result.getString(4));
            System.out.println("is Published : " + result.getString(5));
            System.out.println("Last Update Date : " + result.getString(6));
            System.out.println("Publish Date : " + result.getString(7));
            System.out.println("User ID : " + result.getInt(8));
            System.out.println("CategoryRepository ID : " + result.getInt(9));
            System.out.println("Article is Free : " + result.getBoolean(10));
            System.out.println("price : " + result.getString(11));

            System.out.println("---------------------------------------------\n");

        }
        statement.close();

    }


}
