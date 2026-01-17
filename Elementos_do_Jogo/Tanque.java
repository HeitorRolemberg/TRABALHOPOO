public class Tanque extends Combatente{
    private int vigor;

    public Tanque(String nome, int vidaInicial, int vigor){
        super(nome, vidaInicial);
        this.vigor = vigor;
    }

    public int getVigor(){
        return vigor;
    }

    public int setVigor(int vg){
        return this.vigor = vg;
    }
}