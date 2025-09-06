package lab4;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class ListHelper {
    public static double average(List<Integer> list) {
        return list.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0);
    }

    public static List<String> upperNew(List<String> list) {
        return list.stream()
                .map(s -> "_new_" + s.toUpperCase())
                .toList();
    }

    public static List<Integer> uniqueSquares(List<Integer> list) {
        return list.stream()
                .collect(Collectors.groupingBy(item -> item, Collectors.counting()))
                .entrySet().stream()
                .filter(item -> item.getValue() == 1)
                .map(item -> item.getKey() * item.getKey())
                .toList();
    }

    public static <T> T last(List<T> list) {
        return list.stream()
                .reduce((a, b) -> b)
                .orElseThrow(() -> new NoSuchElementException("Empty collection"));
    }

    public static int sumEven(int[] array) {
        return Arrays.stream(array)
                .filter(item -> item % 2 == 0)
                .sum();
    }

    public static Map<Character, String> mapByFirstChar(List<String> list) {
        return list.stream()
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toMap(
                        s -> s.charAt(0),
                        s -> s.substring(1),
                        (a, b) -> a
                ));
    }
}