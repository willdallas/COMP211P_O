import static java.lang.System.out;
import java.util.Scanner;

class GameManagement {

    private static Scanner scan = new Scanner(System.in);
    private static int numQuestionsInFile = FileManagement.getNumQuestions();
    //   static Question[] questionObjects = new Question[numQuestionsInFile - 1];
    private static final int QUESTIONS_PER_GAME = 5;

    static String newGame(User currentUser) {
        String returnString;

        if (currentUser != null) {
            startGame(currentUser, createRandomizedQuestionArray());
            returnString = "";
        } else {
            returnString = "----------\nPlease login to play the game.\n----------\n";
        }
        return returnString;
    }

    private static void startGame(User currentUser, Question[] questions) {
        MiscFunctions.clearScreen("");
        out.print("\n\n\t-----Randomized questions-----\n\n\n");

        for (int i = 0; i < questions.length; i++) {
            out.println(questions[i] + "\n");
        }

        out.print("\n\tPress enter to return to the Menu: ");
        scan.nextLine();
    }

    private static Question[] createRandomizedQuestionArray() {
        Question[] randomizedQuestions = new Question[QUESTIONS_PER_GAME];
        int randomInt;
        boolean isQuestionNew;
        String oldQuestions = "";

        for (int i = 0; i < QUESTIONS_PER_GAME; i++) {

            do {
                randomInt = MiscFunctions.randomIntBetweenNumbers(0, numQuestionsInFile - 1);
                isQuestionNew = !oldQuestions.contains(Integer.toString(randomInt));
            } while (!isQuestionNew);

            randomizedQuestions[i] = new Question(FileManagement.createQuestion(randomInt));
            oldQuestions += randomInt + ",";
        }

        return randomizedQuestions;
    }
}
