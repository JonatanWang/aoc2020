package Day16;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TicketTranslation {

    private static HashMap<String, ArrayList<Integer>> rules = new HashMap<>();
    private static HashMap<String, ArrayList<Integer>> map = new HashMap<>();

    private static boolean contains(int i) {
        for (var rule : rules.keySet()) {
            if (rules.get(rule).contains(i)) return true;
        }

        return false;
    }

    public static void main(String[] args) throws IOException {

        var scanner = new Scanner(new File("/home/zyw/dev/aoc/2020/src/Day16/input.txt"));

        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();
            if (line.equals("")) break;
            var sc4 = new Scanner(line).useDelimiter(":");

            var sc2 = new Scanner(line);
            var nums = new ArrayList<Integer>();
            while (sc2.hasNext()) {
                var str = sc2.next();
                if (str.contains("-")) {
                    var sc3 = new Scanner(str).useDelimiter("-");
                    var low = sc3.nextInt();
                    var high = sc3.nextInt();
                    for (var i = low; i <= high; i ++) nums.add(i);
                }
            }
            rules.put(sc4.next(), nums);
        }

        var mine = new ArrayList<Integer>();
        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();
            var sc2 = new Scanner(line).useDelimiter(",");
            while (sc2.hasNextInt()) {
                mine.add(sc2.nextInt());
            }

            if (line.equals("")) break;
        }

        scanner.nextLine();
        var count = 0;
        for (var rule : rules.keySet()) {
            var list = new ArrayList<Integer>();
            for (var i = 0; i < rules.size(); i ++) list.add(i);
            map.put(rule, list);
        }

        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();
            var sc2 = new Scanner(line).useDelimiter(",");
            var nums = new ArrayList<Integer>();
            while (sc2.hasNextInt()) {
                nums.add(sc2.nextInt());
            }

            for (var i = 0; i < nums.size(); i ++) {
                if (!contains(nums.get(i))) {
                    count += nums.get(i);
                } else {
                    var position = i;
                    for (var rule : rules.keySet()) {
                        if (!rules.get(rule).contains(nums.get(i))) map.get(rule).remove(Integer.valueOf(position));
                    }
                }
            }
        }

        System.out.println("Count 1: " + count);

        for (var i = 0; i < 20; i ++) {
            for (String rule : map.keySet()) {
                if (map.get(rule).size() == 1) {
                    for (var rule2 : map.keySet()) {
                        if (!rule.equals(rule2)) {
                            map.get(rule2).remove(Integer.valueOf(map.get(rule).get(0)));
                        }
                    }
                }
            }
        }

        var count2 = 1L;
        for (var rule : map.keySet()) {
            if (rule.contains("departure")) {
                count2 *= mine.get(map.get(rule).get(0));
            }
        }

        System.out.println("Count 2: " + count2);
    }
}
