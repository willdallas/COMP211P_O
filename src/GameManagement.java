import static java.lang.System.out;

import java.util.Scanner;

class GameManagement {

    private static Scanner scan = new Scanner(System.in);
    private static int numQuestionsInFile = FileManagement.getNumQuestions();
    private static final int QUESTIONS_PER_GAME = 10;

    static String newGame() {

        User currentUser = UserManagement.getUserLoggedIn();

        if (currentUser == null) {
            return "----------\nPlease login to play the game.\n----------";
        }

        Question[] questions = createRandomizedQuestionArray();

        MiscFunctions.clearScreen("");

        for (int i = 0; i < questions.length; i++) {
            MiscFunctions.clearScreen("");
            out.println("----------\nQuestion " + (i+1) + "\n----------\n");
            out.println(questions[i].toStringRandomized());

            if (i != questions.length - 1) {
                out.print("\n\tPress enter for next question: ");
                scan.nextLine();
            }
        }

        out.print("\n\tPress enter to return to the menu: ");
        scan.nextLine();
        return "\n";
    }

    private static Question[] createRandomizedQuestionArray() {
        Question[] randomizedQuestions = new Question[QUESTIONS_PER_GAME];
        int randomInt;
        boolean isQuestionNew;
        String usedQuestionNumbers = "";

        for (int i = 0; i < QUESTIONS_PER_GAME; i++) {
            do {        // Finds a unique random integer corresponding to a line in the questions.txt file
                randomInt = MiscFunctions.randomIntBetweenNumbers(0, numQuestionsInFile - 1);
                isQuestionNew = !usedQuestionNumbers.contains(Integer.toString(randomInt));
            } while (!isQuestionNew);

            randomizedQuestions[i] = FileManagement.createQuestion(randomInt);
            usedQuestionNumbers += randomInt + ",";
        }

        return randomizedQuestions;
    }
}
