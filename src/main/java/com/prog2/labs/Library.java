package com.prog2.labs;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library {

    private List<Book> books = new ArrayList<>();
    private List<LibraryObserver> observers = new ArrayList<>();

    public void addObserver(LibraryObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(LibraryObserver observer) {
        observers.remove(observer);
    }

    public void addBook(Book book) {
        books.add(book);
        notifyObservers();
    }

    private void notifyObservers() {
        for (LibraryObserver observer : observers) {
            observer.updateLibrary(books);
        }
    }

    // Implement search functionality within Library as well.
    public List<Book> searchBooks(String query) {
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(query.toLowerCase())
                || book.getAuthor().toLowerCase().contains(query.toLowerCase())
                || book.getGenre().getGenre().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Book> getAllBooks() {
        return books;
    }
}
