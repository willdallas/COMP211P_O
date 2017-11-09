import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.Scanner;

class FileManagement {

    static void writeNewUser(String firstName, String lastName, String username, String password) {

        PrintWriter writeUser = null;

        try {
            writeUser = new PrintWriter(new FileOutputStream("userdata.txt", true));
        } catch (FileNotFoundException e){
            System.out.println("Error writing to file.");
            System.exit(0);
        }

        writeUser.println(firstName + "," + lastName + "," + username + "," + password + ",0,0,");
        writeUser.close();
    }

    static int getNumUsersInFile() {

        Scanner readUser = null;

        try {
            readUser = new Scanner(new FileInputStream("userdata.txt")).useDelimiter(",");
        } catch (FileNotFoundException e) {
            System.out.println("Error reading to file.");
            System.exit(0);
        }

        int count = 0;

        while (readUser.hasNext()) {
            readUser.next();
            count++;
        }

        return (count / 6);

    }

    static String readLine(int userNumber, int whichEntry) {


        /////////

        return "";


    }


}
