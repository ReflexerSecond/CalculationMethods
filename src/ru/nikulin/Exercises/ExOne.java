package ru.nikulin.Exercises;

public class ExOne implements Exercise {
    /*
    Найти значения функции в точках xi = x0 + ih , h = 0.1 с точностью
    ε = 0.0001 . Составить таблицу значений.
    Найти производную f (x) аналитически с заданной точностью и составить таблицу значений.
    */
    public static float function(int k, float x, float f) {
        return f * (float) (Math.pow(-1, (k))) * (x * x) / (4 * (2 * k + 3) * (2 * k + 2));
    }

    @Override
    public void show(Object params) {
        System.out.println("Производная f (x) и значения");
        var F = new float[18];
        int i = 0;
        float summ, e = 0.00001f, f;
        for (float x = 0; x < 1.8; x += 0.1) {
            int k = 0;
            summ = x / 2;
            f = x / 2;
            while (Math.abs(function(k, x, f)) >= e) {
                f = function(k, x, f);
                summ += f;
                ++k;
            }
            F[i] = summ;
            ++i;
        }
        float x = 0;
        System.out.println(ExThree.normalize(x, 1) + ExThree.normalize(Math.sin(x / 2), 7) + ExThree.normalize(F[0], 7)
                + " " + "    -         ".repeat(3) + ExThree.normalize(Math.cos(x / 2) * 0.5, 7));
        x += +0.1f;
        for (int j = 1; j < 17; ++j) {
            var v = F[j] - F[j - 1];
            var v1 = F[j + 1] - F[j];
            System.out.println(ExThree.normalize(x, 1) + ExThree.normalize(Math.sin(x / 2), 7) + ExThree.normalize(F[j], 7)
                    + ExThree.normalize(v / 1, 7) + ExThree.normalize(v1 / 1, 7)
                    + " " + ExThree.normalize((v1 + v) / 2, 7) + ExThree.normalize((Math.cos(x / 2) * 0.5), 7));
            x += 0.1f;
        }
        System.out.println(ExThree.normalize(x, 1) + ExThree.normalize(Math.sin(x / 2), 7) + ExThree.normalize(F[17], 7)
                + " " + "    -         ".repeat(3) + ExThree.normalize((Math.cos(x / 2) * 0.5), 7));
    }
}
