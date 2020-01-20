import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * shoots out every time a zombie gets shot
 */
public class BloodSplat extends Actor
{
    private int animIndex = 0;
    private int numFrames = 13;
    private GreenfootImage[] splat;
    private int actCounter = 0;
    private boolean shouldBeRemoved = false;
     
    public BloodSplat()
    {
        //animation
        splat = new GreenfootImage[numFrames];
        for(int i  = 0; i < splat.length; i++)
        {
            String fileName = "bloodsplat" + i + ".png";
            splat[i] = new GreenfootImage(fileName);
        }
        setImage(splat[animIndex]);
    }
    
    //act method: it animate and put together the blood splat
    public void act() 
    {
        actCounter ++;
        animateBlood();
        setImage(splat[animIndex]);
    }    
    
    //method called when added to the world
    public void addedToWorld (World w)
    {
        act();
    }
    
    //animation
    public void animateBlood ()
    {
         if (actCounter % 5 == 0)
           {        
               animIndex++;
           }
         if (animIndex > numFrames -1 )
         {
              getWorld().removeObject(this);
             animIndex = 1;
         }
    }
}
