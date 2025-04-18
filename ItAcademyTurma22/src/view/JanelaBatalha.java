package view;

import controller.TorneioController;
import model.*;

import javax.swing.*;
import java.awt.*;

public class JanelaBatalha extends JFrame {
	private JLabel labelPontuacaoA;
	private JLabel labelPontuacaoB;

    public JanelaBatalha(TorneioController controller, Batalha batalha, JanelaTorneio anterior) {
        setTitle("Batalha em andamento");
        setSize(500, 400);
        getContentPane().setLayout(new BorderLayout());

        Startup a = batalha.getStartupA();
        Startup b = batalha.getStartupB();

        // Topo com nomes
        JPanel painelTopo = new JPanel(new GridLayout(1, 2));
        painelTopo.setBackground(Color.PINK);
        JLabel label = new JLabel("" + a.getNome(), SwingConstants.CENTER);
        label.setFont(new Font("Tahoma", Font.BOLD, 12));
        painelTopo.add(label);
        JLabel label_1 = new JLabel("" + b.getNome(), SwingConstants.CENTER);
        label_1.setFont(new Font("Tahoma", Font.BOLD, 12));
        painelTopo.add(label_1);
        getContentPane().add(painelTopo, BorderLayout.NORTH);

        // Centro com botões de eventos
        JPanel painelEventos = new JPanel(new GridLayout(Evento.values().length, 2, 5, 5));
        painelEventos.setBackground(Color.PINK);

        for (Evento e : Evento.values()) {
            JButton btnA = new JButton(e.getNomeFormatado());
            btnA.addActionListener(l -> batalha.aplicarEvento(a, e));

            JButton btnB = new JButton(e.getNomeFormatado());
            btnB.addActionListener(l -> batalha.aplicarEvento(b, e));

            painelEventos.add(btnA);
            painelEventos.add(btnB);
        }

        getContentPane().add(painelEventos, BorderLayout.CENTER);

        // Rodapé com botão de finalizar
        JButton finalizar = new JButton("Finalizar Batalha");
        finalizar.setFont(new Font("Tahoma", Font.BOLD, 12));
        getContentPane().add(finalizar, BorderLayout.SOUTH);
        
        finalizar.addActionListener(e -> {
            Startup vencedora = batalha.calcularVencedor();

            String mensagem = "Vencedora: " + vencedora.getNome() + " com " + vencedora.getPontos() + " pontos.";

            if (batalha.teveSharkFight()) {
                mensagem = "🔥 SHARK FIGHT! 🔥\n" + mensagem + "\n(+2 pontos extras na disputa relâmpago!)";
            }

            JOptionPane.showMessageDialog(this, mensagem, "Resultado da Batalha", JOptionPane.INFORMATION_MESSAGE);

            anterior.checarAvanco();
            anterior.setVisible(true);
            dispose();
        });
    }
}



