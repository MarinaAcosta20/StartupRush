package app;

import controller.TorneioController;
import view.JanelaMenu; 

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            TorneioController controller = new TorneioController();
            new JanelaMenu(controller); 
        });
    }
}
