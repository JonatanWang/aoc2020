package Day02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PasswordPhilosophy {

    private static final String FILE_PATH = "/home/zyw/dev/aoc/2020/src/Day02/passwords.txt";
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

        var result = false;
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
        result = counter >= min && counter <= max;

        return result;
    }

    public static void main(String[] args) {
        var numOfValidPasswords = input.stream().filter(passwordInput -> isValidPassword(passwordInput)).count();
        System.out.println(numOfValidPasswords);
    }
}
