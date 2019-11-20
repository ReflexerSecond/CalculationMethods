package ru.nikulin.Exercises;

public class ExTwo implements Exercise {

    //Интерполяция

    private void Interpolate(double[] X, double[] Y) {
        int n = 10, i, j;
        double sum = 0, temp;
        double[] h = new double[10], s = new double[10], F = new double[10];
        double[][] m = new double[10][10];
        s[0] = 0;
        m[0][0] = 0;

        for (i = 0; i < n; i++) {
            X[i] = (float) X[i];
            Y[i] = (float) Y[i];
        }
        for (i = n - 1; i > 0; i--) {
            var v = X[i] - X[i - 1];
            F[i] = (Y[i] - Y[i - 1]) / v;
            h[i - 1] = v;
        }
        for (i = 1; i < n - 1; i++) {
            m[i][i] = 2 * (h[i - 1] + h[i]);
            if (i != 1) {
                m[i][i - 1] = h[i - 1];
                m[i - 1][i] = h[i - 1];
            }
            m[i][n - 1] = 6 * (F[i + 1] - F[i]);
        }
        for (i = 1; i < n - 2; i++) {
            temp = (m[i + 1][i] / m[i][i]);
            for (j = 1; j <= n - 1; j++)
                m[i + 1][j] -= temp * m[i][j];
        }
        for (i = n - 2; i > 0; i--) {
            sum = 0;
            for (j = i; j <= n - 2; j++)
                sum += m[i][j] * s[j];
            s[i] = (m[i][n - 1] - sum) / m[i][i];
        }
        for (double p = 0.05; p < 1.6; p = Math.floor((p + 0.1f) * 100) / 100) {
            for (i = 0; i < n - 1; i++)
                if (X[i] <= p && p <= X[i + 1]) {
                    sum = (float) (((s[i + 1] - s[i]) / (6 * h[i])) * Math.pow((p - X[i]), 3)
                            + (s[i] / 2) * Math.pow((p - X[i]), 2) + ((Y[i + 1] - Y[i]) / h[i] - (2 * h[i] * s[i] + s[i + 1] * h[i]) / 6)
                            * (p - X[i]) + (Y[i]));
                }
            System.out.println(p + ExThree.normalize((10 * sum),7));
        }
    }

    private double newton(double[] X, double[] Y, double x) {
        double res = Y[0], F, den;

        int i, j, k;
        for (i = 1; i < 18; i++) {
            F = 0;
            for (j = 0; j <= i; j++) {
                den = 1;
                for (k = 0; k <= i; k++) {
                    if (k != j) den *= (X[j] - X[k]);
                }
                F += Y[j] / den;

            }
            for (k = 0; k < i; k++)
                F *= (x - X[k]);
            res += F;
        }
        return res * 10;
    }

    private double lagranz(double[] X, double[] Y, double x) {
        double def = 0;
        for (int i = 0; i < 18; i++) {
            double bas = 1;
            for (int j = 0; j < 18; j++) {
                if (j != i) {
                    bas *= (x - X[j]) / (X[i] - X[j]);
                }
            }
            def += bas * Y[i];
        }
        return def * 10;
    }

    @Override
    public void show(Object params) {
        var Y = new double[18];
        var X = new double[18];

        for (int j = 0; j < 18; ++j) {
            X[j] = j;
        }
        int i = 0;
        double summ, e = 0.00001, f;
        for (double x = 0; x < 1.8; x += 0.1) {
            int k = 0;
            summ = x / 2;
            f = x / 2;
            while (Math.abs(ExOne.function(k, (float) x, (float) f)) >= e) {
                f = ExOne.function(k, (float) x, (float) f);
                summ += f;
                ++k;
            }
            Y[i] = summ;
            ++i;
        }
        double x = -0.05f;

        System.out.println("\n" + "Лагранж, Ньютон");
        while (x < 1.5) {
            x = Math.floor((x + 0.1f) * 100) / 100;
            System.out.println(x + ExThree.normalize((lagranz(X, Y, x)),7) + ExThree.normalize((newton(X, Y, x)),7));
        }
        System.out.println("\n" + "Кусочно-линейная");
        Interpolate(X, Y);
    }
}
