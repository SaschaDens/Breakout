package Game;

import java.awt.Graphics2D;
import java.awt.Rectangle;
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

public class Entity {
    private int x, y, dx, dy, type, size = 20, Value;
    private BufferedImage img;

    public Entity(int x, int y, BufferedImage img, int type, int Value) {
        this.x = x;
        this.y = y;
        this.img = img;
        this.dy += 8;
        
        this.type = type;
        this.Value = Value;
    }
    
    public Rectangle getRectangle(){
        return new Rectangle(x, y, size, size);
    }
    
    public boolean isCollision(Paddle pad){
        return getRectangle().intersects(pad.getRectangle());
    }
    
    public void Move(){
        this.y += dy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getType() {
        return type;
    }

    public int getValue() {
        return Value;
    }
    
    public void Draw(Graphics2D g2d){
        g2d.drawImage(img, x, y, null);
    }
}
