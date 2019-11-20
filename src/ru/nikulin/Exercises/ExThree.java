package ru.nikulin.Exercises;

import java.io.*;
import java.text.DecimalFormat;
import java.util.Scanner;

public class ExThree implements Exercise {
    //Gauss
    @Override
    public void show(Object params) {
        int i, j, k, n = 6;
        float d;
        float[][] ch1 = new float[6][6], ch2 = new float[6][6], a = new float[13][13];
        a[0][0] = 0;
        System.out.println("File exists: " + new File("Resources\\Matrix.txt").exists());
        try (FileReader reader = new FileReader("Resources\\Matrix.txt")) {
            Scanner scan = new Scanner(reader);
            for (i = 0; i < n; i++)
                for (j = 0; j < n; j++) {
                    a[i][j] = scan.nextFloat();
                    ch1[i][j] = a[i][j];
                }
            System.out.println("'Matrix.txt' is readable: " + true + "\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        for (i = 0; i < n; i++)
            for (j = 0; j < 2 * n; j++)
                if (j == (i + n))
                    a[i][j] = 1;
        System.out.println("Matrix: ");
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++)
                System.out.print(normalize(a[i][j], 3));
            System.out.println();
        }

        for (i = 0; i < n; i++) {
            if (a[i][i] == 0) {
                double max = a[i][i];
                int numbM = i;
                for (int buf = i; buf < n; ++buf) {
                    if (Math.abs(a[buf][i]) > max) {
                        max = Math.abs(a[buf][i]);
                        numbM = buf;
                    }
                }
                {
                    for (k = i; k < 2 * n; ++k) {
                        float swap = a[i][k];
                        a[i][k] = a[numbM][k];
                        a[numbM][k] = swap;
                    }
                }
            }
            for (j = 0; j < n * 2; j++)
                if (j != i) {
                    d = a[j][i] / a[i][i];
                    for (k = 0; k < n * 2; k++)
                        a[j][k] -= a[i][k] * d;
                }
        }
        for (i = 0; i < n; i++) {
            d = a[i][i];
            for (j = 0; j < n * 2; j++)
                a[i][j] = a[i][j] / d;
        }
        System.out.println("Result: ");
        for (i = 0; i < n; i++) {
            for (j = n; j < n * 2; j++) {
                System.out.print(normalize(a[i][j], 7));
                ch2[i][j - n] = a[i][j];
            }
            System.out.println();
        }

        System.out.println("Check:");
        double[][] ch3 = new double[6][6];
        for (i = 0; i < n; ++i)
            for (j = 0; j < n; ++j) {
                double buf = 0;
                ch3[i][j] = 0;
                for (k = 0; k < n; ++k)
                   { buf += ch1[i][k] * ch2[k][j];
                   }
                ch3[i][j] = buf;

            }
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                System.out.print( (((ch3[i][j]) < 0) ? "" : " ") + "    " + String.format("%1.5e",ch3[i][j]));
            }
            System.out.println();
        }
    }

    public static String normalize(double z, int n) {
        return normalize(z, n, 4);
    }

    public static String normalize(double z, int n, int pre) {
        return ((z < 0) ? "" : " ") + " ".repeat(pre) + String.format("%1." + n + "f", z);
    }
}
