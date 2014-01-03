package dsdevbe;

import java.awt.CardLayout;
import java.awt.Component;
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

/* 
 * Controller = Weinig verantwoordelijkheid!
 */

public class GameController extends JPanel{
    private CardLayout _cl = new CardLayout();
    private GameModel _model;

    public GameController(GameModel _model) {
        setLayout(_cl);
        this._model = _model;
        
        // http://stackoverflow.com/questions/1936404/removing-a-jcomponent-from-a-cardlayout
        add(new Views.Home(this, _model), "Home");
        add(new Views.SetChar(this, _model), "SetChar");
        add(new Views.Help(this, _model), "Help");
        add(new Views.About(this, _model), "About");
        add(new Game.GUI(this, _model), "Game");
        add(new Views.Gameover(this, _model), "Gameover");
        add(new Views.Finished(this, _model), "Finished");
    }
    
    public CardLayout getCL(){
        return this._cl;
    }
    
    public void removeCard(String card){
        Component[] components = getComponents();
        for (int i = 0; i < components.length; i++) {
            if(components[i].getName().equals(card)){
                _cl.removeLayoutComponent(components[i]);
                System.out.println("Removed");
            }
            System.out.println(components[i].getName());
        }
    }    
}
