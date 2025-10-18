package calculator.delimiter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomDelimiterExtractor {

    public static String extractCustomDelimiter(String header) {

        Matcher matcher = Pattern.compile("^//(.)\\\\n").matcher(header);

        if (matcher.find()) {
            return matcher.group(1);
        }

        throw new IllegalArgumentException("커스텀 구분자를 추출할 수 없습니다.");
    }
}
