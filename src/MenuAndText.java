import java.util.Scanner;

import static java.lang.System.out;

class MenuAndText {

    private static Scanner scan = new Scanner(System.in);

    static void menu(String text){

        MiscFunctions.clearScreen();

        out.println(text + "\n");

        out.print("\tLogin (L)\n" +
                "\tRegister (R)\n" +
                "\tAbout (A)\n" +
                "\tPlay the Game (P)\n" +
                "\tShow the Leader Board (B)\n" +
                "\tQuit (Q)\n\n" +
                "\tPlease choose an option: ");

        String userChoice = scan.next();

        if (userChoice.equals("L") || userChoice.equals("l")) UserManagement.login();
        if (userChoice.equals("R") || userChoice.equals("r")) UserManagement.register("");
        if (userChoice.equals("A") || userChoice.equals("a")) about();
        if (userChoice.equals("P") || userChoice.equals("p")) Game.start();


    }

    private static void about(){

        MiscFunctions.clearScreen();

        out.print("\n\n\tGame Instructions:\n\n\t" +
                "1)...................\n\t" +
                "2)...................\n\t" +
                "3)...................\n\t" +
                "4)...................\n\n\n\n");


    }

}
