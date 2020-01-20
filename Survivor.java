import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/*Last man standing on earth
 * controlled by the player
 * tries to run through zombies, survive, and kill
 * 
 */
public class Survivor extends Actor
{
    //array lists for animation
    private GreenfootImage[] idlePistol;
    private GreenfootImage[] idleRifle;
    private GreenfootImage[] shootPistol;
    private GreenfootImage[] shootRifle;
    private GreenfootImage[] movePistol;
    private GreenfootImage[] moveRifle;
    //variables for animation
    private int numFrames = 19;
    private int animIndex = 0;
    private int actCounter = 0;
    //used when determining if the survivor is moving or not
    private boolean idleness = true;
    //othe actors associated 
    private HealthBar hpBar;
    private MedKit med;
    private Rifle ak47;
    
    //different weapons
    private boolean pistol = true;
    private boolean rifle = false;
    private boolean shooting;
    
    //constructor for Survivor
    public Survivor(HealthBar health)
    {
        this.hpBar = health; //sets the parameter
        
        //a whole bunch of animation constructors
        idlePistol = new GreenfootImage[numFrames];
        for(int i  = 0; i < idlePistol.length; i++)
        {
            String fileName = "survivor-idle_handgun_" + i + ".png";
            idlePistol[i] = new GreenfootImage(fileName);
        }
        setImage(idlePistol[animIndex]);
        
        idleRifle = new GreenfootImage[numFrames];
        for(int i  = 0; i < idleRifle.length; i++)
        {
            String fileName = "survivor-idle_rifle_" + i + ".png";
            idleRifle[i] = new GreenfootImage(fileName);
        }
        setImage(idleRifle[animIndex]);
        
        shootPistol = new GreenfootImage[3];
        for(int i  = 0; i < shootPistol.length; i++)
        {
            String fileName = "survivor-shoot_handgun_" + i + ".png";
            shootPistol[i] = new GreenfootImage(fileName);
        }
        setImage(shootPistol[animIndex]);
        
        shootRifle = new GreenfootImage[3];
        for(int i  = 0; i < shootRifle.length; i++)
        {
            String fileName = "survivor-shoot_rifle_" + i + ".png";
            shootRifle[i] = new GreenfootImage(fileName);
        }
        setImage(shootRifle[animIndex]);
        
        movePistol = new GreenfootImage[numFrames];
        for(int i  = 0; i < movePistol.length; i++)
        {
            String fileName = "survivor-move_handgun_" + i + ".png";
            movePistol[i] = new GreenfootImage(fileName);
        }
        setImage(movePistol[animIndex]);
        
        moveRifle = new GreenfootImage[numFrames];
        for(int i  = 0; i < moveRifle.length; i++)
        {
            String fileName = "survivor-move_rifle_" + i + ".png";
            moveRifle[i] = new GreenfootImage(fileName);
        }
        setImage(moveRifle[animIndex]);
    }
    
    
    //method called every act, does whatever Survivor wants to do
    public void act() 
    {
        actCounter++;
        //if any action is performed, the Survivor is not idle
        if(Greenfoot.isKeyDown("w")||Greenfoot.isKeyDown("a") ||Greenfoot.isKeyDown("s")||Greenfoot.isKeyDown("d")||Greenfoot.isKeyDown("E") || ( Greenfoot.mouseClicked(null)))
        {
           boolean idleness = false;
        }
        //execute idle animations if Survivor is not doing anything
        else if(idleness = true)
        {
           animate(); 
           if(pistol)
           {
               setImage(idlePistol[animIndex]);
           }
           else if(rifle)
           {
               setImage(idleRifle[animIndex]);
            }
        }
        
        look(); // Will let the survivor look around
        move(); // Will allow the survivor to move within the world
        fire(); // Will allow the survivor to fire bullets
        takeDamage();//allow survivor to take damage from zombies
        getMed(); //allow survivor to heal from getting medkits
        getRifle(); // allow the survivor to get new weapons
        
          
    }    
    
    /**
     * makes the survivor to always face the mouse
     */
    public void look()
    {
        MouseInfo follow = Greenfoot.getMouseInfo(); // makes the survivor follow and instance of getmouseinfo
        if(follow !=null)
        {
            turnTowards(follow.getX(),follow.getY()); // The survivor will face towards the mouse directions if the mouse is in the world.
        }
    }
    
