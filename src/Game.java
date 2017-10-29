import static java.lang.System.out;

public class Game {

    public Game() { //Will use constructor later to feed in information about the player linked to the instance of the Game class

    }

    public void start() {

        MiscFunctions.clearScreen("");

        MenuAndOtherText menuAndOtherText = new MenuAndOtherText();

        if (UserManagement.getLoginStatus()) {
            out.print("\n\n\t-----GAME GOES HERE-----\n\n\n");
        } else {
            menuAndOtherText.menu("----------\nPlease login to play the game.\n----------\n");
        }
    }

}
