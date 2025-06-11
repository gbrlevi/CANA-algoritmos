package Problem6;

public class KnightTour {

    private static final int N = 8;

    private static final int[] moveX = { 2, 1, -1, -2, -2, -1,  1,  2 };
    private static final int[] moveY = { 1, 2,  2,  1, -1, -2, -2, -1 };

    public static void main(String[] args) {

        int[][] tabuleiro = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tabuleiro[i][j] = -1;
            }
        }

        int casaInicialX = 0;
        int casaInicialY = 0;
        tabuleiro[casaInicialX][casaInicialY] = 0;

        if (resolverPasseio(casaInicialX, casaInicialY, 1, tabuleiro)) {
            System.out.println("Solução encontrada:");
            imprimirTabuleiro(tabuleiro);
        } else {
            System.out.println("Não existe solução a partir do ponto inicial.");
        }
    }

    private static boolean resolverPasseio(int x, int y, int moveCount, int[][] tabuleiro) {
        if (moveCount == N * N) {
            return true;
        }

        for (int k = 0; k < 8; k++) {
            int proximoX = x + moveX[k];
            int proximoY = y + moveY[k];

            if (ehValido(proximoX, proximoY, tabuleiro)) {
                tabuleiro[proximoX][proximoY] = moveCount;

                if (resolverPasseio(proximoX, proximoY, moveCount + 1, tabuleiro)) {
                    return true;
                } else {
                    tabuleiro[proximoX][proximoY] = -1;
                }
            }
        }

        return false;
    }

    private static boolean ehValido(int x, int y, int[][] tabuleiro) {
        return (x >= 0 && x < N && y >= 0 && y < N && tabuleiro[x][y] == -1);
    }

    private static void imprimirTabuleiro(int[][] tabuleiro) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%2d ", tabuleiro[i][j]);
            }
            System.out.println();
        }
    }
}