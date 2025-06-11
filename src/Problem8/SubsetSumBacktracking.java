package Problem8;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class SubsetSumBacktracking {

    public static void main(String[] args) {
        int[] conjunto = {-7, -3, -2, 5, 8, 7};

        System.out.println("Analisando o conjunto: " + Arrays.toString(conjunto));

        findSubsets(conjunto);
    }

    public static void findSubsets(int[] array) {
        List<List<Integer>> solucoesEncontradas = new ArrayList<>();
        backtracking(array, 0, new ArrayList<>(), solucoesEncontradas);

        if (solucoesEncontradas.isEmpty()) {
            System.out.println("\nNenhum subconjunto com soma zero foi encontrado.");
        } else {
            System.out.println("\nSubconjunto(s) com soma zero encontrado(s):");
            for (List<Integer> solucao : solucoesEncontradas) {
                System.out.println(solucao);
            }
        }
    }

    private static void backtracking(int[] array, int indice, List<Integer> subconjuntoAtual, List<List<Integer>> solucoesEncontradas) {

         System.out.println("Índice: " + indice + ", Subconjunto Parcial: " + subconjuntoAtual);

        if (indice == array.length) {
            if (!subconjuntoAtual.isEmpty()) {
                int soma = 0;
                for (int numero : subconjuntoAtual) {
                    soma += numero;
                }

                if (soma == 0) {
                    solucoesEncontradas.add(new ArrayList<>(subconjuntoAtual));
                }
            }
            return;
        }

        backtracking(array, indice + 1, subconjuntoAtual, solucoesEncontradas);

        subconjuntoAtual.add(array[indice]);
        backtracking(array, indice + 1, subconjuntoAtual, solucoesEncontradas);
        subconjuntoAtual.remove(subconjuntoAtual.size() - 1);
    }
}