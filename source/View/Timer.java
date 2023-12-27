package source.View;


public class Timer implements Runnable{

    private JFrameCampo janela;
    private int tempo = 0;
    private String textoLabel;

    // Construtor
    public Timer(JFrameCampo janela) {
        this.janela = janela;

    }

    // Metodo para resetar o tempo
    public void reset() {
        tempo = 0;
        textoLabel = "Tempo: " + tempo;
        janela.timer(textoLabel);
    }

    public int registrarTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    // Metodo contínuo para mudar os segundos no jogo
    public void run() {
       tempo=0;
        try {
            
            String textoLabel = "Tempo: " + tempo;
           
            while(true) {
                if(janela.stopTimer) {
                    
                } else {
                    tempo++;
                }

                textoLabel = "Tempo: "+tempo;
                janela.timer(textoLabel);
                Thread.sleep(1000);   
            }


        } catch (InterruptedException ie) {

        }
    }
}
