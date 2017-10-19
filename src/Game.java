import static java.lang.System.out;

class Game {

    static void start(){

        MiscFunctions.clearScreen();

        if (UserManagement.isUserAuthenticated){
            out.print("\n\n\t-----GAME GOES HERE-----\n\n\n");
        }
        else{
            MenuAndOtherText.menu("----------\nPlease login to play the game.\n----------\n");
        }
    }

}
