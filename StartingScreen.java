import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StartingScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StartingScreen extends World
{

    /**
     * Constructor for objects of class StartingScreen.
     * 
     */
    public StartingScreen()
    {    
        // Create a new world with 1015 * 860 cells with a cell size of 1x1 pixels.
        super(1015, 860, 1); 
        
    }
    
    public void act()
    {
       /*change the world into the game world when "space" is pressed
        * starts the game
        */
       
        if(Greenfoot.isKeyDown("space"))
        {
            Greenfoot.setWorld(new MyWorld());
        }
    }
}
