class User {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private int numGames;
    private int totalScore;
    private int userNumber;
    private static int userCount = 0;

    User(String firstNameInput, String lastNameInput, String usernameInput, String passwordInput, int numGamesInput, int totalScoreInput) {

        username = usernameInput;
        password = passwordInput;
        firstName = firstNameInput;
        lastName = lastNameInput;
        numGames = numGamesInput;
        totalScore = totalScoreInput;

        userNumber = userCount;
        userCount++;
    }

    String getUsername() {
        return this.username;
    }

    String getPassword() {
        return this.password;
    }

    void setNumGames(int numInput) {
        this.numGames = numInput;
    }

    int getNumGames() {
        return this.numGames;
    }

    void setTotalScore(int scoreInput) {
        this.totalScore = scoreInput;
    }

    int getTotalScore() {
        return this.totalScore;
    }

    int getUserNumber() {
        return this.userNumber;
    }

    static int getUserCount() {
        return userCount;
    }
}
