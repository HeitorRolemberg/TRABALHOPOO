public class Mago extends Combatente{
    private int mana;

    public Mago(String nome){
        super(nome, 300);
        this.mana = 0;
    }

    public int getMana(){
        return mana;
    }

    @Override
    public void atacar(Combatente oponente){
        System.out.println(this.nome + " golpeu com sua magia o " + oponente.getNome());
        if(this.mana >= 45){
            oponente.receberDano(50);
        }
        else{
            oponente.receberDano(25);
            this.mana += 10;
        }
    }

    @Override
    public void receberDano(int dano){
        super.receberDano(dano);
    }
}