package jogo.cenas;

import jogo.WindowCobrinha;
import jogo.utils.Retangulo;
import jogo.utils.TecladoControle;
import motor.ICena;
import motor.entradas.Mouse;
import motor.entradas.Teclado;
import motor.utils.Botao;
import motor.utils.CarregadorDeImagem;
import motor.utils.Imagem;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CenaPause extends ICena {

    private Mouse mouse;
    private Teclado teclado;
    private Imagem imgFundo;

    private Imagem imgTextoPause;
    private Botao botaoVoltarJogo, botaoSair;

    public CenaPause(Teclado teclado, Mouse mouse) {
        this.teclado = teclado;
        this.mouse = mouse;

        imgFundo = new Imagem(CarregadorDeImagem.lerImagem("assets/fundo_pause.png"), new Retangulo(0, 0, 800, 600));

        BufferedImage sprites = CarregadorDeImagem.lerImagem("assets/texto_pause.png");
        //Recorta pedaços da imagem dentro de sprites!
        imgTextoPause = new Imagem(sprites.getSubimage(182, 55, 616-182, 169-55),new Retangulo(100, 100, 600, 150));

        botaoVoltarJogo = new Botao(sprites.getSubimage(229, 236, 576-229, 290-236), sprites.getSubimage(229, 236, 576-229, 290-236), new Retangulo(250, 300, 300, 100));
        botaoSair = new Botao(sprites.getSubimage(354, 344, 452-354, 385-344), sprites.getSubimage(354, 344, 452-354, 385-344), new Retangulo(350, 400, 100, 100));
    }
    @Override
    public void atualiza(double dt) {


        if(botaoVoltarJogo.isEncima(mouse)){
            if(botaoVoltarJogo.isClicado(mouse)){
                WindowCobrinha.getWindow().mudarEstado(1);
            }
        }
        if(botaoSair.isEncima(mouse)){
            if(botaoSair.isClicado(mouse)){
                WindowCobrinha.getWindow().close();
            }
        }
    }

    @Override
    public void desenha(Graphics2D g) {

        imgFundo.desenha(g);

        imgTextoPause.desenha(g);

        botaoVoltarJogo.desenha(g);
        botaoSair.desenha(g);

    }
}
