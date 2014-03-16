package gui;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;
import utility.Fish;
import utility.Heater;

/**
 *
 * @author Michal Lech
 */
public class AquariumPanel  extends JPanel
                            implements ActionListener{
    protected Vector<Fish> fish;
    protected int NumberOfFish = 10;
    
    protected Random rand = new Random();
    
    protected javax.swing.Timer Timer = new javax.swing.Timer(200, null);
        
    protected BufferedImage background = null;
    protected BufferedImage fish_img = null;
    
    protected final int downLimit = 40;
    
    public AquariumPanel(Heater heater){
        try {
            background = ImageIO.read(new File(".\\src\\gui\\Aquarium.png"));
        } catch (IOException e) {
            System.out.println(e);
        }
        
        try {
            fish_img = ImageIO.read(new File(".\\src\\gui\\fish.png"));
        } catch (IOException e) {
            System.out.println(e);
        }
        
        fish = new Vector<Fish>();
        
        Timer.addActionListener(this);
        Timer.start();
                
        for(int i=0; i<NumberOfFish; ++i){
            Fish temp_f = new Fish(rand.nextInt(background.getWidth() - fish_img.getWidth()),
                    rand.nextInt(background.getHeight() - fish_img.getHeight() - downLimit),
                    heater.getTemperature()/100.0);
            
            heater.addPropertyChangeListener(temp_f);
            
            if(background != null && fish_img != null) {
                temp_f.setMaxX(background.getWidth() - fish_img.getWidth());
                temp_f.setMaxY(background.getHeight() - fish_img.getHeight() - downLimit);
            }
            
            fish.add(temp_f);
            
            Thread temp = new Thread(temp_f);
            temp.start();
        };        
    }
    
    @Override
    public void paint(Graphics graphics){
        super.paint(graphics);
        
        Image bufferedImage = createImage(background.getWidth(), background.getHeight());

        Graphics2D buffer = (Graphics2D) bufferedImage.getGraphics();

        buffer.clearRect(0, 0, background.getWidth(), background.getHeight());
        buffer.drawImage(background, 0, 0, this);
        
        for(int i=0; i<fish.size(); ++i){
            buffer.drawImage(fish_img, fish.get(i).getX(), fish.get(i).getY(), null);
        }
        
        graphics.drawImage(bufferedImage, 0, 0, this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        this.repaint();
    }

}
