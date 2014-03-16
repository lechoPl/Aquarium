package gui;

import java.awt.Dimension;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Michal Lech
 */
public class MySpinner extends JSpinner{
    private int Max = 100;
    
    public MySpinner(int val){
        this.setModel(new SpinnerNumberModel(
                    val, 0, Max, 1));
        
        this.setPreferredSize(new Dimension(45,25));
    }
}
