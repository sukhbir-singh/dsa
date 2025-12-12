package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// We use the same Movie class as above (it can have or not have
// the Comparable implementation, the Comparator doesn't depend on it).

public class ComparatorExample {
    public static void main(String[] args) {
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie("The Shawshank Redemption", 1994));
        movieList.add(new Movie("The Godfather", 1972));
        movieList.add(new Movie("The Dark Knight", 2008));

        // Define a Comparator to sort by Name alphabetically
        Comparator<Movie> nameComparator = new Comparator<Movie>() {
            @Override
            public int compare(Movie m1, Movie m2) {
                return m1.getName().compareTo(m2.getName());
            }
        };

        // Or, more simply, using a Java 8 lambda/method reference:
        Comparator<Movie> yearDescendingComparator = Comparator.comparingInt(Movie::getYear).reversed();


        System.out.println("Sorted by Name:");
        // Pass the custom comparator to the sort method
        Collections.sort(movieList, nameComparator);
        for (Movie movie : movieList) {
            System.out.println(movie);
        }

        System.out.println("\nSorted by Year Descending:");
        Collections.sort(movieList, yearDescendingComparator);
        for (Movie movie : movieList) {
            System.out.println(movie);
        }
    }
}
