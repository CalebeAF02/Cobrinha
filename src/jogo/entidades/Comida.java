package jogo.entidades;

import jogo.utils.Constantes;
import jogo.utils.Retangulo;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Comida {

    private BufferedImage imgComida;

    private Retangulo campo;
    private Cobrinha cobrinha;
    private int largura;
    private int altura;
    private Retangulo retangulo;

    private int xMargem;

    private boolean valorIsSpawned;

    public Comida(Retangulo campo, Cobrinha cobrinha, int largura, int altura, BufferedImage imgComida){
        this.campo = campo;
        this.cobrinha = cobrinha;
        this.largura = largura;
        this.altura = altura;
        this.imgComida = imgComida;
        this.retangulo = new Retangulo(0,0,largura,altura);

        xMargem = (int)((Constantes.QUADRADINHO - this.largura) / 2.0);
    }

    public void spawn(){
        do{
            Random random = new Random();
            double aleatorioX= campo.x + random.nextInt(29)*Constantes.QUADRADINHO;
            double aleatorioY= campo.y + random.nextInt(19)*Constantes.QUADRADINHO;
            this.retangulo.x = aleatorioX;
            this.retangulo.y = aleatorioY;

        }while (cobrinha.colisaoComRetangulo(this.retangulo));

        this.valorIsSpawned = true;
    }

    public void atualiza(double dt){
        if(cobrinha.colisaoComRetangulo(this.retangulo)){
            cobrinha.cresce();
            this.retangulo.x = -100;
            this.retangulo.y = -100;
            valorIsSpawned = false;
        }
    }

    public void desenha(Graphics2D g2){
        g2.drawImage(imgComida,(int)this.retangulo.x + xMargem, (int)this.retangulo.y + xMargem, largura, altura, null);
    }

    public boolean isSpawned(){
        return valorIsSpawned;
    }
}
