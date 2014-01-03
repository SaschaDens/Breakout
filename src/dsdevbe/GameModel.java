package dsdevbe;

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

public class GameModel {

    public static final int ballGreen = 2, ballBlue = 0, ballPink = 4,
            WIDTH = 800, HEIGHT = 600; // Leesbaar bij aanroepen
    public static final float EPSILON_TIME = 1e-2f;  // Threshold for zero time
    private final int startBallX = 400, startBallY = 500, UPDATE_RATE = 30;
    private int currentPane, // 0 = Home, 1 = Help, 2 = About, 3 = SetChar, 4 = Game, 5 = Gameover
            ballChoice = 0, // 0 = Blue, 1 = Steel Blue, 2 = Green, 3 = Steel Green, 4 = Pink, 5 = Steel Pink
            ballSpeed = 10,
            ballRadius = 9,
            ballsLeft = 3,
            Score = 0,
            currentLevel = 1,
            startLevel = 1,
            maxLevel = 3;
    private boolean isRunning = true,
            isMoving = false,
            isPadMoving = true,
            isGameEnd = false,
            autoPlay = false, // Als dit aanstaat speelt de computer Breakout!
            resetGame = false,
            isPause = false;

    public int getBallChoice() {
        return ballChoice;
    }

    public void setBallChoice(int ballChoice) {
        this.ballChoice = ballChoice;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public int getCurrentPane() {
        return currentPane;
    }

    public void setCurrentPane(int currentPane) {
        this.currentPane = currentPane;
    }

    public int getBallSpeed() {
        return ballSpeed;
    }

    public void setBallSpeed(int ballSpeed) {
        this.ballSpeed = ballSpeed;
    }

    public int getBallsLeft() {
        return ballsLeft;
    }

    public void setBallsLeft(int ballsLeft) {
        this.ballsLeft = ballsLeft;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int Score) {
        this.Score = Score;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setIsMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }

    public boolean isPadMoving() {
        return isPadMoving;
    }

    public void setIsPadMoving(boolean isPadMoving) {
        this.isPadMoving = isPadMoving;
    }

    public boolean isGameEnd() {
        return isGameEnd;
    }

    public void setIsGameEnd(boolean isGameEnd) {
        this.isGameEnd = isGameEnd;
    }

    public int getStartBallX() {
        return startBallX;
    }

    public int getStartBallY() {
        return startBallY;
    }

    public int getUPDATE_RATE() {
        return UPDATE_RATE;
    }

    public int getBallRadius() {
        return ballRadius;
    }

    public void setBallRadius(int ballRadius) {
        this.ballRadius = ballRadius;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public boolean isAutoPlay() {
        return autoPlay;
    }

    public boolean isResetGame() {
        return resetGame;
    }

    public void setResetGame(boolean resetGame) {
        this.resetGame = resetGame;
    }

    public boolean isPause() {
        return isPause;
    }

    public void togglePause() {
        this.isPause = !this.isPause;
        this.isPadMoving = !isPause;
    }

    public void resetGame() {
        ballsLeft = 3;
        Score = 0;
        currentLevel = startLevel;
        isMoving = false;
        isPadMoving = true;
        isGameEnd = false;
    }
}
