public class Mago extends Combatente{
    private int quantMagia;

    public Mago(String nome, int vidaInicial, int quantMagia){
        super(nome, vidaInicial);
        this.quantMagia = quantMagia;
    }

    public int get_quantMagia(){
        return quantMagia;
    }

    public int set_quantMagia(int magia){
        return this.quantMagia = magia;
    }
}