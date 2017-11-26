import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

class FileManagement {

    private static int numUsers = getNumUsersInFile();

    static void writeUsers() {  // Run at the end of the game, to write the array of users to the file
        PrintWriter printFile = null;
        PrintWriter clearFile = null;

        try {
            printFile = new PrintWriter(new FileOutputStream("userdata.txt", true));
            clearFile = new PrintWriter(new FileOutputStream("userdata.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Error writing to file.");
            System.exit(0);
        }

        clearFile.print(""); // Clears the old data from the file so it can be replaced with new user array
        clearFile.close();

        for (int i = 0; i < User.getUserCount(); i++) {
            printFile.println(UserManagement.getAUser(i)); // Uses the toString method in User class to print to file
        }
        printFile.close();
    }

    static void createUserArray() { // Run at the start of the game, to create array of users from file data
        String firstName;
        String lastName;
        String username;
        String password;
        int numGames;
        int totalScore;

        Scanner scanLine;

        for (int i = 0; i < numUsers; i++) {

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
        ArrayList<String> answers = new ArrayList<String>();

        Scanner scanLine = new Scanner(readQuestion(n)).useDelimiter(",");

        question = scanLine.next();
        while (scanLine.hasNext()) {
            answers.add(scanLine.next());
        }

        return new Question(question, answers);
    }

    static int getNumUsersInFile() {
        Scanner userFileScanner = null;
        try {
            userFileScanner = new Scanner(new FileInputStream("userdata.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Error reading file.");
            System.exit(0);
        }

        int count = 0;

        while (userFileScanner.hasNextLine()) {
            userFileScanner.nextLine();
            count++;
        }
        userFileScanner.close();

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

    private static String readUser(int fileUserNumber) { // Returns a String containing a single line from the userdata.txt file
        Scanner userFileScanner = null;
        try {
            userFileScanner = new Scanner(new FileInputStream("userdata.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Error reading file.");
            System.exit(0);
        }

        for (int i = 0; i < fileUserNumber; i++) {
            userFileScanner.nextLine();
        }

        String userString = userFileScanner.nextLine();
        userFileScanner.close();

        return userString;
    }

    private static String readQuestion(int questionNumber) { // Returns a String containing a single line of text from the question.txt file
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
