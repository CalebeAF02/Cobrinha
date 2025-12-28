package motor;

public class MotorJogo {

    public static void CRIAR_JOGO( Window window ){
        //Cria uma tela na qual fica desenhando ao infinito e repassando para a janela principal(window)!
        Thread thread = new Thread(window);
        //Inicia a Thread!
        thread.start();
    }

}
