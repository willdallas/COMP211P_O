import java.util.Scanner;

import static java.lang.System.out;

class UserManagement {

    private static Scanner scan = new Scanner(System.in);
    private static int lastUserLoggedIn = -1;                // Stores the array index number corresponding to last user logged in. -1 by default to test if any user has logged in.
    private static final int NUMBER_OF_USERS = 5;            // Constant used to set size of userObject and gameObjects arrays.
    static User[] userObjects = new User[NUMBER_OF_USERS];

    static String login() {

        MiscFunctions.clearScreen("");

        boolean loginSuccess = false;

        out.print("\n\n\tPlease enter your username: ");
        String usernameInput = scan.nextLine();

        out.print("\tPlease enter your password: ");
        String passwordInput = scan.nextLine();


        for (int i = 0; i < User.getUserCount(); i++) { //  Loops through userObject array to check if username & password provided match an existing User object
            if (userObjects[0] == null) {  // Necessary to prevent a "NullPointerException" if no elements in the array have been initialized yet (no users)
                break;
            } else if (usernameInput.equals(userObjects[i].getUsername()) && passwordInput.equals(userObjects[i].getPassword())) {
                lastUserLoggedIn = i;
                loginSuccess = true;
                break;
            }
        }

        if (!loginSuccess) {
            out.print("\n\nYour username and password didn't match the records.");
            out.print("\nPress enter to return to the Menu: ");
            scan.nextLine();
        }

        return loginSuccess ? "----------\nYou're logged in!\n----------\n" : "";

    }

    static String register() {

        MiscFunctions.clearScreen("");

        String returnString;

        String usernameInput = usernameRegistration("");
        while (usernameInput.equals("")) { // If inadequate username given, loops until adequate one given
            out.print("\nSorry! Your username must be at least two characters long,\n" +
                    "start with a letter, and contain no symbols.");

            usernameInput = usernameRegistration("re-");
        }

        String passwordInput = passwordRegistration("");
        while (passwordInput.equals("")) {
            out.print("\nSorry! Your password must be at least five characters long, \n" +
                    "and contain at least one symbol.\n\n");

            passwordInput = passwordRegistration("re-");
        }

        if (User.getUserCount() <= (NUMBER_OF_USERS - 1)) {                  // if statement avoids ArrayIndexOutOfBoundsException by limiting number of registrations possible to array size
            userObjects[User.getUserCount()] = new User();                   // Creates new instance of User class in the userCount array for user registering
            userObjects[User.getUserCount() - 1].setUsername(usernameInput); // 1 is subtracted from the previous array index because because userCount increments by 1 when the User object is created
            userObjects[User.getUserCount() - 1].setPassword(passwordInput);
            returnString = "----------\nYou have registered!\n----------\n";
        } else {
            returnString = "----------\nSorry, no more players can be stored\n----------\n";
        }

        return returnString;

    }

    private static String usernameRegistration(String secondTimeModifier) {

        out.print("\n\n\tPlease " + secondTimeModifier + "enter a new username: ");  // Modifies text printed depending on whether it's the first time this code is run
        String input = scan.nextLine();

        return (!(specialCharactersInString(input) > 0 || input.length() < 2 || Character.isDigit(input.charAt(0)))) ? input : "";
    }

    private static String passwordRegistration(String secondTimeModifier) {

        out.print("\tPlease " + secondTimeModifier + "enter a new password: ");
        String input = scan.nextLine();

        return (!(input.length() < 5 || specialCharactersInString(input) < 1)) ? input : "";
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

    static int getLastUserLoggedIn() {
        return lastUserLoggedIn;
    }

    static int getNumberOfUsers() {
        return NUMBER_OF_USERS;
    }

}
