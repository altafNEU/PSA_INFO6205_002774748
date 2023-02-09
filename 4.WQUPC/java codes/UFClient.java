/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.neu.coe.info6205.union_find;

import java.util.Random;

/**
 * import java.util.Random;
 *
 * @author altaf
 */
public class UFClient {

    public static int count(int n) {
        UF_HWQUPC uf = new UF_HWQUPC(n);

        int num = 0;
        Random random = new Random();
        while (uf.components() > 1) {
            int i = random.nextInt(n);
            int j = random.nextInt(n);

            if (!uf.connected(i, j)) {
                uf.union(i, j);
            }

            num++;
        }

        return num;
    }

    public static void main(String[] args) {
        System.out.printf("%-10s %-10s %-30s %-20s\n", "n", "m", "fn = 0.5 * n * ln(n)", "offset = (fn - m) / m");

        for (int i = 100; i < 1000000; i *= 2) {
            int sum = 0;
            for (int j = 0; j < 10; j++) {
                sum += count(i);
            }

            double m = sum / 10.0;
            int fn = (int) (0.5 * i * Math.log(i));

            System.out.printf("%-10s %-10s %-30s %-20s\n", i, (int) m, fn, (fn - m) / m);
        }
    }
}
