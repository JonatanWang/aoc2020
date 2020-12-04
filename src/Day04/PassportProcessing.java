package Day04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
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
        var result = Stream.of(PARAGRAPH.split(source))
                .map(para -> MULTISPACE.matcher(para).replaceAll(" "))
                .collect(Collectors.toList());

        //System.out.println(result);
        return result;
    }

    /**
     * Solution: Part I
     * @return
     */
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

    private static List<List<String>> getPassPortsByList() {
        var entries = getCompactLines(source);
        var result = new ArrayList<List<String>>();
        for (String s : entries) {
            result.add(Arrays.asList(s.split(" ")));
        }

        //System.out.println(result;
        return result;
    }

    private static long getNumberOfValidPassportByValidation() {
        long result = 0;
        var passports = getPassPortsByList();

        result = passports.stream().filter(list -> {
            var isValidPassport = false;
            var numberOfValidAttributes = list.stream().filter(str -> {
                var isValidAttribute = false;
                var key = str.split(":")[0];
                var value = str.split(":")[1];
                var valueLength = value.toCharArray().length;

                switch (key) {
                    case "byr":
                        var byr = (int) Integer.valueOf(value);
                        //System.out.println(byr);
                        isValidAttribute = valueLength == 4 && byr >= 1920 && byr <= 2002;
                        break;
                    case "iyr":
                        var iyr = (int) Integer.valueOf(value);
                        //System.out.println(iyr);
                        isValidAttribute = valueLength == 4 && iyr >= 2010 && iyr <= 2020;
                        break;
                    case "eyr":
                        var eyr = (int) Integer.valueOf(value);
                        //System.out.println(eyr);
                        isValidAttribute = valueLength == 4 && eyr >= 2020 && eyr <= 2030;
                        break;
                    case "hgt":
                        //System.out.println(value);
                        if (value.equals("") || valueLength < 3) {
                            isValidAttribute = false;
                            break;
                        }
                        var hgt = (int) Integer.valueOf(value.substring(0, valueLength - 2));
                        var unit = value.substring(valueLength - 2);
                        if (unit.equals("cm")) {
                            isValidAttribute = (hgt >= 150 && hgt <= 193);
                            break;
                        } else if (unit.equals("in")) {
                            isValidAttribute = hgt >= 59 && hgt <= 76;
                            break;
                        } else {
                            isValidAttribute = false;
                            break;
                        }
                    case "hcl":
                        //System.out.println(value);
                        var hcl = value.toCharArray();
                        if (hcl[0] == '#' || valueLength == 7) {
                            isValidAttribute = true;
                        }
                        for(var i = 1; i < hcl.length; i ++) {
                            if (!((hcl[i] >= '0' && hcl[i] <= '9') || (hcl[i] >= 'a' && hcl[i] <= 'f'))) {
                                isValidAttribute = false;
                                break;
                            }
                        }
                        break;
                    case "ecl":
                        //System.out.println(value);
                        isValidAttribute =
                                value.equals("amb")
                                || value.equals("blu")
                                || value.equals("brn")
                                || value.equals("gry")
                                || value.equals("grn")
                                || value.equals("hzl")
                                || value.equals("oth");
                        break;
                    case "pid":
                        //System.out.println(value);
                        var pid = value.toCharArray();
                        isValidAttribute = valueLength == 9;
                        for (var i = 0; i < pid.length; i ++) {
                            if (!(pid[i] >= '0' && pid[i] <= '9')) {
                                isValidAttribute = false;
                                break;
                            }
                        }
                        break;
                    case "cid":
                        //System.out.println(value);
                        break;
                    default:
                        break;
                }

                return isValidAttribute;
            }).count();
            //System.out.println(numberOfValidAttributes);
            isValidPassport = numberOfValidAttributes >= 7;
            return isValidPassport;
        }).count();

        return result;
    }

    public static void main(String[] args) {
        System.out.println("Part 1: " + getNumberOfValidPassports());
        System.out.println("Part 2: " + getNumberOfValidPassportByValidation());
    }
}
