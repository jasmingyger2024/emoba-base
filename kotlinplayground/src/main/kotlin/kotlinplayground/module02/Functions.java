package kotlinplayground.module02;

import java.util.List;
import java.util.NoSuchElementException;

public class Functions {

    public static double bmi(double weight, int bodyHeight){
        final double h = bodyHeight / 100.0;

        return weight / (h * h);
    }

    public static void printIt(Number value, String unit){
        System.out.println(value + " " + unit);
    }

    public static void printIt(Number value){
        printIt(value, "kg");
    }

    public static void main(String[] args) {
        printIt(200,"ms");
        printIt(2f);

        final List<Integer> items = List.of(1, 2, 5, 2);

        items.stream()
                .filter(item -> {
                    final int threshold = 3;
                    return item > threshold;
                })
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

}