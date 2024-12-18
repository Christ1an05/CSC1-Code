import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

class Sudoku {

private int[][] grid;
private int[][] startingGrid;

public Sudoku(String sudokuFile) throws FileNotFoundException  {

        grid = new int[9][9];
        startingGrid = new int[9][9];
        FileReader fileReader = new FileReader(sudokuFile);
        Scanner scanner = new Scanner(fileReader);

        for(int i = 0; i < 9; i ++) {

                for(int j = 0; j < 9; j ++) {

                        if(scanner.hasNextInt()) {
                                grid[i][j] = scanner.nextInt();
                                startingGrid[i][j] = grid[i][j];
                        }

                        else {

                                String sym = scanner.next();

                                if(sym.equals("-")) {
                                        grid[i][j] = 0;
                                        startingGrid[i][j] = 0;

                                }

                        }

                                //scanner.close();

                        }
                }
        }


        public String toString() {
                String output = "";

                for(int i = 0; i < 3; i ++) { //create first 3 rows

                        for(int j = 0; j < 3; j ++) { //create first 3 columns

                                if(grid[i][j] != 0) {
                                        output += grid[i][j];
                                }

                                else {
                                        output += "-";
                                }

                                output += " ";
                        }

                        output += "|";

                        for(int j = 3; j < 6; j ++) { //create second 3 columns

                                if(grid[i][j] != 0) {
                                        output += grid[i][j];
                                }

                                else {
                                        output += "-";
                                }

                                output += " ";
                        }

                        output += "|";

                        for(int j = 6; j < 9; j ++) { //create third 3 columns

                                if(grid[i][j] != 0) {
                                        output += grid[i][j];
                                }

                                else {
                                        output += "-";
                                }

                                output += " ";
                        }

                        output += "\n";


                }

                output += "---------------------";
                output += "\n";

                for(int i = 3; i < 6; i ++) { //create second 3 rows

                        for(int j = 0; j < 3; j ++) { //create first 3 columns

                                if(grid[i][j] != 0) {
                                        output += grid[i][j];
                                }

                                else {
                                        output += "-";
                                }

                                output += " ";
                        }

                        output += "|";

                        for(int j = 3; j < 6; j ++) { //create second 3 columns

                                if(grid[i][j] != 0) {
                                        output += grid[i][j];
                                }

                                else {
                                        output += "-";
                                }

                                output += " ";
                        }

                        output += "|";

                        for(int j = 6; j < 9; j ++) { //create third 3 columns

                                if(grid[i][j] != 0) {
                                        output += grid[i][j];
                                }

                                else {
                                        output += "-";
                                }

                                output += " ";
                        }

                        output += "\n";


                }

                output += "---------------------";
                output += "\n";


                for(int i = 6; i < 9; i ++) { //create third 3 rows

                        for(int j = 0; j < 3; j ++) { //create first 3 columns

                                if(grid[i][j] != 0) {
                                        output += grid[i][j];
                                }

                                else {
                                        output += "-";
                                }

                                output += " ";
                        }

                        output += "|";

                        for(int j = 3; j < 6; j ++) { //create second 3 columns

                                if(grid[i][j] != 0) {
                                        output += grid[i][j];
                                }

                                else {
                                        output += "-";
                                }

                                output += " ";
                        }

                        output += "|";

                        for(int j = 6; j < 9; j ++) { //create third 3 columns

                                if(grid[i][j] != 0) {
                                        output += grid[i][j];
                                }

                                else {
                                        output += "-";
                                }

                                output += " ";
                        }

                        output += "\n";


                }

                return output;         

        }       


        public int getValue(int row, int col) {

                assert row >= 1 && row <= 9: "row must be between 1 and 9";
                assert col >= 1 && col <= 9: "column must be between 1 and 9";

                return grid[row-1][col-1];



        }

        public void setValue(int row, int col, int value) {

                if(grid[row-1][col-1] == 0) {
                        grid[row-1][col-1] = value;
                }

                assert row >= 1 && row <= 9: "row must be between 1 and 9";
                assert col >= 1 && col <= 9: "column must be between 1 and 9";
                assert value >= 1 && value <= 9: "value must be between 1 and 9";


        }


        private ArrayList<Integer> getRowValues(int row) {

                assert row >= 1 && row <= 9: "row must be between 1 and 9";

                ArrayList<Integer> rowValues = new ArrayList<Integer>();

                for(int j = 0; j < 9; j++) {

                        if(grid[row-1][j] != 0) {
                        rowValues.add(grid[row-1][j]);                                         
                        }       
                }

                return rowValues;

        }

