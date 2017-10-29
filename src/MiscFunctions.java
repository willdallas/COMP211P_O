import static java.lang.System.out;

public class MiscFunctions {

    public static void clearScreen(String s) { //Clears the screen, and prints "s" after

        final String ANSI_CLS = "\u001b[2J";
        final String ANSI_HOME = "\u001b[H";
        System.out.print(ANSI_CLS + ANSI_HOME);
        System.out.flush();

        out.print(s);

    }

}
