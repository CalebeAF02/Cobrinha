package motor.arquivo;

import java.util.Random;

public class Nomeador {

    public static String CRIAR_NOME(){

        String consoantes = "BCDFGHJKLMNPQRSTVWXYZ";
        String vogais = "AEIOU";

        Random aleatorio = new Random();

        String nome = String.valueOf(consoantes.charAt(aleatorio.nextInt(consoantes.length()))) +  String.valueOf(vogais.charAt(aleatorio.nextInt(vogais.length())))+ String.valueOf(consoantes.charAt(aleatorio.nextInt(consoantes.length()))) +  String.valueOf(vogais.charAt(aleatorio.nextInt(vogais.length())));

        return nome;

    }

}
