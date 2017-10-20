import java.util.Scanner;

import static java.lang.System.out;

class MenuAndOtherText {

    private static Scanner scan = new Scanner(System.in).useDelimiter("\n");

    static void menu(String text) {

        MiscFunctions.clearScreen(text + "\n");  //When this method is called, text can be passed through to be displayed before the menu

        out.print("\tLogin (L)\n" +
                "\tRegister (R)\n" +
                "\tAbout (A)\n" +
                "\tPlay the Game (P)\n" +
                "\tShow the Leader Board (B)\n" +
                "\tQuit (Q)\n\n" +
                "\tPlease choose an option: ");

        String userChoice = scan.next();

        if (userChoice.equals("L") || userChoice.equals("l")) {
            UserManagement.login();
        } else if (userChoice.equals("R") || userChoice.equals("r")) {
            UserManagement.register();
        } else if (userChoice.equals("A") || userChoice.equals("a")) {
            about();
        } else if (userChoice.equals("P") || userChoice.equals("p")) {
            Game.start();
        }

    }

    private static void about() {

        MiscFunctions.clearScreen("");

        out.print("\n\n\tGame Instructions:\n\n\t" +
                "1)...................\n\t" +
                "2)...................\n\t" +
                "3)...................\n\t" +
                "4)...................\n\n\n\n");

        out.print("Press any key to return to the Menu: ");
        scan.next();

        menu("");
    }

}
