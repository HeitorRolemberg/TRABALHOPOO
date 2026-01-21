public class Atirador extends Combatente{
    private int precisao;

    public Atirador(String nome){
        super(nome, 250, 0, 3);
        this.precisao = 0;
    }

    @Override
    public void atacar(Combatente oponente){

        if(this.precisao >= 20){
            System.out.println(this.nome + " golpeu com um tiro crítico o " + oponente.getNome());
            this.dano = 70;
            int danoTotal = this.dano - oponente.getDefesa();
            oponente.receberDano(danoTotal);
            this.precisao -= 20;
        }

        else{
            System.out.println(this.nome + " golpeu com um tiro o " + oponente.getNome());
            this.dano = 35;
            int danoTotal = this.dano - oponente.getDefesa();
            oponente.receberDano(danoTotal);
            this.precisao += 5;
        }
    }

    @Override
    public void receberDano(int dano){
        System.out.println(this.nome + " perdeu " + dano + " PV");
        super.receberDano(dano);
    }
}