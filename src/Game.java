import static java.lang.System.out;

class Game {

    private int gameNumber;

    Game(User currentUser) {
        gameNumber = currentUser.getUserNumber();
    }

    void start() {
        MiscFunctions.clearScreen("");

        out.print("\n\n\t-----GAME GOES HERE-----\n\n\n");

    }

    int getGameNumber() {
        return gameNumber;
    }
}
