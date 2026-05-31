package BackTraking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Sudoku {
    private static char[][] sudoku = new char[9][9];
    private static final ArrayList<Character> naturais = new ArrayList<>(Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9'));
    private static final Random rand = new Random();
    private static Map<Integer, Map<Character, int[]>> valoresPresentes = new LinkedHashMap<>();
    private static ArrayList<int[]> posicoesEmBranco = new ArrayList<>();


    public static void main(String[] args) throws Exception {
        //     BufferedReader br = new BufferedReader(new FileReader(args[0]));


        String linha;
        int posY = 0;

        // Por linha de comando
//        while ((linha = br.readLine()) != null) {
//            for (int i = 0; i < linha.length(); i++) {
//                sudoku[posY][i] = linha.charAt(i);
//            }
//            posY++;
//        }


        // Para debug
        sudoku = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},

                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},

                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };


        // Mapeando valores existente
        valorExiste();

        printSudoku();

        // Preenchendo tabuleiro
        preencheTabuleiro(0);

    }

    /// Preencher o tabuleiro
    private static void preencheTabuleiro(int matriz) {






    }

    ///  Preenche os valores existentes para cada matriz - AJUSTAR VISÃO DAS COORDENADAS (DESENHAR...)
    private static void valorExiste() throws Exception {
        int xMax = 3;
        int yMax = 3;
        int xMin = 0;
        int yMin = 0;

        int matriz = 0;
        // Por tod o tamanho sudoku
        while (matriz < 9) {

            Map<Character, int[]> mapaAtual = new HashMap<>();

            // Pego a matriz que se inicia em i e vai até o tamanho máximo delas (conhecido)
            for (int i = xMin; i < xMax; i++) {
                for (int j = yMin; j < yMax; j++) {

                    // Preenche um mapa temporario
                    char ch = sudoku[i][j];
                    int[] coord = new int[]{i, j};


                    // Caso valor esteja repetido na matriz
                    if (mapaAtual.get(ch) != null) {
                        throw new Exception("Sudoku inválido! Valor " + ch + " já existe nessa matriz!");
                    }

                    if (ch != '.') {
                        mapaAtual.put(ch, coord);
                    } else {
                        // Guarda apenas as posicoes vazias
                        posicoesEmBranco.add(coord);
                    }

                }


            }

            int linhaQuadrante = matriz / 3;
            int colunaQuadrante = matriz % 3;

            xMin = linhaQuadrante * 3;
            xMax = xMin + 3;


            yMin = colunaQuadrante * 3;
            yMax = yMin + 3;

            //Inclui no mapa final
            valoresPresentes.put(matriz, mapaAtual);
            matriz++;
//            //Controlador das linhas que estou no momento - preguiçoso porém funciona
//            //Primeira linha
//            if (matriz == 1) {
//                xMin = 0;
//                xMax = 3;
//                yMin = 3;
//                yMax = 6;
//            } else if (matriz == 2) {
//                xMin = 0;
//                xMax = 3;
//                yMin = 6;
//                yMax = 9;
//            }
//
//            // Segunda linha
//            if (matriz == 3) {
//                xMin = 3;
//                xMax = 6;
//                yMin = 0;
//                yMax = 3;
//            } else if (matriz == 4) {
//                xMin = 3;
//                xMax = 6;
//                yMin = 3;
//                yMax = 6;
//            } else if (matriz == 5) {
//                xMin = 3;
//                xMax = 6;
//                yMin = 6;
//                yMax = 9;
//            }
//
//            // Terceira linha
//            if (matriz == 6) {
//                xMin = 6;
//                xMax = 9;
//                yMin = 0;
//                yMax = 3;
//            } else if (matriz == 7) {
//                xMin = 6;
//                xMax = 9;
//                yMin = 3;
//                yMax = 6;
//            } else if (matriz == 8) {
//                xMin = 6;
//                xMax = 9;
//                yMin = 6;
//                yMax = 9;
//            }
//

        }
    }



    /// Printando o que foi lido
    public static void printSudoku() {
        System.out.println("Tabuleiro: ");
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku.length; j++) {
                System.out.print(sudoku[i][j] + " ");
            }
            System.out.println();
        }


    }

}
