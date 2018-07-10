package com.example.tronku.book_app;

public class Books {
    private String title, author, rating, publisher, category, isbn, description, pgs, imgThumbnail, imgUrl, price;

    public Books(){

    }

    public Books(String title, String author, String rating, String publisher, String category, String isbn, String description, String pgs, String imgThumbnail, String imgUrl, String price) {
        this.title = title;
        this.author = author;
        this.rating = rating;
        this.publisher = publisher;
        this.category = category;
        this.isbn = isbn;
        this.description = description;
        this.pgs = pgs;
        this.imgThumbnail = imgThumbnail;
        this.imgUrl = imgUrl;
        this.price = price;
    }

    //Getter
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getRating() {
        return rating;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getCategory() {
        return category;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getDescription() {
        return description;
    }

    public String getPgs() {
        return pgs;
    }

    public String getImgThumbnail() {
        return imgThumbnail;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getPrice() {
        return price;
    }


    //Setter
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPgs(String pgs) {
        this.pgs = pgs;
    }

    public void setImgThumbnail(String imgThumbnail) {
        this.imgThumbnail = imgThumbnail;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
