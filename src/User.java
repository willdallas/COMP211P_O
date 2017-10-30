class User {

    private String username;
    private String password;
    private int userNumber;
    static int userCount;

    User() {  //Anticipated that, in future, this constructor will receive input from a file storing user details
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

}
