package calculator.parser;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberSeparatorTest {

    @Test
    void 숫자_추출() {

        //given
        String numberPart = "3,4:5^6";
        String delimiters = ",:^";
        NumberSeparator numberSeparator = new NumberSeparator(delimiters);

        //when
        List<Integer> numbers = numberSeparator.getNumbers(numberPart);

        //then
        assertEquals(Arrays.asList(3, 4, 5, 6), numbers);
    }
}