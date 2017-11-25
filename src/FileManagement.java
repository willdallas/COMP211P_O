import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

class FileManagement {

    private static int answersPerQuestion = GameManagement.getAnswersPerQuestion();

    static void writeUsers() {
        PrintWriter printFile = null;
        PrintWriter clearFile = null;

        try {
            printFile = new PrintWriter(new FileOutputStream("userdata.txt", true));
            clearFile = new PrintWriter(new FileOutputStream("userdata.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Error writing to file.");
            System.exit(0);
        }

        clearFile.print("");
        clearFile.close();

        for (int i = 0; i < User.getUserCount(); i++) {
            printFile.println(UserManagement.getAUser(i));
        }
        printFile.close();
    }

    static void createUserArray() {
        String firstName;
        String lastName;
        String username;
        String password;
        int numGames;
        int totalScore;

        Scanner scanLine;

        for (int i = 0; i < getNumUsersInFile(); i++) {

            scanLine = new Scanner(readUser(i)).useDelimiter(",");

            firstName = scanLine.next();
            lastName = scanLine.next();
            username = scanLine.next();
            password = scanLine.next();
            numGames = scanLine.nextInt();
            totalScore = scanLine.nextInt();

            UserManagement.addAUser(i, new User(firstName, lastName, username, password, numGames, totalScore));
        }
    }

    static Question createQuestion(int n) { // Returns a question object corresponding to a particular line in the text file
        String question;
        ArrayList<String> answers = new ArrayList<String>(answersPerQuestion);

        Scanner scanLine = new Scanner(readQuestion(n)).useDelimiter(",");

        question = scanLine.next();
        for (int i = 0; i < answersPerQuestion; i++) {
            answers.add(i, scanLine.next());
        }

        return new Question(question, answers);
    }

    static int getNumUsersInFile() {
        Scanner scanUser = null;

        try {
            scanUser = new Scanner(new FileInputStream("userdata.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Error reading file.");
            System.exit(0);
        }

        int count = 0;

        while (scanUser.hasNextLine()) {
            scanUser.nextLine();
            count++;
        }
        scanUser.close();

        return count;
    }

    static int getNumQuestions() {
        Scanner scanQuestions = null;

        try {
            scanQuestions = new Scanner(new FileInputStream("questions.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Error reading file.");
            System.exit(0);
        }

        int count = 0;

        while (scanQuestions.hasNextLine()) {
            scanQuestions.nextLine();
            count++;
        }
        scanQuestions.close();

        return count;
    }

    private static String readUser(int fileUserNumber) {
        Scanner scanFile = null;

        try {
            scanFile = new Scanner(new FileInputStream("userdata.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Error reading file");
            System.exit(0);
        }

        for (int i = 0; i < fileUserNumber; i++) {
            scanFile.nextLine();
        }

        String userString = scanFile.nextLine();
        scanFile.close();

        return userString;
    }

    private static String readQuestion(int questionNumber) { //  Returns a line of text from the question.txt file
        Scanner scanFile = null;

        try {
            scanFile = new Scanner(new FileInputStream("questions.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Error reading file");
            System.exit(0);
        }

        for (int i = 0; i < questionNumber; i++) {
            scanFile.nextLine();
        }

        String questionString = scanFile.nextLine();
        scanFile.close();

        return questionString;
    }
}
