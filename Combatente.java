import java.util.Random; 

public class Combatente {
    // --- ATRIBUTOS ---
    private String nome;
    private int vida;
    private int experiencia;

    // --- CONSTRUTOR ---
    public Combatente(String nome, int vidaInicial) {
        this.nome = nome;
        this.vida = vidaInicial;
        this.experiencia = 1; 
    }


    // 1. Atacar Básico Generico
    public int atacar() {
       
        return 10; 
    }

    // 2.Receber Dano
    public void receberDano(int danoRecebido) {
        this.vida -= danoRecebido;

        //A vida não pode ser menor que zero
        if (this.vida < 0) {
            this.vida = 0;
        }

        System.out.println(this.nome + " recebeu " + danoRecebido + " de dano. Vida restante: " + this.vida);
        
        if (this.vida == 0) {
            System.out.println(this.nome + " caiu em combate!");
        }
    }

    // --- GETTERS ---
    public String getNome() { return nome; }
    public int getVida() { return vida; }
    public int getExperiencia() { return experiencia; }
    
    // --- TESTE---
    public static void main(String[] args) {
        // Criando dois lutadores para testar
        Combatente c1 = new Combatente("Lutador A", 100);
        Combatente c2 = new Combatente("Lutador B", 100);

        // Simulando uma rodada simples
        int danoDoAtaque = c1.atacar(); // C1 ataca
        c2.receberDano(danoDoAtaque);   // C2 recebe o dano
    }
}
