package motor.utils;

import motor.fonte.Fonte;
import motor.fonte.Letra;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ColorirImagem {
    public BufferedImage antiga;
    public Color nova;

    public ColorirImagem(BufferedImage antiga, Color nova) {
        this.antiga = antiga;
        this.nova = nova;
        recolorir(antiga, nova);
    }

    public static BufferedImage recolorir(BufferedImage img, Color novaCor) {
        if (img == null) return null;

        int largura = img.getWidth();
        int altura = img.getHeight();

        for (int x = 0; x < largura; x++) {
            for (int y = 0; y < altura; y++) {
                int rgb = img.getRGB(x, y);
                Color atual = new Color(rgb, true);

                // Só processamos pixels que não são totalmente transparentes
                if (atual.getAlpha() > 0) {

                    // 1. Calculamos o brilho (luminosidade) do pixel original (0.0 a 1.0)
                    // Isso preserva as sombras da sua fonte e dos botões
                    float[] hsb = Color.RGBtoHSB(atual.getRed(), atual.getGreen(), atual.getBlue(), null);
                    float luminosidade = hsb[2];

                    // 2. Aplicamos a nova cor multiplicada pela luminosidade original
                    // Isso evita que a cor fique "chapada" e intensa demais
                    int r = (int) (novaCor.getRed() * luminosidade);
                    int g = (int) (novaCor.getGreen() * luminosidade);
                    int b = (int) (novaCor.getBlue() * luminosidade);

                    // Garantimos que os valores fiquem entre 0 e 255
                    r = Math.min(255, Math.max(0, r));
                    g = Math.min(255, Math.max(0, g));
                    b = Math.min(255, Math.max(0, b));

                    // Criamos a nova cor mantendo o Alpha (transparência) original
                    Color novo = new Color(r, g, b, atual.getAlpha());
                    img.setRGB(x, y, novo.getRGB());
                }
            }
        }
        return img;
    }

    public static Fonte recolorir(Fonte fonte, Color novaCor) {
        for (Letra l : fonte.getLetras()) {
            recolorir(l.getImagem(), novaCor);
        }
        return fonte;
    }

    public static void recolorir(Botao botao, Color novaCor) {
        if (botao != null) {
            // Pintamos a imagem do estado normal
            if (botao.getNormal() != null) recolorir(botao.getNormal(), novaCor);
            // Pintamos a imagem do estado pressionado
            if (botao.getPressionado() != null) recolorir(botao.getPressionado(), novaCor);
        }
    }
}
