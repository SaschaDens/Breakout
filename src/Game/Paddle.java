package Game;

import java.awt.Color;
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

public class Paddle {
    private int x, y, width, height;
    private ObstaclePolygon polygon;
    private BufferedImage paddleIMG;

    public Paddle(int x, int y, int width, int height, BufferedImage paddleIMG) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        
        this.paddleIMG = paddleIMG;
        
        int[] polygonX = {x, x + (width/2), x+width, x+width, x};
        int[] polygonY = {y, y-5, y, y+height, y+height};
        this.polygon = new ObstaclePolygon(polygonX, polygonY, Color.green);
    }

    public ObstaclePolygon getPolygon() {
        return polygon;
    }
    
    public Rectangle getRectangle(){
        return new Rectangle(x, y, width, height);
    }
    
    public int getMid(){
        return this.width/2;
    }

    public int getX() {
        return x + getMid();
    }
    
    public void moveX(int xMiddle){
        //Verander X waarde
        this.x = xMiddle - (width/2);
        //Verander poligon mee zodat collision mogelijk is.
        int[] polygonX = {x, x + (width/2), x+width, x+width, x};
        polygon.moveX(polygonX);
    }
    
    public void Draw(Graphics2D g2d){
        g2d.drawImage(paddleIMG, x, y, null);
    }
}
