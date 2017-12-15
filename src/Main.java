import static java.lang.System.out;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        UserFileManagement.createUsers();
        QuestionFileManagement.createQuestions();

        menu();
        out.println();

        UserFileManagement.writeUsers();
    }

    private static void menu() {
        boolean exit = false;
        String menuText;

        while (!exit) {

            if (UserManagement.getUserLoggedIn() != null) {
                menuText = "Player logged in: " + UserManagement.getUserLoggedIn().getUsername() + " │ Total score: " + UserManagement.getUserLoggedIn().getTotalScore();
            } else {
                menuText = "Welcome to the Word Game!";
            }

            MiscFunctions.clearScreen();
            out.print(MiscFunctions.getStringWithBorder(menuText) + "\n\n");

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
                    UserManagement.login();
                    break;
                case 'R':
                case 'r':
                    UserManagement.register();
                    break;
                case 'A':
                case 'a':
                    about();
                    break;
                case 'P':
                case 'p':
                    new Game().newGame();
                    break;
                case 'B':
                case 'b':
                    LeaderBoard.leaderBoard();
                    break;
                case 'Q':
                case 'q':
                    exit = true;
                    break;
            }
        }
    }

    private static void about() {
        Scanner scan = new Scanner(System.in);

        MiscFunctions.clearScreen();

        out.println(MiscFunctions.getStringWithBorder("Game Instructions:") +
                "\n\n\t* You need to register and login to play the game\n\t" +
                "* The program will display a word, and a list of other words\n\t" +
                "* You need find the word that is a synonym of the first\n\t" +
                "* You may skip a word if you are unsure of the answer");

        out.print("\n\n\tPress enter to return to the menu: ");
        scan.nextLine();
    }
}