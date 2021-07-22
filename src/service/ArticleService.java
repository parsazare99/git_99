package service;

import domain.Article;
import repository.ArticleRepository;
import repository.CategoryRepository;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class ArticleService {


    public static void createArticle(Connection connection, int userId) throws SQLException {
        Article article = new Article();


        article.setTitle(JOptionPane.showInputDialog("Enter Article Title : "));
        article.setBrief(JOptionPane.showInputDialog("Enter Article Brief : "));


        CategoryRepository.showAllCategories(connection);
        String category = JOptionPane.showInputDialog("Enter Category ID :\n " +
                " for add new category Enter 'new' :");

        if (category.toLowerCase().equals("new")) {
            String newCategory = JOptionPane.showInputDialog("Enter the name of the new category you want to create :");
            CategoryRepository.createCategory(connection, newCategory);

            int mm = CategoryRepository.getCategoryId(connection, newCategory);

            article.setAricleCategoryId(mm);

        } else {
            article.setAricleCategoryId(Integer.parseInt(category));
        }

        String publish = JOptionPane.showInputDialog("Do you want Publish Your Article?\n    'yes'  Or 'no' ");
        if (publish.toLowerCase().equals("yes")) {
            article.setPublished(true);
            article.setPublishDate(Date.valueOf(LocalDate.now()));


        } else if (publish.toLowerCase().equals("no")) {

            article.setPublished(false);
            article.setPublishDate(null);

        }

        String free = JOptionPane.showInputDialog("Do you want Free Your Article?\n    'yes'  Or 'no' ");
        if (publish.toLowerCase().equals("yes")) {
            article.setFree(true);


        } else if (publish.toLowerCase().equals("no")) {

            article.setFree(false);
            String price = JOptionPane.showInputDialog("Enter the desired price for your article");
            article.setPrice(price);

        }


        article.setCreateDate(Date.valueOf(LocalDate.now()));

        ArticleRepository.setArticle(connection, article, userId);

    }


    public static void editArticle(Connection connection, int userId) throws SQLException {
        ArticleRepository.showUserArticles(connection, userId);
        int i = Integer.parseInt(JOptionPane.showInputDialog("Enter the article ID you want to edit"));
        Article article = ArticleRepository.getArticle(connection, i);

        String enter = JOptionPane.showInputDialog("1 : Edit Article Title \n" +
                "2 :Edit Article Brief\n" +
                "3 :change publish mood \n" +
                "4 :change category ");

        int x = Integer.parseInt(enter);

        if (x == 1) {
            String newTitle = JOptionPane.showInputDialog("enter new title : ");

            article.setTitle(newTitle);
            article.setLastUpdateDate(Date.valueOf(LocalDate.now()));


        } else if (x == 2) {

            String newBrief = JOptionPane.showInputDialog("enter new Brief : ");
            article.setBrief(newBrief);
            article.setLastUpdateDate(Date.valueOf(LocalDate.now()));

        } else if (x == 3) {


            if (article.isPublished()) {

                article.setPublished(false);
                article.setPublishDate(null);
                article.setLastUpdateDate(Date.valueOf(LocalDate.now()));

            } else {


                article.setPublished(true);
                article.setPublishDate(Date.valueOf(LocalDate.now()));
                article.setLastUpdateDate(Date.valueOf(LocalDate.now()));
            }


        } else if (x == 4) {

            CategoryRepository.showAllCategories(connection);
            String category = JOptionPane.showInputDialog("Enter Category ID : " +
                    " for add new category Enter 'new' :");

            if (category.toLowerCase().equals("new")) {
                String newCategory = JOptionPane.showInputDialog("Enter the name of the new category you want to create :");
                CategoryRepository.createCategory(connection, newCategory);
                article.setAricleCategoryId(CategoryRepository.getCategoryId(connection, newCategory));
            } else {
                article.setAricleCategoryId(Integer.parseInt(category));
            }


        }


        ArticleRepository.updateArticle(connection, article);


    }


    public static void showArticlesByPrice(Connection connection) throws SQLException {
        String freeQuery="select * from articles where isFree=true";
        System.out.println("***************  Free Articles******************** ");
        ArticleRepository.showByQuery(connection,freeQuery);
        String nonFreeQuery="select * from articles where isFree=false";
        System.out.println("***************  NON-Free Articles******************** ");
        ArticleRepository.showByQuery(connection,nonFreeQuery);

    }




}
