package calculator.delimiter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomDelimiterExtractor {

    public static String extractCustomDelimiter(String Header) {

        Matcher matcher = Pattern.compile("^//(.)\\\\n").matcher(Header);

        return matcher.group(1);
    }
}
