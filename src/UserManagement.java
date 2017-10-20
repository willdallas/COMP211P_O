import java.util.Scanner;

import static java.lang.System.out;

class UserManagement {

    private static Scanner scan = new Scanner(System.in).useDelimiter("\n");

    static boolean isUserAuthenticated = false;  //Variable that can be checked by other classes (e.g. Game) to determine if the user is logged in

    private static String username;
    private static String password;

    static void login() { //Prompts user for login details, and sets 'ifUserAuthenticated' to true if username/password match those used for registration

        MiscFunctions.clearScreen("");

        out.print("\n\n\tPlease enter your username: ");
        String usernameInput = scan.next();

        out.print("\tPlease enter your password: ");
        String passwordInput = scan.next();


        if (passwordInput.equals(password) && usernameInput.equals(username)) {
            isUserAuthenticated = true;
            MenuAndOtherText.menu("----------\nYou're logged in!\n----------\n");
        } else {
            out.print("\n\nYour username and password didn't match the records.");
            out.print("\n\nEnter 'R' to retry, or press enter to return to the Menu: ");

            String input = scan.next();

            if (input.equals("R") || input.equals("r")) {
                login();
            } else {
                MenuAndOtherText.menu("");
            }
        }

    }

    static void register() {

        MiscFunctions.clearScreen("");

        String usernameInput = usernameRegistration("");

        while (usernameInput.equals("")) {
            out.print("\nSorry! Your username must be at least two characters long,\n" +
                    "start with a letter, and contain no symbols.");

            usernameInput = usernameRegistration("re-");
        }
        username = usernameInput;

        String passwordInput = passwordRegistration("");

        while (passwordInput.equals("")) {
            out.print("\nSorry! Your password must be at least five characters long, \n" +
                    "and contain at least one symbol.");

            passwordInput = passwordRegistration("re-");
        }
        password = usernameInput;

        MenuAndOtherText.menu("----------\nYou have registered!\n----------\n");

    }

    private static String usernameRegistration(String firstTimeOrNot) {
        out.print("\n\n\tPlease " + firstTimeOrNot + "enter a new username: ");
        String input = scan.next();

        if (!(specialCharactersInString(input) > 0 || input.length() < 2 || Character.isDigit(input.charAt(0)))) {
            return input;
        } else {
            return "";
        }
    }

    private static String passwordRegistration(String firstTimeOrNot) {
        out.print("\n\n\tPlease " + firstTimeOrNot + "enter a new password: ");
        String input = scan.next();

        if (!(input.length() < 5 || specialCharactersInString(input) < 1)) {
            return input;
        } else {
            return "";
        }

    }

    private static int specialCharactersInString(String str) { //Returns number of special characters in string

        String specialCharacters = " !\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";

        int count = 0;

        for (int i = 0; i < str.length(); i++) {
            if (specialCharacters.contains(str.substring(i))) {
                count++;
            }
        }

        return count;
    }

}
