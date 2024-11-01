import java.io.FileNotFoundException;
import java.util.Scanner;


class SudokuGame {

        public static void main(String[] args) throws FileNotFoundException {

        Sudoku sudoku = new Sudoku(args[0]);

        Scanner input = new Scanner(System.in);




        while(true) {

        System.out.println(sudoku);
        System.out.println();
        System.out.print("Enter the next row, column, and value (separated by spaces): ");

        int inpRow = input.nextInt();
        int inpCol = input.nextInt();
        int inpValue = input.nextInt();

        //System.out.println(sudoku.getBoxValues(inpRow, inpCol));
        //System.out.println(sudoku.getRowValues(inpRow));
        //System.out.println(sudoku.getColumnValues(inpCol));

                if(sudoku.isValid(inpRow, inpCol, inpValue)) {

                        sudoku.setValue(inpRow, inpCol, inpValue);
                        System.out.println(sudoku);
                }

                else {

                        System.out.println("Invalid move");

                }
        }



        }


}
