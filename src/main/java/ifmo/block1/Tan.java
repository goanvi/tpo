package ifmo.block1;

import java.math.BigInteger;

public class Tan {

    public static double calcTan(double x, int n) {
        if (Double.isInfinite(x) || Double.isNaN(x)) {
            throw new IllegalArgumentException("Argument can't be equal to infinite or null!");
        } else if (Math.abs(x) % (Math.PI) == Math.PI / 2) {
            throw new IllegalArgumentException("tg(" + x + ") is equal to infinity!");
        }
        if ((int)(Math.abs(x / (Math.PI / 2))) % 2 == 1) {
            x = x % (Math.PI / 2);
            if (x>=0){
                x -= (Math.PI / 2);
            } else {
                x += (Math.PI / 2);
            }
        }else {
            x = x % (Math.PI / 2);
        }
        double sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += ((Math.pow(-1, i + 1) * Math.pow(2, 2 * i) * (Math.pow(2, 2 * i) - 1) * BernoulliNumber.computeBernoulliNumber(2 * i))
                    / factorial(2 * i).doubleValue()) * Math.pow(x, 2 * i - 1);
        }
        return sum;
    }

    private static BigInteger factorial(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}


