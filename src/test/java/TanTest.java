import ifmo.block1.Tan;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class TanTest {

    @Test
    @DisplayName("Check illegal inputs")
    void checkWrongInputs() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> Tan.calcTan(Double.POSITIVE_INFINITY, 50)),
                () -> assertThrows(IllegalArgumentException.class, () -> Tan.calcTan(Double.NEGATIVE_INFINITY, 50)),
                () -> assertThrows(IllegalArgumentException.class, () -> Tan.calcTan(Double.NaN, 50)),
                () -> assertThrows(IllegalArgumentException.class, () -> Tan.calcTan(Math.PI / 2, 50)),
                () -> assertThrows(IllegalArgumentException.class, () -> Tan.calcTan(5 * Math.PI / 2, 50)),
                () -> assertThrows(IllegalArgumentException.class, () -> Tan.calcTan(-Math.PI / 2, 50))
        );

    }

    @ParameterizedTest(name = "tan({0})")
    @DisplayName("Check PI")
    @ValueSource(doubles = {
            -2 * Math.PI,
            -1.3 * Math.PI,
            -Math.PI,
            -0.7 * Math.PI,
            0,
            0.4 * Math.PI,
            Math.PI,
            1.8 * Math.PI,
            2 * Math.PI
    })
    void checkPiDots(double param) {
        assertAll(
                () -> assertEquals(Math.tan(param), Tan.calcTan(param, 50), 10e-4)
        );
    }

    @ParameterizedTest(name = "tan({0}) = {1}")
    @DisplayName("Check dots with high accuracy")
    @CsvFileSource(resources = "/tan_test.csv", numLinesToSkip = 1, delimiter = ';')
    void checkBetweenDotsFromCsvHighAccuracy(double x, double y) {
        assertAll(
                () -> assertEquals(y, Tan.calcTan(x, 50), 10e-7)
        );
    }


}
