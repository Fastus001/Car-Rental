package pl.tomek.main;

import java.util.regex.Pattern;

public class Validate {

    public static boolean validatePesel(String pesel)
    {
        return Pattern.compile("\\d{11}").matcher(pesel).matches();
    }

    public static boolean validateDataFormat(String data)
    {
        return Pattern.compile("\\d{4}-\\d{2}-\\d{2}").matcher(data).matches();
    }
}
