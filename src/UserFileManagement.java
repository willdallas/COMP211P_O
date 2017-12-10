import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

class UserFileManagement {

    static void writeUsers() {  // Run at the end of the game, to write the array of users to the file
        PrintWriter printFile = null;
        PrintWriter clearFile = null;

        try {
            printFile = new PrintWriter(new FileOutputStream("userdata.txt", true));
            clearFile = new PrintWriter(new FileOutputStream("userdata.txt"));
        } catch (FileNotFoundException e) {
            MiscFunctions.clearScreen();
            System.out.println("\n\tError writing to file.\n");
            System.exit(0);
        }

        clearFile.print(""); // Clears the old data from the file so it can be replaced with new user array
        clearFile.close();

        ArrayList<User> userObjects = UserManagement.getUserObjects();

        for (int i = 0; i < User.getUserCount(); i++) {
            printFile.println(userObjects.get(i)); // Uses the toString method in User class to print to file
        }
        printFile.close();
    }

    static void createUsers() { // Run at the start of the game, to create array of users from file data
        Scanner userFileScanner = getFileScanner();

        String firstName = null;
        String lastName = null;
        String username = null;
        String password = null;
        int numGames = 0;
        int totalScore = 0;
        Scanner scanLine;

        while (userFileScanner.hasNextLine()) {
            scanLine = new Scanner(userFileScanner.nextLine()).useDelimiter(",");

            try {  // Catches exception if to many entries in a line, or wrong data type given
                firstName = scanLine.next();
                lastName = scanLine.next();
                username = scanLine.next();
                password = scanLine.next();
                numGames = scanLine.nextInt();
                totalScore = scanLine.nextInt();
            } catch (NoSuchElementException e) {
                MiscFunctions.clearScreen();
                System.out.println("\n\tuserdata.txt file has invalid entries\n");
                System.exit(0);
            }

            UserManagement.addAUser(new User(firstName, lastName, username, password, numGames, totalScore));
        }
        userFileScanner.close();
    }

    private static Scanner getFileScanner() { // Returns Scanner object for above class to use, and deals with potential problems with the file
        Scanner userFileScanner = null;

        try {
            userFileScanner = new Scanner(new FileInputStream("userdata.txt"));
        } catch (FileNotFoundException e) {
            MiscFunctions.clearScreen();
            System.out.println("\n\tError reading userdata.txt file.\n");
            System.exit(0);
        }

        return userFileScanner;
    }
}
