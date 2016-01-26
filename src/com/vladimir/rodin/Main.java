package com.vladimir.rodin;

import java.lang.Math;


public class Main {

    static int EXIT_ERROR = 0;

    /* Заданная функция */
    public static double f(double x) {
        return -(Math.log(x)) - 3 * (Math.cos(2 * x));
    }

    /* Первая производная */
    public static double ff(double x) {
        return -(1 / x) + 6 * (Math.sin(2 * x));
    }

    /*Вторая производная */
    public static double fff(double x) {
        return (1 / x * x) + 12 * (Math.cos(2 * x));
    }


    /* Метод хорд */
    public static double findRoot(double a, double b, double e) {
        int steps = 0;
        System.out.println("=================== Детальное представление по шагам ======================");
        while (Math.abs(f(b)) > e) {
            a = b - ((b - a) * f(b)) / (f(b) - f(a));
            b = a - ((a - b) * f(a)) / (f(a) - f(b));
            steps++;
            System.out.println("a: " + a + "\t b: " + b);

        }
        System.out.println("=====================================================");

        System.out.println("Кол-во шагов используя метод хорд: " + steps);
        System.out.println("Значение функции в точке " + b + " равна: " + f(b));
        return b;
    }

    /* Метод хорд и касательных */
    public static double fRootPlus(double a, double b, double e) {
        int stepss = 0;


        System.out.println("=================== Детальное представление по шагам (x) ======================");
        while (true) {
            if (!(Math.abs(b - a) > e)) break;
            stepss++;

            double functionResultA = f(a);
            double functionResultB = f(b);

            a = a - ((functionResultA * (b - a)) / (functionResultB - functionResultA));
            b = b - (functionResultB / ff(b));


            System.out.println("a: " + a + "\t b: " + b);

        }
        System.out.println("=====================================================");

        System.out.println("Кол-во шагов используя метод хорд и касательных: " + stepss);
        System.out.println("Значение функции в точке " + b + " равна: " + f(b));
        return b;
    }


    public static double iterationMethod(double a, double b, double e) {
        int steps = 0;
        double x0 = 0, x1 = 0;
        boolean error = false;

        x0 = (a + b) / 2;

        do {

            double t = f(x0);

            if (t < 0) {
                t = Math.abs(t);
            }
            x1 = (x0 - ((1.00 / 6.00) * t));

            System.out.println("x0: " + x0 + "\tx1: " + x1);

            if (x0 < a || x1 > b) {
                error = true;
                break;
            }
            steps++;


            x0 = ((x0 + x1) / 2);

        } while (Math.abs(x1 - x0) >= e);

        if (error) {
            System.out.println("Нет корней!");
        }

        System.out.println("Кол-во шагов используя метод простых итераций: " + steps);
        System.out.println("Значение функции в точке " + x1 + " равна: " + Math.abs(f(x1)));
        return x1;
    }

    public static void main(String[] args) {

        double a = 5, b = 5.5, e = Math.pow(10, -4);


        System.out.println("=================== Метод простых итераций ======================");
        System.out.println("Приближенное значение корня с точностью " + e + " равен: " + iterationMethod(a, b, e));
        System.out.println();

        System.out.println("Приближенное значение корня с точностью " + e + " равен: " + fRootPlus(a, b, e));
        System.out.println();

//        System.out.println("Приближенное значение корня с точностью " + e + " равен: " + findRoot(a, b, e));
//        System.out.println();
//

    }
}
