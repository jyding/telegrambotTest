package Util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

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

    public static String readLineByLine(String filePath)
    {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8))
        {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }

}
