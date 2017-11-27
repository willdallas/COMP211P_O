import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Scanner;

class GameManagement {

    private static Scanner scan = new Scanner(System.in);
    private static int numQuestionsInFile = FileManagement.getNumQuestions();
    private static String[] questionStrings = new String[numQuestionsInFile];
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
        String usedQuestionNumbers = ""; // Stores list of index numbers of questions already put into array

        for (int i = 0; i < QUESTIONS_PER_GAME; i++) {
            do {                // Finds a random (without replacement) integer corresponding to an entry in the questionStrings array
                randomInt = MiscFunctions.randomIntBetweenNumbers(0, numQuestionsInFile - 1);
                isQuestionNew = !usedQuestionNumbers.contains(Integer.toString(randomInt));
            } while (!isQuestionNew);

            randomizedQuestions[i] = createQuestionObject(randomInt);
            usedQuestionNumbers += randomInt + ",";
        }

        return randomizedQuestions;
    }

    private static Question createQuestionObject(int n) { // Returns a question object corresponding to a particular entry in the questionStrings array
        String question;
        ArrayList<String> answers = new ArrayList<String>();

        Scanner scanLine = new Scanner(questionStrings[n]).useDelimiter(",");

        question = scanLine.next();
        while (scanLine.hasNext()) {
            answers.add(scanLine.next());
        }

        return new Question(question, answers);
    }

    static void addQuestionString(int index, String aQuestionString) {
        questionStrings[index] = aQuestionString;
    }
}
