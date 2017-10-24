import static java.lang.System.out;

class Game {

    void start() {

        MiscFunctions.clearScreen("");

        MenuAndOtherText menuAndOtherText = new MenuAndOtherText();

        if (UserManagement.getAuthenticationStatus()) {
            out.print("\n\n\t-----GAME GOES HERE-----\n\n\n");
        } else {
            menuAndOtherText.menu("----------\nPlease login to play the game.\n----------\n");
        }
    }

}
