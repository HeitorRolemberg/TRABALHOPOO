public class Tanque extends Combatente{
    private int vigor;

    public Tanque(String nome){
        super(nome, 400, 45, 5);
        this.vigor = 0;
    }

    public void getVigor(){
        System.out.println(this.nome + " estah com " + this.vigor + " de vigor");
    }

    @Override
    public int getDefesa(){
        
        if(this.vigor >= 55){
            return this.defesa = 9;
        }
        else if(this.vigor >= 30){
            return this.defesa = 7;
        }
        else{
            return this.defesa = 5;
        }
    }

    @Override
    public void atacar(Combatente oponente){
        System.out.println(this.nome + " golpeu com escudo o " + oponente.getNome());
       
        int danoTotal = this.dano - oponente.getDefesa();
        oponente.receberDano(danoTotal);
    }

    @Override
    public void receberDano(int dano){

        if(this.vigor >= 75){
            System.out.println(this.nome + " BLOQUEOU O ATAQUE!");
            this.vigor -= 75;
        }
        else{
            System.out.println(this.nome + " perdeu " + dano + " PV");
            super.receberDano(dano);
            this.vigor += 10;
        }
    }
}