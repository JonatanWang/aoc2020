package Day04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ClientInfoStatus;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PassportProcessing {

    private static final String FILE_PATH = "/home/zyw/dev/aoc/2020/src/Day04/input.txt";
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
        return Stream.of(PARAGRAPH.split(source))
                .map(para -> MULTISPACE.matcher(para).replaceAll(" "))
                .collect(Collectors.toList());
    }

    private static long getNumberOfValidPassports() {
        long result = 0;
        var entries = getCompactLines(source);
        result = entries.stream().filter(
                s -> s.contains("byr")
                        && s.contains("iyr")
                        && s.contains("eyr")
                        && s.contains("hgt")
                        && s.contains("hcl")
                        && s.contains("ecl")
                        && s.contains("pid"))
                .count();

        return result;
    }

    public static void main(String[] args) {
        System.out.println(getNumberOfValidPassports());
    }
}
