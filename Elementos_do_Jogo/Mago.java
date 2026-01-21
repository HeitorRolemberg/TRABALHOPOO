public class Mago extends Combatente{
    private int mana;

    public Mago(String nome){
        super(nome, 300, 0, 5);
        this.mana = 75;
    }

    public int getMana(){
        return mana;
    }

    @Override
    public void atacar(Combatente oponente){
        
        if(this.mana >= 45){
            System.out.println(this.nome + " golpeu com sua magia o " + oponente.getNome());
            this.dano = 50;
            int danoTotal = this.dano - oponente.getDefesa(); 
            oponente.receberDano(danoTotal);
            this.mana -= 5;
        }
        
        else{
            System.out.println(this.nome + " golpeu fracamente o " + oponente.getNome());
            this.dano = 25;
            int danoTotal = this.dano - oponente.getDefesa(); 
            oponente.receberDano(danoTotal);
            this.mana += 10;
        }
    }

    @Override
    public void receberDano(int dano){
        System.out.println(this.nome + " perdeu " + dano + " PV");
        super.receberDano(dano);
    }
}