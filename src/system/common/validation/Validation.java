package system.common.validation;

import system.common.exception.PhoneNumberFormatException;

import java.util.regex.Pattern;

import static system.prolog.PrologUtil.validatePassword;
import static system.prolog.PrologUtil.validateUserID;

public class Validation {

    public static Result<String, Exception> validateCredentials(String id, String pass) {
        if (!isValidUserID(id) && !id.equals("ADMIN")) {
            return new Failure<>(new IllegalArgumentException("IC/Passport Number format is invalid!"));
        }
        if (!isValidPassword(pass)) {
            return new Failure<>(new IllegalArgumentException("Password format is invalid!"));
        }
        return new Success<>("All inputs are valid");
    }

    public static Result<String, Exception> validateProfileInputs(String id, String pass, String phoneNum) {
        if (!isValidUserID(id)) {
            return new Failure<>(new IllegalArgumentException("IC/Passport Number format is invalid!"));
        }
        if (!isValidPassword(pass)) {
            return new Failure<>(new IllegalArgumentException("Password format is invalid!"));
        }
        if (!isValidPhoneNum(phoneNum)) {
            return new Failure<>(new PhoneNumberFormatException("Phone Number format is invalid!"));
        }
        return new Success<>("All inputs are valid");
    }

    //Checks the validity of password according to a set of rules
    private static boolean isValidPassword(String password)
    {
//        if (password.length() < 8)
//        {
//            JOptionPane.showMessageDialog(null,
//                    "Password must be more than 8 characters in length.",
//                    "Inane error",
//                    JOptionPane.ERROR_MESSAGE);
//            return false;
//        }
//        String upperCaseChars = "(.*[A-Z].*)";
//        if (!password.matches(upperCaseChars ))
//        {
//            JOptionPane.showMessageDialog(null,
//                    "Password must have at least one uppercase character.",
//                    "Inane error",
//                    JOptionPane.ERROR_MESSAGE);
//            return false;
//        }
//        String lowerCaseChars = "(.*[a-z].*)";
//        if (!password.matches(lowerCaseChars ))
//        {
//            JOptionPane.showMessageDialog(null,
//                    "Password must have at least one lowercase character.",
//                    "Inane error",
//                    JOptionPane.ERROR_MESSAGE);
//            return false;
//        }
//        String numbers = "(.*[0-9].*)";
//        if (!password.matches(numbers ))
//        {
//            JOptionPane.showMessageDialog(null,
//                    "Password must have at least one number.",
//                    "Inane error",
//                    JOptionPane.ERROR_MESSAGE);
//            return false;
//        }
//
//        return true;
        //Uses logic paradigm
        return validatePassword(password);
    }

    private static boolean isValidUserID(String userID)
    {
        //Regular Expression for a valid Malaysian NRIC
//        return Pattern.compile("(([0-9]{2})(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01]))-([0-9]{2})-([0-9]{4})")
//                .matcher(userID).matches();
        //Uses logic paradigm
        return validateUserID(userID);
    }

    private static boolean isValidPhoneNum(String phoneNum)
    {
        //Regular Expression for a valid Malaysian phone number
        return Pattern.compile("\\+?6?(?:01[0-46-9]\\d{7,8}|0\\d{8})").matcher(phoneNum).matches();
    }

}
