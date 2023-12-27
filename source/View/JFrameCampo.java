package source.View;

import java.awt.*;
import java.awt.event.WindowEvent;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;


import java.io.BufferedWriter;
import java.io.IOException;

import javax.swing.*;

import source.Classes.*;


public class JFrameCampo extends JFrame {

    Constantes c;
    JPanel panelBarra;
    JPanel panelCampo;
    JButtonCampo[][] matBut;
    Tabuleiro t;
    JButton resetBut;
    JLabel minasRestantes;
    JLabel contador;
    Timer timer;
    int minasRestantesContador;

    JMenuBar menuBar;
    JMenu menu, subMenuEscolhaDificuldade;
    JMenuItem subFacil, subMedio, subDificil, sair, reiniciar, ajuda;

    boolean inicial = true;
    boolean stopTimer = false;

    // Construtor
    public JFrameCampo() {

        panelBarra = new JPanel();
        panelCampo = new JPanel();
        

        confIniciais(10, 8, 10, 50, panelBarra, panelCampo);

        menuBar = new JMenuBar();
  
        menu = new JMenu("Menu Jogo");
        subMenuEscolhaDificuldade = new JMenu("Dificuldades");
  
        subFacil = new JMenuItem("Fácil");
        subMedio = new JMenuItem("Médio");
        subDificil = new JMenuItem("Difícil");
        sair = new JMenuItem("Sair");
        reiniciar = new JMenuItem("Reiniciar");
        ajuda = new JMenuItem("Ajuda");
    
  
        // adicionando itens do menu ao menu
        subMenuEscolhaDificuldade.add(subFacil);
        subMenuEscolhaDificuldade.add(subMedio);
        subMenuEscolhaDificuldade.add(subDificil);

        // adicionando submenu
        menu.add(subMenuEscolhaDificuldade);
        menu.add(reiniciar);
        menu.add(sair);
        menu.add(ajuda);
  
        // add menu ao menubar
        menuBar.add(menu);

        //Adicionando menu ao frame
        this.setJMenuBar(menuBar);

        subFacil.addActionListener((e) -> {
            panelBarra.removeAll();
            panelCampo.removeAll();
            confIniciais(10, 8, 10, 50, panelBarra, panelCampo);
            stopTimer = false;
            timer.reset();
        });

        subMedio.addActionListener((e) -> {
            panelBarra.removeAll();
            panelCampo.removeAll();
            confIniciais(16, 12, 30, 45, panelBarra, panelCampo);
            stopTimer = false;
            timer.reset();
        });

        subDificil.addActionListener((e) -> {
            panelBarra.removeAll();
            panelCampo.removeAll();
            confIniciais(18, 14, 40, 42, panelBarra, panelCampo);
            stopTimer = false;
            timer.reset();
        });

        sair.addActionListener((e) -> {
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        });

        reiniciar.addActionListener(e -> {
            this.reset();
        });
        
        ajuda.addActionListener((e) -> {
            exibirInstrucoes();
        });
    }


    // Inicializa a interface gráfica
    public void confIniciais(int colunas, int linhas, int minas, int tamanho, JPanel panelBarra, JPanel panelCampo){

        // Inicializando as variaveis padrão do jogo
        c = new Constantes(colunas, linhas, minas, tamanho);
        t = new Tabuleiro(c);
        minasRestantesContador = c.getNum_minas();
        matBut = new JButtonCampo[c.getNum_linhas()][c.getNum_colunas()];

        

        // Definindo o tipo do layout da Barra superior
        panelBarra.setLayout(new GridLayout(1, 3));

        // Inicializando os componentes da Barra superior
        minasRestantes = new JLabel("Minas: " + minasRestantesContador);
        contador = new JLabel("");

        //Adicionar o contador de segundos e minas no panel da Barra superior
        panelBarra.add(contador);
        panelBarra.add(minasRestantes);

        // Definir o tamanho da janela
        panelCampo.setLayout(new GridLayout(c.getNum_linhas(),c.getNum_colunas()));
        panelCampo.setSize(c.getTam_botaoEspaco(), c.getTam_botaoEspaco());
        panelCampo.setVisible(true);
        

        //Criar cada espaço do campo
        for (int i = 0; i < c.getNum_linhas(); i++) {
            for (int j = 0; j < c.getNum_colunas(); j++) {
                matBut[i][j] = new JButtonCampo(this.t, this, c);
                t.getEspaco(i, j).setButton(matBut[i][j]);
                
                panelCampo.add(matBut[i][j]);
                matBut[i][j].setPos(i, j);
            }
        }

        // Gerando as minas do campo
        t.gerarMinas();

        // Adicionando os atributos ao JFrame
        add(panelBarra, BorderLayout.NORTH);
        add(panelCampo,BorderLayout.CENTER);
        setSize(c.getTam_botaoEspaco()*c.getNum_colunas(), c.getNum_linhas()*c.getTam_botaoEspaco() + c.getBarra_superior());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setVisible(true);

        if(inicial) {
            startTimer();
            inicial = false;
        }
    }

