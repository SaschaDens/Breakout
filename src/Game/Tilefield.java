package Game;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author DS
 */
public class Tilefield {
    private ObstaclePolygon polygon;
    private BufferedImage img;
    private int id, nextID, x, y,
            bonusChance, brickLife, reward;
    private int size = 30;

    public Tilefield(BufferedImage img, int id, int x, int y, int bonusChance, int brickLife, int nextID, int reward) {
        this.img = img;
        this.id = id;
        this.nextID = nextID;
        this.x = x;
        this.y = y;
        this.bonusChance = bonusChance;
        this.brickLife = brickLife;
        this.reward = reward;

        int[] polygonX = {x, x + size, x + size, x};
        int[] polygonY = {y, y, y + size, y + size};
        this.polygon = new ObstaclePolygon(polygonX, polygonY, Color.BLUE);
    }

    public void set(BufferedImage img, int id, int nextID) {
        this.img = img;
        this.id = id;
        this.nextID = nextID;
    }

    public int getId() {
        return id;
    }

    public int getNextID() {
        return nextID;
    }

    public BufferedImage getImg() {
        return img;
    }

    public ObstaclePolygon getPolygon() {
        return polygon;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getBonusChance() {
        return bonusChance;
    }

    public void setBonusChance(int bonusChance) {
        this.bonusChance = bonusChance;
    }

    public int getBrickLife() {
        return brickLife;
    }

    public void setBrickLife(int brickLife) {
        this.brickLife = brickLife;
    }

    public int getReward() {
        return reward;
    }
}
