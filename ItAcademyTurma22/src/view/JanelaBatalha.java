package view;

import controller.TorneioController;
import model.*;

import javax.swing.*;
import java.awt.*;

public class JanelaBatalha extends JFrame {

    public JanelaBatalha(TorneioController controller, Batalha batalha, JanelaTorneio anterior) {
        setTitle("Batalha em andamento");
        setSize(500, 400);
        setLayout(new BorderLayout());

        Startup a = batalha.getStartupA();
        Startup b = batalha.getStartupB();

        JPanel painelTopo = new JPanel(new GridLayout(2, 2));
        painelTopo.setBackground(Color.PINK);

        JLabel nomeA = new JLabel("" + a.getNome(), SwingConstants.CENTER);
        JLabel nomeB = new JLabel("" + b.getNome(), SwingConstants.CENTER);
        JLabel pontosA = new JLabel("Pontos: " + a.getPontos(), SwingConstants.CENTER);
        JLabel pontosB = new JLabel("Pontos: " + b.getPontos(), SwingConstants.CENTER);

        painelTopo.add(nomeA);
        painelTopo.add(nomeB);
        painelTopo.add(pontosA);
        painelTopo.add(pontosB);

        add(painelTopo, BorderLayout.NORTH);

        JPanel painelEventos = new JPanel(new GridLayout(Evento.values().length, 2, 5, 5));
        painelEventos.setBackground(Color.PINK);

        for (Evento e : Evento.values()) {
            JButton btnA = new JButton(e.getNomeFormatado());
            JButton btnB = new JButton(e.getNomeFormatado());

            btnA.addActionListener(l -> {
                if (batalha.aplicarEvento(a, e)) {
                    pontosA.setText("Pontos: " + a.getPontos());
                    btnA.setEnabled(false);
                }
            });

            btnB.addActionListener(l -> {
                if (batalha.aplicarEvento(b, e)) {
                    pontosB.setText("Pontos: " + b.getPontos());
                    btnB.setEnabled(false);
                }
            });

            painelEventos.add(btnA);
            painelEventos.add(btnB);
        }

        add(painelEventos, BorderLayout.CENTER);

        JButton finalizar = new JButton("Finalizar Batalha");
        finalizar.setFont(new Font("Tahoma", Font.BOLD, 12));
        add(finalizar, BorderLayout.SOUTH);

        finalizar.addActionListener(e -> {
            if (batalha.isFinalizada()) {
                JOptionPane.showMessageDialog(this, "Batalha já finalizada.");
                return;
            }

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
