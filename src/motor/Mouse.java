package motor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

//Herda a classe do mouse e implementa  aclasse que estuca o mouse
public class Mouse extends MouseAdapter implements MouseMotionListener {
    private boolean isPressed = false;
    private double x = 0.0, y = 0.0;

    @Override
    public void mousePressed(MouseEvent e) {
        isPressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isPressed = false;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public boolean isPressed() {
        return this.isPressed;
    }
}
