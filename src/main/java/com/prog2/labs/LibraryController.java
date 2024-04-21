package com.prog2.labs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LibraryController {

    private final Library library;

    public LibraryController(Library library) {
        this.library = library;
    }

    public void addBook(int bookID, String title, String author, Genre genre) {
        String sql = "INSERT INTO books (book_id, title, author, genre) VALUES (?,?,?,?)";
        try (Connection conn = DatabaseUtil.connect(); PreparedStatement psm = conn.prepareStatement(sql)) {
            psm.setInt(1, bookID);
            psm.setString(2, title);
            psm.setString(3, author);
            psm.setString(4, genre.getGenre());
            psm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding book: " + e.getMessage());
        }
    }

    public List<Book> searchBooks(String query) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE title LIKE ? OR author LIKE ? OR genre LIKE ?";

        try (Connection conn = DatabaseUtil.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + query + "%");
            pstmt.setString(2, "%" + query + "%");
            pstmt.setString(3, "%" + query + "%");

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        GenreFactory.getGenre(rs.getString("genre"))
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error searching books: " + e.getMessage());
        }
        return books;
    }

    public boolean bookExists(int bookID) {
        List<Book> books = library.getAllBooks();
        for (Book book : books) {
            if (book.getBookID() == bookID) {
                return true;
            }
        }
        return false;
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList();
        String sql = "SELECT * FROM books";
        try (Connection conn = DatabaseUtil.connect(); PreparedStatement psm = conn.prepareStatement(sql); ResultSet rs = psm.executeQuery()) {
            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        GenreFactory.getGenre(rs.getString("genre"))
                );
                books.add(book);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving books: " + e.getMessage());
        }
        return books;
    }
}
