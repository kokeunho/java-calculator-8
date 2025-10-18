package calculator.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


public class NumberValidatorTest {

    private NumberValidator numberValidator;

    @BeforeEach
    void setUp() {
        String delimiters = ",:^";
        numberValidator = new NumberValidator(delimiters);
    }

    @Test
    void 정상_문자열() {

        //when
        String input1 = "//^\\n3,4:5^6";
        String input2 = "//^\\n";

        //then
        assertDoesNotThrow(() -> numberValidator.validate(input1));
        assertDoesNotThrow(() -> numberValidator.validate(input2));
    }

    @Test
    void 구분자_시작() {

        //when
        String input = "//^\\n^3,4:5";

        //then
        assertThatThrownBy(() -> numberValidator.validate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("숫자 문자열은 구분자로 시작할 수 없습니다.");
    }

    @Test
    void 연속된_구분자() {

        //when
        String input = "//^\\n3,,4:5^6";

        //then
        assertThatThrownBy(() -> numberValidator.validate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구분자는 연속으로 입력할 수 없습니다.");
    }

    @Test
    void 사용_불가능_구분자() {

        //when
        String input = "//^\\n3,4:5&6";

        //then
        assertThatThrownBy(() -> numberValidator.validate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("허용되지 않은 문자가 포함되어 있습니다.");
    }

    @Test
    void 음수_포함() {

        //when
        String input = "//^\\n3,-4:5^6";

        //then
        assertThatThrownBy(() -> numberValidator.validate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("음수는 입력할 수 없습니다.");
    }
}
