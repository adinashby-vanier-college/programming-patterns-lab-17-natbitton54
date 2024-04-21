/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prog2.labs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author natha
 */
public class DatabaseUtil {
    private static final String URL = "jdbc:sqlite:library_system.db";
    
    public static Connection connect() throws SQLException{
        return DriverManager.getConnection(URL);
    }
    
    public static void initializeDB(){
        String booksTable = "CREATE TABLE IF NOT EXISTS books (" + 
                            "book_id INTEGER PRIMARY KEY, " + 
                            "title TEXT NOT NULL, " + 
                            "author TEXT NOT NULL, " + 
                            "genre TEXT NOT NULL)";
        try(Connection conn = connect(); Statement sm = conn.createStatement()){
            sm.execute(booksTable);
        } catch(SQLException e){
            System.out.println("Error during database initialization: " + e.getMessage());
        }
    }
}
