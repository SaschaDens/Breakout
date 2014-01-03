package Views;

import dsdevbe.GameController;
import dsdevbe.GameModel;
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

public class About extends Layout {
    private JLabel BG;
    //private GameInterface _Controller;

    public About(GameController controller, GameModel model) {
        setName("About");
        setLayout(null);
        this._Controller = controller;
        this._Model = model;

        btnExit.setBounds(141, 447, 70, 40);
        add(btnExit);
        
        BG = setBG("/Views/About.jpg");   
        add(BG);
        
        validate();
    }
}
