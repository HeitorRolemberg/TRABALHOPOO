public abstract class Combatente {
    protected String nome;
    private int vida;
    private int experiencia;

    public Combatente(String nome, int vida) {
        this.nome = nome;
        this.vida = vida;
        this.experiencia = 1;
    }

    public abstract void atacar(Combatente oponente);

    public void receberDano(int dano){
        this.vida -= dano;
    }

    public boolean estarVivo(){
        return vida > 0;
    }

    public String getNome() {
        return nome;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getExperiencia(){
        return experiencia;
    }

    public void setExperiencia(int exp){
        this.experiencia = exp;
    }
}
