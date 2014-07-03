package com.zotix.riak;

/**
 * Created by akhettar on 29/06/2014.
 */
public class Book {


    private String Title;
    private String Author;
    private String Body;
    private String ISBN;

    public Integer getCopiesOwned() {
        return CopiesOwned;
    }

    public void setCopiesOwned(Integer copiesOwned) {
        CopiesOwned = copiesOwned;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getBody() {
        return Body;
    }

    public void setBody(String body) {
        Body = body;
    }

    private Integer CopiesOwned;
}
