package source.Classes;

// Classe para configurações iniciais do jogo
public class Constantes {
    private int num_colunas;
    private int num_linhas;
    private int num_minas;
    private int tam_botaoEspaco;
    private int barra_superior = 120;

    // Construtor
    public Constantes(int num_colunas, int num_linhas, int num_minas, int tam_botaoEspaco) {
        this.num_colunas = num_colunas;
        this.num_linhas = num_linhas;
        this.num_minas = num_minas;
        this.tam_botaoEspaco = tam_botaoEspaco;
    }

    public int getNum_colunas() {
        return num_colunas;
    }

    public int getNum_linhas() {
        return num_linhas;
    }

    public int getNum_minas() {
        return num_minas;
    }

    public int getTam_botaoEspaco() {
        return tam_botaoEspaco;
    } 

    public int getBarra_superior() {
        return barra_superior;
    }

    public void setNum_colunas(int num_colunas) {
        this.num_colunas = num_colunas;
    }

    public void setNum_linhas(int num_linhas) {
        this.num_linhas = num_linhas;
    }

    public void setNum_minas(int num_minas) {
        this.num_minas = num_minas;
    }

    public void setTam_botaoEspaco(int tam_botaoEspaco) {
        this.tam_botaoEspaco = tam_botaoEspaco;
    }

    public void setNum_tamanho(int i) {
    } 

    

    

    
}
