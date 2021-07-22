package domain;

import java.sql.Date;

public class Article {
    private int articleId;
    private String title;
    private String brief;
    private Date createDate;
    private boolean isPublished;
    private Date lastUpdateDate;
    private Date publishDate;
    private  boolean isFree;
    private String price;
    private Category aricleCategory = new Category();

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getAricleCategoryId() {
        return aricleCategory.getCategoryId();
    }

    public void setAricleCategoryId(int categoryId) {
        this.aricleCategory.setCategoryId(categoryId);
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public String toString() {
        return "Article :" +
                "\narticleId = " + getArticleId() +
                "\n title = '" + getTitle() + '\'' +
                "\n brief = '" + getBrief() + '\'' +
                "\ncreateDate = " + getCreateDate() +
                "\n isPublished = " + isPublished() +
                "\n publishedDate = "+getPublishDate()+
                "\n lastUpdateDate = " + getLastUpdateDate() +
                "\n publishDate = " + getPublishDate() + "";

    }
}