package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Movie implements Comparable<Movie> {
    private String name;
    private int year;

    public Movie(String name, int year) {
        this.name = name;
        this.year = year;
    }

    public String getName() { return name; }
    public int getYear() { return year; }

    // Define the natural ordering: sort by year in ascending order
    @Override
    public int compareTo(Movie other) {
        return Integer.compare(this.year, other.year); //
    }

    @Override
    public String toString() {
        return name + " (" + year + ")";
    }
}

public class ComparableExample {
    public static void main(String[] args) {
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie("The Shawshank Redemption", 1994));
        movieList.add(new Movie("The Godfather", 1972));
        movieList.add(new Movie("The Dark Knight", 2008));

        // Collections.sort() uses the compareTo() method internally
        Collections.sort(movieList);

        System.out.println("Sorted by Year (Natural Order):");
        for (Movie movie : movieList) {
            System.out.println(movie);
        }
    }
}
