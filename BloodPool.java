import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * creates a blood pool everytime a zombie is hit
 * much of this was borrowed from the OOP lesson Bug Simulation's DeadBug
 */
public class BloodPool extends Actor
{
    //there are some variation for blood pool, this chooses a randome one each time
    int ran;
    public BloodPool ()
    {
        ran = Greenfoot.getRandomNumber(3);
        setImage("BloodPool" + ran + ".png");
    }
    //called when added to the world
    public void addedToWorld (World w)
    {
        act();
    }
    
    // allows the blood pool to slowly fade away to keep the background clean
    public void act()
    {
        this.getImage().setTransparency(getImage().getTransparency() - 1);
        if (this.getImage().getTransparency() < 5)
        {
            getWorld().removeObject(this);
        }
    }
}
