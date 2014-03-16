package gui;

import java.awt.*;
import java.beans.PropertyVetoException;
import javax.swing.*;
import javax.swing.event.*;
import utility.*;

/**
 *
 * @author Michal Lech
 */
public class MainFrame extends JFrame{
    protected AquariumPanel panel;
    protected Heater heater;
    protected Thermostat thermostat;
    
    
    
    public MainFrame(){
        super("Akwarium");
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //------- SIZE ------------
        Dimension dScreanSize = Toolkit.getDefaultToolkit().getScreenSize();
               
        int width = 806;
        int height = 628;
        
        this.setSize(width, height);
        this.setResizable(false);
        this.setLocation((dScreanSize.width - width)/2,
                (dScreanSize.height - height)/2);
        
        //--------------------------
        heater = new Heater(15);
        thermostat = new Thermostat();
        
        heater.addVetoableChangeListener(thermostat);
        
        setLayout(new BorderLayout());
        JPanel spinner_panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        MySpinner spinner = new MySpinner(15);
        spinner.addChangeListener(SpinList);
        
        spinner_panel.add(new JLabel("Grzałka"));
        spinner_panel.add(spinner);
        
        add(spinner_panel, BorderLayout.PAGE_START);
        
        panel = new AquariumPanel(heater);
        add(panel, BorderLayout.CENTER);
        
        this.setVisible(true);
    }
    
    private ChangeListener SpinList = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            MySpinner temp = ((MySpinner)e.getSource());
            int val = (int)temp.getValue();
            
            
            try {
                heater.setTemperature(val);
            } catch (PropertyVetoException ex) {
                
                
                temp.setValue(ex.getPropertyChangeEvent().getOldValue());
                
                String msg;
                
                if((int)ex.getPropertyChangeEvent().getOldValue() > (int)ex.getPropertyChangeEvent().getNewValue()){
                    msg = "Za mała temperetura";
                }else{
                    msg = "Za wysoka temperatura";
                }
                JOptionPane.showMessageDialog(temp.getRootPane(),msg);
            }
        }
    };
}
