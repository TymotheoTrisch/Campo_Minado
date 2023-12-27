package source.View;

import java.awt.Image;
import java.awt.event.MouseEvent;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import source.Classes.*;

public class JButtonCampo extends JButton {
    
    int linha;
    int coluna;
    Espaco espaco;
    String text;
    JFrameCampo campoGrafico;
    Constantes c;
    Tabuleiro t;

    // Construtor
    public JButtonCampo(Tabuleiro t, JFrameCampo cg, Constantes c) {
        this.t = t;
        this.campoGrafico = cg;
        this.c = c;
        this.text = "";
        this.setText(text);
       

        // Codigo para o mouse
        this.addActionListener((java.awt.event.ActionEvent evt) -> {
            botaoPressionado(false);
        });

        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    botaoPressionado(true);
                };
            }
        });
    }

    // Metodo para revelar o botão pressionado com botão esquerdo do mouse
    public void clicar() {

        System.out.println("linha: " + linha + " coluna: " + coluna);
        this.setEnabled(false);

        int numVizinhosMinados = espaco.clicar();
        if (this.espaco.isMinado()) {
            this.campoGrafico.revelarMinas();
        }

        if (numVizinhosMinados == 0) {
            for (Espaco vizinho : espaco.getVizinhos()) {
                if (!vizinho.isClicado()) {
                    vizinho.getButton().clicar();

                    if(vizinho.isMarcado()){
                        vizinho.getButton().setIcon(null);
                        campoGrafico.atualizaMinas(false);
                    }
                    
                }
            }
            this.text = "";
        } else {
            this.text = Integer.toString(numVizinhosMinados);
        }
        this.revela(this.text);

        
    }

    // Metodo para reiniciar cada espaço do campo
    public void reset() {
        this.espaco.resetarJogo();
        this.setEnabled(true);
        this.setIcon(null);
        this.setText("");
    }

    // Mostrando a mina quando clicada
    public void revela(String cod) {

        if (cod.equals("-1")) {
            try {
                Image img = ImageIO.read(getClass().getResource("mine.jpg"));
                img = img.getScaledInstance(c.getTam_botaoEspaco(), c.getTam_botaoEspaco(), java.awt.Image.SCALE_SMOOTH);
                this.setIcon(new ImageIcon(img));
            } catch (Exception ex) {
                this.setText("-1");
                System.out.println("ERRO!");
            }
        } else {
            this.setText(cod);
        }
        this.setEnabled(false);
    }

    // Metodo para dizer a posição de cada botão do campo
    public void setPos(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
        this.espaco = t.getEspaco(linha, coluna);
    }

    // Ação do clique do mouse 
    private void botaoPressionado(boolean mouseBotaoDireito) {
        if (!mouseBotaoDireito) { //botao esquerdo
            if (!this.espaco.isMarcado()) {
                this.clicar();
            }
        } else {
            this.marcar();
        }
        this.campoGrafico.checkEstado();
    }

    // Metodo para marcar um espaço do campo
    public void marcar() {
        if (this.espaco.isClicado()) {
            return;
        }
        this.espaco.marcar();
        if (this.espaco.isMarcado()) {
            
            campoGrafico.atualizaMinas(true);

            try {
                Image img = ImageIO.read(getClass().getResource("marcado.png"));
                img = img.getScaledInstance(c.getTam_botaoEspaco(), c.getTam_botaoEspaco(), java.awt.Image.SCALE_SMOOTH);
                this.setIcon(new ImageIcon(img));
            } catch (Exception ex) {
                this.setText("M");
                System.out.println("ERRO!");
            }
        } else {
            this.setIcon(null);
            this.setText("");
            campoGrafico.atualizaMinas(false);

        }
    }
}
