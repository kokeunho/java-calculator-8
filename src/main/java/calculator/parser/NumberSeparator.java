package calculator.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class NumberSeparator {

    private final String delimiters;

    public NumberSeparator(String delimiters) {
        this.delimiters = delimiters;
    }

    public List<Integer> getNumbers(String numberPart) {

        List<Integer> numbers = new ArrayList<>();

        String[] tokens = numberPart.split("[" + Pattern.quote(delimiters) + "]");

        for (String token : tokens) {
            numbers.add(Integer.parseInt(token));
        }

        return numbers;
    }
}
