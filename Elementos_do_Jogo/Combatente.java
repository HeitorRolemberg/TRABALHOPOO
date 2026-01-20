public abstract class Combatente {
    protected String nome;
    private int vida;
    protected int dano;
    protected int defesa;
    private int experiencia;

    public Combatente(String nome, int vida, int dano, int defesa) {
        this.nome = nome;
        this.vida = vida;
        this.dano = dano;
        this.defesa = defesa;
        this.experiencia = 1;
    }

    public abstract void atacar(Combatente oponente);

    public void receberDano(int dano){

        if(this.vida > dano){
            this.vida -= dano;
        }
        else{
            this.vida = 0;
        }
    }

    public boolean estarVivo(){
        return vida > 0;
    }

    public int getDefesa(){
        return defesa;
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
