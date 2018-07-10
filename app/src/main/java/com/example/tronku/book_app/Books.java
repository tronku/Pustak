package com.example.tronku.book_app;

public class Books {
    private String title, author, publisher, category, isbn, description, pgs, imgUrl, pubDate;

    public Books(){

    }

    public Books(String title, String author, String publisher, String category, String isbn, String pubDate, String description, String pgs, String imgUrl) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.category = category;
        this.isbn = isbn;
        this.description = description;
        this.pgs = pgs;
        this.imgUrl = imgUrl;
        this.pubDate = pubDate;
    }

    //Getter
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public String getPubDate() { return pubDate; }


    //Setter
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setPubDate(String pubDate) { this.pubDate = pubDate; }
}
