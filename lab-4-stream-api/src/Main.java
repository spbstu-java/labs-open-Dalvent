import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class Main {

    public static void main(String[] args) {
        var numbers = List.of(1, 2, 3, 4, 5);
        System.out.println("Average: " + ListHelper.average(numbers));

        var words = List.of("alpha", "beta", "gamma");
        System.out.println("Upper with prefix: " + ListHelper.upperNew(words));

        var numbersWithDuplicates = List.of(1, 2, 2, 3, 4, 4, 5);
        System.out.println("Unique squares: " + ListHelper.uniqueSquares(numbersWithDuplicates));

        List<String> letters = List.of("a", "b", "c", "d");
        System.out.println("Last element: " + ListHelper.last(letters));
        try {
            ListHelper.last(Collections.emptyList());
        } catch (NoSuchElementException ex) {
            System.out.println("Empty collection exception handled");
        }

        int[] arr = {1, 2, 3, 4, 5, 6};
        System.out.println("Sum of evens: " + ListHelper.sumEven(arr));

        var collection = List.of("apple", "banana", "cherry", "gg", "v", "art");
        System.out.println("Map by first char: " + ListHelper.mapByFirstChar(collection));
    }
}