/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.neu.coe.info6205.sort.elementary;

/**
 *
 * @author altaf
 */
import edu.neu.coe.info6205.sort.*;
import edu.neu.coe.info6205.util.*;
import org.junit.Test;
import java.util.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.awt.Color;
import java.awt.Font;
import edu.neu.coe.info6205.util.*;
import java.util.*;

public class BenchmarkTestUser {

    private final int SumRuns;
    private final int n;
    final static LazyLogger logger = new LazyLogger(BenchmarkTestUser.class);

    public static void main(String[] args) {
        int SumRuns = 500;
        for (int n = 250; n <= 16000; n *= 2) {
            new BenchmarkTestUser(n, SumRuns).executeBenchmark(n);
        }
    }

    public BenchmarkTestUser(int n, int SumRuns) {
        this.n = n;
        this.SumRuns = SumRuns;
    }

    public void executeBenchmark(int n) {
        ArrayList<Integer> list = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            list.add(rand.nextInt(n));
        }
        Integer[] array = list.toArray(new Integer[0]);

        System.out.println("Final Benchmark test user Output : --- " + n);
        String title = "Insertion Sort";

        InsertionSort<Integer> insertionSort = new InsertionSort<>();

        final double timeRandom = new Benchmark_Timer<Integer[]>(
                title + " (Random Ordered)",
                null,
                (x) -> insertionSort.sort(array.clone(), 0, array.length),
                null
        ).run(array, SumRuns);
        for (TimeLogger timeLogger : timeLoggers) {
            timeLogger.log(timeRandom, n);
        }

        Arrays.sort(array);
        final double timeSorted = new Benchmark_Timer<Integer[]>(
                title + " (Sorted Ordered)",
                null,
                (x) -> insertionSort.sort(array.clone(), 0, array.length),
                null
        ).run(array, SumRuns);
        for (TimeLogger timeLogger : timeLoggers) {
            timeLogger.log(timeSorted, n);
        }

        Arrays.sort(array, 0, n / 2);
        final double timePartially = new Benchmark_Timer<Integer[]>(
                title + " (Partially Ordered)",
                null,
                (x) -> insertionSort.sort(array.clone(), 0, array.length),
                null
        ).run(array, SumRuns);
        for (TimeLogger timeLogger : timeLoggers) {
            timeLogger.log(timePartially, n);
        }

        Arrays.sort(array, Collections.reverseOrder());
        final double timeReverse = new Benchmark_Timer<Integer[]>(
                title + " (Reverse Ordered)",
                null,
                (x) -> insertionSort.sort(array.clone(), 0, array.length),
                null
        ).run(array, SumRuns);
        for (TimeLogger timeLogger : timeLoggers) {
            timeLogger.log(timeReverse, n);
        }
    }

    private final static TimeLogger[] timeLoggers = {
        new TimeLogger("Run Time ", (time, n) -> time)
    };

}
