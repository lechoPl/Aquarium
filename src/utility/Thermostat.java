package utility;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.io.*;

/**
 *
 * @author Michal Lech
 */
public class Thermostat implements VetoableChangeListener, Serializable{
    private final int Min = 15;
    private final int Max = 30;
    
    public Thermostat() {}
    
    @Override
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
        if (!evt.getPropertyName().equals("temperature")) return;
        
        int t = (int) evt.getNewValue();
        
        if (t < Min) {
            throw new PropertyVetoException("to low",evt);
        }
        
        if (t > Max) {
            throw new PropertyVetoException("to high",evt);
        }

    }

}
