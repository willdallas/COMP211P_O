import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import static java.lang.System.out;

class LeaderBoard {

    private static ArrayList<ArrayList<String>> table;
    private static int[] colWidths;
    private static int totalWidth;

    static void leaderBoard() {
        Scanner scan = new Scanner(System.in);
        table = new ArrayList<ArrayList<String>>(4);
        colWidths = null;
        totalWidth = 0;
        ArrayList<User> orderedUsersArray = UserManagement.getUserObjects();
        Collections.sort(orderedUsersArray, Collections.<User>reverseOrder()); // Orders array by percentage correct, using compareTo() method in User

        MiscFunctions.clearScreen();
        out.print(MiscFunctions.getStringWithBorder("Leader Board"));

        table.get(0).add(" Rank ");
        table.get(0).add("──────");
        for (int i = 0; i < orderedUsersArray.size(); i++) {
            if (orderedUsersArray.get(i).getNumGames() != 0) {
                table.get(0).add(" " + (i + 1));
            } else {
                table.get(0).add(" Hasn't played yet! ");
            }
        }
        table.get(1).add(" Username (First Name) ");
        table.get(1).add("───────────────────────");
        for (User aUser : orderedUsersArray) {
            table.get(1).add(" " + aUser.getUsername() + " (" + aUser.getFirstName() + ") ");
        }
        table.get(2).add(" Games played ");
        table.get(2).add("──────────────");
        for (User aUser : orderedUsersArray) {
            table.get(2).add(" " + aUser.getNumGames());
        }
        table.get(3).add(" % of answers correct ");
        table.get(3).add("──────────────────────");
        for (User aUser : orderedUsersArray) {
            table.get(3).add(" " + aUser.getPercentageCorrect() + "% ");
        }

        out.println("\n");
        out.print(formatTable());
        out.print("\n\n\tPress enter to return to the menu: ");
        scan.nextLine();
    }

    private static String formatTable() {  // Used to dynamically format a table based on the lengths of entries
        String tableString = "";
        colWidths = new int[]{getMaxLength(table.get(0)), getMaxLength(table.get(1)), getMaxLength(table.get(2)), getMaxLength(table.get(3))};
        for (int aColumnWidth : colWidths) {
            totalWidth += aColumnWidth;
        }
        padStrings();
        for (int i = 0; i < table.get(0).size() + 2; i++) {
            tableString += getRow(i);
        }
        return tableString;
    }

    private static String getRow(int rowNumber) {
        String row = "\t";
        if (rowNumber == 0 || rowNumber == 2 || rowNumber == table.get(0).size() + 1) {
            row += (rowNumber > 2) ? "╰" : (rowNumber == 0) ? "╭" : "├";
            for (int i = 0; i < totalWidth + 3; i++) {
                if (i == colWidths[0] || i == colWidths[0] + colWidths[1] + 1 || i == totalWidth - colWidths[3] + 2) {
                    row += (rowNumber > 2) ? "┴" : (rowNumber == 0) ? "┬" : "┼";
                } else {
                    row += "─";
                }
            }
            row += (rowNumber > 2) ? "╯\n" : (rowNumber == 0) ? "╮\n" : "┤\n";
            return row;
        }
        for (ArrayList<String> aColumn : table) {
            row += ("│" + aColumn.get(rowNumber - 1));
        }
        row += "│\n";
        return row;
    }

    private static int getMaxLength(ArrayList<String> anArrayList) {
        int maxStringLength = 0;
        for (String anEntry : anArrayList) {
            if (anEntry.length() > maxStringLength) {
                maxStringLength = anEntry.length();
            }
        }
        return maxStringLength;
    }

    private static void padStrings() {   // Adds spaces to entries in columns that are shorter than the longest entry
        for (int i = 0; i < table.size(); i++) {
            for (int j = 0; j < table.get(i).size(); j++) {
                for (int k = table.get(i).get(j).length(); k < colWidths[i]; k++) {
                    table.get(i).set(j, table.get(i).get(j) + " ");
                }
            }
        }
    }
}
