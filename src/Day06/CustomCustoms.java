package Day06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomCustoms {
    private static final String FILE_PATH = "/home/zyw/dev/aoc/2020/src/Day06/input.txt";
    private  static final Path input = Paths.get(FILE_PATH);
    private  static String source = null;

    static {
        try {
            source = new String(Files.readAllBytes(input));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final Pattern PARAGRAPH = Pattern.compile("\\s*^\\s*$\\s*", Pattern.MULTILINE);
    private static final Pattern MULTISPACE = Pattern.compile("\\s+");

    public static List<String> getCompactLines(final String source) {
        var result = Stream.of(PARAGRAPH.split(source))
                .map(para -> MULTISPACE.matcher(para).replaceAll(" "))
                .collect(Collectors.toList());

        //System.out.println(result);
        return result;
    }

    private static List<List<String>> getGroupAnswersByList() {
        var entries = getCompactLines(source);
        var result = new ArrayList<List<String>>();
        for (String s : entries) {
            result.add(Arrays.asList(s.split(" ")));
        }

        return result;
    }

    private static HashSet<Character> getIndividualAnswersByGroup(List<String> stringList) {
        var result = new HashSet<Character>();
        for (String s : stringList) {
            var chars = s.toCharArray();
            for (char c : chars) {
                result.add(c);
            }
        }
        
        return result;
    }

    /**
     * Part I: solution to get sum of all 'Yes' of all groups' individuals
     * @return
     */
    private static int getSumOfCounts() {
        var result = Integer.MIN_VALUE;
        var answerLists = getGroupAnswersByList();
        result = answerLists.stream().mapToInt(list -> getIndividualAnswersByGroup(list).size()).sum();

        return result;
    }

    public static void main(String[] args) {
        System.out.println(getSumOfCounts());
    }
}
