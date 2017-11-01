class User {

    private String username;
    private String password;
    private int userNumber;
    private static int userCount = 0;

    User() {
        userNumber = userCount;
        userCount++;
    }

    void setUsername(String usernameInput) {
        this.username = usernameInput;
    }

    void setPassword(String passwordInput) {
        this.password = passwordInput;
    }

    String getUsername() {
        return this.username;
    }

    String getPassword() {
        return this.password;
    }

    int getUserNumber(){
        return this.userNumber;
    }

    static int getUserCount() {
        return userCount;
    }

}
