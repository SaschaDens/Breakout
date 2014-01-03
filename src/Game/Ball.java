package Game;

import collisionphysics.CollisionPhysics;
import collisionphysics.CollisionResponse;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * @description Java project geschreven als student ICT aan Thomasmore Kempen.
 *              Breakout: Doel van het spel is om alle blokjes weg te werken,
 *                        zonder dat de bal in het water valt
 * Grafisch werk zelf bewerkt naar noden van toepassing.
 * URL van assets: http://kenney.nl/post/platformer-art-assets-deluxe
 * @Stats 2905 Lijnen code en veel tijd :)
 * @website ds-dev.be -> voor handleiding van het spel -> voor handleiding van het spel
 * @version V1.0
 * @author Dens Sascha
 * 
 * @Gebruikte bron: Collision detectie van Hock-Chuan Chua met eigen implementatie hiervan.
 *                  URL: http://www.ntu.edu.sg/home/ehchua/programming/java/J8a_GameIntro-BouncingBalls.html
 */

public class Ball {
    private BufferedImage ballImg;

    private float x, y,
            speedX, speedY,
            radius;

    public CollisionResponse earliestCollisionResponse = new CollisionResponse();

    public Ball(float x, float y, float radius, float speed, float angleInDegree, BufferedImage ballImg) {
        this.x = x;
        this.y = y;
        
        this.speedX = (float) (speed * Math.cos(Math.toRadians(angleInDegree)));
        this.speedY = (float) (-speed * (float) Math.sin(Math.toRadians(angleInDegree)));
        this.radius = radius;
        
        this.ballImg = ballImg;
    }
    
    private CollisionResponse tempResponse = new CollisionResponse();

    /**
     * Check if this ball collides with the container box in the coming
     * time-step.
     *
     * @param box: outer rectangular container.
     * @param timeLimit: upperbound of the time interval.
     */
    public void intersect(ContainerBox box, float timeLimit) {
        // Call movingPointIntersectsRectangleOuter, which returns the 
        // earliest collision to one of the 4 borders, if collision detected.
        CollisionPhysics.pointIntersectsRectangleOuter(
                this.x, this.y, this.speedX, this.speedY, this.radius,
                box.minX, box.minY, box.maxX, box.maxY,
                timeLimit, tempResponse);
        if (tempResponse.t < earliestCollisionResponse.t) {
            earliestCollisionResponse.copy(tempResponse);
        }
    }
    // Working copy for computing response in intersect(Ball, timeLimit), 
    // to avoid repeatedly allocating objects.
    private CollisionResponse thisResponse = new CollisionResponse();
    private CollisionResponse anotherResponse = new CollisionResponse();

    /**
     * Check if this ball collides with the given BlockPolygon in the interval
     * (0, timeLimit].
     *
     * @param polygon: the polygon-shape obstacle.
     * @param timeLimit: upperbound of the time interval.
     */
    public void intersect(ObstaclePolygon polygon, float timeLimit) {
        int numPoints = polygon.xPoints.length;
        CollisionPhysics.pointIntersectsPolygon(
                this.x, this.y, this.speedX, this.speedY, this.radius,
                polygon.xPoints, polygon.yPoints, numPoints,
                timeLimit, tempResponse);
        if (tempResponse.t < earliestCollisionResponse.t) {
            earliestCollisionResponse.copy(tempResponse);
        }
    }
    /**
     * Update the states of this ball for the given time, where time <= 1.
     *
     * @param time: the earliest collision time detected in the system. If this
     * ball's earliestCollisionResponse.time equals to time, this ball is the
     * one that collided; otherwise, there is a collision elsewhere.
     */
    public void update(float time) {
        // Check if this ball is responsible for the first collision?
        if (earliestCollisionResponse.t <= time) { // FIXME: threshold?
            // This ball collided, get the new position and speed
            this.x = earliestCollisionResponse.getNewX(this.x, this.speedX);
            this.y = earliestCollisionResponse.getNewY(this.y, this.speedY);
            this.speedX = earliestCollisionResponse.newSpeedX;
            this.speedY = earliestCollisionResponse.newSpeedY;
        } else {
            // This ball does not involve in a collision. Move straight.
            this.x += this.speedX * time;
            this.y += this.speedY * time;
        }
        // Clear for the next collision detection
        earliestCollisionResponse.reset();
    }

    public void setBallImg(BufferedImage ballImg) {
        this.ballImg = ballImg;
    }
    
    public void draw(Graphics2D g2d) {
        g2d.drawImage(ballImg, (int) (x-radius), (int) (y-radius), null);
    }
    
    public float getRadius() {
        return radius;
    }
    
    public float getSpeed() {
        return (float) Math.sqrt(speedX * speedX + speedY * speedY);
    }
    
    public float getMoveAngle() {
        return (float) Math.toDegrees(Math.atan2(-speedY, speedX));
    }
    
    public float getMass() {
        return radius * radius * radius / 1000f;
    }
    
    public float getKineticEnergy() {
        return 0.5f * getMass() * (speedX * speedX + speedY * speedY);
    }

    public float getY() {
        return this.y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }
    
    public void setAngle(float speed, float angleInDegree){
        this.speedX = (float) (speed * Math.cos(Math.toRadians(angleInDegree)));
        this.speedY = (float) (-speed * (float) Math.sin(Math.toRadians(angleInDegree)));
    }
}