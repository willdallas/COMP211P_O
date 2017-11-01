class GameManagement {

    private static Game[] gameObjects = new Game[UserManagement.getNumberOfUsers()];

    static String newGame(int userNumber) {

        String returnString;

        if (userNumber != -1) {
            gameObjects[userNumber] = new Game(UserManagement.userObjects[userNumber]);
            gameObjects[userNumber].start();

            returnString = "EXIT";
        } else {
            returnString = "----------\nPlease login to play the game.\n----------\n";
        }

        return returnString;
    }
}
