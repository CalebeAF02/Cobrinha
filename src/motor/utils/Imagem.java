package motor.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Imagem {

    public static BufferedImage lerImagem(String localArquivo){
        try {
            return  ImageIO.read(new File(localArquivo));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
