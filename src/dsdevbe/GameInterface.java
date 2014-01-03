package dsdevbe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

public class GameInterface extends JPanel{
    private GameModel _model;
    private GameController _controller;
    
    public GameInterface(){
        setPreferredSize(new Dimension(GameModel.WIDTH, GameModel.HEIGHT));
        setLayout(new BorderLayout());
        
        this._model = new GameModel();
        this._controller = new GameController(_model);
        
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(new Handler());
        add(_controller, BorderLayout.CENTER);
    }
    class Handler implements KeyListener{
        // MOET hier staan spijtig genoeg :'( . Heb lang gezocht maar is niet mogelijk ergens anders te plaatsen
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(_model.getCurrentPane() == 4 && e.getKeyCode() == 80){
                _model.togglePause();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }
}
