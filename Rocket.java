import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

/**
 * A rocket that can be controlled by the arrowkeys: up, left, right.
 * The gun is fired by hitting the 'space' key.
 * 
 * @author Poul Henriksen
 * @author Michael Kölling
 * 
 * @version 2.0
 */
public class Rocket extends Mover
{
    private int gunReloadTime;              // The minimum delay between firing the gun.
    private int reloadDelayCount;           // How long ago we fired the gun the last time.
    private Vector acceleration;            // A vector used to accelerate when using booster.
    private int shotsFired;                 // Number of shots fired.
    
    private GreenfootImage rocket = new GreenfootImage("rocket.png");
    private GreenfootImage rocketWithThrust = new GreenfootImage("rocketWithThrust.png");

    /**
     * Initialise this rocket.
     */
    public Rocket()
    {
        gunReloadTime = 5;
        reloadDelayCount = 0;
        acceleration = new Vector(0, 0.3);    // used to accelerate when thrust is on
        increaseSpeed(new Vector(13, 0.3));   // initially slowly drifting
        shotsFired = 0;
        setRotation(270);
    }

    /**
     * Do what a rocket's gotta do. (Which is: mostly flying about, and turning,
     * accelerating and shooting when the right keys are pressed.)
     */
    public void act()
    {
        move();
        checkKeys();
        checkCollision();
        reloadDelayCount++;
    }
    
    /**
     * Return the number of shots fired from this rocket.
     */
    public int getShotsFired()
    {
        return shotsFired;
    }
    
    /**
     * Set the time needed for re-loading the rocket's gun. The shorter this time is,
     * the faster the rocket can fire. The (initial) standard time is 20.
     */
    public void setGunReloadTime(int reloadTime)
    {
        gunReloadTime = reloadTime;
    }
    
    /**
     * Check whether we are colliding with an asteroid.
     */
    public boolean checkCollision() 
    {
        Asteroid a = (Asteroid) getOneIntersectingObject(Asteroid.class);
        if (a != null) {
            getWorld().addObject(new Explosion(), getX(), getY());
            getWorld().removeObject(this);
            return(true);
        }
        else    {
            return(false);
        }
    }

    // public void asteroidCounter()
    // {
        
    // }
    
    /**
     * Check whether there are any key pressed and react to them.
     */
    private void checkKeys() 
    {
        if (Greenfoot.isKeyDown("up"))  {
            thrust(true);
        }
        if (Greenfoot.isKeyDown("down"))  {
            counterThrust(true);
        }
        if (Greenfoot.isKeyDown("down") == false && Greenfoot.isKeyDown("up") == false) {
            setImage(rocket);
        }       
        if (Greenfoot.isKeyDown("left")) {
            turn(-5);
        }        
        if (Greenfoot.isKeyDown("right")) {
            turn(5);
        }
        if (Greenfoot.isKeyDown("space")) {
            fire();
        }      
    }
    
    /**
     * Should the rocket be ignited?
     */
    private void thrust(boolean boosterOn) 
    {
        if (boosterOn == true) {
            setImage(rocketWithThrust);
            acceleration.setDirection(getRotation());
            increaseSpeed(acceleration);
        }
    }

    private void counterThrust(boolean boosterOn) 
    {
        if (boosterOn == true) {
            setImage(rocketWithThrust);
            acceleration.setDirection(getRotation());
            reduceSpeed(acceleration);
        }
    }
    
    /**
     * Fire a bullet if the gun is ready.
     */
    private void fire() 
    {
        if (reloadDelayCount >= gunReloadTime) {
            Bullet b = new Bullet(getMovement().copy(), getRotation());
            getWorld().addObject(b, getX(), getY());
            b.move();
            shotsFired++;
            reloadDelayCount = 0;   // time since last shot fired
        }
    }
}