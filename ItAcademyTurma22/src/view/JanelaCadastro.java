package view;

import controller.TorneioController;
import model.Startup;

import javax.swing.*;
import java.awt.*;

public class JanelaCadastro extends JFrame {
    private final TorneioController controller;

    private final JTextField txtNome = new JTextField(15);
    private final JTextField txtSlogan = new JTextField(15);
    private final JTextField txtAno = new JTextField(5);
    private final DefaultListModel<String> listModel = new DefaultListModel<>();

    public JanelaCadastro(TorneioController controller) {
        this.controller = controller;
        setTitle("Cadastro de Startups");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        JPanel form = new JPanel();
        form.setBackground(new Color(255, 192, 203));
        form.setLayout(new GridLayout(3, 2));
        JLabel label = new JLabel("Nome:");
        label.setFont(new Font("Tahoma", Font.BOLD, 15));
        form.add(label);
        form.add(txtNome);
        JLabel label_1 = new JLabel("Slogan:");
        label_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        form.add(label_1);
        form.add(txtSlogan);
        JLabel label_2 = new JLabel("Ano:");
        label_2.setFont(new Font("Tahoma", Font.BOLD, 15));
        form.add(label_2);
        form.add(txtAno);
        getContentPane().add(form, BorderLayout.NORTH);

        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.addActionListener(e -> adicionarStartup());

        JButton btnIniciar = new JButton("Iniciar Torneio");
        btnIniciar.addActionListener(e -> iniciarTorneio());

        JPanel botoes = new JPanel();
        botoes.setBackground(new Color(255, 192, 203));
        botoes.add(btnAdicionar);
        botoes.add(btnIniciar);
        getContentPane().add(botoes, BorderLayout.SOUTH);

        JList<String> lista = new JList<>(listModel);
        lista.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lista.setBackground(SystemColor.control);
        lista.setForeground(Color.BLACK);
        getContentPane().add(new JScrollPane(lista), BorderLayout.CENTER);
    }

    private void adicionarStartup() {
        String nome = txtNome.getText();
        String slogan = txtSlogan.getText();
        int ano;
        try {
            ano = Integer.parseInt(txtAno.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ano inválido.");
            return;
        }

        if (nome.isEmpty() || slogan.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            return;
        }

        Startup s = new Startup(nome, slogan, ano);
        controller.cadastrarStartup(s);
        listModel.addElement(nome + " - " + slogan + " (" + ano + ")");
        txtNome.setText("");
        txtSlogan.setText("");
        txtAno.setText("");
    }

    private void iniciarTorneio() {
        if (!controller.podeIniciar()) {
            JOptionPane.showMessageDialog(this, "Número de startups inválido (4-8, número par).");
            return;
        }
        controller.iniciarRodada();
        new JanelaTorneio(controller).setVisible(true);
        dispose();
    }
}



