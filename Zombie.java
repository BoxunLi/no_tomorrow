import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.awt.Color;
/*hungry infected undead kind
 * seeks for human brain
 * follows the survivor and tries to devour him
 */

public class Zombie extends Actor
{
   //animation array lists
    private GreenfootImage[] walk;
    private GreenfootImage[] attack;
    //instance varariables
    private int hitPoints;
    private int bulletDamage = 3;
    private int actCounter = 0;
    private int animIndex = 0;
    private int numFrames = 16;
    private int attackNumFrames = 8;
    //other actors
    private Score myScore;
    private Survivor survivor;
    private HealthBar hpBar;
    
    
    //constructor for Zombie
    public Zombie(Score score)
    {
        hitPoints = 10;//sets zombies' HP
        myScore = score;
       // a whole bunch of codes for animation
        walk = new GreenfootImage[numFrames];
        for(int i  = 0; i < walk.length; i++)
        {
            String fileName = "skeleton-move_" + i + ".png";
            walk[i] = new GreenfootImage(fileName);
        }
        setImage(walk[animIndex]);
        
        attack = new GreenfootImage[attackNumFrames];
        for(int i  = 0; i < attack.length; i++)
        {
            String fileName = "skeleton-attack_" + i + ".png";
            attack[i] = new GreenfootImage(fileName);
        }
        setImage(attack[animIndex]);
    }
    
   //called every actm see what the zombie wants to do 
    public void act() 
    {
       actCounter++;
       moveAndAttack();
       die();
    }    
    //combined bot attack and move together
    private void moveAndAttack()
    {
        Actor survivor = (Actor)getWorld().getObjects(Survivor.class).get(0);//gets reference to the Survivor
        turnTowards(survivor.getX(), survivor.getY()); //turn towards always
        if (this.getNeighbours (100, true, Survivor.class).size() > 0)
        {
            attack(); //attack if is close enough, prevent overlaping
        }
        else
        {
           move (1);
           animate(); 
           setImage(walk[animIndex]);
        }
    }
    //attacks the player
    public void attack()
    {
        //set animation for attacking
        animateAttack(); 
        setImage(attack[animIndex]);
    }
    //take damage
    public void shot()
    {
            hitPoints -= bulletDamage;
            BloodPool bldpool= new BloodPool();
            getWorld().addObject(bldpool, getX(), getY());
    }
    
   // if hitpoints decrease to below 0, the world remove the zombie 
    public void die()
    {
        if (hitPoints < 0) 
        {
            myScore.addScoreZombie(); //bonus score is rewared for every zombie deaths
            getWorld().removeObject (this);
        }
    }
   
    //animations
    private void animate ()
    {
        if (actCounter % 5 == 0){        
            animIndex++;
        }
        if (animIndex > numFrames - 1){
            animIndex = 1;
        }
    }
    private void animateAttack ()
    {
        if (actCounter % 7 == 0){        
            animIndex++;
        }
        if (animIndex > attackNumFrames - 1){
            animIndex = 1;
        }
    }
}
