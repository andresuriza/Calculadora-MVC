package Business_Logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.CalculatorPanel;

/**
 *
 * @author andres
 */
public class Controller implements ActionListener {
    private CalculatorPanel cp;

    public Controller(CalculatorPanel calcP) {
        this.cp = calcP;
        calcP.jButton1.addActionListener(this);
        calcP.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Hola");
    }
}