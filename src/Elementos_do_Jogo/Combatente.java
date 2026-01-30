package Elementos_do_Jogo;

import java.awt.Color;

public abstract class Combatente {
    protected String nome;
    protected int vida;
    protected int vidaMaxima; // Usado para calcular o tamanho da barra verde
    protected int dano;
    protected int defesa;
    protected Color cor; // Cor do personagem (Azul, Roxo, Verde)
    
    public Combatente(String nome, int vida, int dano, int defesa) {
        this.nome = nome;
        this.vida = vida;
        this.vidaMaxima = vida;
        this.dano = dano;
        this.defesa = defesa;
    }

    public abstract String atacar(Combatente oponente);

    public String receberDano(int danoTotal){
        if(this.vida > danoTotal){
            this.vida -= danoTotal;
            return this.nome + " recebeu " + danoTotal + " de dano.";
        } else {
            this.vida = 0;
            return this.nome + " CAIU EM BATALHA!";
        }
    }

    public boolean estaVivo() { return this.vida > 0; }
    public int getDefesa(){ return defesa; }
    public String getNome() { return nome; }
    public int getVida() { return vida; }
    public int getVidaMaxima() { return vidaMaxima; }
    public Color getCor() { return cor; }
}
