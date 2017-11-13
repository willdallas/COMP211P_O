import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import static java.lang.System.out;

class UserManagement {

    private static Scanner scan = new Scanner(System.in);
    private static User lastUserLoggedIn = null;    // Stores the array index number corresponding to last user logged in. -1 by default to test if any user has logged in.
    private static final int NUMBER_OF_USERS = 10 + FileManagement.getNumUsersInFile();   // Constant used to set size of userObject and gameObjects arrays.
    static User[] userObjects = new User[NUMBER_OF_USERS];

    static String login() {

        MiscFunctions.clearScreen("");

        boolean loginSuccess = false;

        out.print("\n\n\tPlease enter your username: ");
        String usernameInput = scan.nextLine();

        out.print("\tPlease enter your password: ");
        char[] passwordInputCharArray = System.console().readPassword();
        String passwordInput = hashPassword(new String(passwordInputCharArray));
        out.println();


        for (int i = 0; i < User.getUserCount(); i++) { //  Loops through userObject array to check if username & password provided match an existing User object
            if (userObjects[0] == null) {  // Necessary to prevent a "NullPointerException" if no elements in the array have been initialized yet (no users)
                break;
            } else if (usernameInput.equals(userObjects[i].getUsername()) && passwordInput.equals(userObjects[i].getPassword())) {
                lastUserLoggedIn = userObjects[i];
                loginSuccess = true;
                break;
            }
        }

        if (!loginSuccess) {
            out.print("\nYour username and password didn't match the records.");
            out.print("\nPress enter to return to the Menu: ");
            scan.nextLine();
        }

        return loginSuccess ? "----------\nWelcome, " + lastUserLoggedIn.getFirstName() + "\n----------\n" : "";
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

        String returnString;
        if (User.getUserCount() <= (NUMBER_OF_USERS - 1)) {   // avoids ArrayIndexOutOfBoundsException by limiting number of registrations to array size
            userObjects[User.getUserCount()] = new User(firstNameInput, lastNameInput, usernameInput, hashPassword(passwordInput));
            returnString = "----------\nYou have registered!\n----------\n";
        } else {
            returnString = "----------\nSorry, no more players can be stored\n----------\n";
        }
        return returnString;
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
            if (userObjects[0] == null) {  // Necessary to prevent a "NullPointerException" if no elements in the array have been initialized yet (no users)
                break;
            } else if (input.equals(userObjects[i].getUsername())) {
                alreadyRegistered = true;
                break;
            }
        }
        return alreadyRegistered;
    }

    private static String hashPassword(String passwordInput) {

        String hashedInput = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(passwordInput.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            hashedInput = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.exit(0);
        }
        return hashedInput;
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

    static boolean isUserOK(User userObject) {
        return isNameOK(userObject.getFirstName()) && isNameOK(userObject.getLastName()) &&
                (userObject.getPassword().length() == 32) && isUsernameOK(userObject.getUsername());
    }

    static User getLastUserLoggedIn() {
        return lastUserLoggedIn;
    }

}