    public void move()
    {
        // The player will move north when pressing  W key
        
        if (Greenfoot.isKeyDown("W") || Greenfoot.isKeyDown("S") || Greenfoot.isKeyDown("D") || Greenfoot.isKeyDown("A"))
        {
           animate(); 
           if(pistol)
           {
               setImage(movePistol[animIndex]);
            }
           else if(rifle)
           {
               setImage(moveRifle[animIndex]);
            }
        }
       
        if (Greenfoot.isKeyDown("W"))
        {
            setLocation(getX(), getY() - 5 );
        }
        // The player will move south when pressing the S key
        if (Greenfoot.isKeyDown("S"))
        {
            setLocation(getX(), getY() + 5 );
        }
        // The player will move east when pressing the D key
        if (Greenfoot.isKeyDown("D"))
        {
            setLocation(getX() + 5, getY() );
        }
        // The player will move west when pressing the A key
        if (Greenfoot.isKeyDown("A"))
        {
            setLocation(getX() - 5, getY() );
        }
    }
    
    /**
     * Fires a bullet 
     */
    public void fire()
    {
        animateShoot(); 
        
         //if the player is holding a pistol, he/she can only fire one bullet at a time
        if(pistol)
        {
             if ( Greenfoot.mouseClicked(null))
            {
                //execute shooting animation
                setImage(shootPistol[animIndex]);
                Bullet bullet = new Bullet();
                getWorld().addObject(bullet,getX(),getY());// Creates a bullet at the coordinates of the player
                bullet.setRotation(getRotation());
                bullet.move(100);
                bullet.turn(90);
                bullet.move(40);
                bullet.turn(-90);//moves the bullet to the tip of the gun
            }
        }
        
        //if the player is holding a rifle, he/she can continuously fire  bullets
        else if(rifle)
        {
            /*following two lines borrowed from https://www.greenfoot.org/topics/58360/0
             * checks if the mouse if constantly being held down
             */
            if (shooting && (Greenfoot.mouseDragEnded(null) || Greenfoot.mouseClicked(null))) shooting = false;
            if (!shooting && Greenfoot.mousePressed(null)) shooting = true;
            
            //if the mouse if being held down, it contantly shoots bullets
            if(shooting)
            {
                 setImage(shootRifle[animIndex]);
                 Bullet bullet = new Bullet();
                 if(actCounter % 5 == 0)
                {
                    getWorld().addObject(bullet,getX(),getY());// Creates a bullet at the coordinates of the player  
                }
                 bullet.setRotation(getRotation());
                 bullet.move(100);
                 bullet.turn(90);
                 bullet.move(32);
                 bullet.turn(-90);//moves the bullet to the tip of the gun
            }
        }
           
    }
    
    //tells the health bar to decrease health when survivor come in contact with a zombie
    public void takeDamage()
    {
        Zombie zombies = (Zombie) getOneIntersectingObject (Zombie.class);//collision detection
        if( zombies != null)
        {
            if(actCounter % 40 == 0)
            {
                hpBar.hit(); //calls HealthBar's method to take damage
            }
            
        }
    }
    
    /*check if the survivor is touching MedKit and pressing "E"
     * both condition must be met for survivor to heal
     */
    public void getMed()
    {
        med = (MedKit) getOneIntersectingObject (MedKit.class);
         if(Greenfoot.isKeyDown("e")& med!=null)
        {
            hpBar.heal();
            getWorld().removeObject(med);//removes the medkit after it has been picked up
        }     
    }
     
    /*check if the survivor is touching Rifle and pressing "E"
     * both condition must be met for survivor to grab the weapon
     */
    public void getRifle()
    {
         ak47 = (Rifle) getOneIntersectingObject (Rifle.class);
         if(Greenfoot.isKeyDown("e")& ak47!=null || Greenfoot.isKeyDown("r"))
        {
             pistol = false;
             rifle = true;
            getWorld().removeObject(ak47);//remove the rifle aster it was already been picked up
        }     
    }
    
    //method for animation
    private void animate ()
    {
        if (actCounter % 5 == 0){        
            animIndex++;
        }
        if (animIndex > numFrames - 1){
            animIndex = 1;
        }
    }
    
    //method for animating shoot animations
    private void animateShoot ()
    {
        if (actCounter % 5== 0){        
            animIndex++;
        }
        if (animIndex > 2){
            animIndex = 1;
        }
    }
}
