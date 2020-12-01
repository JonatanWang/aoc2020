package A1;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        var intSumOfThree = getThreeNumbers();
    }
}
