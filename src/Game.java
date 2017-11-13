import static java.lang.System.out;
import java.util.Scanner;

class Game {

    private int gameNumber;
    private Question[] questions;
    private User currentUser;
    private static Scanner scan = new Scanner(System.in);

    Game(User currentUserObject, Question[] randomizedQuestions) {
        this.currentUser = currentUserObject;
        this.gameNumber = currentUser.getUserNumber();
        this.questions = randomizedQuestions;
    }

    void start() {
        MiscFunctions.clearScreen("");
        out.print("\n\n\t-----Randomized questions-----\n\n\n");

        for (int i = 0; i < questions.length; i++) {
            out.println("\tQuestion: " + questions[i].getQuestion());
            out.println("\t\tWrong answer 1: " + questions[i].getIncorrectOne());
            out.println("\t\tWrong answer 2: " + questions[i].getIncorrectTwo());
            out.println("\t\tWrong answer 3: " + questions[i].getIncorrectThree());
            out.println("\t\tCorrect answer: " + questions[i].getCorrect());
            out.println();
        }

        out.print("\n\tPress enter to return to the Menu: ");
        scan.nextLine();
    }

    int getGameNumber() {
        return this.gameNumber;
    }
}
