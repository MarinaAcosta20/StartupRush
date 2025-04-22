package view;

import controller.TorneioController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JanelaMenu extends JFrame {

    private TorneioController controller;

    public JanelaMenu(TorneioController controller) {
        this.controller = controller;

        setTitle("Startup Rush");
        setSize(800, 700);
        setLocationRelativeTo(null); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(new Color(255, 192, 203)); 

        JLabel titulo = new JLabel("Bem vindo(a) ao Startup Rush!", JLabel.CENTER);
        titulo.setFont(new Font("Tahoma", Font.BOLD, 32));
        titulo.setForeground(new Color(0, 0, 0)); 
        painel.add(titulo);

        painel.add(Box.createVerticalStrut(20));

        JLabel tituloRegras = new JLabel("Regras do Jogo", JLabel.CENTER);
        tituloRegras.setFont(new Font("Tahoma", Font.BOLD, 18));
        tituloRegras.setForeground(new Color(0, 0, 0));
        painel.add(tituloRegras);
        
        painel.add(Box.createVerticalStrut(10));

        JTextArea regras = new JTextArea();
        regras.setText(" O torneio de startups envolve de 4 a 8 startups, obrigatoriamente em número par. Cada startup começa com 70 pontos e ganha mais 30 a cada vitória, as rodadas são compostas por batalhas em pares, sorteados automaticamente pelo sistema. As batalhas são administradas pelo usuário, que escolhe uma batalha pendente e insere os eventos ocorridos. Durante a batalha, o usuário pode registrar eventos que afetam diretamente a pontuação das startups, como:\n"
        		+ " • Pitch convincente (+6 pontos)\n"
        		+ " • Produto com bugs (-4 pontos)\n"
        		+ " • Boa tração de usuários (+3 pontos)\n"
        		+ " • Investidor irritado (-6 pontos)\n"
        		+ " • Fake news no pitch (-8 pontos).\n");
        
        regras.setEditable(false);
        regras.setWrapStyleWord(true);
        regras.setLineWrap(true);
        regras.setFont(new Font("Tahoma", Font.PLAIN, 14));
        regras.setBackground(new Color(240, 240, 240)); 
        regras.setForeground(new Color(50, 50, 50)); 
        regras.setPreferredSize(new Dimension(500, 150)); 
        regras.setCaretPosition(0); 
        JScrollPane scrollPaneRegras = new JScrollPane(regras); 
        painel.add(scrollPaneRegras);

        painel.add(Box.createVerticalStrut(20)); 

        JLabel tituloEventos = new JLabel("Eventos Globais", JLabel.CENTER);
        tituloEventos.setFont(new Font("Tahoma", Font.BOLD, 18));
        tituloEventos.setForeground(new Color(0, 0, 0));
        painel.add(tituloEventos);
        
        painel.add(Box.createVerticalStrut(10));

        JTextArea eventos = new JTextArea();
        eventos.setText(" A cada rodada, um evento global afeta todas as startups, são eles:\n"
        				+" • Crise Econômica: Todas as startups perdem 10 pontos.\n"
                         +" • Festival de Tecnologia: Todas as startups ganham 10 pontos!\n"
                         +" • Greve Mundial: Todas as startups perdem 5 pontos.\n"
                         +" • Tendência de Inovação: Todas as startups ganham 15 pontos!\n"
                         +" • Nova Lei Restritiva: Todas as startups perdem 8 pontos.");
        	
        eventos.setEditable(false);
        eventos.setWrapStyleWord(true);
        eventos.setLineWrap(true);
        eventos.setFont(new Font("Tahoma", Font.PLAIN, 14));
        eventos.setBackground(new Color(240, 240, 240));
        eventos.setForeground(new Color(50, 50, 50));
        eventos.setPreferredSize(new Dimension(500, 100));
        eventos.setCaretPosition(0);
        JScrollPane scrollPaneEventos = new JScrollPane(eventos);
        painel.add(scrollPaneEventos);

        painel.add(Box.createVerticalStrut(20)); 

        JLabel tituloEmpate = new JLabel("Empate e Shark Fight", JLabel.CENTER);
        tituloEmpate.setFont(new Font("Tahoma", Font.BOLD, 18));
        tituloEmpate.setForeground(new Color(0, 0, 0));
        painel.add(tituloEmpate);

        painel.add(Box.createVerticalStrut(10));

        JTextArea empate = new JTextArea();
        empate.setText("Se houver empate ao final de uma batalha, um 'Shark Fight' é ativado, onde uma startup aleatória recebe +2 pontos.\n"
                        + " O novo placar decide o vencedor.");
        empate.setEditable(false);
        empate.setWrapStyleWord(true);
        empate.setLineWrap(true);
        empate.setFont(new Font("Tahoma", Font.PLAIN, 14));
        empate.setBackground(new Color(240, 240, 240));
        empate.setForeground(new Color(50, 50, 50));
        empate.setPreferredSize(new Dimension(500, 100));
        empate.setCaretPosition(0);
        JScrollPane scrollPaneEmpate = new JScrollPane(empate);
        painel.add(scrollPaneEmpate);

        painel.add(Box.createVerticalStrut(30));

        JButton botaoCadastro = new JButton("Cadastrar Startups");
        botaoCadastro.setFont(new Font("Tahoma", Font.BOLD, 16));
        botaoCadastro.setBackground(new Color(255, 255, 255)); // Cor de fundo azul
        botaoCadastro.setForeground(new Color(0, 0, 0)); // Cor do texto no botão
        botaoCadastro.setFocusPainted(false); // Remove o foco
        botaoCadastro.setBorderPainted(false); // Remove a borda
        botaoCadastro.setPreferredSize(new Dimension(200, 50)); // Tamanho do botão
        botaoCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new JanelaCadastro(controller).setVisible(true);
                dispose(); 
            }
        });
        painel.add(botaoCadastro);

        getContentPane().add(painel);

    
        setVisible(true);
    }
}
