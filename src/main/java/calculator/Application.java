package calculator;

import calculator.delimiter.CustomDelimiterExtractor;
import calculator.delimiter.CustomDelimiterValidator;
import calculator.parser.NumberSeparator;
import calculator.parser.NumberValidator;

import java.io.Console;
import java.util.List;

public class Application {

    public static void main(String[] args) {

        CustomDelimiterValidator customDelimiterValidator = new CustomDelimiterValidator();
        CustomDelimiterExtractor customDelimiterExtractor = new CustomDelimiterExtractor();

        System.out.println("덧셈할 문자열을 입력해주세요.");
        Console console = System.console();
        String input = console.readLine();

        int result = 0;
        String numberPart = input;
        String delimiters = ",:";

        // 커스텀 구분자 구문이 있는 경우
        if (input.startsWith("//")) {
            int customDelimiterEndIdx = input.indexOf("\\n");

            if (customDelimiterEndIdx == -1) {
                throw new IllegalArgumentException("잘못된 커스텀 구분자 구문입니다.");
            }

            String customDelimiterStr = input.substring(0, customDelimiterEndIdx+2);

            customDelimiterValidator.validate(customDelimiterStr);

            String customDelimiter = customDelimiterExtractor.extractCustomDelimiter(customDelimiterStr);

            delimiters += customDelimiter;

            numberPart = input.substring(customDelimiterEndIdx + 2);
        }

        NumberValidator numberValidator = new NumberValidator(delimiters);
        NumberSeparator numberSeparator = new NumberSeparator(delimiters);

        numberValidator.validate(numberPart);

        List<Integer> numbers = numberSeparator.getNumbers(numberPart);

        for (int number : numbers) {
            result += number;
        }

        System.out.println("결과 : " + result);
    }
}
