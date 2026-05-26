package BackTraking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Sudoku {
    private static final char[][] sudoku = new char[3][3];
    private static final ArrayList<Character> naturais = new ArrayList<>(Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9'));
    private static final Random rand = new Random();
    private static final ArrayList<Character> valorTestado = new ArrayList<>();
    private static final Map<Character, Integer> valorTestadoMap = new LinkedHashMap<>();
    private static final char[][] teste = new char[3][3];


    public static void main(String[] args) throws IOException {
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
        teste[0][0] = '7';
        teste[0][1] = '.';
        teste[0][2] = '1';
        teste[1][0] = '.';
        teste[1][1] = '.';
        teste[1][2] = '2';
        teste[2][0] = '5';
        teste[2][1] = '.';
        teste[2][2] = '.';

        valorTestadoMap.put('1', 0);
        valorTestadoMap.put('2', 0);
        valorTestadoMap.put('3', 0);
        valorTestadoMap.put('4', 0);
        valorTestadoMap.put('5', 0);
        valorTestadoMap.put('6', 0);
        valorTestadoMap.put('7', 0);
        valorTestadoMap.put('8', 0);
        valorTestadoMap.put('9', 0);

        for (int i = 0; i < teste.length; i++) {
            for (int j = 0; j < teste.length; j++) {
                sudoku[i][j] = teste[i][j];
            }
        }

        //valorExiste();

        printSudoku();

        preencheTabuleiroComBackTraking(0, 0, 0);

    }

    ///  Remove os valores já existentes do quadrante para não repetir (matriz 3X3)
    private static void valorExiste() {
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku.length; j++) {
                if (naturais.contains(sudoku[i][j])) {
                    naturais.remove(Character.valueOf(sudoku[i][j]));
                }

            }
        }

    }

    /// Preencher o tabuleiro com backtraking
    private static void preencheTabuleiroComBackTraking(int posX, int posY, int posicao) {

        // Controle da linha

        //condicao de parada
        if (sudoku[sudoku.length - 1][sudoku.length - 1] != '.') {
            printSudoku();
            return;
        }


        for (int i = 0; i < sudoku.length; i++) {

            if (posY > sudoku.length - 1) {
                posX++;
            }

            char natural = naturais.get(i);

            char charPosicao = sudoku[posX][i];

            if (charPosicao == '.') {

                // Posso usar aquele valor
                if (valorTestadoMap.get(natural) == 0) {
                    sudoku[posX][i] = natural;
                    valorTestadoMap.put(natural, 1);
                    printSudoku();

                    preencheTabuleiroComBackTraking(posX, i + 1, posicao + 1); // preencher em linha

                    // Desfaz a última escolha
                    sudoku[posX][i] = '.';
                    valorTestadoMap.put(natural, 0);
                    printSudoku();
                }

            } else {
                // Verifica se o valor já não foi posto, se sim, retorna e tenta outro
                if (valorTestadoMap.get(charPosicao) == 1) {
                    return;
                }
                valorTestadoMap.put(charPosicao, 1);
            }

        }

    }


    /// Preencher um tabuleir - simples e sem backtraking de fato, funciona com recursão, pode ser feito com um for iterativo pelas posições e colocando os valores...
    private static void preencheTabuleiroSemBackTraking(int posX, int posY) {

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

            int valorRemovido = naturais.remove(index); // Remove valor do array para não ter risco de inserir novamente
            preencheTabuleiroSemBackTraking(posX, posY + 1); // preencher em linha

            //removeValores(posX, posY);
            //posY--;

        } else {
            // Se ja tiver um valor, pula para próxima posição
            char character = sudoku[posX][posY];
            naturais.remove(Character.valueOf(character));

        }


        preencheTabuleiroSemBackTraking(posX, posY + 1); // preencher em linha


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
