import static java.lang.System.out;

class MiscFunctions {

    static void clearScreen(String s) { //50 blank lines to clear the screen
        for (int i = 0; i < 50; i++) {
            out.print("\n");
        }
        out.print(s);
    }

}
