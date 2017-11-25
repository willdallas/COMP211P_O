import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.out;

class UserManagement {

    private static Scanner scan = new Scanner(System.in);
    private static User userLoggedIn = null;  // Stores the array index number corresponding to last user logged in. null by default (test for whether user has logged in).
    private static ArrayList<User> userObjects = new ArrayList<User>(FileManagement.getNumUsersInFile());

    static String login() {
        MiscFunctions.clearScreen("");
        boolean loginSuccess = false;

        out.print("\n\n\tPlease enter your username: ");
        String usernameInput = scan.nextLine();

        out.print("\tPlease enter your password: ");
        char[] passwordInputCharArray = System.console().readPassword(); // Hides password text in console for privacy
        String passwordInput = MiscFunctions.hashString(new String(passwordInputCharArray));
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
            out.print("\nYour username and password didn't match the records.");
            out.print("\nPress enter to return to the Menu: ");
            scan.nextLine();
            return "\n";
        }
        return "----------\nWelcome, " + userLoggedIn.getFirstName() + "\n----------";
    }

    static String register() {
        MiscFunctions.clearScreen("");
        String firstNameInput = null;
        String lastNameInput = null;
        String usernameInput = null;
        String passwordInput = null;

        while (!isNameOK(firstNameInput)) {
            out.print("\n\tPlease enter your first name: ");
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
            char[] passwordInputCharArray = System.console().readPassword();
            passwordInput = new String(passwordInputCharArray);
            out.println();
        }

        addAUser(new User(firstNameInput, lastNameInput, usernameInput, MiscFunctions.hashString(passwordInput)));
        return "----------\nYou have registered!\n----------";
    }

    static boolean isUserOK(User userObject) {
        return isNameOK(userObject.getFirstName()) && isNameOK(userObject.getLastName()) &&  // Checks if User object fulfills requirements. (32 is length of password hash)
                (userObject.getPassword().length() == 64) && isUsernameOK(userObject.getUsername());
    }

    static User getUserLoggedIn() {
        return userLoggedIn;
    }

    static User getAUser(int index) {
        return userObjects.get(index);
    }

    static void addAUser(int index, User aUser) {
        userObjects.add(index, aUser);
    }

    private static void addAUser(User aUser) {
        userObjects.add(aUser);
    }

    private static boolean isUsernameOK(String input) {
        boolean usernameAcceptable;
        if (input == null) {
            usernameAcceptable = false;
        } else if (!userAlreadyRegistered(input) && !((specialCharactersInString(input) > 0) || input.length() < 2 || Character.isDigit(input.charAt(0)))) {
            usernameAcceptable = true;
        } else if (userAlreadyRegistered(input)) {
            out.print("\nSorry! This username has already been registered.");
            usernameAcceptable = false;
        } else {
            out.print("\nSorry! Your username must be at least two characters long,\n" +
                    "start with a letter, and contain no symbols.");
            usernameAcceptable = false;
        }
        return usernameAcceptable;
    }

    private static boolean isPasswordOK(String input) {
        boolean passwordAcceptable;
        if (input == null) {
            passwordAcceptable = false;
        } else if (!(input.length() < 5 || specialCharactersInString(input) < 1)) {
            passwordAcceptable = true;
        } else {
            out.print("\nSorry! Your password must be at least five characters long,\n" +
                    "and at least one symbol.\n");
            passwordAcceptable = false;
        }
        return passwordAcceptable;
    }

    private static boolean isNameOK(String input) {
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
        boolean alreadyRegistered = false;
        for (int i = 0; i < User.getUserCount(); i++) {
            if (userObjects.get(0) == null) {  // Necessary to prevent a "NullPointerException" if no elements in the array have been initialized yet (no users)
                break;
            } else if (input.equals(userObjects.get(i).getUsername())) {
                alreadyRegistered = true;
                break;
            }
        }
        return alreadyRegistered;
    }

    private static int specialCharactersInString(String aString) {
        String specialCharacters = " !\"#$£%&'()*+,-./:;<=>?@[\\]^_`{|}~";

        int numberOfCharacters = 0;

        for (int i = 0; i < aString.length(); i++) {
            if (specialCharacters.contains(Character.toString(aString.charAt(i)))) {
                numberOfCharacters++;
            }
        }
        return numberOfCharacters;
    }
}
