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
}
