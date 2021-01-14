package Day14;

import java.io.*;
import java.util.*;

public class DockingDataPart1 {

    public static void main(String[] args) throws FileNotFoundException {

        var scanner = new Scanner(new File("/home/zyw/dev/aoc/2020/src/Day14/input.txt"));
        var mask = "";
        var memory = new HashMap<String, Long>();

        while(scanner.hasNextLine()){
            var nextLine = scanner.nextLine();
            var dataByString = nextLine.split(" = ");
            if(dataByString[0].equals("mask")){
                mask = dataByString[1];
            } else {
                var binary = Long.toBinaryString(Long.parseLong(dataByString[1]));
                binary = ("000000000000000000000000000000000000" + binary).substring(binary.length());

                var stringBuilder = new StringBuilder();
                for(var i = 0; i < 36; i ++){
                    if (mask.charAt(i) == 'X'){
                        stringBuilder.append(binary.charAt(i));
                    } else{
                        stringBuilder.append(mask.charAt(i));
                    }
                }
                var result = Long.parseLong(stringBuilder.toString(), 2);
                memory.put(dataByString[0], result);
            }
        }

        var answer = 0;
        for (var i : memory.values()){
            answer += i;
        }

        System.out.println(answer);
    }
}
