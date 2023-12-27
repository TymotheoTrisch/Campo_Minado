package source.Classes;

import java.util.Random;

public class Tabuleiro {
    Espaco[][] matriz;
    int linhas;
    int colunas;
    int minas;

    
    // Construtor
    public Tabuleiro(Constantes c){
        linhas = c.getNum_linhas();
        colunas = c.getNum_colunas();
        minas = c.getNum_minas();
        matriz = new Espaco[linhas][colunas];
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                matriz[i][j] = new Espaco();
            }
        }

        // Logica para implementar os espaços vizinhos
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                if (i > 0){
                    if (j > 0) matriz[i][j].adicionarVizinhos(matriz[i-1][j-1]);
                    matriz[i][j].adicionarVizinhos(matriz[i-1][j]);
                    if (j < colunas-1) matriz[i][j].adicionarVizinhos(matriz[i-1][j+1]);
                }
                
                if (j > 0) matriz[i][j].adicionarVizinhos(matriz[i][j-1]);                
                if (j < colunas-1)matriz[i][j].adicionarVizinhos(matriz[i][j+1]);
                
                if(i < linhas -1){
                    if (j > 0)matriz[i][j].adicionarVizinhos(matriz[i+1][j-1]);
                    matriz[i][j].adicionarVizinhos(matriz[i+1][j]);
                    if (j < colunas-1)matriz[i][j].adicionarVizinhos(matriz[i+1][j+1]);
                }
            }
        }

    }

    // Metodo par gerar as minas do campo (Chamado no JFrameCampo)
    public void gerarMinas() {
        Random gerar = new Random();
        int minasRestantes = minas;
        while(minasRestantes > 0) {
            int l = gerar.nextInt(linhas);
            int c = gerar.nextInt(colunas);
            if(matriz[l][c].minar()) {
                minasRestantes--;
            }
        }
    }

    // Usado para validar o clique
    public int clicar(int l, int c) {
        return matriz[l][c].clicar();
    }

    // Metodo para verificar se venceu
    public boolean isVencido(){        
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                if (!matriz[i][j].isFinalizado()) return false;
            }            
        }
        return true;
    }

    // Metodo para verificar se perdeu
    public boolean isPerdido(){        
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                if (matriz[i][j].isClicado() && matriz[i][j].isMinado()) return true;
            }            
        }
        return false;
    }

    // Metodo para pegar o botão espaço pela posição no campo
    public Espaco getEspaco(int linha, int coluna){
        return matriz[linha][coluna];
    }
}
