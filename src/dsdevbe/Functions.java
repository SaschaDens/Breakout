package dsdevbe;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;

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

public class Functions {
    public static Font getFont(String resource, float size) {
        Font font = null;
        try {
            InputStream is = getResourceStream(resource);
            font = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return font.deriveFont(size);
    }
    
    public static String formMap(int i){
        return "/Maps/level" + i + ".map";
    }

    public static BufferedImage loadIMG(String path) {
        try {
            return ImageIO.read(getResourceStream(path));
        } catch (Exception e) {
            //e.printStackTrace();
            return null;
        }
    }
    
    public static BufferedImage getBallIMG(int i) {
        int amountBalls = 6, offset = 2, size = 20;
        try {
            BufferedImage img = ImageIO.read(getResourceStream("/GameRes/balls.png"));
            return img.getSubimage(offset + (offset * i) + (size * i), offset, size, size);
        } catch (Exception e) {
            //e.printStackTrace();
            return null;
        }
    }
    
    public static int random(int Min, int Max){
        return Min + (int)(Math.random() * ((Max - Min) + 1));
    }
    
    public static InputStream getResourceStream(String path){
        return Functions.class.getResourceAsStream(path);
    }
}
