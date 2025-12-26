package jogo.cenas;

import jogo.*;
import jogo.utils.Constantes;
import jogo.utils.Direcao;
import jogo.utils.Retangulo;
import jogo.utils.TecladoControle;
import motor.ICena;
import motor.entradas.Teclado;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class CenaJogo extends ICena {

    private Retangulo imgFundo, imgFrente;
    private Cobrinha cobrinha;

    public Teclado teclado;

    public CenaJogo(Teclado teclado) {
        this.teclado = teclado;

        imgFundo = new Retangulo(0, 0, Constantes.LARGURA_JANELA, Constantes.ALTURA_JANELA);
        imgFrente = new Retangulo(24,48,24*31,24*22);
        cobrinha = new Cobrinha(10,48,48+24,24,24);
    }
    @Override
    public void atualiza(double dt) {
        if (teclado.isKeyPressed(TecladoControle.SETA_SUBIR)) {
            cobrinha.setDiracao(Direcao.ACIMA);
        }
        if (teclado.isKeyPressed(TecladoControle.SETA_DECER)) {
            cobrinha.setDiracao(Direcao.ABAIXO);
        }
        if (teclado.isKeyPressed(TecladoControle.SETA_ESQUERDA)) {
            cobrinha.setDiracao(Direcao.ESQUERDA);
        }
        if (teclado.isKeyPressed(TecladoControle.SETA_DIREITA)) {
            cobrinha.setDiracao(Direcao.DIREITA);
        }
        cobrinha.atualiza(dt);
    }

    @Override
    public void desenha(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        imgFundo.pintar(g2,Color.BLACK);
        imgFrente.pintar(g2, Color.WHITE);

        cobrinha.desenha(g2);

    }
}
