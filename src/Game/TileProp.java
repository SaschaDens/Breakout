package Game;

import java.awt.image.BufferedImage;

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

public class TileProp {
    private BufferedImage img;
    private int ID, bonusChance, startLife,
            nextID, reward;

    public TileProp(int ID, BufferedImage img, int bonusChance, int startLife, int nextID, int reward) {
        this.ID = ID;
        this.img = img;
        this.bonusChance = bonusChance;
        this.startLife = startLife;
        this.nextID = nextID;
        this.reward = reward;
    }

    public BufferedImage getImg() {
        return img;
    }

    public int getID() {
        return ID;
    }

    public int getBonusChance() {
        return bonusChance;
    }

    public int getStartLife() {
        return startLife;
    }

    public int getNextID() {
        return nextID;
    }

    public int getReward() {
        return reward;
    }
}
