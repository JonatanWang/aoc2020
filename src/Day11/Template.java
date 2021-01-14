package Day11;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public abstract class Template {

    protected String[] input;

    public Template(String fileName) {
        setup(fileName);
    }

    private void setup(String fileName) {
        try (BufferedReader rdr = new BufferedReader(new FileReader(fileName))) {
            ArrayList<String> rawInput = new ArrayList<>();
            String line;

            // Read all input into the initial container
            while((line = rdr.readLine()) != null) {
                rawInput.add(line);
            }

            // Convert to array
            input = rawInput.toArray(new String[0]);
        } catch(FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Could not find the specified file: " + fileName,
                    "File Not Found", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "IOException", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    public void doTasks() {
        long start = System.nanoTime();
        System.out.println("---- Task 1 ----");
        task1();
        System.out.println("\n---- Task 2 ----");
        task2();
        long elapsed = System.nanoTime() - start;
        System.out.println();
        System.out.printf("Total runtime was %fms\n", elapsed / 1000.0 / 1000.0);
    }

    protected abstract void task1();

    protected abstract void task2();
}
