package lk.ijse.simple_hostel_management_hibernate.controller.util;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static org.controlsfx.control.WorldMapView.Country.LK;

public class ValidateFields {

    public static boolean studentIdCheck(String studentId) {
        String pattern = "^ST-\\d+$";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(studentId);
        return m.matches();
    }

    public static boolean roomIdCheck(String txt) {
        String pattern = "^RM-\\d+$";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(txt);
        return m.matches();
    }

    public static boolean rerservationIdCheck(String txt) {
        String pattern = "^RES-\\d+$";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(txt);
        return m.matches();
    }

    public static boolean nameCheck(String name) {
        String pattern = "^[A-Za-z\\s'-]+$";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(name);
        return m.matches();
    }

    public static boolean addressCheck(String add) {
        String pattern = "^[0-9A-Za-z\\s\\-\\,\\.\\']+$";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(add);
        return m.matches();
    }

    public static boolean contactCheck(String contact) {
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        Phonenumber.PhoneNumber phoneNumber = null;
        try {
            phoneNumber = phoneUtil.parse(contact, String.valueOf(LK));
        } catch (NumberParseException e) {
            // The input is not a valid phone number
            return false;
        }
        boolean isValid = phoneUtil.isValidNumber(phoneNumber);
        return isValid;
    }

    public static boolean moneyCheck(String txt) {
        String pattern = "^(?!0\\d)(?:\\d{1,3}(?:,\\d{3})*|\\d+)(?:\\.\\d{1,2})?$";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(txt);
        return m.matches();
    }

    public static boolean numberCheck(String input) {
        try {
            if (input.matches("\\d+")) {
                return true; // Input contains only numeric characters
            } else {
                return false; // Input contains non-numeric characters
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
