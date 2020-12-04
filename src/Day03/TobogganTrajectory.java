package Day03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class TobogganTrajectory {
    private static final String FILE_PATH = "/home/zyw/dev/aoc/2020/src/Day03/input.txt";
    private static List<String> input;

    static {
        try {
            input = Files.lines(Paths.get(FILE_PATH))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Right 3, Down 1
     * @return
     */
    private static int getNumberOfTreesPart1() {

        var result = 0;
        var pointer = 0;
        for (var i = 1; i < input.size(); i ++) {
            var chars = input.get(i).toCharArray();
            pointer += 3;
            if(pointer >= chars.length) {
                pointer %= chars.length;
            }
            if (chars[pointer] == '#') {
                result ++;
            }
        }

        return result;
    }

    private  static long getNumberOfTreesPart2() {

        return (long) getNumberOfTreesRight1Down1()
                * getNumberOfTreesPart1()
                * getNumberOfTreesRight5Down1()
                * getNumberOfTreesRight7Down1()
                * getNumberOfTreesRight1Down2();

        }

    private static int getNumberOfTreesRight1Down1() {

        var result = 0;
        var pointer = 0;
        for (var i = 1; i < input.size(); i ++) {
            var chars = input.get(i).toCharArray();
            pointer ++;
            if(pointer >= chars.length) {
                pointer %= chars.length;
            }
            if (chars[pointer] == '#') {
                result ++;
            }
        }

        return result;
    }

    private static int getNumberOfTreesRight5Down1() {

        var result = 0;
        var pointer = 0;
        for (var i = 1; i < input.size(); i ++) {
            var chars = input.get(i).toCharArray();
            pointer += 5;
            if(pointer >= chars.length) {
                pointer %= chars.length;
            }
            if (chars[pointer] == '#') {
                result ++;
            }
        }

        return result;
    }

    private static int getNumberOfTreesRight7Down1() {

        var result = 0;
        var pointer = 0;
        for (var i = 1; i < input.size(); i ++) {
            var chars = input.get(i).toCharArray();
            pointer += 7;
            if(pointer >= chars.length) {
                pointer %= chars.length;
            }
            if (chars[pointer] == '#') {
                result ++;
            }
        }

        return result;
    }

    private static int getNumberOfTreesRight1Down2() {

        var result = 0;
        var pointer = 0;
        for (var i = 2; i < input.size(); i += 2) {
            var chars = input.get(i).toCharArray();
            pointer ++;
            if(pointer >= chars.length) {
                pointer %= chars.length;
            }
            if (chars[pointer] == '#') {
                result ++;
            }
        }

        return result;
    }

    public static void main(String[] args) {

        System.out.println(getNumberOfTreesPart1() + "\n" + getNumberOfTreesPart2());
    }
}
