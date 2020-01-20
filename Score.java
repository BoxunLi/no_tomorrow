import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 *records and display your score so you can compare your score with you r friends
 */
public class Score extends Actor
{
   private int score=0; // starts with 0 score
   private int actCounter = 0;
   
   
   public Score()
   {
       //greates the image for score
       setImage(new GreenfootImage("Score: " + score, 24, Color.WHITE, Color.BLACK));
    }
    //called every act, do what ever score wants to do 
    public void act() 
    {
            actCounter ++;
            setImage(new GreenfootImage("Score: " + score, 24, Color.WHITE, Color.BLACK));
            addScoreTime();
    }    
    
    public void addScoreZombie() //bonus points for killing zombies
    {
        score += 5;
    }
    
    public void addScoreTime() //survival time is also a factor of points
    {
            if(actCounter % 70 ==0)
            {
                score += 1;
            }
    }
}
