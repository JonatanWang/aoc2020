package Day02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PasswordPhilosophy {

    private static final String FILE_PATH = "/home/zyw/dev/aoc/2020/src/Day02/input.txt";
    private static List<String> input;

    static {
        try {
            input = Files.lines(Paths.get(FILE_PATH))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isValidPassword(String passwordInput) {

        var passwordParts = passwordInput.split(" ");
        var interval = passwordParts[0].split("-");
        var min = Integer.valueOf(interval[0]);
        var max = Integer.valueOf(interval[1]);
        var targetChar = passwordParts[1].split(":")[0].charAt(0);
        var password = passwordParts[2].toCharArray();

        var counter = 0;
        for(Character c : password) {
            if (c == targetChar) {
                counter++;
            }
        }
        var result = counter >= min && counter <= max;

        return result;
    }

    private static boolean isValidPasswordByNewPolicy (String passwordInput) {

        var passwordParts = passwordInput.split(" ");
        var interval = passwordParts[0].split("-");
        var firstPlace = Integer.valueOf(interval[0]);
        var secondPlace = Integer.valueOf(interval[1]);
        var targetChar = passwordParts[1].split(":")[0].charAt(0);
        var password = passwordParts[2].toCharArray();

        var counter = 0;
        if (firstPlace > 0 && password[firstPlace - 1] == targetChar) {
            counter++;
        }
        if (secondPlace <= password.length && password[secondPlace - 1] == targetChar) {
            counter++;
        }

        var result = counter == 1 ? true : false;

        return result;
    }

    public static void main(String[] args) {
        var numOfValidPasswords = input.stream().filter(passwordInput -> isValidPassword(passwordInput)).count();
        System.out.println(numOfValidPasswords);

        var numOfValidPasswordsByNewPolicy = input.stream().filter(passwordInput -> isValidPasswordByNewPolicy(passwordInput)).count();
        System.out.println(numOfValidPasswordsByNewPolicy);
    }
}
