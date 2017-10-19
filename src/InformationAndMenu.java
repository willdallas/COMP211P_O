import java.util.Scanner;

import static java.lang.System.out;

class InformationAndMenu {

    private static Scanner scan = new Scanner(System.in);

    static void menu(){

        out.println("\nWelcome to the Word Game");
        out.println("\tLogin (L)");
        out.println("\tRegister (R)");
        out.println("\tAbout (A)");
        out.println("\tPlay the Game (P)");
        out.println("\tShow the Leader Board (B)");
        out.println("\tQuit (Q)\n");
        out.print("\tPlease choose an option: ");

        String userChoice = scan.next();

        if (userChoice.equals("L") || userChoice.equals("l")) UserManagement.login();
        if (userChoice.equals("R") || userChoice.equals("r")) UserManagement.register();
        if (userChoice.equals("A") || userChoice.equals("a")) about();
        if (userChoice.equals("P") || userChoice.equals("p")) Game.game();
        else System.exit(0);

    }

    static void about(){

        out.print("\n\n\tGame Instructions:\n\n\t" +
                "1)...................\n\t" +
                "2)...................\n\t" +
                "3)...................\n\t" +
                "4)...................\n\t");


    }

}
