package Views;

import dsdevbe.Functions;
import dsdevbe.GameController;
import dsdevbe.GameModel;
import java.awt.Color;
import java.awt.Font;
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

public class Home extends Layout{
    private int bWidth = 200, bHeight = 60,
            bStart = 250, bCenter = 300;
    private JButton[] buttons = new JButton[3];
    private String[] menu = new String[] {"START", "HELP", "ABOUT"};
    
    private Color bColor = new Color(210, 50, 45);
    private Font fntBTNS;
    
    private JLabel BG;

    public Home(GameController controller, GameModel model) {
        setName("Home");
        setLayout(null);
        this._Controller = controller;
        this._Model = model;
        
        fntBTNS = Functions.getFont("/Fonts/DroidSans-Bold.ttf", 24f);
                        
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(menu[i]);
            buttons[i].setBackground(bColor);
            buttons[i].setForeground(Color.white);
            buttons[i].setFocusPainted(false);
            buttons[i].setBorderPainted(false);
            buttons[i].setFont(fntBTNS);
            buttons[i].setBounds(bCenter, bStart + (70*i), bWidth, bHeight);
            buttons[i].addActionListener(new Handler());            
        }
        
        for(JButton btn : buttons){
            add(btn);
        }
        
        btnExit.setBounds(182, 397, 70, 40);
        add(btnExit);
        
        BG = new JLabel();
        BG = setBG("/Views/Home.jpg");   
        add(BG);
        
        validate();
    }
    
    class Handler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == buttons[0]){
                _Model.setCurrentPane(3);
                _Controller.getCL().show(_Controller, "SetChar");
            }
            if(e.getSource() == buttons[1]){
                _Model.setCurrentPane(1);
                _Controller.getCL().show(_Controller, "Help");
            }
            if(e.getSource() == buttons[2]){
                _Model.setCurrentPane(2);
                _Controller.getCL().show(_Controller, "About");
            }
        }
    } 
}
