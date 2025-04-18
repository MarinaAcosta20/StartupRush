package view;

import controller.TorneioController;
import model.Startup;

import javax.swing.*;
import java.awt.*;

public class JanelaRelatorio extends JFrame {
    public JanelaRelatorio(TorneioController controller) {
        setTitle("Resultado Final");
        setSize(500, 400);
        setLayout(new BorderLayout());

        JTextArea area = new JTextArea();
        area.setEditable(false);
        StringBuilder sb = new StringBuilder();
        sb.append("🏆 Startup Campeã: ").append(controller.getCampea().getNome()).append("\n");
        sb.append("Slogan: ").append(controller.getCampea().getSlogan()).append("\n\n");
        sb.append("Ranking Final:\n");

        for (Startup s : controller.getRankingFinal()) {
            sb.append(s.getNome()).append(" - ").append(s.getPontos()).append(" pontos\n");
            sb.append("Pitch: ").append(s.getPitches()).append(", Bugs: ").append(s.getBugs()).append(", Trações: ").append(s.getTracoes()).append("\n");
            sb.append("Investidores Irritados: ").append(s.getInvestidoresIrritados()).append(", Fake News: ").append(s.getFakeNews()).append("\n\n");
        }

        area.setText(sb.toString());
        add(new JScrollPane(area), BorderLayout.CENTER);
    }
}

