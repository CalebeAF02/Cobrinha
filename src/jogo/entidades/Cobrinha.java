package jogo.entidades;

import jogo.WindowCobrinha;
import jogo.cenas.CenaJogo;
import jogo.utils.Direcao;
import jogo.utils.Historico;
import jogo.utils.Retangulo;
import motor.utils.Tempo;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Cobrinha {

    private ArrayList<Retangulo> corpo = new ArrayList<>();
    private double larguraCorpo, alturaCorpo;
    private int tamanho;
    private int mudarVelocidadeNoTamanho = 4;
    // Começa se movendo pra direita
    private Direcao direcao = Direcao.DIREITA;
    private Retangulo ultimaPosicao;


    private double tempoDeEspera = 0.3f;
    private double tempo = tempoDeEspera;

    private Retangulo campo;

    private CenaJogo cenaJogo;


    public Cobrinha(CenaJogo eCena, int tamanho, double startX, double startY, double larguraCorpo, double alturaCorpo, Retangulo campo) {
        this.tamanho = tamanho;
        this.larguraCorpo = larguraCorpo;
        this.alturaCorpo = alturaCorpo;
        this.campo = campo;
        this.cenaJogo = eCena;

        int i = 0;
        while (i < tamanho) {
            Retangulo parteDoCorpo = new Retangulo(startX - (i * larguraCorpo), startY, larguraCorpo, alturaCorpo);
            corpo.add(parteDoCorpo);
            i += 1;
        }
    }

    public void atualiza(double dt) {
        if (tempo > 0) {
            tempo -= dt;
            return;
        }

        if (autoColisao()) {
            cenaJogo.finalizar();

            int numeroPartida = WindowCobrinha.getWindow().getNumeroPartida();
            String nomeJogador = WindowCobrinha.getWindow().getNomeJogador();

            Historico.salvarHistorico(numeroPartida,nomeJogador,cenaJogo.contagemComida(), cenaJogo.getTempoPartida());
            WindowCobrinha.getWindow().mudarEstado(4);
        }

        tempo = tempoDeEspera;
        double posX = 0;
        double posY = 0;

        boolean movimentou = false;

        if (direcao == Direcao.DIREITA) {
            posX = corpo.get(0).x + larguraCorpo;
            posY = corpo.get(0).y;
            movimentou = true;
        } else if (direcao == Direcao.ESQUERDA) {
            posX = corpo.get(0).x - larguraCorpo;
            posY = corpo.get(0).y;
            movimentou = true;
        } else if (direcao == Direcao.ACIMA) {
            posX = corpo.get(0).x;
            posY = corpo.get(0).y - larguraCorpo;
            movimentou = true;
        } else if (direcao == Direcao.ABAIXO) {
            posX = corpo.get(0).x;
            posY = corpo.get(0).y + larguraCorpo;
            movimentou = true;
        }

        if (movimentou) {
            int i = 0;
            for (Retangulo parte : corpo) {
                i += 1;
            }

            Retangulo u = corpo.get(corpo.size() - 1);
            ultimaPosicao = new Retangulo(u.x, u.y, u.largura, u.altura);

            int iUltimo = corpo.size() - 1;
            while (iUltimo >= 0) {
                if (iUltimo > 0) {
                    corpo.get(iUltimo).x = corpo.get(iUltimo - 1).x;
                    corpo.get(iUltimo).y = corpo.get(iUltimo - 1).y;
                }
                iUltimo -= 1;
            }

            corpo.get(0).x = posX;
            corpo.get(0).y = posY;

            i = 0;
            for (Retangulo parte : corpo) {
                i += 1;
            }
        }
    }

    public void desenha(Graphics2D g2) {

        int i = 0;
        for (Retangulo parteDoCorpo : corpo) {
            double subLargura = (parteDoCorpo.largura - 6.0) / 2.0;
            double subAltura = (parteDoCorpo.altura - 6.0) / 2.0;

            if (i == 0) {
                g2.setColor(Color.BLACK);
            } else {
                g2.setColor(Color.MAGENTA);
            }

            g2.fill(new Rectangle2D.Double(parteDoCorpo.x + 2.0, parteDoCorpo.y + 2.0, subLargura, subAltura));
            g2.fill(new Rectangle2D.Double(parteDoCorpo.x + 4.0 + subLargura, parteDoCorpo.y + 2.0, subLargura, subAltura));
            g2.fill(new Rectangle2D.Double(parteDoCorpo.x + 2.0, parteDoCorpo.y + 4.0 + subAltura, subLargura, subAltura));
            g2.fill(new Rectangle2D.Double(parteDoCorpo.x + 4.0 + subLargura, parteDoCorpo.y + 4.0 + subAltura, subLargura, subAltura));

            i += 1;
        }
    }

    public void setDiracao(Direcao newDirecao) {
        if (newDirecao == Direcao.DIREITA && direcao != Direcao.ESQUERDA) {
            direcao = newDirecao;
        } else if (newDirecao == Direcao.ESQUERDA && direcao != Direcao.DIREITA) {
            direcao = newDirecao;
        } else if (newDirecao == Direcao.ACIMA && direcao != Direcao.ABAIXO) {
            direcao = newDirecao;
        } else if (newDirecao == Direcao.ABAIXO && direcao != Direcao.ACIMA) {
            direcao = newDirecao;
        }
    }

    public boolean autoColisao() {
        Retangulo cabecaR = corpo.get(0);
        for (Retangulo pedaco1 : corpo) {
            for (Retangulo pedaco2 : corpo) {
                if (pedaco1 != null && pedaco2 != null) {
                    if (pedaco1 != pedaco2) {
                        if (Retangulo.colisao(pedaco1, pedaco2)) {
                            return true;
                        }
                    }
                }
            }
        }

        return colideComLimiteDaTela(cabecaR);
    }

    public boolean colisaoComRetangulo(Retangulo retangulo) {
        if (Retangulo.colisao(corpo.get(0), retangulo)) {
            return true;
        }

        return false;
    }

    public boolean colideComLimiteDaTela(Retangulo cabeca) {
        return (cabeca.x < campo.x || (cabeca.x + cabeca.largura) > campo.x + campo.largura || cabeca.y < campo.y || (cabeca.y + cabeca.altura) > campo.y + campo.altura);
    }

    public void cresce() {
        tamanho += 1;
        corpo.add(ultimaPosicao);

        if (tamanho == mudarVelocidadeNoTamanho) {
            mudarVelocidadeNoTamanho += 2;
            tempoDeEspera -= 0.01;
        }

    }

    public double getVelocidade() {
        return tempoDeEspera;
    }
}
