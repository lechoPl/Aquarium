package utility;

import java.beans.*;
import java.io.Serializable;

/**
 *
 * @author Michal Lech
 */
public class Heater implements Serializable{
    
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private VetoableChangeSupport vcs = new VetoableChangeSupport(this);
    
    protected int temperature;
    
    public Heater() { temperature = 0; }
    public Heater(int val) { temperature = val; }
    
    
    public synchronized void addPropertyChangeListener (PropertyChangeListener lst)
        { pcs.addPropertyChangeListener(lst); }
    public synchronized void removePropertyChangeListener (PropertyChangeListener lst)
        { pcs.removePropertyChangeListener(lst); }
    public synchronized void addVetoableChangeListener (VetoableChangeListener lst)
        { vcs.addVetoableChangeListener(lst); }
    public synchronized void removeVetoableChangeListener (VetoableChangeListener lst)
        { vcs.removeVetoableChangeListener(lst); }
    
       
    public synchronized int getTemperature() { return temperature; } 
    public synchronized void setTemperature(int val) throws PropertyVetoException {
        int oldTemperature = temperature;
        
        vcs.fireVetoableChange("temperature", oldTemperature, val);
        
        temperature = val;
        
        pcs.firePropertyChange("temperature", oldTemperature, val);
    }
}
