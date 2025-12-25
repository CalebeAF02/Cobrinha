package Jogo;

public class Time {
    // variavel para pegar o tempo em segundos!
    public static double timeStarted = System.nanoTime();

    public static double getTime() {
        return (System.nanoTime() - timeStarted) * 1E-9;
    }
}
