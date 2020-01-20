import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Font;
import java.awt.Color;

/**
 * Write a description of class HealthBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HealthBar extends Actor
{
    //instance varaibles
    private int health = 500;
    private int healthBarWidth = 220;
    private int healthBarHeight = 30;
    //borrowed following line from the internet
    float pixelsPerHealthPoint = (float) healthBarWidth/health;
    
    
    //constuctor
    public HealthBar()
    {
   
    }
    
    public void act() 
    {
        update(); 
        endGame();
    }    
   
    
    public void update ()//creates the bar
    {
        setImage(new GreenfootImage(healthBarWidth + 2, healthBarHeight + 2));
        GreenfootImage myImage = getImage();
        myImage.setColor(Color.WHITE);
        myImage.setColor(Color.GREEN);
        myImage.fillRect(1, 1, Math.round(health*pixelsPerHealthPoint), healthBarHeight);
    }

   
    public void hit()//health remove
    {
          health=health-25;
    }

    public void heal()//add life to the survivor
    {
        if( health < 400)
        {
            health=health+100;
        }
        else
        {
            health = 500;
        }
    }
    
    public void endGame() //ends the game when survivor dies
    {
        if(health < 0) 
        {
           Greenfoot.stop(); // stops the entire game
            getWorld().addObject( new GameOver(), 520, 450);
        }
        
    }
    
}
