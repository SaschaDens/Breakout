package Views;

import dsdevbe.GameController;
import dsdevbe.GameModel;
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

public class SetChar extends Layout {
    private int bWidth = 70, bHeight = 95,
            bStart = 300, bCenter = 380;
    private JButton[] btnChars = new JButton[3];
    
    private JLabel BG;
    //private GameInterface _Controller;

    public SetChar(GameController controller, GameModel model) {
        setName("SetChar");
        setLayout(null);
        this._Controller = controller;
        this._Model = model;
        
        for (int i = 0; i < btnChars.length; i++) {
            btnChars[i] = setBTNInvis();
            btnChars[i].setBounds(bStart + (i * (bWidth+10)), bCenter, bWidth, bHeight);
            btnChars[i].addActionListener(new Handler());
        }
        
        for(JButton btn : btnChars){
            add(btn);
        }
        
        btnExit.setBounds(155, 410, 70, 40);
        add(btnExit);

        BG = setBG("/Views/SetChar.jpg");
        add(BG);

        validate();
    }
    class Handler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == btnChars[0]){
                //System.out.println("Green Char");
                _Model.setBallChoice(GameModel.ballGreen);
            }
            if(e.getSource() == btnChars[1]){
                //System.out.println("Blue Char");
                _Model.setBallChoice(GameModel.ballBlue);
            }
            if(e.getSource() == btnChars[2]){
                //System.out.println("Pink Char");
                _Model.setBallChoice(GameModel.ballPink);
            }
            for(JButton btnChar : btnChars){
                if(e.getSource() == btnChar){
                    // GamePanel pas aanmaken wanneer nodig
                    // Overschrijft vorige game panel.
                    _Model.setCurrentPane(4);
                    _Model.setResetGame(true);
                    _Controller.getCL().show(_Controller, "Game");
                }
            }
        }
    }
}
