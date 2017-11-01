class GameManagement {

    private static Game[] gameObjects = new Game[UserManagement.getNumberOfUsers()];

    static String newGame(int userNumber) {

        String returnText;

        if (userNumber != -1) {
            gameObjects[userNumber] = new Game(UserManagement.userObjects[userNumber]);
            gameObjects[userNumber].start();

            returnText = "EXIT";
        } else {
            returnText = "----------\nPlease login to play the game.\n----------\n";
        }

        return returnText;
    }
}
