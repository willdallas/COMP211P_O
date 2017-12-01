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

        if (!UserManagement.isUserOK(this)) {  // Throws exception if user being created has invalid attributes
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

    public int compareTo(User otherUser) { // Used to order array of Users by percentage correct
        if (getPercentageCorrect() == otherUser.getPercentageCorrect()) {
            return 0;
        }
        if (getPercentageCorrect() < otherUser.getPercentageCorrect()) {
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

    static int getUserCount() {
        return userCount;
    }
}
