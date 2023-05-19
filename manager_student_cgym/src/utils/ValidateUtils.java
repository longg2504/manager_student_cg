package utils;

import java.util.regex.Pattern;

public class ValidateUtils {
    public static final String NAME_REGEX = "^([A-ZÀ-Ă-Â-ỹ][a-zÀ-ỹ]*[ ]?)+$";
    public static final String PHONE_REGEX = "^[0][1-9][0-9]{8,9}$";
    public static final String ADDREE_REGEX = "^([^. ][.]*[ ]?)+$";


    public static boolean isNameValid(String name) {
        return Pattern.compile(NAME_REGEX).matcher(name).matches();
    }

    public static boolean isPhoneValid(String number) {
        return Pattern.compile(PHONE_REGEX).matcher(number).matches();
    }

    public static boolean isAddValid(String address){
        return Pattern.compile(ADDREE_REGEX).matcher(address).matches();
    }

    public static String parseCommaToChar(String s) {
        String s1 = s.replaceAll(",", "!");
        return s1;
    }
    public static String parseCharToComma(String s) {
        String s1 = s.replaceAll("!", ",");
        return s1;
    }
}
