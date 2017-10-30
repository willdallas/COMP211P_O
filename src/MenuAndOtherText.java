import java.util.Scanner;

import static java.lang.System.out;

class MenuAndOtherText {

    private static Scanner scan = new Scanner(System.in);

    static void menu(String text) {

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
            try {
                userChoice = scan.nextLine().charAt(0);
                choiceSuccess = true;
            } catch (StringIndexOutOfBoundsException e) {
                out.print("\tInvalid input - please try again: ");
            }
        }

        UserManagement aUserManagement = new UserManagement();
        GameManagement aGameManagement = new GameManagement();

        switch (userChoice) {
            case 'L':
            case 'l':
                aUserManagement.login();
                break;
            case 'R':
            case 'r':
                aUserManagement.register();
                break;
            case 'A':
            case 'a':
                about();
                break;
            case 'P':
            case 'p':
                aGameManagement.newGame(UserManagement.getLastUserLoggedIn());
        }

    }

    private static void about() {

        MiscFunctions.clearScreen("");

        out.print("\n\n\tGame Instructions:\n\n\t" +
                "1)...................\n\t" +
                "2)...................\n\t" +
                "3)...................\n\t" +
                "4)...................\n\n\n\n");

        out.print("Press enter to return to the Menu: ");
        scan.nextLine();

        menu("");
    }

}
