import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

public class NumberParser {
    private Map<String, Integer> countryCodes;
    private Map<String, String> prefixes;

    public NumberParser(Map<String, Integer> countryCodes, Map<String, String> prefixes) {
        this.countryCodes=countryCodes;
        this.prefixes=prefixes;
    }

    public NumberParser(Map<String, Integer> countryCodes) {
        //ENUM or database
        Map<String, String> prefixes = new HashMap<>();
        prefixes.put("GB", "0");
        prefixes.put("US", "1");
        this.countryCodes=countryCodes;
        this.prefixes=prefixes;
    }

    public String parse(String dialedNumber, String userNumber) {
        if (!dialedNumber.contains("+")) {
           final StringBuilder intNatDNumberBuilder = new StringBuilder();
            countryCodes.forEach((key, value) -> {
                if (value.toString().equals(userNumber.substring(1, value.toString().length() + 1))) {
                    Optional<String> prefixValue = prefixes.entrySet().stream()
                            .filter(entry -> Objects.equals(entry.getKey(), key))
                            .map(Map.Entry::getValue).findFirst();
                    if (prefixValue.isPresent()) {
                        String intNatDialledNumber = dialedNumber.replaceFirst(prefixValue.get(), "");
                        intNatDNumberBuilder.append("+" + value.toString() + intNatDialledNumber);
                    }
                }
            });
            return intNatDNumberBuilder.toString();
        }
        else
      return dialedNumber;
    }

    public String parse2(String dialedNumber, String userNumber) {

        if (!dialedNumber.contains("+")) {
            final StringBuilder number = new StringBuilder();
            for(Map.Entry<String,String> entry : prefixes.entrySet()) {
                final String prefix = entry.getValue();
                final int size = prefix.length();
                if (prefix.equals(dialedNumber.substring(0,size))){
                    final String key = entry.getKey();
                    final String countryCode = countryCodes.get(key).toString();
                    number.append("+").append(countryCode).append(dialedNumber.substring(size));
                }
            }
            return number.toString();
        }
        else
            return dialedNumber;
    }
    public String parse3(String dialedNumber, String userNumber) {

        if (!dialedNumber.contains("+")) {
            final StringBuilder number = new StringBuilder();
            for (Map.Entry<String, Integer> entry : countryCodes.entrySet()) {
                final String countryCode = entry.getValue().toString();
                final int size = countryCode.length();
                if (countryCode.equals(userNumber.substring(1, size + 1))) {
                    final String key = entry.getKey();
                    final String prefix = prefixes.get(key);
                    dialedNumber = dialedNumber.substring(prefix.length());
                    number.append("+").append(countryCode).append(dialedNumber);
                }
            }
            return number.toString();
        } else
            return dialedNumber;
    }

}
