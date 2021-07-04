import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, Integer> countryCodes = new HashMap<>();
        Map<String, String> prefixes = new HashMap<>();
        countryCodes.put("GB", 44); prefixes.put("GB", "0");
        countryCodes.put("US", 1); prefixes.put("US", "1");

        NumberParser parser = new NumberParser(countryCodes);
        System.out.println(parser.parse("02079460056", "+441614960148"));
        System.out.println(parser.parse2("02079460056", "+441614960148"));
        System.out.println(parser.parse3("02079460056", "+441614960148"));
        //System.out.println(parser.parse("+442079460056", "+441614960148"));
       // System.out.println(parser.parse("02079460056", "+441614960148"));
    }





}
