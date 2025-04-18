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
        setSize(700, 400);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(255, 240, 245));
        setLayout(new BorderLayout());

        // Título
        JLabel titulo = new JLabel("📊 Relatório Geral do Startup Rush", SwingConstants.CENTER);
        titulo.setFont(new Font("Tahoma", Font.BOLD, 22));
        titulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        add(titulo, BorderLayout.NORTH);

        // Tabela de dados
        String[] colunas = {"Startup", "Pontos", "Pitches", "Bugs", "Boa Tração", "Invest. Irritados", "Penalidades"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);

        List<Startup> todas = controller.getTodasStartups();
        todas.sort((a, b) -> Integer.compare(b.getPontos(), a.getPontos())); // ordena por pontos decrescente

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
        add(scrollPane, BorderLayout.CENTER);

        // Slogan da campeã
        Startup campea = controller.getCampea();
        if (campea != null) {
            JLabel slogan = new JLabel("🏆 Slogan da Campeã \"" + campea.getNome() + "\": \"" + campea.getSlogan() + "\"", SwingConstants.CENTER);
            slogan.setFont(new Font("Tahoma", Font.ITALIC, 16));
            slogan.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
            add(slogan, BorderLayout.SOUTH);
        }

        setVisible(true);
    }
}
