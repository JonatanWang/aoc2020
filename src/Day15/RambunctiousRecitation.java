package Day15;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class RambunctiousRecitation {

    public static void main(String[] args) {
        var input = "0,13,1,8,6,15";
        System.out.println("Part 1: " + getSpokenNumber(input, 2_020L));
        System.out.println("Part 2: " + getSpokenNumber(input, 30_000_000L));
    }

    public static long getSpokenNumber(String input, Long number) {
        var spokenNumbers = new HashMap<Long, Long>(); // number, index spoken
        var split = input.split(",");
        // Check if there is a repeat.
        IntStream.range(0, split.length - 1)
                .forEach(i -> spokenNumbers.put(Long.parseLong(split[i]), (long) i));

        var lastNumber = getNextNumber(spokenNumbers, Long.parseLong(split[split.length - 1]), split.length);
        long nextNumber;
        spokenNumbers.put(Long.parseLong(split[split.length - 1]), (long) (split.length - 1));

        for (long i = split.length; i < number - 1; i++) {
            nextNumber = getNextNumber(spokenNumbers, lastNumber, i);
            spokenNumbers.put(lastNumber, i);
            lastNumber = nextNumber;
        }

        return lastNumber;
    }

    private static long getNextNumber(Map<Long, Long> spokenNumbers, Long lastNumber, long index) {
        if (!spokenNumbers.containsKey(lastNumber)) {

            return 0L;
        } else {

            return index - spokenNumbers.get(lastNumber);
        }
    }
}