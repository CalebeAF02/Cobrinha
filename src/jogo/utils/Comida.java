package jogo.utils;

import jogo.Cobrinha;

import java.awt.*;

public class Comida {

    private Retangulo fundo;
    private Cobrinha cobrinha;
    private int largura;
    private int altura;
    private Color cor;
    private Retangulo retangulo;

    public int xMargem;

    public boolean isSpawned;

    public Comida(Retangulo fundo, Cobrinha cobrinha, int largura,  int altura, Color cor){
        this.fundo = fundo;
        this.cobrinha = cobrinha;
        this.largura = largura;
        this.altura = altura;
        this.cor = cor;
        this.retangulo = new Retangulo(0,0,largura,altura);

        xMargem = (int)((Constantes.QUADRADINHO - this.largura) / 2.0);
    }

    public void spawn(){
        do{
            double aleatorioX = (int)(Math.random() * (int)(fundo.largura / Constantes.QUADRADINHO)) * Constantes.QUADRADINHO + fundo.x;
            double aleatorioY = (int)(Math.random() * (int)(fundo.altura / Constantes.QUADRADINHO)) * Constantes.QUADRADINHO + fundo.y;
            this.retangulo.x = aleatorioX;
            this.retangulo.y = aleatorioY;
        }while (cobrinha.colisaoComRetangulo(this.retangulo));
        this.isSpawned = true;
    }

    public void atualiza(double dt){
        if(cobrinha.colisaoComRetangulo(this.retangulo)){
            cobrinha.cresce();
            this.retangulo.x = -100;
            this.retangulo.y = -100;
            isSpawned = false;
        }
    }

    public void desenha(Graphics2D g2){
        g2.setColor(cor);
        g2.fillRect((int)this.retangulo.x + xMargem, (int)this.retangulo.y + xMargem, largura, altura);
    }
}
