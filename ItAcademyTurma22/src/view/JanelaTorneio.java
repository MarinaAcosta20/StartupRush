package view;

import controller.TorneioController;
import model.Batalha;

import javax.swing.*;
import java.awt.*;

public class JanelaTorneio extends JFrame {
    private TorneioController controller;
    private JList<Batalha> listaBatalhas;
    private JLabel lblRodada;

    public JanelaTorneio(TorneioController controller) {
    	getContentPane().setBackground(Color.PINK);
        this.controller = controller;
        setTitle("Torneio Startup Rush");
        setSize(500, 400);
        BorderLayout borderLayout = new BorderLayout();
        getContentPane().setLayout(borderLayout);

        // Label da rodada
        lblRodada = new JLabel("Rodada " + controller.getRodada(), SwingConstants.CENTER);
        lblRodada.setBackground(Color.PINK);
        lblRodada.setFont(new Font("Tahoma", Font.BOLD, 24));
        getContentPane().add(lblRodada, BorderLayout.NORTH);

        // Lista de batalhas
        listaBatalhas = new JList<>();
        listaBatalhas.setFont(new Font("Tahoma", Font.PLAIN, 15));
        listaBatalhas.setBackground(SystemColor.control);
        listaBatalhas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll = new JScrollPane(listaBatalhas);
        getContentPane().add(scroll, BorderLayout.CENTER);

        // Botão para administrar batalha
        JButton btnAdministrar = new JButton("Administrar Batalha Selecionada");
        btnAdministrar.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnAdministrar.addActionListener(e -> {
            Batalha selecionada = listaBatalhas.getSelectedValue();
            if (selecionada != null) {
                new JanelaBatalha(controller, selecionada, this).setVisible(true);
                setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione uma batalha!", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        getContentPane().add(btnAdministrar, BorderLayout.SOUTH);

        // Primeira chamada para popular a tela
        atualizarTela();
    }

    public void checarAvanco() {
        if (controller.batalhasRestantes().isEmpty()) {
            controller.avancarFase();

            if (controller.getCampea() != null) {
                JOptionPane.showMessageDialog(this,
                        "🏆 A campeã do Startup Rush é: " + controller.getCampea().getNome() +
                                "\nSlogan: \"" + controller.getCampea().getSlogan() + "\"",
                        "Campeã!", JOptionPane.INFORMATION_MESSAGE);

                new JanelaRelatorio(controller).setVisible(true);
                dispose();
            } else {
                atualizarTela();
            }
        }
    }

    private void atualizarTela() {
        // Atualiza lista de batalhas da rodada atual
        java.util.List<Batalha> batalhas = controller.batalhasRestantes();
        listaBatalhas.setListData(batalhas.toArray(new Batalha[0]));

        // Atualiza número da rodada
        lblRodada.setText("Rodada " + controller.getRodada());
    }
}



