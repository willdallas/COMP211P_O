class GameManagement {

    private static int numQuestionsInFile = FileManagement.getNumQuestions();
    private static Game[] gameObjects = new Game[UserManagement.NUMBER_OF_USERS];
    //   static Question[] questionObjects = new Question[numQuestionsInFile - 1];
    private static final int QUESTIONS_PER_GAME = 5;

    static String newGame(User currentUser) {
        String returnString;

        if (currentUser != null) {
            gameObjects[currentUser.getUserNumber()] = new Game(currentUser, createRandomizedQuestionArray());
            gameObjects[currentUser.getUserNumber()].start();

            returnString = "";
        } else {
            returnString = "----------\nPlease login to play the game.\n----------\n";
        }

        return returnString;
    }

    private static Question[] createRandomizedQuestionArray() {
        Question[] randomizedQuestions = new Question[QUESTIONS_PER_GAME];
        int randomInt;
        boolean isQuestionNew;
        String oldQuestions = "";

        for (int i = 0; i < QUESTIONS_PER_GAME; i++) {

            do {
                randomInt = MiscFunctions.randomIntBetweenNumbers(0, numQuestionsInFile - 1);
                isQuestionNew = !oldQuestions.contains(Integer.toString(randomInt));
            } while (!isQuestionNew);

            randomizedQuestions[i] = new Question(FileManagement.createQuestion(randomInt));
            oldQuestions += randomInt + ",";
        }

        return randomizedQuestions;
    }
}
