package com.prog2.labs;

public class Book {
    private int bookID;
    private String title;
    private String author;
    private Genre genre;  // Use the Genre interface

    // Constructor now accepts a Genre interface implementation
    public Book(int bookID, String title, String author, Genre genre) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    // Getters
    public int getBookID() { return bookID; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public Genre getGenre() { return genre; }  // Returns a Genre object
}
