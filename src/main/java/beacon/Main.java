package beacon;
import beacon.exceptions.WrongInputException;
import beacon.helpers.ArgumentsParser;
import java.io.IOException;
import java.util.*;

/**
 * Created by Yupa on 24.02.2016.
 */
public class Main {
    private static BeaconResponce beaconResponce;
    public static void main(String[] args) {
        try {
            ArgumentsParser parser = new ArgumentsParser(args);
            beaconResponce = new BeaconResponce(parser.getDateFrom(), parser.getDateTo());
            printResult(countTheSumOfCharacters(beaconResponce.getOutput()));
        }
        catch ( NumberFormatException | WrongInputException | IOException  exception) {
            exception.printStackTrace();
            exit();
        }
    }

    public static Map<Character, Integer> countTheSumOfCharacters(String str) {
        int len = str.length();
        Map<Character, Integer> numChars = new LinkedHashMap();
        for (int i = 0; i < len; ++i) {
            char charAt = str.charAt(i);
            if (!numChars.containsKey(charAt))
                numChars.put(charAt, 1);
            else
                numChars.put(charAt, numChars.get(charAt) + 1);
        }
        return numChars;
    }

    private static void printResult(Map<Character, Integer> numChars){
        for ( Map.Entry<Character, Integer>  entry : numChars.entrySet())
            System.out.println(entry.getKey() + ", " + entry.getValue());
    }

    private static void exit() {
        printHelp();
        System.exit(0);
    }

    private static void printHelp() {
        System.out.println("Program usage: ");
        System.out.println("java -jar summarize-beacon-1.0.jar");
        System.out.println("java -jar summarize-beacon-1.0.jar --from \"3 months 1 day 1 hour ago\" --to \"1 month 1 hour ago");
    }
}