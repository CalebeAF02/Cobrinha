package jogo.cenas;

import jogo.WindowCobrinha;
import jogo.utils.Retangulo;
import jogo.utils.TecladoControle;
import motor.ICena;
import motor.entradas.Mouse;
import motor.entradas.Teclado;
import motor.fonte.Fonte;
import motor.utils.Botao;
import motor.utils.CarregadorDeImagem;
import motor.utils.ColorirImagem;
import motor.utils.Imagem;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CenaMenu extends ICena {

    private Mouse mouse;
    private Teclado teclado;
    private Botao botaoPlay, botaoExit;

    //Cria varias variaveis de imagens
    private Imagem titulo;
    private Imagem fundo;
    private Imagem cobrinha;
    private Fonte fonteTitulo;
    private Fonte fonteNormal;

    public CenaMenu(Teclado teclado, Mouse mouse) {
        this.teclado = teclado;
        this.mouse = mouse;
        this.fonteTitulo = new Fonte(85,85, 85, 85);
        this.fonteNormal = new Fonte(15,15,30,30);

        //Verifiqca Se imagem foi encontrada e não está corrompida
        fundo = new Imagem(CarregadorDeImagem.lerImagem("assets/pl_chao_brita_calcada.png"), new Retangulo(0, 0, 800, 600));
        cobrinha = new Imagem(CarregadorDeImagem.lerImagem("assets/icone_cobrinha.png"), new Retangulo(350, 300, 100, 100));
        BufferedImage sprites = CarregadorDeImagem.lerImagem("assets/menuSprite.png");
        //Recorta pedaços da imagem dentro de sprites!
        titulo = new Imagem(sprites.getSubimage(0, 242, 960, 240),new Retangulo(100, 100, 600, 250));

        //botaoPlay = new Botao(sprites.getSubimage(265, 121, 261, 121), sprites.getSubimage(0, 121, 261, 121), new Retangulo(200, 400, 100, 100));
        botaoPlay = new Botao(sprites.getSubimage(265, 121, 261, 121), sprites.getSubimage(0, 121, 261, 121), new Retangulo(200, 400, 100, 100));

        botaoExit = new Botao(sprites.getSubimage(265, 0, 253, 93), sprites.getSubimage(0, 0, 253, 93), new Retangulo(500, 400, 100, 80));
    }

    @Override
    public void atualiza(double dt) {

        if (teclado.isKeyPressedOnce(TecladoControle.ESCAPE)) {
            WindowCobrinha.getWindow().close();
        }

        if(botaoPlay.isEncima(mouse)){
            if(botaoPlay.isClicado(mouse)){
                WindowCobrinha.getWindow().mudarEstado(1);
            }
        }
        if(botaoExit.isEncima(mouse)){
            if(botaoExit.isClicado(mouse)){
                WindowCobrinha.getWindow().close();
            }
        }
    }

    @Override
    public void desenha(Graphics2D g) {
        fundo.desenha(g);
        cobrinha.desenha(g);
        //titulo.desenha(g);

        //ColorirImagem.recolorir(botaoPlay.getNormal(), Color.yellow);
        //ColorirImagem.recolorir(botaoExit.getNormal(), Color.BLUE);

        botaoPlay.desenha(g);
        botaoExit.desenha(g);
        ColorirImagem.recolorir(fonteTitulo, Color.GREEN);
        fonteTitulo.escreva(g,70,100, "Cobrinha");

        ColorirImagem.recolorir(fonteNormal, Color.WHITE);
        fonteNormal.escrevaCasoSensitivo(g,200,250, "Vamos brincar de pegar comida!");

    }

}
