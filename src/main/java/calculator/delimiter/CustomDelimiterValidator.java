package calculator.delimiter;

import calculator.validator.Validator;

import java.util.regex.Pattern;

public class CustomDelimiterValidator implements Validator<String> {

    @Override
    public void validate(String Header) {

        /*
        * 커스텀 구분자는 한개의 문자만으로 구성 (공백 문자도 포함)
        * 커스텀 구분자 구문 안에 문자가 없다면 틀린 입력으로 간주
        */
        Pattern p = Pattern.compile("^//.\\\\n$");
        if (!p.matcher(Header).matches()) {
            throw new IllegalArgumentException("커스텀 구분자는 문자 1개만 허용됩니다.");
        }
    }
}
