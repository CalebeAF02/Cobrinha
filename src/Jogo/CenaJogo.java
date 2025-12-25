package Jogo;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class CenaJogo extends Cena {

    Retangulo imgFundo, imgFrente;

    public CenaJogo() {
        imgFundo = new Retangulo(0, 0, Constantes.LARGURA_JANELA, Constantes.ALTURA_JANELA);
        imgFrente = new Retangulo(24,48,24*31,24*22);
    }
    @Override
    public void atualiza(double dt) {

    }

    @Override
    public void desenha(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.fill(new Rectangle2D.Double(imgFundo.x, imgFundo.y, imgFundo.largura, imgFundo.altura));

        g2.setColor(Color.WHITE);
        g2.fill(new Rectangle2D.Double(imgFrente.x, imgFrente.y, imgFrente.largura, imgFrente.altura));

    }
}
