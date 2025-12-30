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

public class CenaMenu extends ICena {

    private Mouse mouse;
    private Teclado teclado;
    private Botao botaoJogar, botaoSair;

    private Fonte fonteTitulo, fonteNormal;
    private Fonte fonteBotaoJogar, fonteBotaoSair;

    private Imagem imgPlanoDeFundo;
    private Imagem cobrinha;

    public CenaMenu(Teclado teclado, Mouse mouse) {
        this.teclado = teclado;
        this.mouse = mouse;
        this.fonteTitulo = new Fonte(70,70, 70, 70);
        this.fonteNormal = new Fonte(15,15,30,30);
        this.fonteBotaoJogar = new Fonte(35,35,45,45);
        this.fonteBotaoSair = new Fonte(35,35,45,45);

        //Verifiqca Se imagem foi encontrada e não está corrompida
        imgPlanoDeFundo = new Imagem(CarregadorDeImagem.lerImagem("assets/pl_chao_brita_calcada.png"), new Retangulo(0, 0, 800, 600));
        cobrinha = new Imagem(CarregadorDeImagem.lerImagem("assets/icone_cobrinha.png"), new Retangulo(360, 330, 100, 100));

        ColorirImagem.recolorir(fonteNormal, Color.WHITE);
        ColorirImagem.recolorir(fonteTitulo, Color.GREEN);
        ColorirImagem.recolorir(fonteNormal, Color.WHITE);
        ColorirImagem.recolorir(fonteNormal, Color.WHITE);
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

        fonteTitulo.escreva(g,110,130, "Cobrinha");

        fonteNormal.escrevaCasoSensitivo(g,170,270, "Vamos brincar de pegar comida!");

        cobrinha.desenha(g);

        fonteBotaoJogar.escreva(g,130,430, "Jogar");

        fonteBotaoSair.escreva(g,500,430, "Sair");
    }

}
