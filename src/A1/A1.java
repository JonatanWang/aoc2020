package A1;

import java.io.*;
import java.lang.annotation.Target;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

public class A1 {

    private static final int TARGET_SUM = 2020;
    private static final String FILE_NAME = "/home/zyw/dev/aoc/2020/src/A1/data";
    private static List<Integer> input;

    static {
        try {
            input = Files.lines(Paths.get(FILE_NAME))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * O(n)
     * @param input
     * @param target
     * @return
     */
    private static Set<Integer> getTwoNumbersImproved(List<Integer> input, int target) {

        var result = new HashSet<Integer>();
        for (var i = 0; i < input.size(); i ++) {
            var firstNumber = input.get(i);
            var secondNumber = target - firstNumber;
            if (input.contains(secondNumber) && secondNumber != firstNumber) {
                System.out.println("Result of Two Numbers: " + firstNumber * secondNumber);
                result.add(firstNumber);
                result.add(secondNumber);
            }
        }

        return result;
    }

    /**
     * O(n²)
     * @param input
     * @param target
     * @return
     */
    private static Set<Integer> getThreeNumbersImproved(List<Integer> input, int target) {

        var result = new HashSet<Integer>();
        for (var i = 0; i < input.size(); i ++) {
            var firstNumber = input.get(i);
            var diff = target - firstNumber;
            for(var j = 0; j < input.size(); j ++) {
                var secondNumber = input.get(j);
                var thirdNumber = diff - secondNumber;
                if (input.contains(thirdNumber) && thirdNumber != firstNumber && secondNumber != firstNumber && secondNumber != thirdNumber) {
                    System.out.println("Result of Three Numbers: " + firstNumber * secondNumber * thirdNumber);
                    result.add(firstNumber);
                    result.add(secondNumber);
                }
            }

        }

        return result;
    }

    /**
     * O(n²)
     * @return
     * @throws IOException
     */
    private static List<Integer> getTwoNumbers() throws IOException {
        var pair = new ArrayList<Integer>();
        for (int i = 0; i < input.size() - 1; i ++) {
            var firstNumber = input.get(i);
            for(int j = i + 1; j < input.size(); j ++) {
                var secondNumber = input.get(j);

                if (firstNumber + secondNumber == TARGET_SUM) {
                    System.out.println("Result of Two Numbers: " + firstNumber * secondNumber);
                    pair.add(firstNumber);
                    pair.add(secondNumber);
                }
            }
        }

        return pair;
    }

    /**
     * O(n³)
     * @return
     * @throws IOException
     */
    private static List<Integer> getThreeNumbers() throws IOException {
        var pair = new ArrayList<Integer>();
        for (int i = 0; i < input.size() - 2; i ++) {
            var firstNumber = input.get(i);
            for(int j = i + 1; j < input.size() - 1; j ++) {
                var secondNumber = input.get(j);

                for (int k = j + 1; k < input.size(); k ++) {
                    var thirdNumber = input.get(k);
                    if (firstNumber + secondNumber + thirdNumber == TARGET_SUM) {
                        System.out.println("Result of Three Numbers: " + firstNumber * secondNumber * thirdNumber);
                        pair.add(firstNumber);
                        pair.add(secondNumber);
                        pair.add(thirdNumber);
                    }
                }
            }
        }

        return pair;
    }

    public static void main(String[] args) throws IOException {
        var intSum = getTwoNumbers();
        var intSumImproved = getTwoNumbersImproved(input, TARGET_SUM);
        var intSumOfThree = getThreeNumbers();
        var intSumOfThreeImproved = getThreeNumbersImproved(input, TARGET_SUM);
    }
}
