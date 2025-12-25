package Jogo;

public class Retangulo {
    public int x;
    public int y;
    public int largura;
    public int altura;

    public Retangulo(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.largura = width;
        this.altura = height;
    }

    public boolean isDentro(int posX, int posY){
        if (posX >= x && posX <= x + largura &&
                posY >= y && posY <= y + altura) {
            return true;
        }else{
            return false;
        }
    }
}
