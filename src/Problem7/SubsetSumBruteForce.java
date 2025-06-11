package Problem7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetSumBruteForce {

    public static void main(String[] args) {
        int[] conjunto = {-7, -3, -2, 5, 8};

        System.out.println("Analisando o conjunto: " + Arrays.toString(conjunto));
        encontrarSomaZero(conjunto);
    }

    public static void encontrarSomaZero(int[] array) {
        int n = array.length;
        int totalSubconjuntos = 1 << n;

        for (int i = 1; i < totalSubconjuntos; i++) {

            List<Integer> subconjuntoAtual = new ArrayList<>();
            int somaAtual = 0;

            System.out.println("\n--- Testando combinação " + i + " ---");

            for (int j = 0; j < n; j++) {

                if (((i >> j) & 1) == 1) {
                    subconjuntoAtual.add(array[j]);
                    somaAtual += array[j];
                    System.out.println("Incluindo: " + array[j]);
                }
            }

            System.out.println("Subconjunto: " + subconjuntoAtual + " | Soma: " + somaAtual);

            if (somaAtual == 0) {
                System.out.println("\n*** SOLUÇÃO ENCONTRADA! ***");
                System.out.println("Subconjunto com soma zero: " + subconjuntoAtual);
                return;
            }
        }

        System.out.println("\nNenhuma solução encontrada.");
    }
}