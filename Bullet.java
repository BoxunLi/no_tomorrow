import greenfoot.*; 
import java.util.Random;
/**
 * Write a description of class Bullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bullet extends Actor
{
    //instance variables
    private int damage;
    private boolean alreadyExecuted = false; // This boolean is to check if a function has been peformed or not.
    private int bulletSpeed = 20; 
    private int inaccuracy = 4; // This is the amount of random inaccuracy when firing.
    private Zombie z;
    private BloodSplat bld;
    
   //constructor of the Bullet
    public Bullet()
    {
        damage = 3;
       
    }
     /**
     * Act - do whatever the Bullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
         //MouseInfo follow = Greenfoot.getMouseInfo(); // creates follow as an object of getMouseInfo() from the Greenfoot class
        
         /*following "if(!alreadyExecuted) block was borrowed from
          * http://www.greenfoot.org/scenarios/21213
          * for its bullet inacuracy 
          */
        if(!alreadyExecuted) 
        {
            {
                Random rnd = new Random();
                int ran = rnd.nextInt(inaccuracy); // selects a random number based on the inaccuracy value.
                setRotation(getRotation()- 2 + ran);
            }
            alreadyExecuted = true; // Once the previous function is preformed, this will be set to true so it won't be preformed a second time.
        }

        move(bulletSpeed); // moves the speed of the bullet
        if (getX() <= 0 || getX() >= 1010 || getY()<=0 || getY() >= 855)
        {
            getWorld().removeObject(this); // Once the object leaves the boundaries of the world, it removes itself.
        }
        
        else
        {
            Zombie z = (Zombie)getOneIntersectingObject(Zombie.class);
            //excecuted if the bullet hits a zombie
            if(z != null)
            {
                z.shot(); //calls Zombie to excecute his own method to take damage
                BloodSplat bld= new BloodSplat();
                /*creates a little splash of blood that appears where the bullet lands
                 * and it is always splashing away from the zombie
                 */
                MyWorld w = (MyWorld) getWorld();
                w.addObject (bld, getX(), getY());
                bld.setRotation(getRotation() + 90);
                
                w.removeObject(this); //remove the bullet once it hits a zombie
                
            }
            
            
        }
    }    
}
