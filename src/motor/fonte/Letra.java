package motor.fonte;

import java.awt.image.BufferedImage;

public class Letra {
    private BufferedImage imagem;
    private String letra;

    public Letra(BufferedImage imagem, String letra) {
        this.imagem = imagem;
        this.letra = letra;
    }

    public BufferedImage getImagem() {
        return imagem;
    }

    public String getLetra() {
        return letra;
    }
}
