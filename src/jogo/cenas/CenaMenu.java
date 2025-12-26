package jogo.cenas;

import jogo.utils.Retangulo;
import motor.ICena;
import motor.entradas.Mouse;
import motor.entradas.Teclado;
import motor.Window;
import motor.utils.Botao;
import motor.utils.Imagem;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class CenaMenu extends ICena {

    public Teclado teclado;
    public Mouse mouse;

    //Cria varias variaveis de imagens
    public BufferedImage fundo;
    public BufferedImage titulo;

    public Botao botaoPlay, botaoExit;
    private Retangulo retanguloTitulo;


    public CenaMenu(Teclado teclado, Mouse mouse) {
        this.teclado = teclado;
        this.mouse = mouse;

        //Verifiqca Se imagem foi encontrada e não está corrompida
        fundo = Imagem.lerImagem("assets/fundo_menu.png");




        BufferedImage sprites = Imagem.lerImagem("assets/menuSprite.png");
        //Recorta pedaços da imagem dentro de sprites!
        titulo = sprites.getSubimage(0, 242, 960, 240);

        botaoPlay = new Botao(sprites.getSubimage(265, 121, 261, 121), sprites.getSubimage(0, 121, 261, 121), new Retangulo(200, 400, 100, 100));
        botaoExit = new Botao(sprites.getSubimage(265, 0, 253, 93), sprites.getSubimage(0, 0, 253, 93), new Retangulo(500, 400, 100, 80));

        retanguloTitulo = new Retangulo(100, 100, 600, 250);

    }

    @Override
    public void atualiza(double dt) {
        if(botaoPlay.acao(mouse)){
            motor.Window.getWindow().mudarEstado(1);
        }
        if(botaoExit.acao(mouse)){
            Window.getWindow().close();
        }
    }

    @Override
    public void desenha(Graphics g) {
        g.drawImage(fundo, 0, 0, 800, 600, null);
        //g.fillRect(0,0,Constantes.LARGURA_JANELA, Constantes.ALTURA_JANELA);

        botaoPlay.desenha(g);
        botaoExit.desenha(g);
        g.drawImage(titulo, (int)retanguloTitulo.x, (int)retanguloTitulo.y, (int)retanguloTitulo.largura, (int)retanguloTitulo.altura, null);
    }
}