    // Inicialização do timer do jogo
    public void startTimer() {
        timer = new Timer(this);
        new Thread(timer).start();
    }



    // Metodo para reiniciar o jogo
    public void reset() {
        for (int i = 0; i < c.getNum_linhas(); i++) {
            for (int j = 0; j < c.getNum_colunas(); j++) {
                matBut[i][j].reset();
            }
        }
        minasRestantesContador = c.getNum_minas();
        this.minasRestantes.setText("Minas: " + minasRestantesContador);
        this.t.gerarMinas();
        timer.setTempo(0);
        timer.reset();
        stopTimer = false;
    }


    // Após o jogo acabar o metodo revela o lugar das minas
    public void revelarMinas() {
        for (int i = 0; i < c.getNum_linhas(); i++) {
            for (int j = 0; j < c.getNum_colunas(); j++) {
                if (matBut[i][j].espaco.isMinado()) {
                    matBut[i][j].revela("-1");
                }
            }
        }
    }

    // Desativa os botões após o termino do jogo
    public void desativaBotoes() {
        for (int i = 0; i < c.getNum_linhas(); i++) {
            for (int j = 0; j <c.getNum_colunas(); j++) {
                matBut[i][j].setEnabled(false);
            }
        }
    }

    // Atualiza o contador de minas
    public void atualizaMinas(boolean verifica) {
        if(verifica) {
            this.minasRestantesContador--;
        } else {
            this.minasRestantesContador++;
        }
        this.minasRestantes.setText("Minas: " +  minasRestantesContador);
     }


     // Verifica se o jogo ja acabou
    public void checkEstado() {

        String mensagem;
        
        if (this.t.isVencido()) {
            mensagem = "PARABENS!! VOCÊ VENCEU";
            this.desativaBotoes();
            salvarTarefa("O jogador ganhou com " + timer.registrarTempo() + " segundos de jogo");
            stopTimer = true;
            JOptionPane.showMessageDialog(null, mensagem);
        }

        if (this.t.isPerdido()) {
            mensagem = "QUE PENA!! VOCÊ PERDEU";
            this.desativaBotoes();
            salvarTarefa("O jogador perdeu com " + timer.registrarTempo() + " segundos de jogo");
            stopTimer = true;
            JOptionPane.showMessageDialog(null, mensagem);
            
            
        }

    }

    //Muda o numero por segundo
    public void timer(String tempo) {
        this.contador.setText(tempo);
    }

    // Salva o resultado do jogo no arquivo de tarefas
    public void salvarTarefa(String status) {
        Path p = Paths.get("./StatusPartida.txt");
        try (BufferedWriter out = Files.newBufferedWriter(  p, 
                                                            StandardOpenOption.CREATE, 
                                                            StandardOpenOption.APPEND)) {
            out.write(status);
            out.write("\n");
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
    }
    
    public void exibirInstrucoes() {
        String mensagem = "Bem-vindo ao Campo Minado!\n\n" +
                          "Instruções:\n" +
                          "- Clique com o botão esquerdo para revelar um espaço.\n" +
                          "- Clique com o botão direito para marcar/desmarcar uma mina.\n" +
                          "- O objetivo é revelar todos os espaços sem minas.\n" +
                          "- Cuidado! Se clicar em uma mina, o jogo termina.\n\n" +
                          "Boa sorte!";
        JOptionPane.showMessageDialog(this, mensagem, "Instruções do Jogo", JOptionPane.INFORMATION_MESSAGE);
    }
}
