package app;

import model.Startup;
import model.Evento;
import controller.TorneioController;
import view.JanelaCadastro;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            TorneioController controller = new TorneioController();
            new JanelaCadastro(controller).setVisible(true);
        });
    }
}
