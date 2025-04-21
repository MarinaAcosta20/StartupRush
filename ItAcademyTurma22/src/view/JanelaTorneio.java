package view;

import controller.TorneioController;
import model.Batalha;
import model.EventoGlobal;

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
        getContentPane().setLayout(new BorderLayout());

        lblRodada = new JLabel("Rodada " + controller.getRodada(), SwingConstants.CENTER);
        lblRodada.setBackground(new Color(255, 192, 203));
        lblRodada.setFont(new Font("Tahoma", Font.BOLD, 24));
        getContentPane().add(lblRodada, BorderLayout.NORTH);

        listaBatalhas = new JList<>();
        listaBatalhas.setFont(new Font("Tahoma", Font.PLAIN, 15));
        listaBatalhas.setBackground(SystemColor.control);
        listaBatalhas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll = new JScrollPane(listaBatalhas);
        getContentPane().add(scroll, BorderLayout.CENTER);

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

        atualizarTela();
    }
    private void atualizarTela() {
        java.util.List<Batalha> batalhas = controller.batalhasRestantes();
        listaBatalhas.setListData(batalhas.toArray(new Batalha[0]));
        lblRodada.setText("Rodada " + controller.getRodada());

        EventoGlobal evento = controller.getEventoGlobalRodadaAtual();
        if (evento != null) {
            JOptionPane.showMessageDialog(this,
                "🌐 Evento Global desta rodada:\n" + evento.getDescricao(),
                "Evento Global", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    	public void checarAvanco() {
    	    if (controller.batalhasRestantes().isEmpty()) {
    	        controller.avancarFase();

    	        if (controller.getStartupComBye() != null) {
    	            JOptionPane.showMessageDialog(this,
    	                    "🚀 A startup \"" + controller.getStartupComBye().getNome() + "\" avançou automaticamente para a próxima rodada!",
    	                    "Avanço Automático (Bye)",
    	                    JOptionPane.INFORMATION_MESSAGE);
    	        }

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
    }

