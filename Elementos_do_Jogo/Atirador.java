public class Atirador extends Combatente{
    private int precisao;

    public Atirador(String nome){
        super(nome, 250);
        this.precisao = 0;
    }

    @Override
    public void atacar(Combatente oponente){
        if(this.precisao >= 20){
            System.out.println(this.nome + " golpeu com um tiro crítico o " + oponente.getNome());
            oponente.receberDano(70);
        }
        else{
            System.out.println(this.nome + " golpeu com um tiro o " + oponente.getNome());
            oponente.receberDano(35);
        }
    }

    @Override
    public void receberDano(int dano){
        super.receberDano(dano);
        this.precisao += 5;
    }
}