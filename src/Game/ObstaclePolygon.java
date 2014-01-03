package Game;

import java.awt.Color;
import java.awt.Graphics;

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

public class ObstaclePolygon {
   int[] xPoints;  // x-coordinate of the points 
   int[] yPoints;  // y-coordinate of the points
   Color color;    // Polygon's filled color
   
   /** Constructors */
   public ObstaclePolygon(int[] xPoints, int[] yPoints, Color color) {
      this.xPoints = xPoints;
      this.yPoints = yPoints;
      this.color = color;
   }
   
   /** Constructor with the default color */
   public ObstaclePolygon(int[] xPoints, int[] yPoints) {
      this(xPoints, yPoints, Color.YELLOW);
   }
   
   public void moveX(int[] x){
       this. xPoints = x;
   }

   /** Draw itself using the given graphic context. */
   public void draw(Graphics g) {
      g.setColor(color);
      g.fillPolygon(xPoints, yPoints, xPoints.length);
   }
}
