package view;

import controller.TorneioController;
import model.Startup;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class JanelaRelatorio extends JFrame {

    public JanelaRelatorio(TorneioController controller) {
        setTitle("Relatório Final do Torneio");
        setSize(1000, 400);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(255, 192, 203));
        getContentPane().setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Relatório Geral do Startup Rush", SwingConstants.CENTER);
        titulo.setFont(new Font("Tahoma", Font.BOLD, 22));
        titulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        getContentPane().add(titulo, BorderLayout.NORTH);

        String[] colunas = {"Startup", "Pontos", "Pitch Convincente", "Produto com Bugs", "Boa Tração Usuários", "Invest. Irritados"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);

        List<Startup> todas = controller.getTodasStartups();
        todas.sort((a, b) -> Integer.compare(b.getPontos(), a.getPontos())); 

        for (Startup s : todas) {
            Object[] linha = {
                    s.getNome(),
                    s.getPontos(),
                    s.getPitches(),
                    s.getBugs(),
                    s.getTracoes(),
                    s.getInvestidoresIrritados(),
                    s.getFakeNews(),
            };
            modelo.addRow(linha);
        }

        JTable tabela = new JTable(modelo);
        tabela.setRowHeight(24);
        tabela.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tabela.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
        JScrollPane scrollPane = new JScrollPane(tabela);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        Startup campea = controller.getCampea();
        if (campea != null) {
            JLabel slogan = new JLabel("Campeã: \"" + campea.getNome() + "\": \"" + campea.getSlogan() + "\"", SwingConstants.CENTER);
            slogan.setFont(new Font("Tahoma", Font.ITALIC, 16));
            slogan.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
            getContentPane().add(slogan, BorderLayout.SOUTH);
        }

        setVisible(true);
    }
}
