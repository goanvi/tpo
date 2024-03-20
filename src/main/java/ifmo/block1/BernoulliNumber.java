package ifmo.block1;

import java.math.BigInteger;

public class BernoulliNumber {
    public static double computeBernoulliNumber(int N) {

        N++;
        BigInteger[][] bin = new BigInteger[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            bin[0][i] = BigInteger.ZERO;
        }
        for (int i = 0; i <= N; i++) {
            bin[i][0] = BigInteger.ONE;
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                bin[i][j] = bin[i - 1][j - 1].add(bin[i - 1][j]);
            }
        }

        double[] bernoulliNumbers = new double[N + 1];
        bernoulliNumbers[0] = 1;
        bernoulliNumbers[1] = -0.5;
        for (int i = 2; i < N; i++) {
            bernoulliNumbers[i] = 0;
            for (int j = 0; j < i; j++) {
                BigInteger coef = bin[i + 1][i + 1 - j];
                bernoulliNumbers[i] = bernoulliNumbers[i] - (coef.doubleValue() * bernoulliNumbers[j]);
            }
            bernoulliNumbers[i] = bernoulliNumbers[i] / (i + 1);
        }
        return bernoulliNumbers[N - 1];
    }
}