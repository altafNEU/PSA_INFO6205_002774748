/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.neu.coe.info6205.parallel;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
/**
 *
 * @author altaf
 */
/**
 * This code has been fleshed out by Ziyao Qiao. Thanks very much.
 * TODO tidy it up a bit.
 */
public class Main {
    public static void main(String[] args) {
        processArgs(args);
        int parallel_thread = 2, arraySize = 300000;
        while (parallel_thread < 65) {
            ForkJoinPool pool = new ForkJoinPool(parallel_thread);
            System.out.println("Degree of parallelism: " + pool.getParallelism());
            Random random = new Random();
            int[] array = new int[arraySize];
            ArrayList<Long> arr_time = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                ParallelSort.cutoff = 7000 * (j + 1);
                // for (int i = 0; i < array.length; i++) array[i] = random.nextInt(10000000);
                long time;
                long initial_t = System.currentTimeMillis();
                for (int t = 0; t < 10; t++) {
                    for (int i = 0; i < array.length; i++) array[i] = random.nextInt(10000000);
                    // update here
                    ParallelSort.sort(array, 0, array.length, pool);
                }
                long end_t = System.currentTimeMillis();
                time = (end_t - initial_t);
                arr_time.add(time);

                System.out.println("cutoff：" + (ParallelSort.cutoff) + "\t\t10 times Time:" + time + "ms");
            }
            try {
                FileOutputStream fis = new FileOutputStream("./src/result.csv" + arraySize + "_parallel_thread_" + parallel_thread + ".csv");
                OutputStreamWriter isr = new OutputStreamWriter(fis);
                BufferedWriter bw = new BufferedWriter(isr);
                int j = 0;
                for (long i : arr_time) {
                    String content = (double) ParallelSort.cutoff * (j + 1) / arraySize + "," + (double) i / 10 + "\n";
                    j++;
                    bw.write(content);
                    bw.flush();
                }
                bw.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            parallel_thread *= 2;
        }
    }

    private static void processArgs(String[] args) {
        String[] xs = args;
        while (xs.length > 0)
            if (xs[0].startsWith("-")) xs = processArg(xs);
    }

    private static String[] processArg(String[] xs) {
        String[] result = new String[0];
        System.arraycopy(xs, 2, result, 0, xs.length - 2);
        processCommand(xs[0], xs[1]);
        return result;
    }

    private static void processCommand(String x, String y) {
        if (x.equalsIgnoreCase("N")) setConfig(x, Integer.parseInt(y));
        else
            // TODO sort this out
            if (x.equalsIgnoreCase("P")) //noinspection ResultOfMethodCallIgnored
                ForkJoinPool.getCommonPoolParallelism();
    }

    private static void setConfig(String x, int i) {
        configuration.put(x, i);
    }

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private static final Map<String, Integer> configuration = new HashMap<>();
}
