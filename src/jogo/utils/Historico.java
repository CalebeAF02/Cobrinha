package jogo.utils;

import motor.arquivo.ArquivoTexto;

import java.util.ArrayList;

public class Historico {

    public static int getIndice(){
        int indice = 0;
        String texto = ArquivoTexto.LER_ARQUIVO("assets/config.txt");

        ArrayList<String> linhas = ArquivoTexto.DIVIDIR_LINHAS(texto);
        if (linhas.size() > 0) {
            indice = Integer.parseInt(linhas.get(0));
            indice += 1;
        }
        return indice;
    }


    public static void salvarHistorico(int indice,String nome,int pontos,double tempo) {

        System.out.println(">> salvar....");

        String novo = "";
        novo += String.valueOf(indice) + "\n";
        novo += "PARTIDA :: JOGADOR :: PONTOS :: TEMPO" + "\n";

        String texto = ArquivoTexto.LER_ARQUIVO("assets/config.txt");
        ArrayList<String> linhas = ArquivoTexto.DIVIDIR_LINHAS(texto);

        int i = 0;
        for (String linha : linhas) {
            if (i > 1) {
                novo += linha + "\n";
            }
            i += 1;
        }

        novo += String.valueOf(indice) + " :: " + nome + " :: "+ String.valueOf(pontos) + " :: " + String.format("%.2f",tempo) + "\n";


        ArquivoTexto.SALVAR_ARQUIVO("assets/config.txt", novo);


    }

}
