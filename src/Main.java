import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        FileManagement.createUserArray();
        FileManagement.createQuestionStringArray();

        menu();

        FileManagement.writeUsers();
    }

    private static void menu() {
        boolean exit = false;

        String text = MiscFunctions.getStringWithBorder("Welcome to the Word Game!");

        while (!exit) {

            MiscFunctions.clearScreen(text + "\n\n");

            out.print("\tLogin (L)\n" +
                    "\tRegister (R)\n" +
                    "\tAbout (A)\n" +
                    "\tPlay the Game (P)\n" +
                    "\tShow the Leader Board (B)\n" +
                    "\tQuit (Q)\n\n" +
                    "\tPlease choose an option: ");

            char userChoice = MiscFunctions.takeCharInput();

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
                    text = new Game().newGame();
                    break;
                case 'B':
                case 'b':
                    text = leaderBoard();
                    break;
                case 'Q':
                case 'q':
                    exit = true;
                    break;
            }
        }
    }

    private static String about() {
        Scanner scan = new Scanner(System.in);

        MiscFunctions.clearScreen("");

        out.print(MiscFunctions.getStringWithBorder("Game Instructions:") +
                "\n\n\t* You need to register and login to play the game\n\t" +
                "* The program will display a word, and a list of other words\n\t" +
                "* You need find the word that is a synonym of the first\n\t" +
                "* You may skip a word if you are unsure of the answer\n\n\n");

        out.print("\tPress enter to return to the Menu: ");
        scan.nextLine();

        return "\n";
    }

    private static String leaderBoard() {
        Scanner scan = new Scanner(System.in);
        ArrayList<User> orderedUsersArray = UserManagement.getUserObjects();
        MiscFunctions.clearScreen("");

        Collections.sort(orderedUsersArray, Collections.<User>reverseOrder()); // Orders array by percentage correct, using compareTo() method in User

        out.print(MiscFunctions.getStringWithBorder("Leader Board"));
        out.print("\n\n\tPercentage of words correct\n");

        for (int i = 0; i < orderedUsersArray.size(); i++) {
            if (orderedUsersArray.get(i).getNumGames() > 0) {
                out.println();
                out.format("%-30s%3d%1s", "\t" + orderedUsersArray.get(i).getFirstName() + " (" + orderedUsersArray.get(i).getUsername() + ")", orderedUsersArray.get(i).getPercentageCorrect(), "%");
            }
        }

        out.print("\n\n\tPress enter to return to the Menu: ");
        scan.nextLine();

        return "";
    }
}