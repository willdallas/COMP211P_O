import java.util.Scanner;

import static java.lang.System.out;

class MenuAndAbout {

    private static Scanner scan = new Scanner(System.in);

    static void menu() {

        String text = "Welcome to the Word Game";

        while (!text.equals("EXIT")) {

            MiscFunctions.clearScreen(text + "\n");

            out.print("\tLogin (L)\n" +
                    "\tRegister (R)\n" +
                    "\tAbout (A)\n" +
                    "\tPlay the Game (P)\n" +
                    "\tShow the Leader Board (B)\n" +
                    "\tQuit (Q)\n\n" +
                    "\tPlease choose an option: ");

            boolean choiceSuccess = false;
            char userChoice = 0;

            while (!choiceSuccess) {
                try {                       // This prevents an exception when return key is pressed with no letter typed in
                    userChoice = scan.nextLine().charAt(0);
                    choiceSuccess = true;
                } catch (StringIndexOutOfBoundsException e) {
                    out.print("\tInvalid input - please try again: ");
                }
            }

            switch (userChoice) {
                case 'L':
                case 'l':
                    text = UserManagement.login();
                    break;
                case 'R':
                case 'r':
                    text = UserManagement.register();
                    break;
                case 'A':
                case 'a':
                    about();
                    break;
                case 'P':
                case 'p':
                    text = GameManagement.newGame(UserManagement.getLastUserLoggedIn());
                    break;
                default:
                    text = "EXIT";
            }
        }
    }

    private static void about() {

        MiscFunctions.clearScreen("");

        out.print("\n\n\tGame Instructions:\n\n\t" +
                "1)...................\n\t" +
                "2)...................\n\t" +
                "3)...................\n\t" +
                "4)...................\n\n\n");

        out.print("\tPress enter to return to the Menu: ");
        scan.nextLine();

    }

}
