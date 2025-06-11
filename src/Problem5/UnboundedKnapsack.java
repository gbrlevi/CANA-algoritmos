package Problem5;

public class UnboundedKnapsack {
    public static void main(String[] args) {

        Item[] itens = {
                new Item("Barra de Ouro", 4, 7),
                new Item("Moeda de Prata", 3, 4),
                new Item("Anel de Rubi", 5, 10)
        };

        int sackCapacity = 10;

        int[][] table = calc(sackCapacity, itens);

        int maxValue = table[itens.length-1][sackCapacity];
        System.out.println("The max possible value for this weight is: " + maxValue);
        takenItens(table, sackCapacity, itens);

    }

    private static int[][] calc(int sackCapacity, Item[] itens) {

        int totalItens = itens.length -1;

        int[][] table = new int[totalItens + 1][sackCapacity + 1];

        for (int i = 1; i <= totalItens; i++) {
            for (int w = 1; w <= sackCapacity; w++) {
                if (itens[i].weight > w) {
                    table[i][w] = table[i - 1][w];
                } else {
                    int notTake = table[i - 1][w];
                    int take = itens[i].value + table[i][w - itens[i].weight];

                    table[i][w] = Math.max(take, notTake);
                }
            }
        }

        return table;
    }

    private static void takenItens(int[][] table, int sackCapacity, Item[] itens) {
        System.out.println("Taken itens (unbounded):");

        int w = sackCapacity;
        int i = itens.length - 1;

        while (i > 0 && w > 0) {
            if (table[i][w] == table[i - 1][w]) {
                i--;
            } else {
                System.out.println("- " + itens[i].name + " (Weight: " + itens[i].weight + ", Value: " + itens[i].value + ")");

                w = w - itens[i].weight;
            }
        }
    }

    private static class Item {
        String name;
        int weight;
        int value;

        public Item(String name, int weight, int value) {
            this.name = name;
            this.weight = weight;
            this.value = value;
        }
    }

}
