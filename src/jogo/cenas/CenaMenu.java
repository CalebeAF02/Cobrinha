package jogo.cenas;

import jogo.utils.Retangulo;
import motor.ICena;
import motor.entradas.Mouse;
import motor.entradas.Teclado;
import motor.Window;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class CenaMenu extends ICena {

    public Teclado teclado;
    public Mouse mouse;

    //Cria varias variaveis de imagens
    public BufferedImage fundo;
    public BufferedImage titulo, playPressionado, play, exitPressionado, exit, playCorrente, exitCorrente;

    private Retangulo retanguloTitulo, retanguloPlay, retanguloExit;


    public CenaMenu(Teclado teclado, Mouse mouse) {
        this.teclado = teclado;
        this.mouse = mouse;

        //Verifiqca Se imagem foi encontrada e não está corrompida
        try {
            fundo = ImageIO.read(new File("assets/fundo_menu.png"));
            BufferedImage sprites = ImageIO.read(new File("assets/menuSprite.png"));

            //Recorta pedaços da imagem dentro de sprites!
            titulo = sprites.getSubimage(0, 242, 960, 240);
            playPressionado = sprites.getSubimage(0, 121, 261, 121);
            play = sprites.getSubimage(265, 121, 261, 121);
            exitPressionado = sprites.getSubimage(0, 0, 253, 93);
            exit = sprites.getSubimage(265, 0, 253, 93);

        } catch (Exception erro) {
            erro.printStackTrace();
        }

        playCorrente = play;
        exitCorrente = exit;

        retanguloTitulo = new Retangulo(100, 100, 600, 250);
        retanguloPlay = new Retangulo(200, 400, 100, 100);
        retanguloExit = new Retangulo(500, 400, 100, 80);

    }

    @Override
    public void atualiza(double dt) {
        if (retanguloPlay.isDentro((int)mouse.getX(), (int)mouse.getY())) {
            playCorrente = playPressionado;
            if(mouse.isPressed()){
                motor.Window.getWindow().mudarEstado(1);
            }
        }else{
            playCorrente = play;
        }
        if (retanguloExit.isDentro((int)mouse.getX(), (int)mouse.getY())) {
            exitCorrente = exitPressionado;
            if(mouse.isPressed()){
                Window.getWindow().close();
            }
        }else{
            exitCorrente = exit;
        }
    }

    @Override
    public void desenha(Graphics g) {
        g.drawImage(fundo, 0, 0, 800, 600, null);
        //g.fillRect(0,0,Constantes.LARGURA_JANELA, Constantes.ALTURA_JANELA);

        g.drawImage(titulo, (int)retanguloTitulo.x, (int)retanguloTitulo.y, (int)retanguloTitulo.largura, (int)retanguloTitulo.altura, null);
        g.drawImage(playCorrente, (int)retanguloPlay.x, (int)retanguloPlay.y, (int)retanguloPlay.largura, (int)retanguloPlay.altura, null);
        g.drawImage(exitCorrente, (int)retanguloExit.x, (int)retanguloExit.y, (int)retanguloExit.largura, (int)retanguloExit.altura, null);

    }
}
