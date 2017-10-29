import java.util.Scanner;

import static java.lang.System.out;

public class UserManagement {

    private MenuAndOtherText menuAndOtherText = new MenuAndOtherText(); //Creates objects for classes used by multiple methods in this class
    private Scanner scan = new Scanner(System.in).useDelimiter("\n");   //
    private static boolean isLoggedIn = false;
    private static User[] userObjects = new User[15];
    private static int userCount = 0;

    public static void createUserObjects() {
        for (int i = 0; i < userObjects.length; i++) {
            userObjects[i] = new User();
        }
    }

    public void login() {

        MiscFunctions.clearScreen("");

        out.print("\n\n\tPlease enter your username: ");
        String usernameInput = scan.next();

        out.print("\tPlease enter your password: ");
        String passwordInput = scan.next();

        for (int i = 0; i < userObjects.length; i++) {
            if (usernameInput.equals(userObjects[i].getUsername()) && passwordInput.equals(userObjects[i].getPassword())) {
                isLoggedIn = true;
                menuAndOtherText.menu("----------\nYou're logged in!\n----------\n");
                break;
            }
        }

        if (!isLoggedIn) {
            out.print("\n\nYour username and password didn't match the records.");
            out.print("\n\nEnter 'R' to retry, or press enter to return to the Menu: ");
            String input = scan.next();

            if (input.equals("R") || input.equals("r")) {
                login();
            } else {
                menuAndOtherText.menu("");
            }
        }

    }

    public void register() {

        MiscFunctions.clearScreen("");

        String usernameInput = usernameRegistration("");
        while (usernameInput.equals("")) { //If inadequate username given, loops until adequate one given
            out.print("\nSorry! Your username must be at least two characters long,\n" +
                    "start with a letter, and contain no symbols.");

            usernameInput = usernameRegistration("re-");
        }

        String passwordInput = passwordRegistration("");
        while (passwordInput.equals("")) { //If inadequate password given, loops until adequate one given
            out.print("\nSorry! Your password must be at least five characters long, \n" +
                    "and contain at least one symbol.\n\n");

            passwordInput = passwordRegistration("re-");
        }

        userObjects[userCount].setUsername(usernameInput);
        userObjects[userCount].setPassword(passwordInput);

        userCount++;

        menuAndOtherText.menu("----------\nYou have registered!\n----------\n"); //When both username and password satisfactory, returns to menu

    }

    private String usernameRegistration(String firstTimeOrNot) {

        out.print("\n\n\tPlease " + firstTimeOrNot + "enter a new username: ");  //Modifies text printed depending on whether it's the first time
        String input = scan.next();

        if (!(specialCharactersInString(input) > 0 || input.length() < 2 || Character.isDigit(input.charAt(0)))) {
            return input;
        } else {
            return "";
        }
    }

    private String passwordRegistration(String firstTimeOrNot) {

        out.print("\tPlease " + firstTimeOrNot + "enter a new password: "); //Modifies text printed depending on whether it's the first time
        String input = scan.next();

        if (!(input.length() < 5 || specialCharactersInString(input) < 1)) { //Checks if given password meets specifications: length > 5, special characters > 1)
            return input;
        } else {
            return "";
        }

    }

    private int specialCharactersInString(String string) { //Returns number of special characters in string

        String specialCharacters = " !\"#$£%&'()*+,-./:;<=>?@[\\]^_`{|}~";

        int numberOfCharacters = 0;

        for (int i = 0; i < string.length(); i++) {
            if (specialCharacters.contains(Character.toString(string.charAt(i)))) {
                numberOfCharacters++;
            }
        }

        return numberOfCharacters;
    }

    public static boolean getLoginStatus() {
        return isLoggedIn;
    }

}
