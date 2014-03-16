package utility;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author Michal Lech
 */
public class Fish implements Serializable, PropertyChangeListener, Runnable{
    protected int x;
    protected int y;
    protected double speed;
    
    protected int dir = 1;
    protected int deg;
    protected final int degMax = 360;
    protected static Random rand = new Random();
    
    protected final int maxSpeed = 20;
    
    protected int Max_x = 0;
    protected int Max_y = 0;
    
    public Fish(){
        x = y = 0;
        speed = 0.01;
    }
    
    public Fish(int x, int y){
        x = y = 0;
        speed = 0.01;
        deg = 0;
    }
    
    public Fish(int x, int y, double speed){
        this.x = x;
        this.y = y;
        this.speed = speed;
        deg = 0;
    }
    
    public Fish(int x, int y, double speed, int max_x, int max_y){
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.Max_x = max_x;
        this.Max_y = max_y;
        deg = 0;
    }
    
    public synchronized int getX() { return x; }
    public synchronized int getY() { return y; }
    public synchronized double getSpeed() {return speed; }
    public synchronized int getMaxX() { return Max_x; }
    public synchronized int getMaxY() { return Max_y; }
    public synchronized int getDir() { return dir; }
    
    public synchronized void setX(int val) { x = val; }
    public synchronized void setY(int val) { y = val; }
    public synchronized void setSpeed(double val) { speed = val; }
    public synchronized void setMaxX(int val) { Max_x = val; }
    public synchronized void setMaxY(int val) { Max_y = val; }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (!evt.getPropertyName().equals("temperature")) return;
        
        int t = (int) evt.getNewValue();
        
        speed = t / 100.0;
    }
    
    
    public synchronized void move(){
        if(rand.nextInt(1000) % 5 == 0){
            deg = rand.nextInt(degMax+1);
        }
        
        //int temp_y
        int temp_y = (int)(speed * maxSpeed * Math.sin(deg*Math.PI/180));
        //int temp_x
        int temp_x = (int)(speed * maxSpeed * Math.cos(deg*Math.PI/180));
        
        if( y+temp_y > 0 && y+temp_y < Max_y ){
            y = y + temp_y;
        }
        
        if( x+temp_x > 0 && x+temp_x< Max_x ){
            x = x + temp_x;           
        }
    }

    @Override
    public void run() {
        while(true){
            move();


            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {}
        }
    }
    
}
