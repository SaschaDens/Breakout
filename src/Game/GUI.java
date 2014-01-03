package Game;

import dsdevbe.Functions;
import dsdevbe.GameController;
import dsdevbe.GameModel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

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

public class GUI extends JPanel implements Runnable {

    private GameModel _model;
    private GameController _controller;
    private TileManager tm;
    private Thread thread;
    private BufferedImage BG, imgLife;
    private ContainerBox containerbox;
    private ArrayList<Ball> balls;
    private EntitiesManager EM;
    private ArrayList<Entity> Entities;
    private Paddle pad;
    private Font fntScore;

    public GUI(GameController _controller, GameModel _model) {
        setName("Game");
        this._model = _model;
        this._controller = _controller;
        
        // Inladen van gamemap
        tm = new TileManager();
        tm.generateGameField(Functions.formMap(_model.getCurrentLevel()));
        
        containerbox = new ContainerBox(0, 0, GameModel.WIDTH, GameModel.HEIGHT + 40);
        pad = new Paddle(370, 520, 60, 10, Functions.loadIMG("/GameRes/paddle.png"));
        
        // Load Life IMG
        imgLife = Functions.loadIMG("/GameRes/RemainingLife.png");
        
        // Entity stuff
        EM = new EntitiesManager("/GameRes/bonusses.png");
        Entities = new ArrayList<Entity>();

        BG = Functions.loadIMG("/GameRes/gamebg.jpg");
        balls = new ArrayList<Ball>();
        balls.add(new Ball(_model.getStartBallX(), _model.getStartBallY(),
                _model.getBallRadius(),
                _model.getBallRadius(),
                Functions.random(70, 120),
                Functions.getBallIMG(_model.getBallChoice())));

        fntScore = Functions.getFont("/Fonts/Lobster.ttf", 48f);
        
        addMouseMotionListener(new Handler());
        addMouseListener(new Handler());
        
        thread = new Thread(this);
        thread.start();
    }
    
    class Handler implements MouseMotionListener, MouseListener{

