package Problem10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CaixeiroViajanteForcaBruta {

    public static void main(String[] args) {
        List<Cit> cidades = new ArrayList<>();
        cidades.add(new Cit(0, 0));
        cidades.add(new Cit(2, 5));
        cidades.add(new Cit(5, 2));
        cidades.add(new Cit(7, 4));
        cidades.add(new Cit(8, 7));
        cidades.add(new Cit(4, 9));
        cidades.add(new Cit(1, 6));
        cidades.add(new Cit(3, 8));
        cidades.add(new Cit(6, 1));
        cidades.add(new Cit(9, 3));

        encontrarMelhorRota(cidades);
    }


    private static double calcularDistancia(Cit c1, Cit c2) {
        return Math.sqrt(Math.pow(c1.x - c2.x, 2) + Math.pow(c1.y - c2.y, 2));
    }

    private static double calcularDistanciaTotal(List<Cit> rota) {
        double distanciaTotal = 0.0;
        for (int i = 0; i < rota.size() - 1; i++) {
            distanciaTotal += calcularDistancia(rota.get(i), rota.get(i + 1));
        }
        distanciaTotal += calcularDistancia(rota.getLast(), rota.getFirst());
        return distanciaTotal;
    }

    private static void gerarPermutacoes(List<Cit> cidades, int inicio, List<List<Cit>> todasAsRotas) {
        if (inicio == cidades.size() - 1) {
            todasAsRotas.add(new ArrayList<>(cidades));
            return;
        }
        for (int i = inicio; i < cidades.size(); i++) {
            Collections.swap(cidades, inicio, i);
            gerarPermutacoes(cidades, inicio + 1, todasAsRotas);
            Collections.swap(cidades, inicio, i); // Backtrack
        }
    }

    private static void encontrarMelhorRota(List<Cit> cidades) {
        List<List<Cit>> todasAsRotas = new ArrayList<>();
        gerarPermutacoes(cidades, 0, todasAsRotas);

        List<Cit> melhorRota = null;
        double menorDistancia = Double.POSITIVE_INFINITY;

        for (List<Cit> rota : todasAsRotas) {
            double distanciaAtual = calcularDistanciaTotal(rota);
            if (distanciaAtual < menorDistancia) {
                menorDistancia = distanciaAtual;
                melhorRota = rota;
            }
        }

        System.out.println("\n--- MELHOR ROTA ENCONTRADA ---");
        mostrarRotaDetalhada(melhorRota);
    }

    private static void mostrarRotaDetalhada(List<Cit> rota) {
        if (rota == null || rota.isEmpty()) {
            System.out.println("Nenhuma rota para mostrar.");
            return;
        }

        double distanciaTotalVerificada = 0.0;

        System.out.println("Cadeia de cidades e distâncias:");
        for (int i = 0; i < rota.size(); i++) {
            Cit cidadeAtual = rota.get(i);
            Cit proximaCidade = rota.get((i + 1) % rota.size());

            double distanciaTrecho = calcularDistancia(cidadeAtual, proximaCidade);
            distanciaTotalVerificada += distanciaTrecho;

            System.out.printf("De (%d, %d) para (%d, %d) -> Distância: %.2f\n",
                    cidadeAtual.x, cidadeAtual.y,
                    proximaCidade.x, proximaCidade.y,
                    distanciaTrecho);
        }

        System.out.printf("\nDistância Total da Rota: %.2f\n", distanciaTotalVerificada);
    }

    private static class Cit {
        int x;
        int y;

        public Cit(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}