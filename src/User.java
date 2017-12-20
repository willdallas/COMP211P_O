class User implements Comparable<User> {

    private String username;
    private String hashedPassword;
    private String firstName;
    private String lastName;
    private int numGames;
    private int totalScore;
    private double totalTimeTaken;
    private static int userCount = 0;

    User(String firstNameInput, String lastNameInput, String usernameInput,
         String passwordInput, int numGamesInput, int totalScoreInput, double totalTimeInput) { // This constructor is used when creating objects from file
        username = usernameInput;
        hashedPassword = passwordInput;
        firstName = firstNameInput;
        lastName = lastNameInput;
        numGames = numGamesInput;
        totalScore = totalScoreInput;
        totalTimeTaken = totalTimeInput;

        if (!isUserOK(this)) {  // Throws exception if user being created has invalid attributes
            MiscFunctions.clearScreen();
            throw new IllegalArgumentException("userdata.csv file contains illegal entries");
        }
        userCount++;
    }

    User(String firstNameInput, String lastNameInput, String usernameInput, String passwordInput) { // This constructor is used when registering new users. Inputs already checked
        username = usernameInput;
        hashedPassword = passwordInput;
        firstName = firstNameInput;
        lastName = lastNameInput;
        numGames = 0; // Default values for numGames and totalScore
        totalScore = 0;
        totalTimeTaken = 0;
        userCount++;
    }

    public int compareTo(User otherUser) { // Used to order array of Users by percentage correct and average time taken.
        double scoreDifference = this.getPercentageCorrect() - (otherUser.getPercentageCorrect());
        double timeDifference = this.getAverageTimeTaken() - otherUser.getAverageTimeTaken();

        if (scoreDifference == 0 && this.numGames != 0) {
            return (timeDifference == 0) ? 0 : ((timeDifference < 0) ? 1 : -1); // Orders by score first, then by time taken
        }
        if (scoreDifference < 0 || this.numGames == 0) {
            return -1;
        } else {
            return 1;
        }
    }

    String getUsername() {
        return this.username;
    }

    String getHashedPassword() {
        return this.hashedPassword;
    }

    String getFirstName() {
        return this.firstName;
    }

    String getLastName() {
        return this.lastName;
    }

    int getNumGames() {
        return this.numGames;
    }

    void setNumGames(int number) {
        this.numGames = number;
    }

    int getTotalScore() {
        return this.totalScore;
    }

    void setTotalScore(int number) {
        this.totalScore = number;
    }

    void setTotalTimeTaken(double timeTaken) {
        this.totalTimeTaken = timeTaken;
    }

    double getTotalTimeTaken() {
        return this.totalTimeTaken;
    }

    double getAverageTimeTaken() {
        return this.totalTimeTaken / (Game.getQuestionsPerGame() * this.numGames);
    }

    int getPercentageCorrect() {
        if (numGames > 0) {
            return (totalScore * 100) / (numGames * Game.getQuestionsPerGame());
        } else {
            return 0;
        }
    }

    public String toString() { // Prints the user's data in a format consistent with the userdata.csv format
        return this.firstName + "," + this.lastName + "," + this.username + "," + this.hashedPassword + "," +
                this.numGames + "," + this.totalScore + "," + this.totalTimeTaken;
    }

    private static boolean isUserOK(User aUser) { // Checks if User object fulfills requirements. (64 is length of hashedPassword hash)
        return UserManagement.isUsernameOK(aUser.getUsername()) &&
                UserManagement.isNameOK(aUser.getFirstName()) &&
                UserManagement.isNameOK(aUser.getLastName()) &&
                aUser.getHashedPassword().length() == 64 &&
                aUser.getNumGames() >= 0 && aUser.getTotalScore() >= 0 &&
                aUser.getNumGames() * Game.getQuestionsPerGame() >= aUser.getTotalScore(); // Checks that the score, and number of games makes sense
    }

    static int getUserCount() {
        return userCount;
    }
}
