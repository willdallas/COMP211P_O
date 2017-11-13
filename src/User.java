class User {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private int numGames;
    private int totalScore;
    private int userNumber;
    private static int userCount = 0;

    User(String firstNameInput, String lastNameInput, String usernameInput, String passwordInput, int numGamesInput, int totalScoreInput) { // This constructor is used when creating objects from file

        username = usernameInput;
        password = passwordInput;
        firstName = firstNameInput;
        lastName = lastNameInput;
        numGames = numGamesInput;
        totalScore = totalScoreInput;

        if (!UserManagement.isUserOK(this)) {
            throw new IllegalArgumentException("userdata.txt file contains illegal entries");
        }

        userNumber = userCount;
        userCount++;
    }

    User(String firstNameInput, String lastNameInput, String usernameInput, String passwordInput) { // This constructor is used when registering new users
        username = usernameInput;
        password = passwordInput;
        firstName = firstNameInput;
        lastName = lastNameInput;
        numGames = 0;
        totalScore = 0;

        userNumber = userCount;
        userCount++;
    }

    String getUsername() {
        return this.username;
    }

    String getPassword() {
        return this.password;
    }

    int getUserNumber() {
        return this.userNumber;
    }

    String getFirstName() {
        return this.firstName;
    }

    String getLastName() {
        return this.lastName;
    }

    public String toString() {
        return this.firstName + "," + this.lastName + "," + this.username + "," + this.password + "," + this.numGames + "," + this.totalScore;
    }

    static int getUserCount() {
        return userCount;
    }
}
