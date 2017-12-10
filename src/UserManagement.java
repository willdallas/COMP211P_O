import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.out;

class UserManagement {

    private static Scanner scan = new Scanner(System.in);
    private static User userLoggedIn = null;  // Stores the array index number corresponding to last user logged in. null by default (test for whether user has logged in).
    private static ArrayList<User> userObjects = new ArrayList<User>();

    static void login() {
        MiscFunctions.clearScreen();
        boolean loginSuccess = false;

        out.print(MiscFunctions.getStringWithBorder("Login", false));
        out.print("\n\n\tPlease enter your username: ");
        String usernameInput = scan.nextLine();

        out.print("\tPlease enter your password: ");
        char[] passwordInputCharArray = System.console().readPassword(); // Hides password text in console for privacy
        String passwordInput = MiscFunctions.hashString(new String(passwordInputCharArray));  // Hashes input so it can be compared with stored hashed password
        out.println();

        if (!userObjects.isEmpty()) { // Necessary to prevent a "NullPointerException" if no elements in the array have been initialized yet (no users)
            for (int i = 0; i < User.getUserCount(); i++) { //  Loops through userObject array to check if username & password provided match an existing User object
                if (usernameInput.equals(userObjects.get(i).getUsername()) && passwordInput.equals(userObjects.get(i).getPassword())) {
                    userLoggedIn = userObjects.get(i);
                    loginSuccess = true;
                    break;
                }
            }
        }
        if (!loginSuccess) {
            out.println("\n\tYour username and password didn't match the records.");
            out.print("\n\n\tPress enter to return to the menu: ");
            scan.nextLine();
        }
    }

    static void register() {
        MiscFunctions.clearScreen();
        String firstNameInput = null;
        String lastNameInput = null;
        String usernameInput = null;
        String passwordInput = null;

        out.print(MiscFunctions.getStringWithBorder("Register", false));
        while (!isNameOK(firstNameInput)) {
            out.print("\n\n\tPlease enter your first name: ");
            firstNameInput = scan.nextLine();
        }
        while (!isNameOK(lastNameInput)) {
            out.print("\tPlease enter your last name: ");
            lastNameInput = scan.nextLine();
        }
        while (!isUsernameOK(usernameInput)) {
            out.print("\n\tPlease enter a new username: ");
            usernameInput = scan.nextLine();
        }
        while (!isPasswordOK(passwordInput)) {
            out.print("\tPlease enter a new password: ");
            char[] passwordInputCharArray = System.console().readPassword(); // Hides password text in console for privacy
            passwordInput = new String(passwordInputCharArray);
            out.println();
        }

        userObjects.add(new User(firstNameInput, lastNameInput, usernameInput, MiscFunctions.hashString(passwordInput)));
    }

    static User getUserLoggedIn() {
        return userLoggedIn;
    }

    static void addAUser(User aUser) {
        userObjects.add(aUser);
    }

    static ArrayList<User> getUserObjects() {
        return new ArrayList<>(userObjects);
    }

    static boolean isUsernameOK(String input) {
        if (input == null) {
            return false;
        } else if (!userAlreadyRegistered(input) && !((specialCharactersInString(input) > 0) || input.length() < 2 || Character.isDigit(input.charAt(0)))) {
            return true;
        } else if (userAlreadyRegistered(input)) {
            out.print("\nSorry! This username has already been registered.");
            return false;
        } else {
            out.print("\nSorry! Your username must be at least two characters long,\n" +
                    "start with a letter, and contain no symbols.");
            return false;
        }
    }

    private static boolean isPasswordOK(String input) {
        if (input == null) {
            return false;
        } else if (input.length() >= 5) {
            return true;
        } else {
            out.print("\nSorry! Your password must be at least five characters long\n");
            return false;
        }
    }

    static boolean isNameOK(String input) {
        boolean nameAcceptable;

        if (input == null) {
            nameAcceptable = false;
        } else if (specialCharactersInString(input) == 0 && input.length() > 1) {
            nameAcceptable = true;
        } else {
            out.print("\nSorry! Your name must be at least two characters long,\n" +
                    "and contain no symbols\n");
            nameAcceptable = false;
        }
        return nameAcceptable;
    }

    private static boolean userAlreadyRegistered(String input) {
        if (userObjects.isEmpty()) {  // Necessary to prevent a "NullPointerException" if no elements in the array have been initialized yet (no users)
            return false;
        }
        for (int i = 0; i < User.getUserCount(); i++) {
             if (input.equals(userObjects.get(i).getUsername())) {
                return true;
            }
        }
        return false;
    }

    private static int specialCharactersInString(String aString) {
        String specialCharacters = " !\"#$£%&'()*+,./:;<=>?@[\\]^`{|}~";
        int numberOfCharacters = 0;

        for (int i = 0; i < aString.length(); i++) {
            if (specialCharacters.contains(Character.toString(aString.charAt(i)))) {
                numberOfCharacters++;
            }
        }
        return numberOfCharacters;
    }
}
