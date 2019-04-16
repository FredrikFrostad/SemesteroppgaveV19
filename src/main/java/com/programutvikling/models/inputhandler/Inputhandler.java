package com.programutvikling.models.inputhandler;

import com.programutvikling.models.exceptions.InvalidEmailException;
import com.programutvikling.models.exceptions.InvalidNameFormatException;

public class Inputhandler {

    /**
     * Method for validating the formatting of an Email adress.
     * @param email String containing the email adress to be verified
     * @return True if the parameter String passes all checks
     * @throws InvalidEmailException Exception is thrown when the parameter String is illegally formatted
     */
    public static boolean checkValidEmailFormat(String email) throws InvalidEmailException {
        String[] splitAlpha = email.split("@");
        if (splitAlpha.length != 2) {
            throw new InvalidEmailException("Not a valid email adress, does not contain @");
        }
        if (!splitAlpha[1].contains(".") || splitAlpha[1].contains("..") || splitAlpha[0].contains("..")) {
            throw new InvalidEmailException("Not a vaild email adress");
        }
        return true;
    }

    public static boolean checkValidNameFormat(String name) throws InvalidNameFormatException {
        if (name.matches("[0-9]")) {
            throw new InvalidNameFormatException("Numbers or special characters are not allowed here");
        }

        return true;
    }
}
