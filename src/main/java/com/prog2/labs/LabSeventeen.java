package com.prog2.labs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author adinashby
 *
 */
public class LabSeventeen {

    /**
     * Write your test code below in the main (optional).
     *
     */
         public static void main(String args[]) {
             // run from AddBookFrame for better UI background Design 
        // Initialize the database
        DatabaseUtil.initializeDB(); // Ensure tables are created before performing any operations

        // Assuming there's a Library class that needs to be instantiated
        Library library = new Library();  // Ensure you have a constructor in Library class

        // Create the controller with the library instance
        LibraryController libraryController = new LibraryController(library);

        // Create and display the main application window
        AddBookFrame frame = new AddBookFrame(libraryController);
        frame.setVisible(true);
    }

}
