public class Combatente {
    private String nome;
    private int experiencia;
    private int vida;

    public Combatente(String nome, int vidaInicial) {
        this.nome = nome;
        this.vida = vidaInicial;
        this.experiencia = 0; 
    }

    public String getNome() {
        return nome;
    }

    public int getVida() {
        return vida;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }
}
