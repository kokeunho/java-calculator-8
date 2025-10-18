package calculator.delimiter;

import java.util.Optional;

public class CustomDelimiter {

    /*
    *   입력에서 커스텀 구분자를 반환
    *   예외)
    *   1. 커스텀 구분자가 없는 경우 -> Optional.empty 반환
    *   2. //, \n 둘 중 하나만 있는 경우 -> IllegalArguemntException
    *   3. 문자열 시작이 //이 아닌 경우 -> IllegalArguementException
    */
    public static Optional<String> findCustomDelimiter(String input) {

        // 커스텀 구분자가 없는 경우
        if (!input.contains("//") && !input.contains("\\n")) {
            return Optional.empty();
        }

        // //, \n 둘 중 하나만 있는 경우 or 문자열 시작이 //이 아닌 경우
        if ((input.contains("//") && !input.contains("\\n"))
                || (!input.contains("//") && input.contains("\\n"))
                || !input.startsWith("//")) {
            throw new IllegalArgumentException("틀린 커스텀 구분자 구문입니다.");
        }

        String customDelimiter = input.substring(2, input.indexOf("\\n"));

        if (!customDelimiter.isEmpty()) {
            // 커스텀 구분자를 반환
            return Optional.of(customDelimiter);
        } else {
            // //과 \n 사이 커스텀 구분자가 없는 경우
            return Optional.empty();
        }
    }
}
