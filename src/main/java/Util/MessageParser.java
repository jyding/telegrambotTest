package Util;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageParser {

    private static final String REGEX = "(?:^|[^\\$])\\$([a-zA-Z]{1,10})";

    public static ArrayList<String> getSymbols(String message){
        Pattern regex = Pattern.compile(REGEX,Pattern.CASE_INSENSITIVE);
        Matcher matcher = regex.matcher(message);
        ArrayList<String> matches = new ArrayList<>();
        while (matcher.find()) {
            System.out.println();
            matches.add(matcher.group(0));
        }
        return matches;
    }

}
