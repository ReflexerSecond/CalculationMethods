package ru.nikulin.Exercises;

public class ExFour implements Exercise {

    private double function(double t, double y) {
        return -t * y + (1 - t) * (Math.pow(Math.E, t) * y * y);
    }

    private double funcCheck(double t) {
        return 1 / (-Math.pow(Math.E, t) + 2 * Math.pow(Math.E, 0.5 * t * t));
    }

    @Override
    public void show(Object params) {
        double[] y = new double[11], yabs = new double[11];
        double k1, k2, k3, k4, t;
        int i;
        y[0] = yabs[0] = 1;
        t = 0.1;
        for (i = 1; i <= 10; ++i) {
            k1 = 0.1 * function(t - 0.1, yabs[i - 1]);
            k2 = 0.1 * function(t - 0.1 + 0.1 / 2, yabs[i - 1] + k1 / 2.0);
            k3 = 0.1 * function(t - 0.1 + 0.1 / 2.0, yabs[i - 1] + k2 / 2.0);
            k4 = 0.1 * function(t - 0.1 + 0.1, yabs[i - 1] + k3);
            yabs[i] = yabs[i - 1] + (k1 + 2 * k2 + 2 * k3 + k4) / 6;
            y[i] = y[i - 1] + 0.1 * function(t, y[i - 1]);
            t += 0.1;
        }

        t = 0.0;
        for (i = 0; i <= 10; ++i) {
            System.out.println(" t ="+  ExThree.normalize(t,1) + ExThree.normalize(y[i], 5) + ExThree.normalize(yabs[i], 5)
                    + ExThree.normalize(funcCheck(t), 5));
            t += 0.1;
        }
    }
}
