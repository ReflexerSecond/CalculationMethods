package ru.nikulin.Exercises;

public class ExFive implements Exercise {
    //приближенное решение задачи Коши методом Эйлера

    double F(double x, double y) {
        return -x*y + (1 - x)*(Math.pow(2.78, x)*y*y);
    }

    @Override
    public void show(Object params) {
        double a = 0, b = 1, h = 0.1,n = (b - a) / h;
        double[] X = new double[20],
                Y = new double[20],
                Y1 = new double[20],
                Y2 = new double[20],
                Y3 = new double[20],
                Y4 = new double[20];
        X[0] = a;
        Y[0] = 1;

        for (int i = 1; i < n+1; i++) {
            X[i] = a + i*h;
            Y1[i] = h*F(X[i - 1], Y[i - 1]);
            Y2[i] = h*F(X[i - 1] + h / 2.0, Y[i - 1] + Y1[i] / 2.0);
            Y3[i] = h*F(X[i - 1] + h / 2, Y[i - 1] + Y2[i] / 2);
            Y4[i] = h*F(X[i - 1] + h, Y[i - 1] + Y3[i]);
            Y[i] = Y[i - 1] + (Y1[i] + 2 * Y2[i] + 2 * Y3[i] + Y4[i]) / 6;
        }
        for (int i = 0; i < n+1; i++) {
            System.out.println("X[" +((i ==10)?"":"0") + i + "]=" + ExThree.normalize(X[i],1,0) +
                    "    Y[" + ((i ==10)?"":"0") + i + "]="+ ExThree.normalize(Y[i],5,0));
        }
    }
}
