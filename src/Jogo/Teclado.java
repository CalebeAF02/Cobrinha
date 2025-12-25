package Jogo;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado extends KeyAdapter implements KeyListener {
    private boolean[] teclas = new boolean[128];

    @Override
    public void keyPressed(KeyEvent tecla) {
        teclas[tecla.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent tecla) {
        teclas[tecla.getKeyCode()] = false;
    }

    public boolean isKeyPressed(int tecla) {
        return teclas[tecla];
    }
}
