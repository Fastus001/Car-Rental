package pl.tomek.main;

import java.util.regex.Pattern;

public class Validate {

    public static boolean validatePesel(String pesel)
    {
        if(pesel.length()!=11)
            return false;
        Pattern pattern = Pattern.compile("(\\d{11})");
        return pattern.matcher(pesel).matches();
    }

    public static boolean validateDataFormat(String data)
    {
        if(data.length()!=10)
            return false;
        Pattern pattern = Pattern.compile("(\\d{4}-\\d{2}-\\d{2})");
        return pattern.matcher(data).matches();
    }
}
