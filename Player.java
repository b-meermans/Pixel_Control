import greenfoot.*;  
import java.util.Random;

public class Player extends SmoothMover {
    private static int nextPlayerNum = 1;
    
    private static int START_COLOR_VAL = 100;
    private static final double FRICTION_MULTIPLIER = 0.93;
    private static final double FORCE = 0.2;
    private static final int TURN_RADIUS = 5;
    
    private int playerNum;
    private double velocity;
    private Color color;
    private String keyUp;
    private String keyLeft;
    private String keyDown;
    private String keyRight;
    
    public static void reset() {
        nextPlayerNum = 1;
    }
    
    public Player() {
        this("w", "a", "s", "d");
    }
    
    public Player(String keyUp, String keyLeft, String keyDown, String keyRight) {
        this.keyUp = keyUp;
        this.keyLeft = keyLeft;
        this.keyDown = keyDown;
        this.keyRight = keyRight;
        this.playerNum = nextPlayerNum++;
 
        setupImageColor();
        
        setRotation((int)(Math.random() * 256));
    }
    
    private void setupImageColor() {
        GreenfootImage img = getImage();
        color = new Color(getNextColorVal(), getNextColorVal(), getNextColorVal());
        img.setColor(color);
        
        img.drawString(Integer.toString(playerNum), img.getWidth() / 3, img.getHeight() / 2);
        setImage(img);
    }
    
    public void act() {
        adjustForInput();
        
        move(velocity);
        velocity *= FRICTION_MULTIPLIER;
    }
    
    public void adjustForInput() {    
        
        if ( Greenfoot.isKeyDown(Integer.toString(playerNum))) {
            
            if (Greenfoot.isKeyDown(keyUp)) {
                velocity += FORCE;
            }
                
            if (Greenfoot.isKeyDown(keyDown)) {
                velocity -= FORCE;
            }
            
            if (Greenfoot.isKeyDown(keyLeft)) {
                turn(-TURN_RADIUS);
            }
            
            if (Greenfoot.isKeyDown(keyRight)) {
                turn(TURN_RADIUS);
            }
        }
    }
    
    public Color getColor() {
        return color;
    }
    
    private int getNextColorVal() {
        int range = 256 - START_COLOR_VAL;
        return (int)(Math.random() * range) + START_COLOR_VAL;
    }
}
