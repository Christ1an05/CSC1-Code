import java.io.FileNotFoundException;
import java.util.Scanner;


class SudokuGame {

        public static void main(String[] args) throws FileNotFoundException {

        Sudoku sudoku = new Sudoku(args[0]);

        Scanner input = new Scanner(System.in);


        System.out.println(sudoku);
        System.out.print("Enter the next row, column, and value (separated by spaces): ");

        int inpRow = input.nextInt();
        int inpCol = input.nextInt();
        int inValue = input.nextInt();

        sudoku.setValue(inpRow, inpCol, inValue);
        System.out.println(sudoku);



        }


}
