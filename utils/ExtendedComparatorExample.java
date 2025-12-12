package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// Assume the same 'Movie' class structure as before:
// class Movie {
//     private String name;
//     private int year;
//     // Getters, constructor, toString omitted for brevity
// }

/**
 * A dedicated class to compare two Movie objects by name alphabetically.
 */
class NameComparator implements Comparator<Movie> {
    @Override
    public int compare(Movie m1, Movie m2) {
        // We use String's built-in compareTo() for alphabetical sorting
        return m1.getName().compareTo(m2.getName());
    }
}

public class ExtendedComparatorExample {
    public static void main(String[] args) {
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie("The Shawshank Redemption", 1994));
        movieList.add(new Movie("The Godfather", 1972));
        movieList.add(new Movie("The Dark Knight", 2008));

        // Instantiate the custom Comparator class
        NameComparator sorterByName = new NameComparator();

        System.out.println("Sorted by Name using a dedicated Comparator class:");
        // Pass the instance of the comparator class to Collections.sort()
        Collections.sort(movieList, sorterByName);

        for (Movie movie : movieList) {
            System.out.println(movie);
        }
    }
}
