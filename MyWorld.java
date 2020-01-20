import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/*INSTRUCTIONS:
*press "W", "A", "S", and "D" to move 
*aim and shoot with mouse
*press "E" to pick up MedKits and Rifle
*When equipted with Rifle, hold "mouse" to rapidly fire
*
*OBJECTIVE:
*The amount of time you survived and the number of zombies you killed both
*contribute to your final score.
*So survive as long as you can and kill as many as you can!
*
*KNOWN BUGS:
*When a large amount of Zombies are on screen at the same time, the entire
*game slows down.
*
*CITATIONS:
*http://www.wonder-tonic.com/zombie/
*https://opengameart.org/content/miscblooddecalparticles
*http://www.greenfoot.org/scenarios/21257
*https://opengameart.org/content/top-down-sci-fi-shooter-pack
*https://opengameart.org/content/animated-top-down-survivor-player
*https://opengameart.org/content/animated-top-down-zombie
*https://www.turbosquid.com/3d-models/3d-model-ak47-kalashnikov-rifle-games/747060
*/
public class MyWorld extends World
{
    //instance variables
    public GreenfootImage background;
    private int xSpawn;
    private int ySpawn;
    private int ran;
    private int medSpawn = 1000;

    private int actCounter = 0;
    private int spawnRate = 100;
    private Score myScore = new Score();
   
    //constructor for the world
    public MyWorld()
    {    
        // Create a new world with 1015 * 860 cells with a cell size of 1x1 pixels.
        super(1015, 860, 1); 
        //sets the order of overlapping objects
        setPaintOrder(GameOver.class,Rifle.class,HealthBar.class,HealthBackground.class,Score.class,Bullet.class,BloodSplat.class,Zombie.class,Survivor.class,MedKit.class,BloodPool.class); 
        //adds objects into the world
        HealthBar survivorLife= new HealthBar();
        addObject (new HealthBackground(),170,820);
        addObject (survivorLife,200,820);
        addObject (new Survivor (survivorLife), 400, 400); 
        addObject (myScore, 750, 50);
        //sets the background of the world
        background = new GreenfootImage ("background.png");
        this.setBackground(background);
        
    }
    
   
    public void act()
    {
        //an increment act counter
        actCounter ++;
        spawnZombie();
        spawnMed();
        spawnRifle();
      
    }
    
    /*spawns zombies randomly at random locations on the edges of the world
     * the rate at which zombies are spawned increases as time goes
     */
    public void spawnZombie()
    {
        //the likelyhood of a zombie spawning increases as act increases
        if (actCounter > 1000)
        {
            spawnRate = 50;
        }
        if (actCounter > 3000)
        {
            spawnRate = 25;
        }
        if (actCounter > 7000)
        {
            spawnRate = 20;
        }
        int ran = Greenfoot.getRandomNumber(4); //decides which edge will the zombie be spawned
        int randomRate = Greenfoot.getRandomNumber(spawnRate);
        if (randomRate == 0)
        {
            if(ran == 0)
                {
                    xSpawn = Greenfoot.getRandomNumber(1015); //spawns zombie at top edge
                    ySpawn = 0;
                }
                else if(ran == 1)
                {
                    xSpawn = Greenfoot.getRandomNumber(1015);//spawns zombie at bottom edge
                    ySpawn = 860;
                }
                else if(ran == 2)
                {
                    xSpawn = 0;                              //spawns zombie at left edge
                    ySpawn = Greenfoot.getRandomNumber(860);
                }
                else if(ran == 3)
                {
                    xSpawn = 1080;                           //spawns zombie at right edge
                    ySpawn = Greenfoot.getRandomNumber(860);
                }
                
                addObject (new Zombie(myScore), xSpawn, ySpawn);
        }   
    }
   
    //spawns MedKits randomly at random locations at an increasing rate
    public void spawnMed()
    {
        if (actCounter > 1000)
        {
            medSpawn = 800;
        }
        //the chance a MedKit will spawn increases as act increases
        if (actCounter > 3000)
        {
            spawnRate = 400;
        }
        if (actCounter > 7000)
        {
            spawnRate = 100;
        }
        if(Greenfoot.getRandomNumber(medSpawn) == 1)//spawns medkits
        {
     
         int x = Greenfoot.getRandomNumber(1015);
         int y = Greenfoot.getRandomNumber(860);
     
         addObject(new MedKit(), x, y);
        }
    }
    //spawn a rifle at a specific point in the game
    public void spawnRifle()
    {
        if(actCounter == 3000) //sets the point at which one rifle will appear
        {
            int x = Greenfoot.getRandomNumber(1015);//sets random location for rifle
            int y = Greenfoot.getRandomNumber(860);
     
            addObject(new Rifle(), x, y);
        }
    }
    
}
