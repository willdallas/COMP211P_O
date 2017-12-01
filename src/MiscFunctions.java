import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    static String formatThreeColumnTable(String[] colOne, String[] colTwo, String[] colThree) {
        int colOneWidth = 0;
        int colTwoWidth = 0;
        int colThreeWidth = 0;
        String table = "";

        for (String anEntry : colOne) {
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

        for (int i = 0; i < colOne.length; i++) {
            table += colOne[i];
            for (int j = colOne[i].length(); j < colOneWidth; j++) {
                table += " ";
            }
            table += "  " + colTwo[i];
            for (int j = colTwo[i].length(); j < colTwoWidth; j++) {
                table += " ";
            }
            table += "  " + colThree[i];
            for (int j = colThree[i].length(); j < colThreeWidth; j++) {
                table += " ";
            }
            table += "\n";
        }

        return table;

    }
}