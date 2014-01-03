package Game;

import dsdevbe.Functions;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

public class TileManager {

    private int spriteRows = 4, spriteKols = 4, spriteOffset = 2, spriteSize = 30;
    private int tileSize = 30, tileOffset = 2, tileRows = 10, tileKols = 25;
    private BufferedImage[][] tileImgs;
    private ArrayList<TileProp> propList;
    private Tilefield[][] GameField;
    private int[][] Map;
    private final int empty = 0, brickWall = 1, boxItemAlt = 2, boxItem = 3, boxExplosive_disabled = 4,
            boxWarning = 5, boxExplosiveAlt = 6, boxCoin_disabled = 7, boxCoinAlt_disabled = 8,
            boxItem_disabled = 9, boxExplosive = 10, boxCoinAlt = 11, boxAlt = 12,
            boxItemAlt_disabled = 13, boxEmpty = 14, boxCoin = 15, box = 16;
    private final int coinBronze = 0, coinGold = 1, coinSilver = 2, gemBlue = 3, gemGreen = 4,
            gemRed = 5, gemYellow = 6, star = 7;

    public TileManager() {
        Map = new int[tileRows][tileKols];
        GameField = new Tilefield[tileRows][tileKols];

        loadProps(); // Inladen van alle box props
        //generateGameField("/Maps/level1.map");
    }

    public int getTileRows() {
        return tileRows;
    }

    public int getTileKols() {
        return tileKols;
    }

    public Tilefield[][] getGameField() {
        return GameField;
    }

    public void loadProps() {
        fillTiles("/GameRes/boxes.png");    // Loading Tiles into the array
        propList = new ArrayList<TileProp>();
        // Voor volledige lijst van OMGANG van boxes brick. Zie WORD document!
        // int ID, BufferedImage img, int bonusChance, int startLife, int nextID, int reward
        // Eerste Rij
        propList.add(new TileProp(brickWall, tileImgs[0][0], 0, 99, brickWall, -1));
        propList.add(new TileProp(boxItemAlt, tileImgs[0][1], 100, 2, boxItem, coinGold));
        propList.add(new TileProp(boxItem, tileImgs[0][2], 75, 1, empty, coinSilver));
        propList.add(new TileProp(boxExplosive_disabled, tileImgs[0][3], 80, 2, boxExplosive, coinGold));
        // Tweede Rij
        propList.add(new TileProp(boxWarning, tileImgs[1][0], 10, 1, empty, gemGreen));
        propList.add(new TileProp(boxExplosiveAlt, tileImgs[1][1], 60, 3, boxExplosive_disabled, gemRed));
        propList.add(new TileProp(boxCoin_disabled, tileImgs[1][2], 100, 2, boxCoinAlt_disabled, star));
        propList.add(new TileProp(boxCoinAlt_disabled, tileImgs[1][3], 60, 3, boxCoinAlt, gemBlue));
        // Derde Rij
        propList.add(new TileProp(boxItem_disabled, tileImgs[2][0], 100, 2, boxItemAlt_disabled, star));
        propList.add(new TileProp(boxExplosive, tileImgs[2][1], 90, 1, empty, coinSilver)); // RAAR?
        propList.add(new TileProp(boxCoinAlt, tileImgs[2][2], 88, 2, boxCoin, coinGold));
        propList.add(new TileProp(boxAlt, tileImgs[2][3], 35, 1, empty, coinSilver));
        // Vierde Rij
        propList.add(new TileProp(boxItemAlt_disabled, tileImgs[3][0], 60, 3, boxItemAlt, gemYellow));
        propList.add(new TileProp(boxEmpty, tileImgs[3][1], 35, 1, empty, coinBronze));
        propList.add(new TileProp(boxCoin, tileImgs[3][2], 80, 1, empty, coinGold));
        propList.add(new TileProp(box, tileImgs[3][3], 35, 1, empty, coinBronze));
    }

    public void loadMap(String path) {
        try {
            InputStream is = Functions.getResourceStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String SChar = "-";
            for (int r = 0; r < tileRows; r++) {
                String line = br.readLine();
                String[] tokens = line.split(SChar);
                for (int k = 0; k < tileKols; k++) {
                    Map[r][k] = Integer.parseInt(tokens[k]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fillTiles(String path) {
        tileImgs = new BufferedImage[spriteRows][spriteKols];
        try {
            BufferedImage img = ImageIO.read(Functions.getResourceStream(path));
            for (int r = 0; r < spriteRows; r++) {
                for (int k = 0; k < spriteKols; k++) {
                    tileImgs[r][k] = img.getSubimage(
                            (spriteOffset * (k + 1)) + (k * spriteSize),
                            (spriteOffset * (r + 1)) + (r * spriteSize),
                            spriteSize, spriteSize);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Find ID in arraylist proplist
    public TileProp getProp(int ID) {
        return this.propList.get(ID - 1); // -1 omdat eerste block start vanaf 1 - 16
    }

    // Add in fields array
    public void generateGameField(String path) {
        loadMap(path);
        for (int r = 0; r < GameField.length; r++) {
            for (int k = 0; k < GameField[r].length; k++) {
                if (Map[r][k] != 0) {
                    TileProp tileprop = getProp(Map[r][k]);
                    GameField[r][k] = new Tilefield(
                            tileprop.getImg(),
                            tileprop.getID(),
                            (tileOffset * k) + (k * tileSize), (tileOffset * r) + (r * tileSize),
                            tileprop.getBonusChance(),
                            tileprop.getStartLife(),
                            tileprop.getNextID(),
                            tileprop.getReward());
                } else {
                    GameField[r][k] = null;
                }
            }
        }
    }

    // Draw result
    public void Draw(Graphics2D g2d) {
        for (int r = 0; r < GameField.length; r++) {
            for (int k = 0; k < GameField[r].length; k++) {
                if (GameField[r][k] != null) {
                    g2d.drawImage(GameField[r][k].getImg(), GameField[r][k].getX(), GameField[r][k].getY(), null);
                }
            }
        }
    }
}