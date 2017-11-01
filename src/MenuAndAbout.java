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
                "* You need to register and login to play the game\n\t" +
                "* The program will display a word, and a list of other words\n\t" +
                "* You need find the word that is a synonym of the first\n\t" +
                "* You may skip a word if you are unsure of the answer\n\n\n");

        out.print("\tPress enter to return to the Menu: ");
        scan.nextLine();
    }
}