        @Override
        public void mouseDragged(MouseEvent e) {
            if (_model.isPadMoving()) {
                if (e.getX() > 30 && e.getX() < GameModel.WIDTH - 30) {
                    pad.moveX(e.getX());
                }
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            if (_model.isPadMoving()) {
                if (e.getX() > 30 && e.getX() < GameModel.WIDTH - 30) {
                    pad.moveX(e.getX());
                }
                if (!_model.isMoving()) {
                    balls.get(0).setX(pad.getX());
                }
            }            
        }

        @Override
        public void mouseClicked(MouseEvent e) {}

        @Override
        public void mousePressed(MouseEvent e) {
            if (!_model.isMoving() && !_model.isGameEnd()) {
                _model.setIsMoving(true);
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {
            if (e.getX() < pad.getMid()) {
                pad.moveX(pad.getMid());
            }
            if (e.getX() > (GameModel.WIDTH - pad.getMid())) {
                pad.moveX(GameModel.WIDTH - pad.getMid());
            }
        }
    }
    
    /**
     * Teken volgorde. Layered tekenen.
     * 1# Background
     * 2# Tegels
     * 3# Paddle
     * 4# Draw
     * 5# LifeEntities
     * 6# Ballen
     */    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Draw Background
        g2d.drawImage(BG, 0, 0, null);
        
        tm.Draw(g2d);
        pad.Draw(g2d);
        
        // Draw Life
        for (int i = 0; i < _model.getBallsLeft(); i++) {
            g2d.drawImage(imgLife, 690 + (i * 32), 565, null);
        }
        
        // Draw Entities
        for (Entity entity : Entities) {
            entity.Draw(g2d);
            if(!_model.isPause()) entity.Move();
        }

        // Draw Balls on screen
        for (Ball b : balls) {
            b.draw(g2d);
        }

        // Draw Score and Pauze on screen
        g.setFont(fntScore);
        g.drawString("" + _model.getScore(), 20, 592);
        if(_model.isPause())g.drawString("Pause - Press P to continue", 170, 320);
    }

    @Override
    public void run() {
        while (_model.isRunning()) {
            long beginTimeMillis, timeTakenMillis, timeLeftMillis;
            beginTimeMillis = System.currentTimeMillis();
            
            if(_model.isResetGame()){
                resetHard();
                for (Ball b : balls) {
                    b.setBallImg(Functions.getBallIMG(_model.getBallChoice()));
                }
                _model.setResetGame(false);
            }

            if (balls.get(0) != null && tm.getGameField() != null) {
                if (_model.isMoving() && !_model.isPause()) {
                    gameUpdate();
                }
            }
            repaint();

            // Draaien op 30 FPS
            timeTakenMillis = System.currentTimeMillis() - beginTimeMillis;
            timeLeftMillis = 1000L / _model.getUPDATE_RATE() - timeTakenMillis;
            if (timeLeftMillis < 5) {
                timeLeftMillis = 5; // Minimum bepaald
            }

            sleep(thread, timeLeftMillis);
        }
    }

    public void sleep(Thread _thread, long millSec) {
        try {
            _thread.sleep(millSec);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Een game routine. Alle game objecten updaten, met correcte object botsing
     */
    public void gameUpdate() {
        float timeLeft = 1.0f;
        
        do {
            float tMin = timeLeft;
            Tilefield[][] tfield = tm.getGameField();

            for (Ball b : balls) {
                // Collision Containerbox
                b.intersect(containerbox, tMin);
                if (b.earliestCollisionResponse.t < tMin) {
                    tMin = b.earliestCollisionResponse.t;
                }

                // Check Collision with PAD
                b.intersect(pad.getPolygon(), tMin);
                if (b.earliestCollisionResponse.t < tMin) {
                    tMin = b.earliestCollisionResponse.t;
                }

                // Check Collision with a Brick
                for (int r = 0; r < tm.getTileRows(); r++) {
                    for (int k = 0; k < tm.getTileKols(); k++) {
                        if (tfield[r][k] != null) {
                            b.intersect(tfield[r][k].getPolygon(), tMin);
                            if (b.earliestCollisionResponse.t < tMin) {
                                tMin = b.earliestCollisionResponse.t;
                                if (tfield[r][k].getNextID() > 1) {
                                    //Special Block
                                    TileProp tp = tm.getProp(tfield[r][k].getNextID());
                                    tfield[r][k].set(tp.getImg(), tp.getID(), tp.getNextID());
                                }

                                tfield[r][k].setBrickLife(tfield[r][k].getBrickLife() - 1);
                                if (tfield[r][k].getBrickLife() <= 0) {
                                    // Destroy Brick
                                    _model.setScore(_model.getScore() + 10);
                                    // Check if block rewards entity
                                    // System.out.println("Chance: " + tfield[r][k].getBonusChance() + " Random: " + random(1, 100));
                                    if (tfield[r][k].getBonusChance() >= Functions.random(1, 100)) {
                                        // Spawn Entity
                                        Entities.add(
                                                new Entity(tfield[r][k].getX() + 5, // +5 voor te centreren
                                                tfield[r][k].getY() + 5,
                                                EM.getProp(tfield[r][k].getReward()).getImg(),
                                                tfield[r][k].getReward(),
                                                EM.getProp(tfield[r][k].getReward()).getValue()));
                                    }
                                    tfield[r][k] = null;
                                }
                            }
                        }
                    }
                }

                // Update plaats
                b.update(tMin);
            }
            
            // Check Collision Entities
            for (int i = 0; i < Entities.size(); i++) {
                boolean isRemoved = false;
                if (!isRemoved && Entities.get(i).isCollision(pad)) {
                    // Add score
                    _model.setScore(_model.getScore() + Entities.get(i).getValue());
                    //Score += Entities.get(i).getValue();
                    //System.out.println(Entities.get(i).getType());
                    // Remove Object instant
                    isRemoved = true;
                    if (Entities.get(i).getType() == 7) {
                        // Special Ball Effect Spawn 3 Balls
                        for (int j = 0; j < 2; j++) {
                            balls.add(
                                    new Ball(balls.get(0).getX(),
                                    balls.get(0).getY(),
                                    balls.get(0).getRadius(),
                                    balls.get(0).getSpeed(),
                                    Functions.random(20, 70),
                                    Functions.getBallIMG(_model.getBallChoice())
                                    ));
                        }
                    }
                }
                if (!isRemoved && Entities.get(i).getY() > GameModel.HEIGHT) {
                    // Remove Object instant
                    isRemoved = true;
                }
                if (isRemoved) {
                    Entities.remove(i);
                }
            }

            for (int i = 0; i < balls.size(); i++) {
                if (balls.get(i).getY() >= GameModel.HEIGHT + balls.get(i).getRadius()) {
                    if (balls.size() == 1) {
                        _model.setIsMoving(false);
                        _model.setBallsLeft(_model.getBallsLeft()-1);
                        resetSoft();
                        if (_model.getBallsLeft() == 0) {
                            _model.setIsGameEnd(true);
                            _controller.getCL().show(_controller, "Gameover");
                            _model.setCurrentPane(5);
                        }
                    }
                    if (balls.size() > 1) {
                        balls.remove(i);
                    }
                }
            }

            // Artificial Inteligence
            if(_model.isAutoPlay()){
                _model.setIsPadMoving(false);
                pad.moveX((int) balls.get(0).getX());
            }

            timeLeft -= tMin;                // Subtract the time consumed and repeat

            // FINAL CHECK if boxes are bricked
            int countBoxes = 0;
            for (int r = 0; r < tfield.length; r++) {
                for (int k = 0; k < tfield[r].length; k++) {
                    if (tfield[r][k] != null) {
                        if (tfield[r][k].getId() > 1) {
                            countBoxes++;
                        }
                    }
                }
            }
            //Check if some bonus enitities are still up else stop game.
            if (countBoxes == 0 && Entities.isEmpty()) {
                // Level Stopped, Load next level
                resetSoft();
                if((_model.getCurrentLevel()+1) <= _model.getMaxLevel()){
                    _model.setCurrentLevel(_model.getCurrentLevel()+1);
                    tm.generateGameField(Functions.formMap(_model.getCurrentLevel()));
                } else{
                    // STOP spel en toon highscore
                    _model.setIsGameEnd(true);
                    _controller.getCL().show(_controller, "Finished");
                    _model.setCurrentPane(6);                    
                }
            }
        } while (timeLeft > GameModel.EPSILON_TIME);  // Ignore remaining time less than threshold
    }
    
    /**
     * Soft Reset of Game
     */
    private void resetSoft() {
        // Alle ballen verwijderen buiten de eerste
        Ball firstBall = balls.get(0);
        balls.clear();
        balls.add(firstBall);
        
        // Reset Ball Position
        balls.get(0).setX(_model.getStartBallX());
        balls.get(0).setY(_model.getStartBallY());
        _model.setIsMoving(false);
        
        // Reset Angle
        for (Ball b : balls) {
            b.setAngle(_model.getBallSpeed(), Functions.random(70, 120));
        }
        // Clear all falling entities
        Entities.removeAll(Entities);
    }
    
    /**
     * Full Reset of game vars
     */
    public void resetHard() {
        resetSoft();
        // Reset Score and Balls
        _model.resetGame();
        tm.generateGameField(Functions.formMap(_model.getCurrentLevel()));
    }
}
