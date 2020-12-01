package A1;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class A1 {

    private static List<Integer> getIntegerList() throws IOException {

        var fileName = "/home/zyw/dev/aoc/2020/src/A1/data";
        List<Integer> list = Files.lines(Paths.get(fileName))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        //System.out.println(list);
        return list;
    }

    public static void main(String[] args) throws IOException {
        var list = getIntegerList();
        var pair = new ArrayList<Integer>();
        for (int i = 0; i < list.size() - 1; i ++) {
            int firstNumber = list.get(i);
            for(int j = i + 1; j < list.size(); j ++) {
                int secondNumber = list.get(j);

                if (firstNumber + secondNumber == 2020) {
                    System.out.println("Result: " + firstNumber * secondNumber);
                    pair.add(firstNumber);
                    pair.add(secondNumber);
                }
            }
        }

        System.out.println(pair);
    }
}
