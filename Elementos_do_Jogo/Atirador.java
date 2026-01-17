public class Atirador extends Combatente{
    private int velocidade;

    public Atirador(String nome, int vidaInicial, int velocidade){
        super(nome, vidaInicial);
        this.velocidade = velocidade;
    }

    public int getVelocidade(){
        return velocidade;
    }

    public int setVelocidade(int v){
        return this.velocidade = v;
    }
}