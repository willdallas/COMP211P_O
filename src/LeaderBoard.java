import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import static java.lang.System.out;

class LeaderBoard {

    static void leaderBoard() {
        Scanner scan = new Scanner(System.in);
        ArrayList<User> orderedUsersArray = UserManagement.getUserObjects();
        Collections.sort(orderedUsersArray, Collections.<User>reverseOrder()); // Orders array by percentage correct, using compareTo() method in User

        ArrayList<String> tableColOne = new ArrayList<String>();
        ArrayList<String> tableColTwo = new ArrayList<String>();
        ArrayList<String> tableColThree = new ArrayList<String>();
        ArrayList<String> tableColFour = new ArrayList<String>();
        ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();

        MiscFunctions.clearScreen();
        out.print(MiscFunctions.getStringWithBorder("Leader Board"));

        tableColOne.add(" Rank ");
        tableColOne.add("──────");
        for (int i = 0; i < orderedUsersArray.size(); i++) {
            if (orderedUsersArray.get(i).getNumGames() != 0) {
                tableColOne.add(" " + (i + 1));
            } else {
                tableColOne.add(" N/A");
            }
        }
        tableColTwo.add(" Username (First Name) ");
        tableColTwo.add("───────────────────────");
        for (int i = 0; i < orderedUsersArray.size(); i++) {
            tableColTwo.add(" " + orderedUsersArray.get(i).getUsername() + " (" + orderedUsersArray.get(i).getFirstName() + ") ");
        }
        tableColThree.add(" # of games played ");
        tableColThree.add("───────────────────");
        for (int i = 0; i < orderedUsersArray.size(); i++) {
            tableColThree.add(" " + orderedUsersArray.get(i).getNumGames());
        }
        tableColFour.add(" % of answers correct ");
        tableColFour.add("──────────────────────");
        for (int i = 0; i < orderedUsersArray.size(); i++) {
            tableColFour.add(" " + orderedUsersArray.get(i).getPercentageCorrect() + "%");
        }

        table.add(tableColOne);
        table.add(tableColTwo);
        table.add(tableColThree);
        table.add(tableColFour);

        out.println("\n");
        out.print(formatTable(table));

        out.print("\n\n\tPress enter to return to the Menu: ");
        scan.nextLine();
    }

    private static String formatTable(ArrayList<ArrayList<String>> tableArray) { // Used to dynamically format a table based on the lengths of entries
        String table = "";

        ArrayList<String> colOne = tableArray.get(0);
        ArrayList<String> colTwo = tableArray.get(1);
        ArrayList<String> colThree = tableArray.get(2);
        ArrayList<String> colFour = tableArray.get(3);
        int colOneWidth = findMaxStringLength(colOne);
        int colTwoWidth = findMaxStringLength(colTwo);
        int colThreeWidth = findMaxStringLength(colThree);
        int colFourWidth = findMaxStringLength(colFour);
        int totalWidth = colOneWidth + colTwoWidth + colThreeWidth + colFourWidth + 3;

        table += "\t╭";
        for (int i = 0; i < totalWidth; i++) {
            if (i == colOneWidth || i == colOneWidth + colTwoWidth + 1 || i == colOneWidth + colTwoWidth + colThreeWidth + 2) {
                table += "┬";
            } else {
                table += "─";
            }
        }
        table += "╮\n";
        for (int i = 0; i < colOne.size(); i++) {
            table += "\t" + getTableBorderSymbol(i,0);
            table += colOne.get(i);
            for (int j = colOne.get(i).length(); j < colOneWidth; j++) {  // Adds spaces to each entry such that the length of the String equals colOneWidth
                table += " ";
            }
            table += getTableBorderSymbol(i,1);
            table += colTwo.get(i);
            for (int j = colTwo.get(i).length(); j < colTwoWidth; j++) {
                table += " ";
            }
            table += getTableBorderSymbol(i,2);
            table += colThree.get(i);
            for (int j = colThree.get(i).length(); j < colThreeWidth; j++) {
                table += " ";
            }
            table += getTableBorderSymbol(i,3);
            table += colFour.get(i);
            for (int j = colFour.get(i).length(); j < colFourWidth; j++) {
                table += " ";
            }
            table += getTableBorderSymbol(i,4) + "\n";
        }
        table += "\t╰";
        for (int i = 0; i < totalWidth; i++) {
            if (i == colOneWidth || i == colOneWidth + colTwoWidth + 1 || i == colOneWidth + colTwoWidth + colThreeWidth + 2) {
                table += "┴";
            } else {
                table += "─";
            }
        }
        table += "╯";
        return table;
    }

    private static int findMaxStringLength(ArrayList<String> anArrayList) {
        int maxStringLength = 0;
        for (String anEntry : anArrayList) {
            if (anEntry.length() > maxStringLength) {
                maxStringLength = anEntry.length();
            }
        }
        return maxStringLength;
    }

    private static String getTableBorderSymbol(int rowNumber, int columnNumber) {
        if (rowNumber == 1) {
            switch (columnNumber) {
                case 0:
                    return "├";
                case 4:
                    return "┤";
                default:
                    return "┼";
            }
        }
        return "│";
    }
}
