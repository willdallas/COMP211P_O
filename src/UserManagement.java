import java.util.Scanner;
import static java.lang.System.out;

class UserManagement {

    private static Scanner scan = new Scanner(System.in);

    static boolean isUserAuthenticated = false;  //Variable that can be checked by other classes to determine if the user is logged in

    private static String username;
    private static String password;

    static void login(){ //Prompts user for login details, and sets 'ifUserAuthenticated' to true if username/password match those used for registration

        MiscFunctions.clearScreen();

        out.print("\n\n\tPlease enter your username: ");
        String usernameInput = scan.next();

        out.print("\tPlease enter your password: ");
        String passwordInput = scan.next();


        if (passwordInput.equals(password) && usernameInput.equals(username)){
            isUserAuthenticated = true;

            MenuAndOtherText.menu("----------\nYou're logged in!\n----------\n");
        }
        else{
            out.print("\n\nYour username and password didn't match the records.");
            out.print("\n\nEnter 'R' to retry, or press return to the Menu: ");

            Scanner scan_1 = scan.useDelimiter("\n"); //Allows scanner to detect carriage return
            String s = scan_1.next();

            if (s.equals("R") || s.equals("r")) login();
            else MenuAndOtherText.menu("");
        }

    }

    static void register(String text){

        MiscFunctions.clearScreen();

        out.print(text + "\n\n"); //When this method is called, text can be passed through to be displayed before the menu

        out.print("\n\n\tPlease enter a username: ");
        String usernameInput = scan.next();

        if (isUserEnteredDataOK(usernameInput, "username")) username = usernameInput;
        else{
            register("\nSorry! Your username must be at least two characters long,\n" +
                    "start with a letter, and contain no symbols.\n\nPlease try again.");
        }

        out.print("\tPlease enter a password: ");
        String passwordInput = scan.next();

        if (isUserEnteredDataOK(passwordInput, "password")) password = passwordInput;
        else{
            register("\nSorry! Your password must be at least five characters long, \n" +
                    "and contain at least one symbol\n\nPlease try again.");
        }

        MenuAndOtherText.menu("----------\nYou have registered!\n----------\n");

    }

    private static boolean isUserEnteredDataOK(String data, String type) { //Checks whether password or username meet requirements

        if (type.equals("username")) {
            return !(specialCharactersInString(data) > 0 || data.length() < 2 || Character.isDigit(data.charAt(0)));
        }

        if (type.equals("password")) if (!(data.length() < 5 || specialCharactersInString(data) < 1)) return true;

        return false;

    }


    private static int specialCharactersInString(String str){ //Returns number of special characters in string

        String specialCharacters = " !\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";

        int count = 0;

        for (int i = 0 ; i < str.length() ; i++){

            if (specialCharacters.contains(str.substring(i))) count++;
        }
        return count;
    }






}
