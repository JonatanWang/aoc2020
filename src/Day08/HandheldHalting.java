package Day08;

import java.io.File;
import java.util.*;

public class HandheldHalting {

    private class InstructionCount{
        int steps;
        String type;

        InstructionCount(String type, String steps){
            this.type = type;
            this.steps = Integer.parseInt(steps);
        }
    }

    private ArrayList<InstructionCount> instructions;

    HandheldHalting(String fileName){
        instructions = new ArrayList<>();

        try{
            var dataReader = new File(fileName);
            var fileReader = new Scanner(dataReader);

            while(fileReader.hasNext()) {
                var line = fileReader.nextLine().split("[+ \\s++]");
                instructions.add(new InstructionCount(line[0], line[line.length - 1]));
            }
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
    }

    public int findLoopPart1(){
        var accumulator = 0;
        var visited = new HashSet<InstructionCount>();

        for(var currIndex = 0; currIndex < instructions.size(); currIndex ++){
            var currInstruction = instructions.get(currIndex);

            if(visited.contains(currInstruction))
                break;

            if(currInstruction.type.equals("acc"))
                accumulator += currInstruction.steps;

            if(currInstruction.type.equals("jmp"))
                currIndex += (currInstruction.steps - 1);

            visited.add(currInstruction);
        }

        return accumulator;
    }

    public int fixLoopPart2(){

        for(var currIndex = 0; currIndex < instructions.size(); currIndex ++){
            var currInstruction = instructions.get(currIndex);

            if(currInstruction.type.equals("jmp") || currInstruction.type.equals("nop")) {
                if (currInstruction.type.equals("jmp"))
                    currInstruction.type = "nop";
                else if (currInstruction.type.equals("nop"))
                    currInstruction.type = "jmp";

                if (canReachEnd())
                    return findLoopPart1();

                if (currInstruction.type.equals("jmp"))
                    currInstruction.type = "nop";
                else if (currInstruction.type.equals("nop"))
                    currInstruction.type = "jmp";
            }
        }

        return 0;
    }

    private boolean canReachEnd(){
        var visited = new HashSet<InstructionCount>();

        for(var currIndex = 0; currIndex < instructions.size(); currIndex ++){
            var currInstruction = instructions.get(currIndex);

            if(visited.contains(currInstruction))
                return false;

            if(currInstruction.type.equals("jmp"))
                currIndex += (currInstruction.steps - 1);

            visited.add(currInstruction);
        }

        return true;
    }

    public static void main(String[] args) {
        var fileName = "/home/zyw/dev/aoc/2020/src/Day08/input.txt";
        var handledHalting = new HandheldHalting(fileName);
        System.out.println("Part 1: " + handledHalting.findLoopPart1());
        System.out.println("Part 2: " + handledHalting.fixLoopPart2());
    }
}