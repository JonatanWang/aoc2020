package Day14;

import java.io.*;
import java.util.*;

public class DockingDataPart2 {

    public static void main(String[] args) throws FileNotFoundException {

        var scanner = new Scanner(new File("/home/zyw/dev/aoc/2020/src/Day14/input.txt"));
        var mask = "";
        var memory = new HashMap<Long, Long>();

        while(scanner.hasNextLine()){
            var nextLine = scanner.nextLine();
            var dataByString = nextLine.split(" = ");
            if(dataByString[0].equals("mask")){
                mask = dataByString[1];
            } else {
                var addr = Long.toBinaryString(Long.parseLong(dataByString[0].substring(4, dataByString[0].length()-1)));
                addr = ("000000000000000000000000000000000000" + addr).substring(addr.length());

                var stringBuilder = new StringBuilder();
                for(var i = 0; i< 36; i ++){
                    if (mask.charAt(i) == 'X'){
                        stringBuilder.append("X");
                    } else if (mask.charAt(i) == '1'){
                        stringBuilder.append("1");
                    } else{
                        stringBuilder.append(addr.charAt(i));
                    }
                }
                addr = stringBuilder.toString();
                var addrs = findMems(addr);
                for (var i : addrs){
                    memory.put(i, Long.parseLong(dataByString[1]));
                }
            }
        }
        var answer = 0L;
        for (var i : memory.values()){
            answer += i;
        }

        System.out.println(answer);
    }

    public static long[] findMems(String addr){
        //count x
        var count = 0;
        for(var i = 0; i < addr.length(); i ++){
            if (addr.charAt(i) == 'X'){
                count++;
            }
        }
        var poss = (long) Math.pow(2, count);
        var result = new long[(int)poss];

        for (var i = 0; i < poss; i ++){
            var binary = Long.toBinaryString(i);
            var a = "000000000000000000000000000000000000" + binary;
            binary = a.substring(a.length() - count);

            var res = new StringBuilder();
            var k = 0;
            for (var j = 0; j < addr.length(); j ++){
                if (addr.charAt(j) == 'X'){
                    res.append(binary.charAt(k));
                    k ++;
                }
                else {
                    res.append(addr.charAt(j));
                }
            }
            result[i] = Long.parseLong(res.toString(), 2);
        }

        return result;
    }
}
