import java.io.*;
import java.util.Scanner;

class FileManagement {

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

        Scanner userFileScanner = null;
        try {
            userFileScanner = new Scanner(new FileInputStream("userdata.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Error reading file.");
            System.exit(0);
        }

        int index = 0;
        Scanner scanLine;

        while (userFileScanner.hasNextLine()) {
            scanLine = new Scanner(userFileScanner.nextLine()).useDelimiter(",");

            firstName = scanLine.next();
            lastName = scanLine.next();
            username = scanLine.next();
            password = scanLine.next();
            numGames = scanLine.nextInt();
            totalScore = scanLine.nextInt();

            UserManagement.addAUser(index, new User(firstName, lastName, username, password, numGames, totalScore));
            index++;
        }
        userFileScanner.close();
    }

    static void createQuestionStringArray() {  // Runs at start of game to create array of strings from the lines of question.txt file
        Scanner scanFile = null;

        try {
            scanFile = new Scanner(new FileInputStream("questions.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Error reading file");
            System.exit(0);
        }

        int index = 0;
        while (scanFile.hasNextLine()) {
            Game.addQuestionString(index, scanFile.nextLine());
            index++;
        }
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
}
