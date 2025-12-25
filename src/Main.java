import motor.Window;

public class Main {
    public static void main(String[] args) {
        Window window = Window.getWindow();
        //Cria uma tela na qual fica desenhando ao infinito e repassadno para a janela principal(window)!
        Thread thread = new Thread(window);
        //Inicia a Thread!
        thread.start();
    }
}