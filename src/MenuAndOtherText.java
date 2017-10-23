import java.util.Scanner;
import static java.lang.System.out;

class MenuAndOtherText {

    private Scanner scan = new Scanner(System.in).useDelimiter("\n");

    void menu(String text) {

        MiscFunctions.clearScreen(text + "\n");  //When this method is called, text can be passed through to be displayed before the menu

        out.print("\tLogin (L)\n" +
                "\tRegister (R)\n" +
                "\tAbout (A)\n" +
                "\tPlay the Game (P)\n" +
                "\tShow the Leader Board (B)\n" +
                "\tQuit (Q)\n\n" +
                "\tPlease choose an option: ");

        char userChoice = scan.next().charAt(0);

        UserManagement userManagement = new UserManagement();
        Game game = new Game();

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
