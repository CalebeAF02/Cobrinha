package jogo;

import jogo.utils.Direcao;
import jogo.utils.Retangulo;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Cobrinha {
    public Retangulo[] corpo = new Retangulo[100];

    public double larguraCorpo , alturaCorpo;

    public int tamanho;
    public int calda = 0;
    public int cabeca = 0;

    public Direcao direcao = Direcao.DIREITA;

    public final double TEMPO_DE_ESPERA = 0.2f;
    public double tempoAguardando = TEMPO_DE_ESPERA;

    public Cobrinha(int tamanho, double startX, double startY, double larguraCorpo, double alturaCorpo ) {
        this.tamanho = tamanho;
        this.larguraCorpo = larguraCorpo ;
        this.alturaCorpo = alturaCorpo;

        for(int i = 0; i <= tamanho; i++){
            Retangulo parteDoCorpo = new Retangulo(startX+ i*larguraCorpo, startY, larguraCorpo, alturaCorpo);
            corpo[i] = parteDoCorpo;
            cabeca++;
        }
        cabeca--;
    }

    public void atualiza(double dt){
        if(tempoAguardando > 0){
            tempoAguardando -= dt;
            return;
        }
        tempoAguardando = TEMPO_DE_ESPERA;
        double posX = 0;
        double posY = 0;

        if (direcao == Direcao.DIREITA) {
            posX = corpo[cabeca].x + larguraCorpo;
            posY = corpo[cabeca].y;
        }else if (direcao == Direcao.ESQUERDA) {
            posX = corpo[cabeca].x - larguraCorpo;
            posY = corpo[cabeca].y;
        }else if (direcao == Direcao.ACIMA) {
            posX = corpo[cabeca].x;
            posY = corpo[cabeca].y - larguraCorpo;
        }else if (direcao == Direcao.ABAIXO ) {
            posX = corpo[cabeca].x;
            posY = corpo[cabeca].y + larguraCorpo;
        }
        corpo[(cabeca+1) % corpo.length] = corpo[calda];
        corpo[calda] = null;
        cabeca = (cabeca+1) % corpo.length;
        calda =  (calda+1) % corpo.length;

        corpo[cabeca].x = posX;
        corpo[cabeca].y = posY;
    }


    public void desenha(Graphics2D g2){
        for(int i = calda; i != cabeca; i = (i + 1) % corpo.length){
            Retangulo parteDoCorpo = corpo[i];
            double subLargura = (parteDoCorpo.largura - 6.0) /2.0;
            double subAltura = (parteDoCorpo.altura - 6.0) /2.0;


            if(i+1 == cabeca){
                g2.setColor(Color.BLACK);
            }else{
                g2.setColor(Color.MAGENTA);
            }
            g2.fill(new Rectangle2D.Double(parteDoCorpo.x + 2.0, parteDoCorpo.y + 2.0, subLargura, subAltura));
            g2.fill(new Rectangle2D.Double(parteDoCorpo.x + 4.0 + subLargura,  parteDoCorpo.y + 2.0, subLargura, subAltura));
            g2.fill(new Rectangle2D.Double(parteDoCorpo.x + 2.0, parteDoCorpo.y + 4.0 + subAltura,  subLargura, subAltura));
            g2.fill(new Rectangle2D.Double(parteDoCorpo.x + 4.0 + subLargura, parteDoCorpo.y + 4.0 + subAltura,  subLargura, subAltura));

        }
    }

    public void setDiracao(Direcao newDirecao){
        if (newDirecao == Direcao.DIREITA && direcao != Direcao.ESQUERDA) {
            direcao = newDirecao;
        }else if (newDirecao == Direcao.ESQUERDA && direcao != Direcao.DIREITA) {
            direcao = newDirecao;
        }else if (newDirecao == Direcao.ACIMA && direcao != Direcao.ABAIXO) {
            direcao = newDirecao;
        }else if (newDirecao == Direcao.ABAIXO && direcao != Direcao.ACIMA) {
            direcao = newDirecao;
        }
    }




}
