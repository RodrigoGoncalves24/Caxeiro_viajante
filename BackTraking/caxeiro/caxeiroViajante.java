package BackTraking.caxeiro;

import java.util.ArrayList;

public class caxeiroViajante {
    private static ArrayList<cidade> cidades = new ArrayList<>();
    private static int min_caminho = Integer.MAX_VALUE;
    private static ArrayList<cidade> jaVisitadas = new ArrayList<>();
    private static int custoTotal = 0;
    private static final int SEM_CAMINHO = Integer.MAX_VALUE;
    private static int tentativa = 0;
    private static cidade cidadeOrigem;

    public static void main(String[] args) {
        cidadeOrigem = new cidade("A", new int[]{0, 10, 15, 20, SEM_CAMINHO, SEM_CAMINHO});

        cidades.add(cidadeOrigem);
        cidades.add(new cidade("B", new int[]{10, 0, 9, SEM_CAMINHO, 9, SEM_CAMINHO}));
        cidades.add(new cidade("C", new int[]{15, 9, 0, 12, 10,SEM_CAMINHO }));
        cidades.add(new cidade("D", new int[]{20, SEM_CAMINHO, 12, 0, SEM_CAMINHO, 6}));
        cidades.add(new cidade("E", new int[]{SEM_CAMINHO, 9, 10, SEM_CAMINHO, 0, 8}));
        cidades.add(new cidade("F", new int[]{SEM_CAMINHO, SEM_CAMINHO, SEM_CAMINHO, 6, 8, 0}));

        int indexCidadeOrigem = cidades.indexOf(cidadeOrigem);

        jaVisitadas.add(cidadeOrigem);
        caxeiroAnda(custoTotal, cidadeOrigem, indexCidadeOrigem);

    }

    private static void caxeiroAnda(int custoTotal, cidade proximaCidade, int indexCidadeOrigem) {

        if (jaVisitadas.size() == cidades.size()) {

            //Visitei todas mas preciso voltar diretamente para a cidade de origem
            int distanciaVoltaCidadeOrigem = proximaCidade.getDistancia(indexCidadeOrigem);

            if(distanciaVoltaCidadeOrigem != SEM_CAMINHO){
                int custoFinal = custoTotal + distanciaVoltaCidadeOrigem;
                jaVisitadas.add(cidadeOrigem);

                if (custoFinal < min_caminho) { ///  ACHOU O MENOR CAMINHO
                    System.out.println("\nCUSTO: " + custoFinal);
                    printCaminho();
                    min_caminho = custoFinal;
                }
                jaVisitadas.removeLast();
            }else{
                System.out.println("NÃO ACHIE CAMINHO DE VOLTA!!");
            }
            return;
        }

        int[] distanciaCidades = proximaCidade.getDistancias();

        /// PERCORRE TODAS AS DISTÂNCIA DE UMA CIDADE PARA ACHAR UMA VÁLIDA
        for (int i = 0; i < distanciaCidades.length; i++) {


            int distancia = distanciaCidades[i];

            // Esta olhando a mesma cidade
            if (distancia == 0 || distancia == SEM_CAMINHO) {
                continue;
            }

            // ou seja, a cidade cujo i parou
            cidade cidadeDestino = cidades.get(i);

            // Caso a cidade não foi visitada, visita
            if (!jaVisitadas.contains(cidadeDestino)) {

                //Adiciona no caminho
                jaVisitadas.add(cidadeDestino);
                caxeiroAnda(custoTotal + distancia, cidadeDestino, indexCidadeOrigem);

                jaVisitadas.removeLast();
                //caxeiroAnda(custoTotal, index, indexCidades, proximaCidade);

            }

        }


    }

    private static void printCaminho() {
        System.out.println("\nTENTATIVA: " + tentativa + "\n");

        for (cidade c : jaVisitadas) {
            System.out.print("Cidade: " + c.getNme() + " -> ");
        }
        tentativa++;
        System.out.println();
    }
}
