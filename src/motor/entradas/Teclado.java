package motor.entradas;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado extends KeyAdapter implements KeyListener {

    private boolean[] teclas = new boolean[256];       // estado atual
    private boolean[] teclasPressionadas = new boolean[256]; // evento "just pressed"

    @Override
    public void keyPressed(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();
        if (!teclas[codigo]) {
            // só marca como "pressionada uma vez" se estava solta antes
            teclasPressionadas[codigo] = true;
        }
        teclas[codigo] = true;
    }

    @Override
    public void keyReleased(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();
        teclas[codigo] = false;
        teclasPressionadas[codigo] = false; // reseta
    }

    // retorna se a tecla está segurada
    public boolean isKeyPressed(int tecla) {
        return teclas[tecla];
    }

    // retorna se a tecla foi pressionada apenas uma vez
    public boolean isKeyPressedOnce(int tecla) {
        if (teclasPressionadas[tecla]) {
            teclasPressionadas[tecla] = false; // consome o evento
            return true;
        }
        return false;
    }
}
