import java.util.Scanner;

import static java.lang.System.out;

class UserManagement {

    private Scanner scan = new Scanner(System.in).useDelimiter("\n");
    private static int lastUserLoggedIn = -1;                // Stores the array index number corresponding to last user logged in. -1 by default to test if any user has logged in.
    private static final int NUMBER_OF_USERS = 5;            // Final variable used to set size of userObject and gameObjects arrays.
    static User[] userObjects = new User[NUMBER_OF_USERS];

    void login() {

        MiscFunctions.clearScreen("");

        boolean loginSuccess = false;

        out.print("\n\n\tPlease enter your username: ");
        String usernameInput = scan.nextLine();

        out.print("\tPlease enter your password: ");
        String passwordInput = scan.nextLine();


        for (int i = 0; i < User.userCount; i++) {
            if (userObjects[0] == null) {  // Necessary to prevent a "NullPointerException" if no elements in the array have been initialized yet (no users)
                break;
            } else if (usernameInput.equals(userObjects[i].getUsername()) && passwordInput.equals(userObjects[i].getPassword())) {
                lastUserLoggedIn = i;
                loginSuccess = true;

                MenuAndOtherText.menu("----------\nYou're logged in!\n----------\n");
                break;
            }
        }

        if (!loginSuccess) {
            out.print("\n\nYour username and password didn't match the records.");
            out.print("\n\nEnter 'R' to retry, or press enter to return to the Menu: ");
            String input = scan.nextLine();

            if (input.equals("R") || input.equals("r")) {
                login();
            } else {
                MenuAndOtherText.menu("");
            }
        }

    }

    void register() {

        MiscFunctions.clearScreen("");

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

        userObjects[User.userCount] = new User(); // Creates new instance of User class in the userCount array for user registering
        userObjects[User.userCount - 1].setUsername(usernameInput); // 1 is subtracted from the previous array index because because userCount increments by 1 when the User object is created
        userObjects[User.userCount - 1].setPassword(passwordInput);

        MenuAndOtherText.menu("----------\nYou have registered!\n----------\n");

    }

    private String usernameRegistration(String secondTimeModifier) {

        out.print("\n\n\tPlease " + secondTimeModifier + "enter a new username: ");  // Modifies text printed depending on whether it's the first time this code is run
        String input = scan.nextLine();
        String output = "";

        if (!(specialCharactersInString(input) > 0 || input.length() < 2 || Character.isDigit(input.charAt(0)))) {
            output = input;
        }
        return output;
    }

    private String passwordRegistration(String secondTimeModifier) {

        out.print("\tPlease " + secondTimeModifier + "enter a new password: ");
        String input = scan.nextLine();
        String output = "";

        if (!(input.length() < 5 || specialCharactersInString(input) < 1)) {
            output = input;
        }
        return output;
    }

    private int specialCharactersInString(String aString) {

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
