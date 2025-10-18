package calculator.parser;

import calculator.validator.Validator;

import java.util.regex.Pattern;

public class NumberValidator implements Validator<String> {

    private final String delimiters;

    public NumberValidator(String delimiters) {
        this.delimiters = delimiters;
    }

    @Override
    public void validate(String input) {

        String numberPart = input.substring(input.indexOf("\\n") + 2);
        String safeDelimiters = escapeDelimiter(delimiters);

        // 숫자 문자열이 없을 때
        if (numberPart.isEmpty()) return;

        // 구분자로 시작할 때
        if (delimiters.indexOf(numberPart.charAt(0)) != -1) {
            throw new IllegalArgumentException("숫자 문자열은 구분자로 시작할 수 없습니다.");
        }

        // 연속된 구분자가 있을 때
        String continuousPattern = "[" + safeDelimiters + "]{2,}";
        if (Pattern.compile(continuousPattern).matcher(numberPart).find()) {
            throw new IllegalArgumentException("구분자는 연속으로 입력할 수 없습니다.");
        }

        // 사용 불가능한 구분자 있을 때
        String invalidPattern = "[^0-9" + safeDelimiters + "-]";
        if (Pattern.compile(invalidPattern).matcher(numberPart).find()) {
            throw new IllegalArgumentException("허용되지 않은 문자가 포함되어 있습니다.");
        }

        // 음수가 있을
        String[] tokens = numberPart.split("[" + safeDelimiters + "]");
        for (String token : tokens) {
            int num = Integer.parseInt(token);
            if (num < 0) {
                throw new IllegalArgumentException("음수는 입력할 수 없습니다.");
            }
        }
    }

    // 정규표현식에서 특수문자를 사용하기 위해 escape
    private String escapeDelimiter(String s) {

        StringBuilder sb = new StringBuilder();

        for (char c : s.toCharArray()) {
            if ("\\-^[]".indexOf(c) != -1) {
                sb.append("\\").append(c);
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}