        private ArrayList<Integer> getColumnValues(int col) {

                assert col >= 1 && col <= 9: "column must be between 1 and 9";

                ArrayList<Integer> colValues = new ArrayList<Integer>();

                for(int i = 0; i < 9; i++) {

                        if(grid[i][col-1] != 0) {
                                colValues.add(grid[i][col-1]);

                        }       
                }

                return colValues;

        }


        private ArrayList<Integer> getBoxValues(int row, int col) {

                ArrayList<Integer> boxValues = new ArrayList<Integer>();

                assert row >= 1 && row <= 9: "row must be between 1 and 9";
                assert col >= 1 && col <= 9: "column must be between 1 and 9";

                if(row - 1 >= 0 && row -1 <= 2 && col -1 >= 0 && col - 1 <= 2) { //checks if row,column is in box 1

                        for(int i = 0; i < 3; i ++) {

                                for(int j = 0; j < 3; j++) {
                                        if(grid[i][j] != 0) {
                                                boxValues.add(grid[i][j]);
                                        }
                                }
                        }
                }

                else if(row - 1 >= 0 && row -1 <= 2 && col -1 >= 3 && col -1  <= 5) {

                        for(int i = 0; i < 3; i ++) {

                                for(int j = 3; j < 6; j++) {
                                        if(grid[i][j] != 0) {
                                                boxValues.add(grid[i][j]);
                                        }
                                }
                        }
                }

                else if(row - 1 >= 0 && row -1 <= 2 && col -1 >= 6 && col -1  <= 8) {

                        for(int i = 0; i < 3; i ++) {

                                for(int j = 6; j < 9; j++) {
                                        if(grid[i][j] != 0) {
                                                boxValues.add(grid[i][j]);
                                        }
                                }
                        }
                }

                else if(row - 1 >= 3 && row -1 <= 5 && col -1 >= 0 && col -1  <= 2) {

                        for(int i = 3; i < 6; i ++) {

                                for(int j = 0; j < 3; j++) {
                                        if(grid[i][j] != 0) {
                                                boxValues.add(grid[i][j]);
                                        }
                                }
                        }
                }

                else if(row - 1 >= 3 && row -1 <= 5 && col -1 >= 3 && col -1  <= 5) {

                        for(int i = 3; i < 6; i ++) {

                                for(int j = 3; j < 6; j++) {
                                        if(grid[i][j] != 0) {
                                                boxValues.add(grid[i][j]);
                                        }
                                }
                        }
                }

                else if(row - 1 >= 3 && row -1 <= 5 && col -1 >= 6 && col -1  <= 8) {

                        for(int i = 3; i < 6; i ++) {

                                for(int j = 6; j < 9; j++) {
                                        if(grid[i][j] != 0) {
                                                boxValues.add(grid[i][j]);
                                        }
                                }
                        }
                }

                else if(row - 1 >= 6 && row -1 <= 8 && col -1 >= 0 && col -1  <= 2) {

                        for(int i = 6; i < 9; i ++) {

                                for(int j = 0; j < 3; j++) {
                                        if(grid[i][j] != 0) {
                                                boxValues.add(grid[i][j]);
                                        }
                                }
                        }
                }

                else if(row - 1 >= 6 && row -1 <= 8 && col -1 >= 3 && col -1  <= 5) {

                        for(int i = 6; i < 9; i ++) {

                                for(int j = 3; j < 6; j++) {
                                        if(grid[i][j] != 0) {
                                                boxValues.add(grid[i][j]);
                                        }
                                }
                        }
                }

                else if(row - 1 >= 6 && row -1 <= 8 && col -1 >= 6 && col -1  <= 8) {

                        for(int i = 6; i < 9; i ++) {

                                for(int j = 6; j < 9; j++) {
                                        if(grid[i][j] != 0) {
                                                boxValues.add(grid[i][j]);
                                        }
                                }
                        }
                }

                return boxValues;


        } //end box method


        public boolean isValid(int row, int col, int val) {


        if(val < 1 || val > 9) {
                return false;
        }


        if(getValue(row, col) != 0) {
                return false;
        }

        else {

                for(int i = 0; i < getBoxValues(row, col).size(); i ++) {

                        if(getBoxValues(row, col).get(i) == val) {
                                return false;
                        }               
                }

                for(int i = 0; i < getRowValues(row).size(); i ++) {

                        if(getRowValues(row).get(i) == val) {
                                return false;
                        }
                }

                for(int i = 0; i < getColumnValues(col).size(); i ++) {

                        if(getColumnValues(col).get(i) == val) {
                                return false;
                        }
                }

        }

        return true;

        }
}
