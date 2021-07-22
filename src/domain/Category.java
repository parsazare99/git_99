package domain;

public class Category {

    private int categoryId;
    private String category;


    public Category() {

    }

    public Category(int categoryId, String title) {
        this.categoryId = categoryId;
        this.category = title;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
