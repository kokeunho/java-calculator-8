package calculator.delimiter;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class CustomDelimiterValidatorTest {

    @Test
    void 정상_구분자_구문() {

        //given
        CustomDelimiterValidator customDelimiterValidator = new CustomDelimiterValidator();
        String header = "//^\\n";

        //when & then
        assertDoesNotThrow(() -> customDelimiterValidator.validate(header));
    }

    @Test
    void 조건_외_구분자() {

        /*
        * given
        * 구분자가 문자 두개 이상이거나
        * 0개 일 때
        */
        CustomDelimiterValidator customDelimiterValidator = new CustomDelimiterValidator();
        String header1 = "//^&\\n";
        String header2 = "//\\n";

        //when&then
        assertThatThrownBy(() -> customDelimiterValidator.validate(header1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("커스텀 구분자는 문자 1개만 허용됩니다.");
        assertThatThrownBy(() -> customDelimiterValidator.validate(header2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("커스텀 구분자는 문자 1개만 허용됩니다.");
    }
}