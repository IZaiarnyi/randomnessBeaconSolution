package beacon.helpers;

import beacon.exceptions.WrongInputException;
import java.io.IOException;
import java.time.Instant;
import java.util.*;

/**
 * Created by Yupa on 26.02.2016.
 */
public class ArgumentsParser {
    private final String argFrom = "--from";
    private final String argTo = "--to";
    private final String ago = "ago";
    private final int beaconStartTS = 1378395540;
    private final Date beaconStartDate = Date.from(Instant.ofEpochSecond(beaconStartTS));
    private Date dateFrom = null;
    private Date dateTo = null;

    public ArgumentsParser(String[] args)throws WrongInputException {
        switch (args.length) {
            case 0:
                break;
            case 4:
                if (!args[0].equals(argFrom))
                    throw new WrongInputException("The first argument should be " + argFrom);
                if (!args[2].equals(argTo))
                    throw new WrongInputException("The third argument should be " + argTo);
                dateFrom = convertStringToDate(args[1]);
                dateTo = convertStringToDate(args[3]);
                if ( dateFrom.compareTo(beaconStartDate) < 0 && dateTo.compareTo(beaconStartDate) < 0)
                    throw new WrongInputException("Your dates should be greater than " + beaconStartDate);
                if ( dateFrom.compareTo(dateTo) > 0)
                    throw new WrongInputException("Your date 'FROM' " + dateFrom +" is greater than date 'TO' " + dateTo);
                break;
            default:
                throw new WrongInputException("Invalid arguments count");
        }
    }

    public Date convertStringToDate(String arguments) throws WrongInputException,NumberFormatException {
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
    public Date getDateFrom() { return dateFrom; }
    public Date getDateTo() { return dateTo; }
}