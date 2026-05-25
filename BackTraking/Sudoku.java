package BackTraking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class Sudoku {
    private static char[][] sudoku = new char[3][3];
    private static ArrayList<Character> naturais = new ArrayList<>(Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9'));
    private static final Random rand = new Random();
    private static ArrayList<Character> valorTestado = new ArrayList<>();
    private static char[][] teste = new char[3][3];


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(args[0]));


        String linha;
        int posY = 0;

        while ((linha = br.readLine()) != null) {
            for (int i = 0; i < linha.length(); i++) {
                sudoku[posY][i] = linha.charAt(i);
            }
            posY++;
        }


        teste[0][0] = '7';
        teste[0][1] = '.';
        teste[0][2] = '5';
        teste[1][0] = '.';
        teste[1][1] = '.';
        teste[1][2] = '2';
        teste[2][0] = '1';
        teste[2][1] = '.';
        teste[2][2] = '.';


//        for (int i = 0; i < teste.length; i++) {
//            for (int j = 0; j < teste.length; j++) {
//                sudoku[i][j] = teste[i][j];
//            }
//        }

        valorExiste();

        printSudoku();

        preencheTabuleiro(0, 0);

    }

    ///  Remove os valores já existentes do quadrante para não repetir
    private static void valorExiste() {
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku.length; j++) {
                if (naturais.contains(sudoku[i][j])) {
                    naturais.remove(Character.valueOf(sudoku[i][j]));
                }

            }
        }

    }

    /// Preencher um tabuleir - simples até o momento (problema: ao encontrar o mesmo valor em uma posição mais longe, não retorna para a posição desejada)
    private static void preencheTabuleiro(int posX, int posY) {

        // Controle da linha
        if (posY > sudoku.length - 1) {
            posX++;
            posY = 0;
        }


        //condicao de parada
        if (posX == sudoku.length) {
            return;
        }

        //Pensando que o valor testado agora é similar ao útlimo adicionado
//        if (valorTestado.contains(sudoku[posX][posY])) {
//            return;
//        }


        // Preenche a posição que chegou
        if (sudoku[posX][posY] == '.') {
            int index = rand.nextInt(naturais.size()); //pegar uma posição aleatória para um valor aleatório da lista
            sudoku[posX][posY] = naturais.get(index);
            printSudoku();

            valorTestado.add(naturais.get(index)); // Adiciona o valor testado

            naturais.remove(index); // Remove valor do array para não ter risco de inserir novamente
            preencheTabuleiro(posX, posY + 1); // preencher em linha

            //removeValores(posX, posY);
            //posY--;

        } else {
            // Se ja tiver um valor, pula para próxima posição
            char character = sudoku[posX][posY];
            naturais.remove(Character.valueOf(character));

        }


        preencheTabuleiro(posX, posY + 1); // preencher em linha


    }

    public static void removeValores(int x, int y) {
        valorTestado.removeLast();
        sudoku[x][y] = '.';
    }

    /// Printando o que foi lido
    public static void printSudoku() {
        System.out.println("Tabuleiro: ");
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku[i].length; j++) {
                System.out.print(sudoku[i][j] + " ");
            }
            System.out.println();
        }


    }

}
