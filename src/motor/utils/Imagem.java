package motor.utils;

import jogo.utils.Retangulo;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Imagem {
    public BufferedImage imagem;
    private Retangulo retangulo;

    public Imagem(BufferedImage imagem, Retangulo retangulo) {
        this.imagem = imagem;
        this.retangulo = retangulo;
    }

    public void desenha(Graphics g) {
        g.drawImage(imagem, (int)retangulo.x, (int)retangulo.y, (int)retangulo.largura, (int)retangulo.altura, null);
    }
}
