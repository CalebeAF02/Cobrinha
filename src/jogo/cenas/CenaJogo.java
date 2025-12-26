package jogo.cenas;

import jogo.*;
import jogo.utils.*;
import motor.ICena;
import motor.entradas.Teclado;

import java.awt.*;

public class CenaJogo extends ICena {

    private Teclado teclado;
    private Retangulo imgFundo, imgFrente;

    private Cobrinha cobrinha;
    private Comida comida;

    public CenaJogo(Teclado teclado) {
        this.teclado = teclado;

        imgFundo = new Retangulo(0, 0, Constantes.LARGURA_JANELA, Constantes.ALTURA_JANELA);
        imgFrente = new Retangulo(24,48,Constantes.QUADRADINHO*31,Constantes.QUADRADINHO*22);
        cobrinha = new Cobrinha(2,48,48+24,24,24, imgFrente);
        comida = new Comida(imgFrente,cobrinha,12,12,Color.GREEN);
        comida.spawn();
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

        if((!comida.isSpawned)){
            comida.spawn();
        }

        comida.atualiza(dt);
        cobrinha.atualiza(dt);
    }

    @Override
    public void desenha(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        imgFundo.pintar(g2,Color.BLACK);
        imgFrente.pintar(g2, Color.WHITE);

        cobrinha.desenha(g2);
        comida.desenha(g2);
    }
}
