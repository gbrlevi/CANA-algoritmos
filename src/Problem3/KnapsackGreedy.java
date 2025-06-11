package Problem3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class KnapsackGreedy {

    public static void main(String[] args) {
        Objeto[] listaObjetos = {
                new Objeto("Notebook", 3.0, 3000.0),
                new Objeto("Câmera", 2.0, 2500.0),
                new Objeto("Tablet", 1.2, 1200.0),
                new Objeto("Fone de ouvido", 0.5, 500.0),
                new Objeto("Jaqueta", 2.0, 300.0),
                new Objeto("Livro", 1.5, 100.0),
                new Objeto("Garrafa", 1.0, 80.0)
        };

        double capacidadeMochila = 7.0;

        System.out.println("Capacidade máxima da mochila: " + capacidadeMochila + "kg\n");

        resolverMochilaGulosa(listaObjetos, capacidadeMochila);
    }

    private static void resolverMochilaGulosa(Objeto[] objetos, double capacidade) {

        Comparator<Objeto> comparadorDeDensidade = new Comparator<Objeto>() {
            @Override
            public int compare(Objeto o1, Objeto o2) {
                Double densidade1 = o1.preco / o1.peso;
                Double densidade2 = o2.preco / o2.peso;

                return densidade2.compareTo(densidade1);
            }
        };

        Arrays.sort(objetos, comparadorDeDensidade);

        double capacidadeRestante = capacidade;
        List<Objeto> itensNaMochila = new ArrayList<>();
        double valorTotal = 0;

        System.out.println("--- Iniciando Seleção Gulosa ---\n");

        for (Objeto obj : objetos) {
            double densidade = obj.preco / obj.peso;
            System.out.println("Analisando: " + obj.nome + " (Preco/kg: " + String.format("%.2f", densidade) + ")");

            if (obj.peso <= capacidadeRestante) {
                itensNaMochila.add(obj);
                capacidadeRestante -= obj.peso;
                valorTotal += obj.preco;
                System.out.println("-> ADICIONADO! Capacidade restante: " + String.format("%.2fkg", capacidadeRestante) + "\n");
            } else {
                System.out.println("-> PULADO (nao cabe).\n");
            }
        }

        System.out.println("\n--- Resultado Final ---");
        System.out.println("Itens colocados na mochila:");
        for (Objeto item : itensNaMochila) {
            System.out.println("- " + item.nome);
        }
        System.out.println("\nValor Total na Mochila: R$" + String.format("%.2f", valorTotal));
        System.out.println("Peso Total na Mochila: " + String.format("%.2fkg", (capacidade - capacidadeRestante)));
    }

    private static class Objeto {
        String nome;
        double peso;
        double preco;

        public Objeto(String nome, double peso, double preco) {
            this.nome = nome;
            this.peso = peso;
            this.preco = preco;
        }
    }
}