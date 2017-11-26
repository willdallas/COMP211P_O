import static java.lang.System.out;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        FileManagement.createUserArray();

        menu();

        FileManagement.writeUsers();
    }

    private static void menu() {
        Scanner scan = new Scanner(System.in);
        boolean exit = false;

        String text = "----------\nWelcome to the Word Game\n----------";

        while (!exit) {

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
                try {              // This prevents an exception when return key is pressed with no letter typed in
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
                    text = about();
                    break;
                case 'P':
                case 'p':
                    text = GameManagement.newGame();
                    break;
                default:
                    exit = true;
            }
        }
    }

    private static String about() {
        Scanner scan = new Scanner(System.in);

        MiscFunctions.clearScreen("");

        out.print("\n\n\tGame Instructions:\n\n\t" +
                "* You need to register and login to play the game\n\t" +
                "* The program will display a word, and a list of other words\n\t" +
                "* You need find the word that is a synonym of the first\n\t" +
                "* You may skip a word if you are unsure of the answer\n\n\n");

        out.print("\tPress enter to return to the Menu: ");
        scan.nextLine();

        return "\n";
    }
}