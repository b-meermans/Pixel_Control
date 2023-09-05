import greenfoot.*; 
import java.util.*;

public class MyWorld extends World
{   
    private static final int START_X_TEXT = 30;
    private static final int START_Y_TEXT = 20;
    
    public MyWorld() {    
        super(600, 400, 1); 
    }
    
    public void act() {
        
        List<Player> players = getObjects(Player.class);
        int[] controlCount = new int[players.size()];
        
        displayColors(players, controlCount);
        displayPercentages(controlCount);        
    }
    
    public void displayColors(List<Player> players, int[] controlCount) {
                
        GreenfootImage bg = getBackground();
        for (int x = 0; x < bg.getWidth(); x++) {
            for (int y = 0; y < bg.getHeight(); y++) {
                bg.setColorAt(x, y, getClosestColor(x, y, players, controlCount));
            }
        }        
    }
    
    public void displayPercentages(int[] controlCount) {
        GreenfootImage bg = getBackground();
        bg.setColor(Color.BLACK);
        double totalPixels = getWidth() * getHeight();
        for (int i = 0; i < controlCount.length; i++) {
            double percent = controlCount[i] / totalPixels * 100;
            bg.drawString( String.format("Player %d: %5.1f%n", i, percent), START_X_TEXT, (i + 1) * START_Y_TEXT);
        }
    }
    
    public Color getClosestColor(int x, int y, List<Player> players, int[] controlCount) {
        int bestPlayer = 0;
        double bestDistance = Double.MAX_VALUE;
        
        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            double currentDistanceSquared = currentDistanceSquared(x, y, p.getX(), p.getY());
            if (currentDistanceSquared < bestDistance) {
                bestDistance = currentDistanceSquared;
                bestPlayer = i;
            }
        }
        
        controlCount[bestPlayer]++;        
        return players.get(bestPlayer).getColor();
    }
    
    public double currentDistanceSquared(int x1, int y1, int x2, int y2) {
        return Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2);
    }
}
