package motor.utils;

public class Tempo {
    // variavel para pegar o tempo em segundos!
    public static double tempoIniciado = System.nanoTime();

    public static double getTempo() {
        return (System.nanoTime() - tempoIniciado) * 1E-9;
    }
}
