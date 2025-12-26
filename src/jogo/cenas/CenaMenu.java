package jogo.cenas;

import jogo.utils.Retangulo;
import motor.ICena;
import motor.entradas.Mouse;
import motor.entradas.Teclado;
import motor.Window;
import motor.utils.Botao;
import motor.utils.CarregadorDeImagem;
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

    public CenaMenu(Teclado teclado, Mouse mouse) {
        this.teclado = teclado;
        this.mouse = mouse;

        //Verifiqca Se imagem foi encontrada e não está corrompida
        fundo = new Imagem(CarregadorDeImagem.lerImagem("assets/fundo_menu.png"), new Retangulo(0, 0, 800, 600));

        BufferedImage sprites = CarregadorDeImagem.lerImagem("assets/menuSprite.png");
        //Recorta pedaços da imagem dentro de sprites!
        titulo = new Imagem(sprites.getSubimage(0, 242, 960, 240),new Retangulo(100, 100, 600, 250));

        botaoPlay = new Botao(sprites.getSubimage(265, 121, 261, 121), sprites.getSubimage(0, 121, 261, 121), new Retangulo(200, 400, 100, 100));
        botaoExit = new Botao(sprites.getSubimage(265, 0, 253, 93), sprites.getSubimage(0, 0, 253, 93), new Retangulo(500, 400, 100, 80));
    }

    @Override
    public void atualiza(double dt) {
        if(botaoPlay.isEncima(mouse)){
            if(botaoPlay.isClicado(mouse)){
                motor.Window.getWindow().mudarEstado(1);
            }
        }
        if(botaoExit.isEncima(mouse)){
            if(botaoExit.isClicado(mouse)){
                Window.getWindow().close();
            }
        }
    }

    @Override
    public void desenha(Graphics g) {
        fundo.desenha(g);
        titulo.desenha(g);
        botaoPlay.desenha(g);
        botaoExit.desenha(g);
    }
}
