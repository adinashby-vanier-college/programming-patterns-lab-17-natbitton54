package com.prog2.labs;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class LibraryView extends JFrame implements LibraryObserver {

    private final LibraryController controller;
    private JTable bookTable;
    private final JTextField searchField;
    private final JButton searchButton;

    public LibraryView(LibraryController controller) {
        this.controller = controller;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        // Initialize the table with column names
        String[] columnNames = {"Book ID", "Title", "Author", "Genre"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        bookTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(bookTable);
        add(scrollPane, BorderLayout.CENTER);

        // Initialize the search panel with the search field and button
        JPanel searchPanel = new JPanel();
        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        searchButton.addActionListener(e -> searchBooks());
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void searchBooks() {
        String searchText = searchField.getText();
        List<Book> searchResults = controller.searchBooks(searchText);
        updateTableWithBooks(searchResults);
    }

    @Override
    public void updateLibrary(List<Book> books) {
        updateTableWithBooks(books);
    }

    private void updateTableWithBooks(List<Book> books) {
        DefaultTableModel model = (DefaultTableModel) bookTable.getModel();
        model.setRowCount(0); // Clear existing rows

        for (Book book : books) {
            model.addRow(new Object[]{
                book.getBookID(),
                book.getTitle(),
                book.getAuthor(),
                book.getGenre().getGenre()
            });
        }
    }

    public void displayBookDetails(Book book) {
        JOptionPane.showMessageDialog(this,
            "Title: " + book.getTitle() + "\nAuthor: " + book.getAuthor() + "\nGenre: " + book.getGenre().getGenre(),
            "Book Details",
            JOptionPane.INFORMATION_MESSAGE);
    }

    public void displayError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
