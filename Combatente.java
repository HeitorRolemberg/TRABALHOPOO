package Elementos_do_Jogo;

import java.awt.Color;

public abstract class Combatente {

    protected String nome;
    protected int nivel; // ✅ Nível de Experiência
    protected int vida;
    protected int vidaMaxima;
    protected int dano;
    protected int defesa;

    public Combatente(String nome, int vida, int dano, int defesa) {
        this(nome, 1, vida, dano, defesa); // ✅ nível padrão = 1
    }

    // ✅ construtor com nível (se você quiser usar depois)
    public Combatente(String nome, int nivel, int vida, int dano, int defesa) {
        this.nome = nome;
        this.nivel = nivel;
        this.vida = vida;
        this.vidaMaxima = vida;
        this.dano = dano;
        this.defesa = defesa;
    }

    public abstract String getSprite();

    public boolean estaVivo() {
        return vida > 0;
    }

    public void receberDano(int danoRecebido) {
        int danoFinal = danoRecebido - defesa;
        if (danoFinal < 0) danoFinal = 0;

        vida -= danoFinal;
        if (vida < 0) vida = 0;
    }

    public int atacar(Combatente inimigo) {
        inimigo.receberDano(dano);
        return dano;
    }

    public String getNome() {
        return nome;
    }

    // ✅ nível
    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        if (nivel < 1) nivel = 1;
        this.nivel = nivel;
    }

    public int getVida() {
        return vida;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public int getDefesa() {
        return defesa;
    }

    public Color getCor() {
        if (this instanceof Tanque) return Color.BLUE;
        if (this instanceof Mago) return new Color(150, 0, 200);
        if (this instanceof Atirador) return Color.GREEN;
        return Color.GRAY;
    }
}
