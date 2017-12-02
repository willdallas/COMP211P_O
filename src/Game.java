import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Scanner;

class Game {

    private static int numQuestionsInFile = FileManagement.getNumQuestions();
    private static String[] questionStrings = new String[numQuestionsInFile];
    private static final int QUESTIONS_PER_GAME = 10;

    private User currentUser;  // Instance variables for each unique game
    private int questionsAnswered;
    private int questionsSkipped;
    private int currentScore;

    Game() {
        currentUser = UserManagement.getUserLoggedIn();
        questionsAnswered = 0;
        questionsSkipped = 0;
        currentScore = 0;
    }

    String newGame() {

        if (currentUser == null) {
            return "Please login to play the game.";
        }

        Question[] questions = createRandomizedQuestionArray();

        MiscFunctions.clearScreen();

        for (int i = 0; i < questions.length; i++) {

            MiscFunctions.clearScreen();
            out.println(MiscFunctions.getStringWithBorder("Question " + (i + 1)));
            displayQuestion(questions[i]);

        }

        displaySummary();
        currentUser.setNumGames(currentUser.getNumGames() + 1);
        User.setTotalGamesPlayed(User.getTotalGamesPlayed() + 1);
        currentUser.setTotalScore(currentUser.getTotalScore() + currentScore);

        return currentUser.getUsername() + " logged in.";
    }

    private void displayQuestion(Question aQuestion) {

        ArrayList<String> randomAnswerArray = aQuestion.getRandomAnswerArray();

        out.print("\n\tNew Word: " + aQuestion.getQuestion() + "\n");
        for (int i = 0; i < randomAnswerArray.size(); i++) {
            out.print("\n\t" + (i + 1) + ".  " + randomAnswerArray.get(i));
        }

        out.println("\n\n\tEnter '5' to skip the question.");
        out.print("\n\tEnter your answer: ");

        int userInput = MiscFunctions.takeIntInputBetweenInts(1, 5);

        int questionOutcome;
        if (userInput == 5) {
            questionsSkipped++;
            questionOutcome = 0;
        } else if (aQuestion.getCorrectAnswer().equals(randomAnswerArray.get(userInput - 1))) {
            currentScore++;
            questionsAnswered++;
            questionOutcome = 1;
        } else {
            questionsAnswered++;
            questionOutcome = -1;
        }

        displayFeedback(questionOutcome, aQuestion);
    }

    private void displayFeedback(int questionOutcome, Question aQuestion) {

        Scanner scan = new Scanner(System.in);

        String outcome = "";
        MiscFunctions.clearScreen();

        switch (questionOutcome) {
            case -1:
                outcome = "Incorrect";
                break;
            case 0:
                outcome = "Skipped";
                break;
            case 1:
                outcome = "Correct!";
                break;
        }

        out.println(MiscFunctions.getStringWithBorder(outcome));
        out.println("\n\t\"" + aQuestion.getCorrectAnswer() + "\" is the synonym of \"" + aQuestion.getQuestion() + "\"");
        out.println("\n\tQuestions answered: \t" + questionsAnswered + "/" + QUESTIONS_PER_GAME);
        out.println("\tQuestions skipped:  \t" + questionsSkipped + "/" + QUESTIONS_PER_GAME);
        out.println("\n\tCurrent score:      \t" + currentScore + "/" + QUESTIONS_PER_GAME);
        out.print("\n\n\n\tPress enter to continue: ");
        scan.nextLine();
    }

    private void displaySummary() {
        Scanner scan = new Scanner(System.in);

        MiscFunctions.clearScreen();
        out.println(MiscFunctions.getStringWithBorder("Game over!"));
        out.println("\n\tQuestions answered: \t" + questionsAnswered + "/" + QUESTIONS_PER_GAME);
        out.println("\tQuestions skipped:  \t" + questionsSkipped + "/" + QUESTIONS_PER_GAME);
        out.println("\n\tTotal score:      \t" + currentScore + "/" + QUESTIONS_PER_GAME);
        out.print("\n\n\n\n\n\tPress enter to continue: ");
        scan.nextLine();
    }

    private Question[] createRandomizedQuestionArray() {
        Question[] randomizedQuestions = new Question[QUESTIONS_PER_GAME];
        int randomInt;
        boolean isQuestionNew;
        String usedQuestionNumbers = ""; // Stores list of index numbers of questions already put into array

        for (int i = 0; i < QUESTIONS_PER_GAME; i++) {
            do
            {                // Finds a random (without replacement) integer corresponding to an entry in the questionStrings array
                randomInt = MiscFunctions.randomIntBetweenNumbers(0, numQuestionsInFile - 1);
                isQuestionNew = !usedQuestionNumbers.contains(Integer.toString(randomInt));
            } while (!isQuestionNew);

            randomizedQuestions[i] = createQuestionObject(randomInt);
            usedQuestionNumbers += randomInt + ",";
        }
        return randomizedQuestions;
    }

    private Question createQuestionObject(int n) { // Returns a question object corresponding to a particular entry in the questionStrings array
        String question;
        ArrayList<String> answers = new ArrayList<String>();

        Scanner scanLine = new Scanner(questionStrings[n]).useDelimiter(",");

        question = scanLine.next();
        while (scanLine.hasNext()) {
            answers.add(scanLine.next());
        }
        return new Question(question, answers);
    }

    static int getQuestionsPerGame() {
        return QUESTIONS_PER_GAME;
    }

    static void addQuestionString(int index, String aQuestionString) {
        questionStrings[index] = aQuestionString;
    }
}
