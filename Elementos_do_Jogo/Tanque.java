public class Tanque extends Combatente{
    private int vigor;

    public Tanque(String nome){
        super(nome, 400);
        this.vigor = 0;
    }

    public int getVigor(){
        return vigor;
    }
    
    @Override
    public void atacar(Combatente oponente){
        System.out.println(this.nome + " golpeu com escudo o " + oponente.getNome());
        if(this.vigor >= 50){
            oponente.receberDano(45);
        }
        else{
            oponente.receberDano(35);
        }
    }

    @Override
    public void receberDano(int dano){
        if(this.vigor >= 75){
            System.out.println(this.nome + " BLOQUEOU O ATAQUE!");
            this.vigor -= 75;
        }
        else{
            super.receberDano(dano);
            this.vigor += 15;
        }
    }
}