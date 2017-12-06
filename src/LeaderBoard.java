import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import static java.lang.System.out;

class LeaderBoard {

    private static ArrayList<ArrayList<String>> table;
    private static int[] colWidths;
    private static int totalWidth;
    private static final char[] borderChars = new char[]{'╭', '╮', '╰', '╯', '├', '┼', '┤', '┴', '┬', '─', '│'};

    static void leaderBoard() {
        Scanner scan = new Scanner(System.in);
        table = new ArrayList<>(4);
        colWidths = null;
        totalWidth = 0;
        ArrayList<User> orderedUsersArray = UserManagement.getUserObjects();
        Collections.sort(orderedUsersArray, Collections.<User>reverseOrder()); // Orders array by percentage correct, using compareTo() method in User

        MiscFunctions.clearScreen();
        out.print(MiscFunctions.getStringWithBorder("Leader Board"));

        table.add(new ArrayList<>(Collections.singletonList(" Rank ")));
        for (int i = 0; i < orderedUsersArray.size(); i++) {
            if (orderedUsersArray.get(i).getNumGames() != 0) {
                table.get(0).add(" " + (i + 1));
            } else {
                table.get(0).add(" Hasn't played yet! ");
            }
        }
        table.add(new ArrayList<>(Collections.singletonList(" Username (First Name) ")));
        for (User aUser : orderedUsersArray) {
            table.get(1).add(" " + aUser.getUsername() + " (" + aUser.getFirstName() + ") ");
        }
        table.add(new ArrayList<>(Collections.singletonList(" Games played ")));
        for (User aUser : orderedUsersArray) {
            table.get(2).add(" " + aUser.getNumGames());
        }
        table.add(new ArrayList<>(Collections.singletonList(" % of answers correct ")));
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
            row += (rowNumber > 2) ? borderChars[2] : (rowNumber == 0) ? borderChars[0] : borderChars[4];
            for (int i = 0; i < totalWidth + 3; i++) {
                if (i == colWidths[0] || i == colWidths[0] + colWidths[1] + 1 || i == totalWidth - colWidths[3] + 2) {
                    row += (rowNumber > 2) ? borderChars[7] : (rowNumber == 0) ? borderChars[8] : borderChars[5];
                } else {
                    row += borderChars[9];
                }
            }
            row += (rowNumber > 2) ? borderChars[3] : (rowNumber == 0) ? borderChars[1] : borderChars[6];
            return row + "\n";
        }
        for (ArrayList<String> aColumn : table) {
            row += (borderChars[10] + aColumn.get(rowNumber - 1));
        }
        row += borderChars[10];
        return row + "\n";
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
