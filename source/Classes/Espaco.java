package source.Classes;

import java.util.ArrayList;

import source.View.JButtonCampo;

public class Espaco {
    
    boolean minado;
    boolean marcado;
    boolean revelado;
    boolean clicado;

    ArrayList<Espaco> vizinhos;

    JButtonCampo button;

    // Construtor
    public Espaco() {
        clicado = false;
        marcado = false;
        minado = false;
        revelado = false;

        vizinhos = new ArrayList<>();
    }

    public boolean isMinado() {
        return minado;
    }

    public boolean isMarcado() {
        return marcado;
    }

    public boolean isRevelado() {
        return revelado;
    }

    public boolean isClicado() {
        return clicado;
    }

    public ArrayList<Espaco> getVizinhos() {
        return vizinhos;
    }

    public JButtonCampo getButton() {
        return button;
    }

    // Metodo para adicionar os vizinhos no espaço
    public void adicionarVizinhos(Espaco e) {
        vizinhos.add(e);
    }

    // Metodo par minar o espaço
    public boolean minar() {
        if(!minado) {
            minado = true;
            return true;
        } else {
            return false;
        }
    }

    // Metodo para marcar o espaço
    public boolean marcar() {
        marcado = !marcado;
        return marcado;
    }

    // Metodo para pegar o numero de minas nos espaços vizinhos
    public int numMinasVizinhos() {
        int n = 0;
        for(Espaco vizinho: this.vizinhos) {
            if(vizinho.minado) {
                n++;
            }
        }

        return n;
    }

    //Se está minado retorna -1
    //Se não possui minas nos vizinhos retorna 0
    //Se á minas nos vizinhos retorna n
    public int clicar() {
        clicado = true;
        if(minado) {
                return -1;
        } else {
            return numMinasVizinhos();
        }
    }

    // Metodo para resetar o jogo
    public void resetarJogo() {
        marcado = false;
        minado = false;
        clicado = false;
        revelado = false;
    }

    // Metodo para verificar se o o botão está marcado e está com mina ou se não está marcado e está revelado
    public boolean isFinalizado(){
        if(this.minado && this.marcado) {
            return true;
        }
        if(!this.minado && !this.marcado && this.clicado) {
            return true;
        }
        return false;
    }

    // Metodo para ainicializar o botão do espaço
    public void setButton(JButtonCampo button){
        this.button = button;
    }
}
