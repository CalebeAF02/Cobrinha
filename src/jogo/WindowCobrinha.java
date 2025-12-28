package jogo;

import jogo.cenas.CenaJogo;
import jogo.cenas.CenaMenu;
import jogo.cenas.CenaPause;
import jogo.utils.Constantes;
import jogo.utils.Historico;
import motor.Window;
import motor.arquivo.Nomeador;
import motor.utils.CarregadorDeImagem;

public class WindowCobrinha extends Window {

    //Cria um singleton para que exista somente um ajanela!
    private static WindowCobrinha window = null;
    private CenaJogo jogoAtual;

    private int numeroPartida = 1;
    private String nomeJogador = "";


    //Este é o Singleton da variavel window!
    public static WindowCobrinha getWindow() {
        if (WindowCobrinha.window == null) {
            WindowCobrinha.window = new WindowCobrinha(Constantes.LARGURA_JANELA, Constantes.ALTURA_JANELA, Constantes.TITULO_JANELA);
        }
        return WindowCobrinha.window;
    }

    public WindowCobrinha(int largura, int altura, String tituloJanela) {
        super(largura, altura, tituloJanela);
        this.setIconImage(CarregadorDeImagem.lerImagem("assets/icone_cobrinha.png"));
        jogoAtual = new CenaJogo(teclado);


        novoJogador();

    }

    @Override
    public void mudarEstado(int newEstado) {
        estadoCorrente = newEstado;
        switch (estadoCorrente) {
            case 0:
                iCenaCorrente = new CenaMenu(teclado, mouse);
                break;
            case 1:
                iCenaCorrente = jogoAtual;
                jogoAtual.voltarDaPausa();
                break;
            case 2:
                iCenaCorrente = new CenaPause(teclado, mouse);
                break;
            case 4:
                jogoAtual = new CenaJogo(teclado);
                iCenaCorrente = new CenaMenu(teclado, mouse);
                novoJogador();
                break;
            default:
                System.out.println("Cena nao encontrada!");
                iCenaCorrente = null;
                break;
        }

    }

    public void novoJogador() {

        nomeJogador = Nomeador.CRIAR_NOME();
        numeroPartida = Historico.getIndice();

    }


    public String getNomeJogador() {
        return nomeJogador;
    }
    public int getNumeroPartida() {
        return numeroPartida;
    }
}
