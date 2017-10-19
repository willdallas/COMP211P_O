import java.util.Scanner;
import static java.lang.System.out;

class UserManagement {

    private static Scanner scan = new Scanner(System.in);

    static boolean isUserAuthenticated = false;  //Not private so can be accessed later from the Game.java class to check if logged in

    private static String username;
    private static String password;

    static void login(){ //Prompts user for login details, verifies their details, etc.......

        String usernameInput, passwordInput;

        out.print("\n\n\tPlease enter your username: ");
        usernameInput = scan.next();

        out.print("\tPlease enter your password: ");
        passwordInput = scan.next();


        if (passwordInput.equals(password) && usernameInput.equals(username)){
            out.print("\n\n\tYou're logged in!\n\n");

            isUserAuthenticated = true;

            InformationAndMenu.menu();
        }
        else{
            out.print("\n\n\tYour username and password didn't match the records.");
            out.print("\n\tEnter 'R' to re-try, or anything else to return to the Menu: ");
            String s = scan.next();

            if (s.equals("R") || s.equals("r")) login();
            else InformationAndMenu.menu();
        }

    }

    static void register(){

        out.print("\n\n\tPlease enter a username: ");
        String usernameInput = scan.next();

        if (isUserEnteredDataOK(usernameInput, "username")) username = usernameInput;
        else{
            out.print("\n\tSorry! Your username must be at least two characters long, start with a letter, and contain no symbols.");
            out.print("\n\tPlease try again.");
            register();
        }

        out.print("\tPlease enter a password: ");
        String passwordInput = scan.next();

        if (isUserEnteredDataOK(passwordInput, "password")) password = passwordInput;
        else{
            out.print("\n\tSorry! Your password must be at least five characters long, and contain at least one symbol");
            out.print("\n\tPlease try again.");
            register();
        }

        out.print("\n\n\tYou have registered!\n\n");
        InformationAndMenu.menu();

    }

    private static boolean isUserEnteredDataOK(String data, String type) {

        if (type.equals("username"))
            return !(specialCharactersInString(data) > 0 || data.length() < 2 || Character.isDigit(data.charAt(0)));

        if (type.equals("password")) return !(data.length() < 2 || specialCharactersInString(data) < 1);

        return false;
    }


    private static int specialCharactersInString(String str){

        String specialCharacters = " !\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";

        int count = 0;

        for (int i = 0 ; i < str.length() ; i++){

            if (specialCharacters.contains(str.substring(i))){
                count++;
            }
        }
        return count;
    }






}
