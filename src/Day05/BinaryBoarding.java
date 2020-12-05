package Day05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BinaryBoarding {

    private static final String FILE_PATH = "/home/zyw/dev/aoc/2020/src/Day05/input.txt";
    private static List<char[]> input;

    static {
        try {
            input = Files.lines(Paths.get(FILE_PATH))
                    .map(String::toCharArray)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getRowNumber(char[] chars) {

        var front = 0;
        var rear = 127;
        var result = Integer.MAX_VALUE;

        for (var i = 0; i < 7 && front != rear; i ++) {
            if (chars[i] == 'F') {
                rear = (front + rear) / 2;
                result = (front + rear) / 2;
                //System.out.println("F: " + result + "[" + front + ", " + rear + "]");
            } else if (chars[i] == 'B') {
                front = (front + rear) / 2 + 1;
                result = (front + rear) / 2;
                //System.out.println("B: " + result + "[" + front + ", " + rear + "]");
            } else {
                System.out.println("Incorrect direction!");
            }
        }

        return result;
    }

    private static int getColumnNumber(char[] chars) {
        var left = 0;
        var right = 7;
        var result = Integer.MAX_VALUE;

        for (var i = 7; i < chars.length && left != right; i ++) {
            if (chars[i] == 'L') {
                right = (left + right) / 2;
                result = (left + right) / 2;
                //System.out.println("F: " + result + "[" + front + ", " + rear + "]");
            } else if (chars[i] == 'R') {
                left = (left + right) / 2 + 1;
                result = (left + right) / 2;
                //System.out.println("B: " + result + "[" + front + ", " + rear + "]");
            } else {
                System.out.println("Incorrect direction!");
            }
        }

        return result;
    }

    private static int getSeatId(int rowNumber, int columnNumber) {
        return rowNumber * 8 + columnNumber;
    }

    private static int getHighestSeatId() {
        var result = Integer.MIN_VALUE;
        result = input.stream().mapToInt(charArray -> {
            var seatId = getSeatId(getRowNumber(charArray), getColumnNumber(charArray));
            //System.out.print(" " + seatId);
            return seatId;}).summaryStatistics().getMax();

        return result;
    }

    private static List<Integer> getSeatIds() {
        var result = new ArrayList<Integer>();
        result = (ArrayList<Integer>) input.stream().map(charArray -> {
            var seatId = getSeatId(getRowNumber(charArray), getColumnNumber(charArray));
            return seatId;}).sorted().collect(Collectors.toList());

        //result.stream().forEach(System.out::println);

        return result;
    }

    private static int getMySeatId() {
        var idList = getSeatIds();
        var result = Integer.MAX_VALUE;
        for (var i = 0; i < idList.size() - 1; i ++) {
            if (idList.get(i) +1 < idList.get(i + 1)) {
                result = idList.get(i) + 1;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println("Highest Seat ID: " + getHighestSeatId());
        System.out.println(getMySeatId());
    }
}
