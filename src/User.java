class User implements Comparable<User> {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private int numGames;
    private int totalScore;
    private static int userCount = 0;

    User(String firstNameInput, String lastNameInput, String usernameInput, String passwordInput, int numGamesInput, int totalScoreInput) { // This constructor is used when creating objects from file

        username = usernameInput;
        password = passwordInput;
        firstName = firstNameInput;
        lastName = lastNameInput;
        numGames = numGamesInput;
        totalScore = totalScoreInput;

        if (!isUserOK(this)) {  // Throws exception if user being created has invalid attributes
            MiscFunctions.clearScreen();
            throw new IllegalArgumentException("userdata.txt file contains illegal entries");
        }
        userCount++;
    }

    User(String firstNameInput, String lastNameInput, String usernameInput, String passwordInput) { // This constructor is used when registering new users
        username = usernameInput;
        password = passwordInput;
        firstName = firstNameInput;
        lastName = lastNameInput;
        numGames = 0; // Default values for numGames and totalScore
        totalScore = 0;
        userCount++;
    }

    public int compareTo(User otherUser) { // Used to order array of Users by percentage correct (with all players with 0 games played at the end of the list)
        if (this.getPercentageCorrect() == otherUser.getPercentageCorrect() && this.numGames != 0) {
            return 0;
        }
        if (this.getPercentageCorrect() < otherUser.getPercentageCorrect() || this.numGames == 0) {
            return -1;
        } else {
            return 1;
        }
    }

    String getUsername() {
        return this.username;
    }

    String getPassword() {
        return this.password;
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

    int getPercentageCorrect() {
        if (numGames > 0) {
            return (totalScore * 100) / (numGames * Game.getQuestionsPerGame());
        } else {
            return 0;
        }
    }

    public String toString() { // Prints the user's data in a format consistent with the userdata.txt format
        return this.firstName + "," + this.lastName + "," + this.username + "," + this.password + "," + this.numGames + "," + this.totalScore;
    }

    private static boolean isUserOK(User aUser) { // Checks if User object fulfills requirements. (64 is length of password hash)
        return UserManagement.isUsernameOK(aUser.getUsername()) &&
                UserManagement.isNameOK(aUser.getFirstName()) &&
                UserManagement.isNameOK(aUser.getLastName()) &&
                aUser.getPassword().length() == 64 &&
                aUser.getNumGames() >= 0 &&
                aUser.getTotalScore() >= 0;
    }

    static int getUserCount() {
        return userCount;
    }
}
