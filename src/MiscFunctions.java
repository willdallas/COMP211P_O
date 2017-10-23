import static java.lang.System.out;

class MiscFunctions {

    static void clearScreen(String s) { //50 blank lines to clear the screen, and prints 's' beneath these blank lines

        final String ANSI_CLS = "\u001b[2J";
        final String ANSI_HOME = "\u001b[H";
        System.out.print(ANSI_CLS + ANSI_HOME);
        System.out.flush();

        out.print(s);

        /*for (int i = 0; i < 50; i++) {
            out.print("\n");
        }
        out.print(s);
        */
    }

}
