package unit.tests;


import java.util.HashMap;
import java.util.Map;
import beacon.Main;
import org.junit.Assert;
import org.testng.annotations.Test;

/**
 * Created by Yupa on 27.02.2016.
 */
public class SumOfCharactersTest {
    @Test
    public void getCountFrom128Chars() {

        String testData = "AE090F0CC3C854F52AE5A453EA9F8C32B48F5EA506DE87BEA4B529C3EBDA56B0417FF839A1FC67E031BB0CF51A5149A502AE1BC85A6CB43E26D592482090F85B";
        Map<Character, Integer> expected = new HashMap<>();
        expected.put('A', 12);
        expected.put('E', 10);
        expected.put('0', 10);
        expected.put('9', 7);
        expected.put('F', 9);
        expected.put('C', 9);
        expected.put('3', 7);
        expected.put('8', 8);
        expected.put('5', 14);
        expected.put('4', 8);
        expected.put('2', 7);
        expected.put('B', 10);
        expected.put('6', 5);
        expected.put('D', 3);
        expected.put('7', 3);
        expected.put('1', 6);

        Map<Character, Integer> actual = Main.countTheSumOfCharacters(testData);
        Assert.assertTrue(actual.equals(expected));
    }

    @Test
    public void getCountForChars() {
        Map<Character, Integer> expected = new HashMap<>();
        expected.put('A', 2);
        expected.put('3', 1);
        expected.put('2', 1);
        expected.put('5', 1);

        String testData = "AA325";

        Map<Character, Integer> actual = Main.countTheSumOfCharacters(testData);
        Assert.assertTrue(actual.equals(expected));
    }

    @Test
    public void getCountForEmptyString() {
        Map<Character, Integer> expected = new HashMap<>();

        Map<Character, Integer> actual = Main.countTheSumOfCharacters("");
        Assert.assertTrue(actual.equals(expected));
    }


}
