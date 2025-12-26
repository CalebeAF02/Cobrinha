package jogo.utils;

public class Retangulo {
    public double x;
    public double y;
    public double largura;
    public double altura;

    public Retangulo(double x, double y, double width, double height) {
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
