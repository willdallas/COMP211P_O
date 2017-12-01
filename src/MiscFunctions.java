import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.out;

class MiscFunctions { //  This class is designed to be expanded throughout the project with functions useful to multiple classes

    static void clearScreen(String s) { //  Clears the screen, and prints "s" after
        final String ANSI_CLS = "\u001b[2J";
        final String ANSI_HOME = "\u001b[H";
        System.out.print(ANSI_CLS + ANSI_HOME);
        System.out.flush();
        out.print(s);
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
            } catch (StringIndexOutOfBoundsException e) {
                out.print("\tInvalid input - please try again: ");
            } catch (NumberFormatException e) {
                out.print("\tInvalid input - please try again: ");
            }
        }
    }

    static String getStringWithBorder(String aString) {
        String border = "";
        for (int i = 0; i < aString.length(); i++) {
            border += "-";
        }
        return border + "\n" + aString + "\n" + border;
    }

    static String formatFourColumnTable(ArrayList<ArrayList<String>> tableArray) { // Used to dynamically format a table based on the lengths of entries
        int colOneWidth = 0;
        int colTwoWidth = 0;
        int colThreeWidth = 0;
        int colFourWidth = 0;
        String table = "";

        ArrayList<String> colOne = tableArray.get(0);
        ArrayList<String> colTwo = tableArray.get(1);
        ArrayList<String> colThree = tableArray.get(2);
        ArrayList<String> colFour = tableArray.get(3);

        for (String anEntry : colOne) {  // Sets length of longest String in column to colOneWidth
            if (anEntry.length() > colOneWidth) {
                colOneWidth = anEntry.length();
            }
        }
        for (String anEntry : colTwo) {
            if (anEntry.length() > colTwoWidth) {
                colTwoWidth = anEntry.length();
            }
        }
        for (String anEntry : colThree) {
            if (anEntry.length() > colThreeWidth) {
                colThreeWidth = anEntry.length();
            }
        }
        for (String anEntry : colFour) {
            if (anEntry.length() > colFourWidth) {
                colFourWidth = anEntry.length();
            }
        }

        for (int i = 0; i < colOne.size(); i++) {
            table += colOne.get(i);
            for (int j = colOne.get(i).length(); j < colOneWidth; j++) {  // Adds spaces to each entry such that the length of the String equals colOneWidth
                table += " ";
            }
            table += "  " + colTwo.get(i);
            for (int j = colTwo.get(i).length(); j < colTwoWidth; j++) {
                table += " ";
            }
            table += "  " + colThree.get(i);
            for (int j = colThree.get(i).length(); j < colThreeWidth; j++) {
                table += " ";
            }
            table += "  " + colFour.get(i);
            for (int j = colFour.get(i).length(); j < colFourWidth; j++) {
                table += " ";
            }
            table += "\n";
        }

        return table;

    }
}