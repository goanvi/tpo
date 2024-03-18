package ifmo.block1;

public class Tan {
    public static double calcTan(double x, int n) {
        if (Double.isInfinite(x) || Double.isNaN(x)) {
            throw new IllegalArgumentException("Argument can't be equal to infinite or null!");
        } else if (Math.abs(x) % (Math.PI) == Math.PI / 2) {
            throw new IllegalArgumentException("tg(" + x + ") is equal to infinity!");
        }
        double sin = calcSin(x, n);
        double cos = calcCos(x, n);
        return sin / cos;
    }


    private static double calcSin(double x, int n) {
        x = x % (Math.PI * 2);
        double result = x;
        double x2 = x * x;
        double pow = x;
        double fact = 1;
        int sign = -1;
        for (int i = 1; i < n; i++) {
            fact *= 2 * i * (2 * i + 1);
            pow *= x2;
            result += sign * pow / fact;    // (-1)^(n) * x^(2n+1) / (2n+1)!
            sign = -sign;
        }

        return result;
    }

    private static double calcCos(double x, int n) {
        x = x % (Math.PI * 2);
        double result = 1;
        double x2 = x * x;
        double pow = 1;
        double fact = 1;
        int sign = -1;
        for (int i = 1; i < n; i++) {
            fact *= 2 * i * (2 * i - 1);
            pow *= x2;
            result += sign * pow / fact;    // (-1)^(n) * x^(2n) / (2n)!
            sign = -sign;
        }
        return result;
    }
}
