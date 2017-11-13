public class Main {

    public static void main(String[] args) {

        FileManagement.createUsers();

        MenuAndAbout.menu();

        FileManagement.writeUsers(UserManagement.userObjects);
    }
}