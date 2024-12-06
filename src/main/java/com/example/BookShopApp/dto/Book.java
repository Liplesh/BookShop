package com.example.BookShopApp.dto;

public class Book {

    private Integer id;
    private String title;
    private String author;
    private String priceold;
    private String price;


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", priceold='" + priceold + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPriceold() {
        return priceold;
    }

    public void setPriceold(String priceold) {
        this.priceold = priceold;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
