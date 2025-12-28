package motor.arquivo;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

public class ArquivoTexto {

    public static String LER_ARQUIVO(String arquivoLocal) {
        String texto = "";
        Scanner in = null;
        try {
            in = new Scanner(new FileReader(arquivoLocal));
            while (in.hasNextLine()) {
                String linha = in.nextLine();
                texto += linha + "\n";
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return texto;
    }


    public static void SALVAR_ARQUIVO(String arquivoLocal,String conteudo) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoLocal))) {
            writer.write(conteudo);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static ArrayList<String> DIVIDIR_LINHAS(String texto){

        int i = 0;
        int o = texto.length();

        String linha = "";
        ArrayList<String> linhas = new ArrayList<>();

        while(i<o){
            String letra = String.valueOf(texto.charAt(i));
            if(letra.contentEquals("\n")){
                linhas.add(linha);
                linha="";
            }else{
                linha+=letra;
            }
            i+=1;
        }

        if(linha.length()>0){
            linhas.add(linha);
        }

        return linhas;
    }

}
