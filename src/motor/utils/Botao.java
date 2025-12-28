package motor.utils;

import jogo.utils.Retangulo;
import motor.entradas.Mouse;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Botao {
    private BufferedImage normal, pressionado, corrente;

    private Retangulo retangulo;

    public Botao(BufferedImage normal, BufferedImage pressionado, Retangulo retangulo) {
        this.normal = normal;
        this.pressionado = pressionado;
        this.corrente = normal;
        this.retangulo = retangulo;

    }

    public boolean isEncima(Mouse mouse){
        if (retangulo.isDentro((int)mouse.getX(), (int)mouse.getY())) {
            corrente = pressionado;
            return true;
        }else{
            corrente = normal;
        }
        return false;
    }

    public boolean isClicado(Mouse mouse){
        if (retangulo.isDentro((int)mouse.getX(), (int)mouse.getY())) {
            if(mouse.isPressed()){
                return true;
            }
        }
        return false;
    }

    public void desenha(Graphics g){
        g.drawImage(corrente, (int)retangulo.x, (int)retangulo.y, (int)retangulo.largura, (int)retangulo.altura, null);
    }
}
