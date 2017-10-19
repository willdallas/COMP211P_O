import static java.lang.System.out;

class Game {

    static void game(){

        if (UserManagement.isUserAuthenticated == true){
            out.print("GAME");
        }
        else{

            out.print("Please login to play the game.");

            InformationAndMenu.menu();
        }
    }


}
