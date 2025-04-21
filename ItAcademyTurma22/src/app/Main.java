package app;

import controller.TorneioController;
import view.JanelaMenu; // Importando o Menu

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            TorneioController controller = new TorneioController();
            new JanelaMenu(controller); 
        });
    }
}
