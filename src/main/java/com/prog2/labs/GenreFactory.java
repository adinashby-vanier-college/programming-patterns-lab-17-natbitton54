package com.prog2.labs;

public class GenreFactory {

    public static Genre getGenre(String genreType) {
        switch (genreType.toLowerCase()) {
            case "comedy":
                return new Comedy();
            case "fantasy":
                return new Fantasy();
            case "romance":
                return new Romance();
            default:
                return new DefaultGenre(); // Assume this handles unknown genres.
        }
    }
}
