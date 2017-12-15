import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

class QuestionFileManagement {

    static void createQuestions() {  // Runs at start of game to create array of strings from the lines of question.txt file
        Scanner questionFileScanner = getFileScanner();

        ArrayList<String> answers;
        String question;
        Scanner scanLine;

        while (questionFileScanner.hasNextLine()) {
            scanLine = new Scanner(questionFileScanner.nextLine()).useDelimiter(",");
            answers = new ArrayList<>();
            question = null;

            try {  // Catches exception if to many entries in a line, or wrong data type given
                question = scanLine.next();
                for (int i = 0; i < 4; i++) {
                    answers.add(scanLine.next());
                }
            } catch (NoSuchElementException e) {
                MiscFunctions.clearScreen();
                System.out.println("\n\tquestions.txt file has invalid entries\n");
                System.exit(0);
            }

            Game.addAQuestion(new Question(question, answers));
        }
    }

    private static Scanner getFileScanner() {  // Returns Scanner object for above class to use, and deals with potential problems with the file
        Scanner questionFileScanner = null;

        try {
            questionFileScanner = new Scanner(new FileInputStream("questions.txt"));
        } catch (FileNotFoundException e) {
            MiscFunctions.clearScreen();
            System.out.println("\n\tError reading file\n");
            System.exit(0);
        }

        if (!questionFileScanner.hasNext()) {
            MiscFunctions.clearScreen();
            System.out.println("\n\tquestions.txt file is empty\n");
            System.exit(0);
        }

        return questionFileScanner;
    }
}
