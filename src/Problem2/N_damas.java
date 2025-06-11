package Problem2;

public class N_damas {
    public static void main(String[] args) {

        int n = 10;

        int[][] tabuleiro = new int[n][n];

        if(resolver(tabuleiro, 0)){
            System.out.println("Tabuleiro " + n + "x" + n + " criado:");

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(tabuleiro[i][j] + " ");
                }
                System.out.println();
            }
        } else{
            System.out.println("Não foi possível encontrar uma solução com back-tracking");
        }


    }

    static boolean resolver(int[][] tabuleiro, int coluna){

        int n = tabuleiro.length;

        if(coluna == n){
            return true;
        }

        for(int linha = 0; linha < n; linha++){

            if(podeColocar(tabuleiro, linha, coluna)){

                tabuleiro[linha][coluna] = 1;

                if(resolver(tabuleiro, coluna+1)){
                    return true;
                }

                // Backtrack
                System.out.println("Backtrack de: (" + linha + "," + coluna + ")" );
                tabuleiro[linha][coluna] = 0;
            }
        }

        return false;
    }

    static boolean podeColocar(int[][] tabuleiro, int linha, int coluna){

        for(int j = 0; j < coluna; j++){
            if (tabuleiro[linha][j] == 1) {
                return false;
            }
        }

        for(int i = linha, j = coluna; i >= 0 && j>=0; i--, j--){
            if (tabuleiro[i][j] == 1) {
                return false;
            }
        }

        for(int i = linha, j = coluna; i < tabuleiro.length && j >=0; i++, j--){
            if (tabuleiro[i][j] == 1) {
                return false;
            }
        }

        return true;
    }
}