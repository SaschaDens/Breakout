package Game;

import dsdevbe.Functions;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

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

public class EntitiesManager {
    private BufferedImage entitiesIMG;
    private BufferedImage[] entitiesArrImg;
    private ArrayList<EntitiesProp> props;
    private final int coinBronze = 0, coinGold = 1, coinSilver = 2, gemBlue = 3, gemGreen = 4,
            gemRed = 5, gemYellow = 6, star = 7;
    private int amountEntities = 8, borderoffset = 1, offset = 2, size = 20;

    public EntitiesManager(String resource) {
        this.entitiesIMG = Functions.loadIMG(resource);
        fillEntitiesArr();
        props = new ArrayList<EntitiesProp>();
        props.add(new EntitiesProp(20, 60, entitiesArrImg[coinBronze]));
        props.add(new EntitiesProp(50, 10, entitiesArrImg[coinGold]));
        props.add(new EntitiesProp(35, 30, entitiesArrImg[coinSilver]));
        props.add(new EntitiesProp(100, 4, entitiesArrImg[gemBlue]));
        props.add(new EntitiesProp(100, 4, entitiesArrImg[gemGreen]));
        props.add(new EntitiesProp(100, 4, entitiesArrImg[gemRed]));
        props.add(new EntitiesProp(100, 4, entitiesArrImg[gemYellow]));
        props.add(new EntitiesProp(200, 1, entitiesArrImg[star]));
    }
    
    public void fillEntitiesArr(){
        entitiesArrImg = new BufferedImage[amountEntities];
        for (int i = 0; i < amountEntities; i++) {
            entitiesArrImg[i] = entitiesIMG.getSubimage(borderoffset + (offset * i) + (size * i), borderoffset, size, size);
        }
    }
    
    public EntitiesProp getProp(int ID){
        return props.get(ID);
    }
}