package motor;

import jogo.CenaJogo;
import jogo.CenaMenu;
import jogo.Constantes;

import javax.swing.*;
import java.awt.*;

// Classe Janela esta herdando a janela Java e implementando a execução!
public class Window extends JFrame implements Runnable {

    //Cria um singolton para que exista somente um ajanela!
    private static Window window = null;
    //Este é o Singonton da variavel window!
    public static Window getWindow(){
        if(Window.window == null){
            Window.window = new Window(Constantes.LARGURA_JANELA, Constantes.ALTURA_JANELA, Constantes.TITULO_JANELA);
        }
        return Window.window;
    }


    //Controle de cenas!
    public int estadoCorrente;
    public ICena ICenaCorrente;
    //Instacia o Teclado!
    public Teclado teclado = new Teclado();
    // Instancia o Mouse!
    public Mouse mouse = new Mouse();

    public boolean executando;
    // Construtor!
    public Window(int largura, int altura, String tituloJanela) {
        //Metodos herdados do JFrame para criarem a janela!
        setSize(largura, altura);
        setTitle(tituloJanela);

        //Metodos para impedir redimensionamento de janela!
        setResizable(false);
        //Metodo para deixar a janela visivel!
        setVisible(true);
        //Metodo para fechar a janela apertando no "X" da janela!
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Metodo para alterar a posição da tela!
        setLocation(500, 250);
        //Metodo para escutar o teclado!
        addKeyListener(teclado);
        //Metodo para escutar os botoes do mouse!
        addMouseListener(mouse);
        //Metodo para escutar o movimento do mause!
        addMouseMotionListener(mouse);

        executando = true;
        mudarEstado(0);

    }

    public void close(){
        executando = false;
    }

    public void mudarEstado(int newEstado) {
        estadoCorrente = newEstado;
        switch (estadoCorrente) {
            case 0:
                ICenaCorrente = new CenaMenu(teclado, mouse);
                break;
            case 1:
                ICenaCorrente = new CenaJogo(teclado);
                break;
            default:
                System.out.println("Cena nao encontrada!");
                ICenaCorrente = null;
                break;
        }

    }

    public void atualiza(double dt) {
        // Cria uma imagem do mesmo tamanho da tela vazia!
        Image dbImage = createImage(getWidth(), getHeight());
        // Pega o objeto_desenhador da imagem!
        Graphics dbg = dbImage.getGraphics();
        //Desenha a imagem na tela!
        this.desenha(dbg);
        //Desenha a tela na janela!
        getGraphics().drawImage(dbImage, 0, 0, this);

        ICenaCorrente.atualiza(dt);
    }

    public void desenha(Graphics g) {
        // Transforma o lapis para 2D!
        Graphics2D g2 = (Graphics2D) g;
        // Seleciona a cor do lapis para preto !
        //g2.setColor(Color.BLACK);
        // Desenha um retangulo Preto!
        //g2.fillRect(0, 0, getWidth(), getWidth());

        ICenaCorrente.desenha(g);
    }

    // Sobrescreve o metodo run da interface Runnable!
    @Override
    public void run() {
        double lastframeTime = 0.0;

        //Verifica se não foi lançado nenhum erro!
        try {
            while (executando) {
                // time = tempo atual!
                double time = Time.getTime();
                // deltaTime = a quantidade de tempo dentre dois quadros!
                double deltaTime = time - lastframeTime;
                // lastframeTime = tempo atual!
                lastframeTime = time;

                atualiza(deltaTime);
            }
        } catch (Exception erro) {
            // Se der erro vai imprimir na tela!
            erro.printStackTrace();
        }

        this.dispose();

    }
}
