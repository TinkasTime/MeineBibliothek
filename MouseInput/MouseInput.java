package MouseInput;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

    public static void main(String[] args) {
        
    }

    /*
     * Wichtig:
     * Wenn du 'implements MouseListener' nutzt, muss du die
     * folgenden Methoden überschreiben:
     * mousePressed, mouseClicked, mouseReleased, mouseEntered, mouseExited
     * Dafür ist das @Override optional, aber empfehlenswert.
     * Auch wenn du nicht alle Methoden brauchst, lasse sie dann leer.
     * 
     * Wenn du 'extends MouseInputAdapter' nutzt, erledigt das die
     * Klasse MouseInputAdapter bereits, indem es alle Methoden leer
     * lässt. So kannst du dir dann etwas Code 'sparen'.
     * Du solltest aber wissen, dass man in Java nur von einer Klasse
     * erben kann (also nur ein extends), während du beliebig viele
     * Interfaces implementieren kannst.
     */

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    
    }

    @Override
    public void mouseExited(MouseEvent e) {
    
    }
}
