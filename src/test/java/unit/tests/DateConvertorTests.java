package unit.tests;

import beacon.*;
import beacon.exception.WrongInputException;
import org.junit.Assert;

import org.testng.annotations.*;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;


/**
 * Created by Yupa on 25.02.2016.
 */
public class DateConvertorTests {
    private Calendar calendar;

    @BeforeMethod
    public void setUp() {
        calendar = Calendar.getInstance();
    }

    @Test(groups = { "dateConvertor"})
    public void minute() throws WrongInputException {
        calendar.add(Calendar.MINUTE, -1);
        Date actualDate = Main.parseDateFromArguments("1 minute ago");
        Date expected = calendar.getTime();
        Assert.assertTrue(" Actual date is " + actualDate + " and expected is " + expected, differenceBetweenDatesToSeconds(expected, actualDate) == 0);
    }

    @Test(groups = { "dateConvertor"})
    public void hundredMinutes() throws WrongInputException {
        calendar.add(Calendar.MINUTE, -100);
        Date actualDate = Main.parseDateFromArguments("100 minutes ago");
        Date expected = calendar.getTime();
        Assert.assertTrue(" Actual date is " + actualDate + " and expected is " + expected, differenceBetweenDatesToSeconds(expected, actualDate) == 0);
    }

    @Test(groups = { "dateConvertor"})
    public void hour() throws WrongInputException {
        calendar.add(Calendar.HOUR, -1);
        Date actualDate = Main.parseDateFromArguments("1 hour ago");
        Date expected = calendar.getTime();
        Assert.assertTrue(" Actual date is " + actualDate + " and expected is " + expected, differenceBetweenDatesToSeconds(expected, actualDate) == 0);
    }

    @Test(groups = { "dateConvertor"})
    public void hundredHours() throws WrongInputException {
        calendar.add(Calendar.HOUR, -100);
        Date actualDate = Main.parseDateFromArguments("100 hours ago");
        Date expected = calendar.getTime();
        Assert.assertTrue(" Actual date is " + actualDate + " and expected is " + expected, differenceBetweenDatesToSeconds(expected, actualDate) == 0);
    }

    @Test(groups = { "dateConvertor"})
    public void day() throws WrongInputException {
        calendar.add(Calendar.DATE, -1);
        Date actualDate = Main.parseDateFromArguments("1 day ago");
        Date expected = calendar.getTime();
        Assert.assertTrue(" Actual date is " + actualDate + " and expected is " + expected, differenceBetweenDatesToSeconds(expected, actualDate) == 0);
    }

    @Test(groups = { "dateConvertor"})
    public void hundredDays() throws WrongInputException {
        calendar.add(Calendar.DATE, -100);
        Date actualDate = Main.parseDateFromArguments("100 days ago");
        Date expected = calendar.getTime();
        Assert.assertTrue(" Actual date is " + actualDate + " and expected is " + expected, differenceBetweenDatesToSeconds(expected, actualDate) == 0);
    }

    @Test(groups = { "dateConvertor"})
    public void month() throws WrongInputException {
        calendar.add(Calendar.MONTH, -1);
        Date actualDate = Main.parseDateFromArguments("1 month ago");
        Date expected = calendar.getTime();
        Assert.assertTrue(" Actual date is " + actualDate + " and expected is " + expected, differenceBetweenDatesToSeconds(expected, actualDate) == 0);
    }

    @Test(groups = { "dateConvertor"})
    public void hundredMonths() throws WrongInputException {
        calendar.add(Calendar.MONTH, -100);
        Date actualDate = Main.parseDateFromArguments("100 months ago");
        Date expected = calendar.getTime();
        Assert.assertTrue(" Actual date is " + actualDate + " and expected is " + expected, differenceBetweenDatesToSeconds(expected, actualDate) == 0);
    }

    @Test(groups = { "dateConvertor"})
    public void minuteHour() throws WrongInputException {
        calendar.add(Calendar.MINUTE, -1);
        calendar.add(Calendar.HOUR, -1);
        Date actualDate = Main.parseDateFromArguments("1 hour 1 minute ago");
        Date expected = calendar.getTime();
        Assert.assertTrue(" Actual date is " + actualDate + " and expected is " + expected, differenceBetweenDatesToSeconds(expected, actualDate) == 0);
    }

    @Test(groups = { "dateConvertor"})
    public void hundredMinutesHours() throws WrongInputException {
        calendar.add(Calendar.MINUTE, -100);
        calendar.add(Calendar.HOUR, -100);
        Date actualDate = Main.parseDateFromArguments("100 hours 100 minutes ago");
        Date expected = calendar.getTime();
        Assert.assertTrue(" Actual date is " + actualDate + " and expected is " + expected, differenceBetweenDatesToSeconds(expected, actualDate) == 0);
    }


    @Test(groups = { "dateConvertor"})
    public void minuteHourDay() throws WrongInputException {
        calendar.add(Calendar.MINUTE, -1);
        calendar.add(Calendar.HOUR, -1);
        calendar.add(Calendar.DATE, -1);
        Date actualDate = Main.parseDateFromArguments("1 day 1 hour 1 minute ago");
        Date expected = calendar.getTime();
        Assert.assertTrue(" Actual date is " + actualDate + " and expected is " + expected, differenceBetweenDatesToSeconds(expected, actualDate) == 0);
    }

