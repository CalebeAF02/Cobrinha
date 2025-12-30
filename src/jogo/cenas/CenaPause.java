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

public class CenaPause extends ICena {

    private Mouse mouse;
    private Teclado teclado;

    private Fonte fonteTitulo, fonteNormal;
    private Fonte fonteBotaoJogar, fonteBotaoSair;

    private Imagem imgPlanoDeFundo;

    public CenaPause(Teclado teclado, Mouse mouse) {
        this.teclado = teclado;
        this.mouse = mouse;
        this.fonteTitulo = new Fonte(100,100, 100, 100);
        this.fonteNormal = new Fonte(15,15,30,30);
        this.fonteBotaoJogar = new Fonte(35,35,45,45);
        this.fonteBotaoSair = new Fonte(35,35,45,45);

        imgPlanoDeFundo = new Imagem(CarregadorDeImagem.lerImagem("assets/pl_chao_brita_calcada.png"), new Retangulo(0, 0, 800, 600));

        ColorirImagem.recolorir(fonteTitulo, Color.RED);
    }
    @Override
    public void atualiza(double dt) {

        if (teclado.isKeyPressedOnce(TecladoControle.ESCAPE)) {
            WindowCobrinha.getWindow().close();
        }

        if(fonteBotaoJogar.isEncima(mouse)){
            ColorirImagem.recolorir(fonteBotaoJogar, Color.GREEN);
            if(fonteBotaoJogar.isClicado(mouse)){
                WindowCobrinha.getWindow().mudarEstado(1);
            }
        }else{
            ColorirImagem.recolorir(fonteBotaoJogar, Color.WHITE);
        }
        if(fonteBotaoSair.isEncima(mouse)){
            ColorirImagem.recolorir(fonteBotaoSair, Color.RED);
            if(fonteBotaoSair.isClicado(mouse)){
                WindowCobrinha.getWindow().close();
            }
        }else{
            ColorirImagem.recolorir(fonteBotaoSair, Color.WHITE);
        }
    }

    @Override
    public void desenha(Graphics2D g) {

        imgPlanoDeFundo.desenha(g);

        fonteTitulo.escreva(g,150,150, "Pause");

        fonteBotaoJogar.escreva(g,160,300, "Voltar ao Jogo");

        fonteBotaoSair.escreva(g,310,400, "Sair");

    }
}
