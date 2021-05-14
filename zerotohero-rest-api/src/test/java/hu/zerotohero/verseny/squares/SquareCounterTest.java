package hu.zerotohero.verseny.squares;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SquareCounter.class)
public class SquareCounterTest {

    @Autowired
    SquareCounter underTest;

    @ParameterizedTest
    @MethodSource("squaresTestCaseProvider")
    public void countShouldReturnNumberOfAllPossibleSquaresFromAListOfPoints(int expected, List<Point> points) throws Exception {
        // Given
        // When
        int result = underTest.count(points);
        // Then
        Assertions.assertEquals(expected, result);
    }

    static Stream<Arguments> squaresTestCaseProvider() {
        return Stream.of(
                Arguments.of(1, Arrays.asList(new Point(2, 2), new Point(2, 3), new Point(3, 2), new Point(3, 3))),
                Arguments.of(0, Arrays.asList(new Point(2, 2), new Point(2, 2), new Point(2, 2), new Point(2, 2))),
                Arguments.of(0, null),
                Arguments.of(0, Arrays.asList()),
                Arguments.of(6, Arrays.asList(new Point(2, 2), new Point(2, 3), new Point(2, 4),
                        new Point(3, 2), new Point(3, 3), new Point(3, 4),
                        new Point(4, 2), new Point(4, 3), new Point(4, 4)))
        // Arguments.of(20, Arrays.asList(new Point(2, 2), new Point(2, 3), new Point(2, 4), new Point(2, 5),
        // new Point(3, 2), new Point(3, 3), new Point(3, 4), new Point(2, 5),
        // new Point(4, 2), new Point(4, 3), new Point(4, 4), new Point(2, 5),
        // new Point(5, 2), new Point(5, 3), new Point(5, 4), new Point(5, 5)))
        );
    }
}
