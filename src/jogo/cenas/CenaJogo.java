package jogo.cenas;

import jogo.WindowCobrinha;
import jogo.entidades.Cobrinha;
import jogo.entidades.Comida;
import jogo.utils.*;
import motor.ICena;
import motor.entradas.Teclado;
import motor.utils.CarregadorDeImagem;
import motor.utils.Imagem;
import motor.utils.Tempo;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class CenaJogo extends ICena {

    private Teclado teclado;
    private Retangulo borda, campo;

    private Imagem imgFundo;
    private BufferedImage imgComida;

    private Cobrinha cobrinha;
    private Comida comida;

    private int contComida = 0;
    private int contNivel = 1;
    private int maxComida = 2;

    private boolean parado = false;

    private double tempoIniciado = 0;
    private double tempoFinalizado = 0;


    private double tempoParadoIniciado = 0;
    private double tempoParadoFinalizado = 0;
    private double tempoParado = 0;


    public CenaJogo(Teclado teclado) {
        this.teclado = teclado;

        imgFundo = new Imagem(CarregadorDeImagem.lerImagem("assets/pl_chao.png"), new Retangulo(Constantes.QUADRADINHO*2,Constantes.QUADRADINHO*4,Constantes.QUADRADINHO*29,Constantes.QUADRADINHO*19));

        borda = new Retangulo(0, 0, Constantes.LARGURA_JANELA, Constantes.ALTURA_JANELA);
        campo = new Retangulo(Constantes.QUADRADINHO*2,Constantes.QUADRADINHO*4,Constantes.QUADRADINHO*29,Constantes.QUADRADINHO*19);

        cobrinha = new Cobrinha(this,2,Constantes.QUADRADINHO*5,Constantes.QUADRADINHO*10,24,24, campo);
        imgComida = CarregadorDeImagem.lerImagem("assets/icone_estrela.png");

        comida = new Comida(campo,cobrinha,24,24,imgComida);

        comida.spawn();

        tempoIniciado= Tempo.getTempo();

    }
    @Override
    public void atualiza(double dt) {
        if (teclado.isKeyPressed(TecladoControle.SETA_SUBIR)) {
            cobrinha.setDiracao(Direcao.ACIMA);
        }else if (teclado.isKeyPressed(TecladoControle.SETA_DECER)) {
            cobrinha.setDiracao(Direcao.ABAIXO);
        }else if (teclado.isKeyPressed(TecladoControle.SETA_ESQUERDA)) {
            cobrinha.setDiracao(Direcao.ESQUERDA);
        }else if (teclado.isKeyPressed(TecladoControle.SETA_DIREITA)) {
            cobrinha.setDiracao(Direcao.DIREITA);
        }else if (teclado.isKeyPressedOnce(TecladoControle.ESCAPE)) {
            parado=true;
            tempoParadoIniciado = Tempo.getTempo();
            WindowCobrinha.getWindow().mudarEstado(2);
        }

        if(parado){
            tempoParadoFinalizado = Tempo.getTempo();
        }

        if(!parado){
            if((!comida.isSpawned())){
                comida.spawn();
                contComida++;

                if(contComida == maxComida){
                    contNivel++;
                    maxComida += 2;
                }
            }
            comida.atualiza(dt);
            cobrinha.atualiza(dt);
        }
    }

    @Override
    public void desenha(Graphics2D g) {
        borda.pintar(g,Color.BLACK);
        imgFundo.desenha(g);

        //campo.pintar(g, Color.WHITE);

        cobrinha.desenha(g);
        comida.desenha(g);

        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(Color.YELLOW);

        g.drawString("Partida : " + WindowCobrinha.getWindow().getNumeroPartida(),200,60);
        g.drawString("Jogador : " +WindowCobrinha.getWindow().getNomeJogador(),200,81);

        g.drawString("Velocidade : " + String.format("%.3f",cobrinha.getVelocidade()),400,60);
        g.drawString("Tempo : " + String.format("%.2f",getTempoRealizando()), 400,81);

        g.drawString("Nivel : " + contNivel,600,60);
        g.drawString("Pontos : " + contComida + " de " + maxComida, 600,81);



    }


    public int contagemComida(){
        return contComida;
    }

    public void finalizar(){
        tempoFinalizado= Tempo.getTempo();
    }

    public double getTempoPartida(){
        return (tempoFinalizado-tempoIniciado)-tempoParado;
    }
    public double getTempoRealizando(){
        return (Tempo.getTempo()-tempoIniciado)-tempoParado;
    }

    public void voltarDaPausa(){
        parado=false;
        tempoParadoFinalizado = Tempo.getTempo();
        tempoParado+=(tempoParadoFinalizado-tempoParadoIniciado);
    }
}
