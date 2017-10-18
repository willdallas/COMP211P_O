import java.util.Scanner;
import static java.lang.System.out;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        out.println("\nWelcome to the Word Game");
        out.println("\tLogin (L)");
        out.println("\tRegister (R)");
        out.println("\tAbout (A)");
        out.println("\tPlay the Game (P)");
        out.println("\tShow the Leader Board (B)");
        out.println("\tQuit (Q)\n");
        out.print("\tPlease choose an option: ");

        String userChoice = scan.next();

        if (userChoice.equals("L") || userChoice.equals("l")) Guts.loginAndRegistration(1);
        if (userChoice.equals("R") || userChoice.equals("r")) Guts.loginAndRegistration(2);
        if (userChoice.equals("A") || userChoice.equals("a")) Guts.about();

    }

}


