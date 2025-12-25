package Jogo;

import java.awt.*;

public abstract class Cena {
    public abstract void atualiza(double dt);

    public abstract void desenha(Graphics g);
}
