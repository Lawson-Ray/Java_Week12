import my.unit.test.TestDemo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TestDemoTest extends Object {

    private TestDemo testDemo;
    @BeforeEach
    void setUp() {
        testDemo = new TestDemo();
    }

    @ParameterizedTest
    @MethodSource("argumentsForAddPositive")
    void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected, Boolean expectException) {
        if (!expectException) {
            Assertions.assertThat(testDemo.addPositive(a,b)).isEqualTo(expected);
        }
        else {
            Assertions.assertThatThrownBy(() -> testDemo.addPositive(a, b)).isInstanceOf(IllegalArgumentException.class);
        }
    }
    private static Stream<Arguments> argumentsForAddPositive() {
        return Stream.of(
                Arguments.of(0, 0, 1, true),
                Arguments.of(1, 1, 2, false),
                Arguments.of(-1, 5, 4, true),
                Arguments.of(-2, 2, 0, false),
                Arguments.of(-5, -5, -10, true),
                Arguments.of(1, 1, 3, true),
                Arguments.of(1, 1, 3, false)
        );
    }
    @Test
    void assertThatNumberSquaredIsCorrect() {
        TestDemo mockDemo = spy(testDemo);
        doReturn(5).when(mockDemo).getRandomInt();
        int fiveSquared = mockDemo.randomNumberSquared();
        Assertions.assertThat(fiveSquared).isEqualTo(25);
    }
}