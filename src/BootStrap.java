import javax.swing.SwingUtilities;

import presentation.View;

public class BootStrap {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new View().setVisible(true);
            }
        });
    }
    
}
