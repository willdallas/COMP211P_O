public class User {

    private String username;
    private String password;

    public User() {  //Anticipated that, in future, this constructor will receive input from a file storing user details

    }

    public void setUsername(String usernameInput) {
        username = usernameInput;
    }

    public void setPassword(String passwordInput) {
        password = passwordInput;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


}
