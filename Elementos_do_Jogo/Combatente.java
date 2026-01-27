package Elementos_do_Jogo;

public abstract class Combatente {
    protected String nome;
    private int vida;
    protected int dano;
    protected int defesa;
    
    public Combatente(String nome, int vida, int dano, int defesa) {
        this.nome = nome;
        this.vida = vida;
        this.dano = dano;
        this.defesa = defesa;
    }

    public abstract void atacar(Combatente oponente);

    public void receberDano(int danoTotal){
        if(this.vida > danoTotal){
            this.vida -= danoTotal;
        } else {
            this.vida = 0;
        }
    }

    public boolean estaVivo() {
    return this.vida > 0;
}

    public int getDefesa(){ return defesa; }
    public String getNome() { return nome; }
    public int getVida() { return vida; }
}
