import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import static java.lang.System.out;

class LeaderBoard {

    private static ArrayList<ArrayList<String>> table;
    private static int[] colWidths;
    private static int totalWidth;

    static void leaderBoard() { // Sorts users, fills array with data on Users, then prints table
        Scanner scan = new Scanner(System.in);
        table = new ArrayList<>(4);
        totalWidth = 0;
        ArrayList<User> sortedUsers = UserManagement.getUserObjects();
        Collections.sort(sortedUsers, Collections.<User>reverseOrder()); // Orders array by percentage correct, using compareTo() method in User

        MiscFunctions.clearScreen();
        out.print(MiscFunctions.getStringWithBorder("Leader Board"));


        table.add(new ArrayList<>(Collections.singletonList(" Name  (Username) ")));
        for (User aUser : sortedUsers) {
            table.get(0).add(" " + aUser.getFirstName() + " " + aUser.getLastName() + "  (" + aUser.getUsername() + ") ");
        }
        table.add(new ArrayList<>(Collections.singletonList(" Games played ")));
        for (User aUser : sortedUsers) {
            table.get(1).add(" " + aUser.getNumGames());
        }
        table.add(new ArrayList<>(Collections.singletonList(" % correct ")));
        for (User aUser : sortedUsers) {
            if (aUser.getNumGames() != 0) {
                table.get(2).add(" " + aUser.getPercentageCorrect() + "% ");
            } else {
                table.get(2).add(" N/A ");
            }
        }
        table.add(new ArrayList<>(Collections.singletonList(" Avg. time per question ")));
        for (int i = 0; i < sortedUsers.size(); i++) {
            if (sortedUsers.get(i).getNumGames() != 0) {
                table.get(3).add(" " + String.format("%.2f", sortedUsers.get(i).getAverageTimeTaken()) + " seconds ");
            } else {
                table.get(3).add(" N/A ");
            }
        }

        out.println("\n");
        out.print(formatTable());

        out.print("\n\n\tPress enter to return to the menu: ");
        scan.nextLine();
    }

    private static String formatTable() {  // Used to dynamically format a table based on the number and lengths of entries
        String tableString = "";
        colWidths = new int[]{getMaxLength(table.get(0)), getMaxLength(table.get(1)), getMaxLength(table.get(2)), getMaxLength(table.get(3))};

        for (int aColumnWidth : colWidths) {
            totalWidth += aColumnWidth;
        }

        padStrings();

        for (int i = 0; i < table.get(0).size() + 3; i++) {
            tableString += getRow(i);
        }
        return tableString;
    }

    private static String getRow(int rowNumber) {  // Returns a String for each row of the table, using Unicode special characters
        String row = "\t";
        if (rowNumber == 0 || rowNumber == 2 || rowNumber == table.get(0).size() + 2) {
            row += (rowNumber > 2) ? "╰" : (rowNumber == 0) ? "╭" : "├";
            for (int i = 0; i < totalWidth + 3; i++) {
                if (i == colWidths[0] || i == colWidths[0] + colWidths[1] + 1 || i == totalWidth - colWidths[3] + 2) {
                    row += (rowNumber > 2) ? "┴" : (rowNumber == 0) ? "┬" : "┼";
                } else {
                    row += "─";
                }
            }
            row += (rowNumber > 2) ? "╯" : (rowNumber == 0) ? "╮" : "┤";
            return row + "\n";
        }
        for (ArrayList<String> aColumn : table) {
            row += (rowNumber == 1) ? ("│" + aColumn.get(0)) : ("│" + aColumn.get(rowNumber - 2));
        }
        row += "│";
        return row + "\n";
    }

    private static int getMaxLength(ArrayList<String> anArrayList) { // Returns length of longest String in a column
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
