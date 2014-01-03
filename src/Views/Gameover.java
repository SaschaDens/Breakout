package Views;

import dsdevbe.Functions;
import dsdevbe.GameController;
import dsdevbe.GameModel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;

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

public class Gameover extends Layout {
    private JLabel BG;
    private JButton btnRestart, btnChooseChar;
    
    public Gameover(GameController controller, GameModel model) {
        setName("Gameover");
        setLayout(null);
        this._Controller = controller;
        this._Model = model;
        
        btnChooseChar = setBTNInvis();
        btnRestart = setBTNInvis();
        btnRestart.setBounds(250, 352, 220, 26);
        btnChooseChar.setBounds(250, 380, 220, 26);
        btnRestart.addActionListener(new Handler());
        btnChooseChar.addActionListener(new Handler());
        add(btnChooseChar);
        add(btnRestart);
        
        btnExit.setBounds(141, 447, 70, 40);
        add(btnExit);
        
        validate();
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Nodig want standaard JLABEL overschrijft PaintComponent
        g2d.drawImage(Functions.loadIMG("/Views/Gameover.jpg"), 0, 0, null);
        
        g2d.setColor(Color.WHITE);
        g2d.setFont(Functions.getFont("/Fonts/Lobster.ttf", 28f));
        g2d.drawString("Score: " + _Model.getScore(), 250, 320);
    }
    
    class Handler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            repaint();
            if(e.getSource() == btnChooseChar){
                _Model.setCurrentPane(3);
                _Controller.getCL().show(_Controller, "SetChar");
            }
            if(e.getSource() == btnRestart){
                _Model.setCurrentPane(4);
                _Controller.getCL().show(_Controller, "Game");
            }
            _Model.setResetGame(true);
        }
    }
}
