package beacon;

import beacon.api.entity.Record;
import beacon.api.restclient.RestClient;
import beacon.exception.WrongInputException;
import java.io.IOException;
import java.time.Instant;
import java.util.*;

/**
 * Created by Yupa on 24.02.2016.
 */
public class Main {
    private static final String argFrom = "--from";
    private static final String argTo = "--to";
    private static final String ago = "ago";
    private static final int beaconStartTS = 1378395540;
    private static  final Date beaconStartDate = Date.from(Instant.ofEpochSecond( beaconStartTS ));

    public static void main(String[] args){
        try {
            switch (args.length) {
                case 0:
                    countDataFromLastBeacon();
                    break;
                case 4:
                    if (!args[0].equals(argFrom))
                        throw new WrongInputException("The first argument should be " + argFrom);
                    if (!args[2].equals(argTo))
                        throw new WrongInputException("The third argument should be " + argTo);
                    Date dateFrom = parseDateFromArguments(args[1]);
                    Date dateTo = parseDateFromArguments(args[3]);
                    //System.out.println("From " + dateFrom.getTime() + " to " + dateTo.getTime());
                    if ( dateFrom.compareTo(beaconStartDate) < 0 && dateTo.compareTo(beaconStartDate) < 0)
                        throw new WrongInputException("Your dates should be greater than " + beaconStartDate);
                    if ( dateFrom.compareTo(dateTo) > 0)
                        throw new WrongInputException("Your date 'FROM' " + dateFrom +" is greater than date 'TO' " + dateTo);
                    countDataBetweenTwoDatesFromBeacon(dateFrom, dateTo);
                    break;
                default:
                    throw new WrongInputException("Invalid arguments count");
            }
        }
        catch ( NumberFormatException exception){ System.err.println("Please provide amount of period by positive integer number");}
        catch ( WrongInputException exception){ exception.printStackTrace(); }
        catch ( IOException exception){ System.err.println("Beacon Rest Service has become an authorized "); }
        catch ( Exception exception) {exit();}
    }
    public static void countDataBetweenTwoDatesFromBeacon(Date dateFrom, Date dateTo) throws IOException {
        final int beaconInterval= 1;
        String output = new String();
        Record record = null;
        RestClient.INSTANCE.initialize();
        while ( dateFrom.compareTo(dateTo) <= 0){
                String timeStamp =  String.valueOf(dateFrom.getTime() / 1000L);
                record = RestClient.INSTANCE.getRestApi().getRecordByTimeStamp(timeStamp ).execute().body();
                if ( record != null)
                    output += record.getOutputValue();
            dateFrom = addMinutesToDate(beaconInterval, dateFrom);
        }
        printNumberOfCharacters (output);
    }
    public static void countDataFromLastBeacon() throws IOException {
        RestClient.INSTANCE.initialize();
        Record record  = RestClient.INSTANCE.getRestApi().getLastRecord().execute().body();
        printNumberOfCharacters(record.getOutputValue());
    }
    public static Date parseDateFromArguments(String arguments) throws WrongInputException,NumberFormatException {
        Map<String, Integer> template = new HashMap<String, Integer>() {{
            put("minute", Calendar.MINUTE);
            put("hour",   Calendar.HOUR);
            put("day",    Calendar.DATE);
            put("month",  Calendar.MONTH);
            put("minutes", Calendar.MINUTE);
            put("hours",   Calendar.HOUR);
            put("days",    Calendar.DATE);
            put("months",  Calendar.MONTH);
        }};
        String[] args = arguments.split(" ");
        Calendar cal = Calendar.getInstance();
        List source = new ArrayList();
        if( !args[args.length-1].equals(ago))
            throw new WrongInputException("Please provide " + ago + " word at the end of the date");
        for (int i = 0; i < args.length - 1; i += 2) {
            int amount = Integer.parseUnsignedInt(args[i]);
            String field =  args[i+1];
            String radicalOfField = field.substring(0,3);
            if( !template.keySet().contains(field) || source.contains(radicalOfField))
                throw new WrongInputException("Argument " + field + " is incorrect");
            source.add(radicalOfField);
            cal.add(template.get(field), -amount);
        }
        return cal.getTime();
    }
    public static void exit() {
        printHelp();
        System.exit(0);
    }
    private static void printNumberOfCharacters(String str){
        int len = str.length();
        Map<Character, Integer> numChars = new LinkedHashMap();
        for (int i = 0; i < len; ++i){
            char charAt = str.charAt(i);
            if (!numChars.containsKey(charAt))
                numChars.put(charAt, 1);
            else
                numChars.put(charAt, numChars.get(charAt) + 1);
        }
        for ( Map.Entry<Character, Integer>  entry : numChars.entrySet())
            System.out.println(entry.getKey() + ", " + entry.getValue());
    }
    private static void printHelp(){
        System.out.println("Program usage: " );
        System.out.println("java -jar summarize-beacon-1.0.jar");
        System.out.println("java -jar summarize-beacon-1.0.jar --from \"3 months 1 day 1 hour ago\" --to \"1 month 1 hour ago" );
    }
    private static Date addMinutesToDate(int minutes, Date beforeTime){
        final long ONE_MINUTE_IN_MILLIS = 60000;//millisecs
        return new Date(beforeTime.getTime() + (minutes * ONE_MINUTE_IN_MILLIS));
    }
}