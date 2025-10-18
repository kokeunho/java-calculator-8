package calculator;

import calculator.delimiter.CustomDelimiter;
import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.*;

class ApplicationTest extends NsTest {

    @Test
    void 구분자_구문_검증() {

        //given

        //when
        String input1 = "//^\\n3^4,5";
        String input2 = "//\\n3^4,5";
        String input3 = "3^4,5";
        String input4 = "//^3^4,5";
        String input5 = "^\\n3^4,5";
        String input6 = "3//^\\n^4,5";

        //then
        /*
        * 1. 올바른 구분자 구문
        * 2. 빈 구분자 구문
        * 3. \n가 없는 틀린 구문
        * 4. //가 없는 틀린 구문
        * 5. 커스텀 구분자 사용하지 않는 구문
        * 6. //로 문자열 시작하지 않는 경우
        */
        assertThat(CustomDelimiter.findCustomDelimiter(input1)).isEqualTo(Optional.of("^"));
        assertThat(CustomDelimiter.findCustomDelimiter(input2)).isEqualTo(Optional.empty());
        assertThat(CustomDelimiter.findCustomDelimiter(input3)).isEqualTo(Optional.empty());
        assertThatThrownBy(() -> CustomDelimiter.findCustomDelimiter(input4))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("틀린 커스텀 구분자 구문입니다.");
        assertThatThrownBy(() -> CustomDelimiter.findCustomDelimiter(input5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("틀린 커스텀 구분자 구문입니다.");
        assertThatThrownBy(() -> CustomDelimiter.findCustomDelimiter(input6))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("틀린 커스텀 구분자 구문입니다.");
    }

    @Test
    void 커스텀_구분자_사용() {
        assertSimpleTest(() -> {
            run("//;\\n1");
            assertThat(output()).contains("결과 : 1");
        });
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("-1,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
