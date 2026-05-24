package BackTraking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Sudoku {
    private static char[][] sudoku = new char[3][3];


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(args[0]));

        String linha;
        String [] conteudo;
        int posY = 0;

        while ((linha = br.readLine()) != null) {
            for (int i = 0; i < linha.length() ; i++) {
                sudoku[posY][i] = linha.charAt(i);
            }
            posY++;
        }

        printSudoku();

    }

    public static  void printSudoku(){
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku[i].length; j++) {
                System.out.print(sudoku[i][j] + " ");
            }
            System.out.println();
        }


    }

}
