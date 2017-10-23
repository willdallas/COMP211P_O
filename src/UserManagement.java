import java.util.Scanner;
import static java.lang.System.out;

class UserManagement {

    private MenuAndOtherText menuAndOtherText = new MenuAndOtherText(); //Creates objects for classes used in this class
    private Scanner scan = new Scanner(System.in).useDelimiter("\n");   //

    private static boolean isUserAuthenticated = false;  //Variable that can be checked by other classes (e.g. Game) to determine if the user is logged in

    private static String username; //These variables used by multiple methods
    private static String password; //

    void login() {

        MiscFunctions.clearScreen("");

        out.print("\n\n\tPlease enter your username: ");
        String usernameInput = scan.next();

        out.print("\tPlease enter your password: ");
        String passwordInput = scan.next();

        if (passwordInput.equals(password) && usernameInput.equals(username)) { //Checks if username and password match those given in register()
            isUserAuthenticated = true;
            menuAndOtherText.menu("----------\nYou're logged in!\n----------\n");
        } else {
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

    void register() {

        MiscFunctions.clearScreen("");

        String usernameInput = usernameRegistration("");
        while (usernameInput.equals("")) { //If inadequate username given, loops until adequate one given
            out.print("\nSorry! Your username must be at least two characters long,\n" +
                    "start with a letter, and contain no symbols.");

            usernameInput = usernameRegistration("re-");
        }
        username = usernameInput;

        String passwordInput = passwordRegistration("");
        while (passwordInput.equals("")) { //If inadequate password given, loops until adequate one given
            out.print("\nSorry! Your password must be at least five characters long, \n" +
                    "and contain at least one symbol.\n\n");

            passwordInput = passwordRegistration("re-");
        }
        password = passwordInput;

        menuAndOtherText.menu("----------\nYou have registered!\n----------\n"); //When both username and password satisfactory, returns to menu

    }

    private String usernameRegistration(String firstTimeOrNot) {
        out.print("\n\n\tPlease " + firstTimeOrNot + "enter a new username: ");
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

    static boolean isUserAuthenticated() {
        return isUserAuthenticated;
    }

}
