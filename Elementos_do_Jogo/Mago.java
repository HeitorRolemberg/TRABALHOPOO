public class Mago extends Combatente{
    private int mana;

    public Mago(String nome){
        super(nome, 300, 0, 5);
        this.mana = 0;
    }

    public int getMana(){
        return mana;
    }

    @Override
    public void atacar(Combatente oponente){
        System.out.println(this.nome + " golpeu com sua magia o " + oponente.getNome());
        
        if(this.mana >= 45){
            this.dano = 50;
            int danoTotal = this.dano - oponente.getDefesa(); 
            oponente.receberDano(danoTotal);
            this.mana -= 10;
        }
        
        else{
            this.dano = 25;
            int danoTotal = this.dano - oponente.getDefesa(); 
            oponente.receberDano(danoTotal);
            this.mana += 8;
        }
    }

    @Override
    public void receberDano(int dano){
        super.receberDano(dano);
    }
}