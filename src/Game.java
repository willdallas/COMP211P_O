import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Scanner;

class Game {

    private static ArrayList<Question> questionObjects = new ArrayList<>();
    private static final int QUESTIONS_PER_GAME = 10;

    private User currentUser;  // Instance variables used for each unique game
    private int questionsAnswered;
    private int questionsSkipped;
    private double timeTaken;
    private int currentScore;

    Game() {
        currentUser = UserManagement.getUserLoggedIn();
        questionsAnswered = 0;
        questionsSkipped = 0;
        currentScore = 0;
        timeTaken = 0;
    }

    void newGame() { // Checks if a user is logged in, and if so, shows the quiz.
        Scanner scan = new Scanner(System.in);

        MiscFunctions.clearScreen();
        if (currentUser == null) {
            out.println(MiscFunctions.getStringWithBorder("Please login to play"));
            out.print("\n\n\tPress enter to return to the menu: ");
            scan.nextLine();
        } else {
            Question[] questions = createRandomizedQuestionArray();
            for (int i = 0; i < questions.length; i++) {
                MiscFunctions.clearScreen();
                out.println(MiscFunctions.getStringWithBorder("Question " + (i + 1)));
                displayQuestion(questions[i]);
            }

            displaySummary();
            currentUser.setNumGames(currentUser.getNumGames() + 1);
            currentUser.setTotalScore(currentUser.getTotalScore() + currentScore);
            currentUser.setTotalTimeTaken(currentUser.getTotalTimeTaken() + timeTaken);
        }
    }

    private void displayQuestion(Question aQuestion) {
        ArrayList<String> randomAnswerArray = aQuestion.getRandomAnswerArray();

        out.print("\n\tNew Word: " + aQuestion.getQuestion() + "\n");
        for (int i = 0; i < randomAnswerArray.size(); i++) {
            out.print("\n\t" + (i + 1) + ".  " + randomAnswerArray.get(i));
        }

        out.println("\n\n\tEnter '5' to skip the question.");
        out.print("\n\tEnter your answer: ");

        double startTime = (double) System.currentTimeMillis() / 1000; // Used to calculate the time taken for a player to answer
        int userInput = MiscFunctions.takeIntInputBetweenInts(1, 5);
        double endTime = (double) System.currentTimeMillis() / 1000;
        timeTaken += endTime - startTime;

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

        displayFeedback(questionOutcome, aQuestion, endTime - startTime);
    }

    private void displayFeedback(int questionOutcome, Question aQuestion, double questionTimeTaken) {
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
                outcome = "Correct! Well done!";
                break;
        }

        out.println(MiscFunctions.getStringWithBorder(outcome));

        out.println("\n\t\"" + aQuestion.getCorrectAnswer() + "\" is the synonym of \"" + aQuestion.getQuestion() + "\"");
        out.println("\n\tQuestions answered: \t" + questionsAnswered + "/" + QUESTIONS_PER_GAME);
        out.println("\tQuestions skipped:  \t" + questionsSkipped + "/" + QUESTIONS_PER_GAME);
        out.println("\n\tTime taken: \t\t" + String.format("%.2f", questionTimeTaken) + " seconds");
        out.println("\tCurrent score:      \t" + currentScore + "/" + QUESTIONS_PER_GAME);

        out.print("\n\n\n\tPress enter to continue: ");
        scan.nextLine();
    }

    private void displaySummary() {
        Scanner scan = new Scanner(System.in);

        MiscFunctions.clearScreen();
        out.println(MiscFunctions.getStringWithBorder("Game over!"));

        out.println("\n\tQuestions answered:\t" + questionsAnswered + "/" + QUESTIONS_PER_GAME);
        out.println("\tQuestions skipped:\t" + questionsSkipped + "/" + QUESTIONS_PER_GAME);
        out.println("\n\tAvg. time per question:\t" + String.format("%.2f", timeTaken / (QUESTIONS_PER_GAME)) + "seconds");
        out.println("\tTotal score:\t" + currentScore + "/" + QUESTIONS_PER_GAME);

        out.print("\n\n\n\tPress enter to continue: ");
        scan.nextLine();
    }

    private Question[] createRandomizedQuestionArray() {
        Question[] randomizedQuestions = new Question[QUESTIONS_PER_GAME];
        int randomInt;
        boolean isQuestionNew;
        String usedQuestionNumbers = ""; // Stores list of index numbers of questions already put into array

        for (int i = 0; i < QUESTIONS_PER_GAME; i++) {  // Finds a random (without replacement) integer corresponding to an entry in the questionObjects array
            do {
                randomInt = MiscFunctions.randomIntBetweenNumbers(0, questionObjects.size() - 1);
                isQuestionNew = !usedQuestionNumbers.contains(Integer.toString(randomInt));
            } while (!isQuestionNew);

            randomizedQuestions[i] = questionObjects.get(randomInt);
            usedQuestionNumbers += randomInt + ",";
        }
        return randomizedQuestions;
    }

    static void addAQuestion(Question aQuestion) {
        questionObjects.add(aQuestion);
    }

    static int getQuestionsPerGame() {
        return QUESTIONS_PER_GAME;
    }
}
