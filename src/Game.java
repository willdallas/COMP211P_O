import static java.lang.System.out;

class Game {

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
