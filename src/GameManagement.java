class GameManagement {

    private static Game[] gameObjects = new Game[UserManagement.getNumberOfUsers()];

    void newGame(int userNumber) {

        if (userNumber == -1) {
            MenuAndOtherText.menu("----------\nPlease login to play the game.\n----------\n");
        } else {
            gameObjects[userNumber] = new Game(UserManagement.userObjects[userNumber]);
            gameObjects[userNumber].start();
        }

    }


}
