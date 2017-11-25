import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static java.lang.System.out;

class MiscFunctions { //  This class is designed to be expanded throughout the project with functions useful to multiple classes

    static void clearScreen(String s) { //  Clears the screen, and prints "s" after
        final String ANSI_CLS = "\u001b[2J";
        final String ANSI_HOME = "\u001b[H";
        System.out.print(ANSI_CLS + ANSI_HOME);
        System.out.flush();

        out.print(s);
    }

    static int randomIntBetweenNumbers(int lowerInt, int upperInt) {
        int randomInt;

        if (lowerInt < upperInt) {
            randomInt = (int) (Math.random() * (upperInt - lowerInt) + lowerInt);
        } else {
            randomInt = 0;
        }
        return randomInt;
    }

    static String hashString(String textInput) {
        String hashedOutput = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(textInput.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            hashedOutput = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.exit(0);
        }
        return hashedOutput;
    }
}
