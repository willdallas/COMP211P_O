import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.out;

class MiscFunctions {

    static void clearScreen() { //  Clears the screen
        System.out.print("\u001b[2J" + "\u001b[H");
        System.out.flush();
    }

    static int randomIntBetweenNumbers(int min, int max) {
        Random random = new Random();
        return max > min ? random.nextInt((max - min) + 1) + min : 0;
    }

    static String hashString(String textInput) { // Hashes string input. Used to secure password storage.
        String hashedOutput = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(textInput.getBytes());
            byte[] bytes = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {  //  Converts bytes to hexadecimal for easier storage in file
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            hashedOutput = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.exit(0);
        }
        return hashedOutput;
    }

    static char takeCharInput() {
        Scanner scan = new Scanner(System.in);
        char userInput;

        while (true) {
            try {              // This prevents an exception when return key is pressed with no letter typed in
                userInput = scan.nextLine().charAt(0);
                return userInput;
            } catch (StringIndexOutOfBoundsException e) {
                out.print("\tInvalid input - please try again: ");
            }
        }
    }

    static int takeIntInputBetweenInts(int min, int max) {
        Scanner scan = new Scanner(System.in);
        char userInputChar;
        int userInputInt;

        while (true) {
            try {
                userInputChar = scan.nextLine().charAt(0);
                userInputInt = Integer.parseInt("" + userInputChar);
                if (userInputInt >= min && userInputInt <= max) {
                    return userInputInt;
                } else {
                    out.print("\tInvalid input - please try again: ");
                }
            } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
                out.print("\tInvalid input - please try again: ");
            }
        }
    }

    static String getStringWithBorder(String aString, boolean tab) {
        if (aString.equals("")) {
            return "";
        }
        String upperBorder = tab ? "\t╭" : "╭";
        String lowerBorder = tab ? "\t╰" : "╰";
        for (int i = 0; i < aString.length() + 2; i++) {
            upperBorder += "─";
            lowerBorder += "─";
        }
        upperBorder += "╮";
        lowerBorder += "╯";
        aString = "│ " + aString + " │";
        return upperBorder + "\n" + (tab ? "\t" : "") + aString + "\n" + lowerBorder;
    }
}