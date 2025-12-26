package jogo.utils;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Retangulo {
    public double x;
    public double y;
    public double largura;
    public double altura;

    public Retangulo(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.largura = width;
        this.altura = height;
    }

    public boolean isDentro(int posX, int posY){
        if (posX >= x && posX <= x + largura &&
                posY >= y && posY <= y + altura) {
            return true;
        }else{
            return false;
        }
    }

    public void pintar(Graphics2D g2, Color cor){
        g2.setColor(cor);
        g2.fill(new Rectangle2D.Double(x, y, largura, altura));
    }
}
