package motor.fonte;

import motor.utils.CarregadorDeImagem;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Fonte {

    private BufferedImage fonte;
    private ArrayList<Letra> letras;

    private int tamanhoPequenoX;
    private int tamanhoPequenoY;
    private int tamanhoGrandeX;
    private int tamanhoGrandeY;
    private String maiusculas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public Fonte(int tamanhoPequenoX, int tamanhoPequenoY, int tamanhoGrandeX, int tamanhoGrandeY) {
        this.tamanhoPequenoX = tamanhoPequenoX;
        this.tamanhoPequenoY = tamanhoPequenoY;
        this.tamanhoGrandeX = tamanhoGrandeX;
        this.tamanhoGrandeY = tamanhoGrandeY;

        fonte = CarregadorDeImagem.lerImagem("assets/fonte_calebe.png");
        letras = new ArrayList<Letra>();

        String linha_1 = "ABCDEFGHIJKLM";
        String linha_2 = "NOPQRSTUVXWYZ";
        String linha_3 = "0123456789=-+";
        String linha_4 = " $$$$$$$$$$?!";
        processarLinha(linha_1, 1,1);
        processarLinha(linha_2,1,29);
        processarLinha(linha_3,1,57);
        processarLinha(linha_4,1,85);
    }

    public void escreva(Graphics2D g, int posX, int posY, String texto){

        int linhaX = posX;

        int i = 0;
        int o= texto.length();
        while (i < o) {
            String letra = String.valueOf(texto.charAt(i));
            for (Letra l : letras) {
                if(l.getLetra().contentEquals(letra.toUpperCase())){
                    g.drawImage(l.getImagem(), linhaX, posY, tamanhoPequenoX, tamanhoPequenoY, null);
                    linhaX += tamanhoPequenoX;
                    break;
                }
            }
            i++;
        }
    }

    public void escrevaCasoSensitivo(Graphics2D g, int posX, int posY, String texto){

        int linhaX = posX;

        int i = 0;
        int o= texto.length();
        while (i < o) {
            String letra = String.valueOf(texto.charAt(i));
            for (Letra l : letras) {
                if(l.getLetra().contentEquals(letra.toUpperCase())){
                    if(maiusculas.contains(letra)){
                        g.drawImage(l.getImagem(), linhaX, posY-tamanhoPequenoY, tamanhoGrandeX, tamanhoGrandeY, null);
                        linhaX += tamanhoGrandeX;

                    }else{
                        g.drawImage(l.getImagem(), linhaX, posY, tamanhoPequenoX, tamanhoPequenoY, null);
                        linhaX += tamanhoPequenoX;
                    }
                    break;
                }
            }
            i++;
        }
    }

    private void processarLinha(String linha, int posX, int posY){
        int i = 0;
        int o= linha.length();

        int largura = 17;
        int altura = 27;

        while (i < o){
            BufferedImage letraAtual;

            String letra = String.valueOf(linha.charAt(i));
            //System.out.println(letra);

            letraAtual = fonte.getSubimage(posX, posY, largura, altura);

            letras.add(new Letra(letraAtual, letra));

            posX += largura;
            posX += 1;

            i++;
        }
    }

    public ArrayList<Letra> getLetras() {
        return letras;
    }

}