    @Test(groups = { "dateConvertor"})
    public void hundredMinutesHoursDays() throws WrongInputException {
        calendar.add(Calendar.MINUTE, -100);
        calendar.add(Calendar.HOUR, -100);
        calendar.add(Calendar.DATE, -100);
        Date actualDate = Main.parseDateFromArguments("100 days 100 hours 100 minutes ago");
        Date expected = calendar.getTime();
        Assert.assertTrue(" Actual date is " + actualDate + " and expected is " + expected, differenceBetweenDatesToSeconds(expected, actualDate) == 0);
    }

    @Test(groups = { "dateConvertor"})
    public void minuteHourDayMonth() throws WrongInputException {
        calendar.add(Calendar.MINUTE, -1);
        calendar.add(Calendar.HOUR, -1);
        calendar.add(Calendar.DATE, -1);
        calendar.add(Calendar.MONTH, -1);
        Date actualDate = Main.parseDateFromArguments("1 month 1 day 1 hour 1 minute ago");
        Date expected = calendar.getTime();
        Assert.assertTrue(" Actual date is " + actualDate + " and expected is " + expected, differenceBetweenDatesToSeconds(expected, actualDate) == 0);
    }

    @Test(groups = { "dateConvertor"})
    public void hundredMinutesHoursDaysMonths() throws WrongInputException {
        calendar.add(Calendar.MINUTE, -100);
        calendar.add(Calendar.HOUR, -100);
        calendar.add(Calendar.DATE, -100);
        calendar.add(Calendar.MONTH, -100);
        Date actualDate = Main.parseDateFromArguments("100 months 100 days 100 hours 100 minutes ago");
        Date expected = calendar.getTime();
        Assert.assertTrue(" Actual date is " + actualDate + " and expected is " + expected, differenceBetweenDatesToSeconds(expected, actualDate) == 0);
    }

    @Test(groups = { "dateConvertor"})
    public void hundredMinutesHoursDaysMonthsViseVersa() throws WrongInputException {
        calendar.add(Calendar.MINUTE, -100);
        calendar.add(Calendar.HOUR, -100);
        calendar.add(Calendar.DATE, -100);
        calendar.add(Calendar.MONTH, -100);
        Date actualDate = Main.parseDateFromArguments("100 minutes 100 hours 100 days 100 months ago");
        Date expected = calendar.getTime();
        Assert.assertTrue(" Actual date is " + actualDate + " and expected is " + expected, differenceBetweenDatesToSeconds(expected, actualDate) == 0);
    }

    @Test(groups = { "dateConvertor"})
    public void minuteHourMonth() throws WrongInputException {
        calendar.add(Calendar.MINUTE, -1);
        calendar.add(Calendar.HOUR, -1);
        calendar.add(Calendar.MONTH, -1);
        Date actualDate = Main.parseDateFromArguments("1 month 1 hour 1 minute ago");
        Date expected = calendar.getTime();
        Assert.assertTrue(" Actual date is " + actualDate + " and expected is " + expected, differenceBetweenDatesToSeconds(expected, actualDate) == 0);
    }

    @Test(groups = { "dateConvertor"})
    public void minuteMonth() throws WrongInputException {
        calendar.add(Calendar.MINUTE, -1);
        calendar.add(Calendar.MONTH, -1);
        Date actualDate = Main.parseDateFromArguments("1 month 1 minute ago");
        Date expected = calendar.getTime();
        Assert.assertTrue(" Actual date is " + actualDate + " and expected is " + expected, differenceBetweenDatesToSeconds(expected, actualDate) == 0);
    }

    @Test(groups = { "dateConvertor"})
    public void hundredMinutesDays() throws WrongInputException {
        calendar.add(Calendar.MINUTE, -100);
        calendar.add(Calendar.DATE, -100);
        Date actualDate = Main.parseDateFromArguments("100 days 100 minutes ago");
        Date expected = calendar.getTime();
        Assert.assertTrue(" Actual date is " + actualDate + " and expected is " + expected, differenceBetweenDatesToSeconds(expected, actualDate) == 0);
    }

    @Test(groups = { "dateConvertor"},expectedExceptions =  WrongInputException.class)
    public void missAgo() throws  WrongInputException {
        Main.parseDateFromArguments("100 days 100 minutes");
    }

    @Test(groups = { "dateConvertor"},expectedExceptions =  WrongInputException.class)
    public void emptyString() throws  WrongInputException {
        Main.parseDateFromArguments("");
    }

    @Test(groups = { "dateConvertor"},expectedExceptions =  NumberFormatException.class)
    public void oneArgument() throws WrongInputException,NumberFormatException {
        Main.parseDateFromArguments("minute ago");
    }

    @Test(groups = { "dateConvertor"},expectedExceptions =  NumberFormatException.class)
    public void oneDouble() throws WrongInputException,NumberFormatException {
        Main.parseDateFromArguments("8.000 minute ago");
    }

    @Test(groups = { "dateConvertor"},expectedExceptions =  WrongInputException.class)
    public void dataRepetition() throws WrongInputException {
        Main.parseDateFromArguments("1 minute 1 minute ago");
    }

    @Test(groups = { "dateConvertor"},expectedExceptions =  WrongInputException.class)
    public void dataWithPluralData() throws WrongInputException {
        Main.parseDateFromArguments("2 minutes 1 minute ago");
    }

    @Test(groups = { "dateConvertor"},expectedExceptions =  NumberFormatException.class)
    public void negativeValue() throws WrongInputException,NumberFormatException {
        Main.parseDateFromArguments("-1 minute ago");
    }

    private long differenceBetweenDatesToSeconds(Date startDate, Date endDate){
        return  TimeUnit.MILLISECONDS.toSeconds( endDate.getTime() - startDate.getTime());
    }
}
