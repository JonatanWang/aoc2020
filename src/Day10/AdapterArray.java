package Day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class AdapterArray {

    /**
     * dp: Dynamic Programming
     * @param sequence
     * @param index
     * @param dp
     * @return
     */
    public static long countWays(ArrayList<Integer> sequence, int index, long[] dp) {
        if (index == sequence.size() - 1) {
            return 1L;
        }

        if (dp[index] != -1) {
            return dp[index];
        } else {
            var count = 0L;
            for (var i = index + 1; i <= Math.min(index + 3, sequence.size() - 1); i++) {
                if (sequence.get(index) + 3 >= sequence.get(i)) {
                    count += countWays(sequence, i, dp);
                }
            }
            dp[index] = count;

            return count;
        }
    }


    public static void main(String[] args) throws FileNotFoundException {

        var file = new File("/home/zyw/dev/aoc/2020/src/Day10/input.txt");
        var scan = new Scanner(file);
        var input = new ArrayList<Integer>();

        input.add(0); // Add rating of charging outlet

        while (scan.hasNextLine()) {
            input.add(Integer.parseInt(scan.nextLine())); // Add ratings of adapters
        }
        scan.close();

        Collections.sort(input);
        input.add(input.get(input.size() - 1) + 3); // Add built-in adapter (of 3 higher than max)

        var count1 = 0;
        var count3 = 0;
        for (var i = 0; i < input.size() - 1; i ++) {
            if (input.get(i) == input.get(i + 1) - 1) {
                count1 ++;
            } else if (input.get(i) == input.get(i + 1) - 3) {
                count3 ++;
            }
        }
        System.out.println("Part 1: " + (count1 * count3));

        var len = input.size();
        var dp = new long[len];
        Arrays.fill(dp, -1);
        System.out.println("Part 2: " + countWays(input, 0, dp));
    }
}
