import java.util.Scanner;

import static java.lang.System.out;

public class MenuAndOtherText {

    private Scanner scan = new Scanner(System.in).useDelimiter("\n");

    public void menu(String text) {

        MiscFunctions.clearScreen(text + "\n");

        out.print("\tLogin (L)\n" +
                "\tRegister (R)\n" +
                "\tAbout (A)\n" +
                "\tPlay the Game (P)\n" +
                "\tShow the Leader Board (B)\n" +
                "\tQuit (Q)\n\n" +
                "\tPlease choose an option: ");

        char userChoice = scan.next().charAt(0);

        UserManagement userManagement = new UserManagement();


        switch (userChoice) {
            case 'L':
            case 'l':
                userManagement.login();
                break;
            case 'R':
            case 'r':
                userManagement.register();
                break;
            case 'A':
            case 'a':
                about();
                break;
            case 'P':
            case 'p':
                Game game = new Game();
                game.start();
        }

    }

    private void about() {

        MiscFunctions.clearScreen("");

        out.print("\n\n\tGame Instructions:\n\n\t" +
                "1)...................\n\t" +
                "2)...................\n\t" +
                "3)...................\n\t" +
                "4)...................\n\n\n\n");

        out.print("Press enter to return to the Menu: ");
        scan.next();

        menu("");
    }

}
