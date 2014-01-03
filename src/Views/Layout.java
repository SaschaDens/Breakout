package Views;

import dsdevbe.Functions;
import dsdevbe.GameController;
import dsdevbe.GameModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @description Java project geschreven als student ICT aan Thomasmore Kempen.
 *              Breakout: Doel van het spel is om alle blokjes weg te werken,
 *                        zonder dat de bal in het water valt
 * Grafisch werk zelf bewerkt naar noden van toepassing.
 * URL van assets: http://kenney.nl/post/platformer-art-assets-deluxe
 * @Stats 2905 Lijnen code en veel tijd :)
 * @website ds-dev.be -> voor handleiding van het spel
 * @version V1.0
 * @author Dens Sascha
 * 
 * @Gebruikte bron: Collision detectie van Hock-Chuan Chua met eigen implementatie hiervan.
 *                  URL: http://www.ntu.edu.sg/home/ehchua/programming/java/J8a_GameIntro-BouncingBalls.html
 */

public class Layout extends JPanel {

    public JButton btnExit;
    public GameController _Controller;
    public GameModel _Model;

    public Layout() {
        btnExit = setBTNInvis();
        btnExit.addActionListener(new Handler());
    }

    public JLabel setBG(String path) {
        JLabel lbl = new JLabel();
        BufferedImage IMG = Functions.loadIMG(path);
        if(IMG != null){
            lbl.setIcon(new ImageIcon(IMG));
        }
        lbl.setBounds(0, 0, 800, 600);
        return lbl;
    }

    public JButton setBTNInvis() {
        JButton btn = new JButton();
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        return btn;
    }

    class Handler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnExit) {
                if (_Model.getCurrentPane() == 0) {
                    System.exit(0); // Proper Shutdown
                }
                _Model.setCurrentPane(0);
                _Controller.getCL().show(_Controller, "Home");
            }
        }
    }
}
