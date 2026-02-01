package Elementos_do_Jogo;

public class Atirador extends Combatente {
    private int precisao;

    public Atirador(String nome){
        super(nome, 250, 0, 3);
        this.precisao = 0;
    }

    @Override
    public String atacar(Combatente oponente){
        if(this.precisao >= 20){
            this.precisao = 0;
            int danoCritico = 70;
            int danoReal = danoCritico - oponente.getDefesa();
            if(danoReal < 0) danoReal = 0;

            String log = oponente.receberDano(danoReal);
            return this.nome + " HEADSHOT CRÍTICO! " + log;
        }
        else{
            this.precisao += 10;
            int danoNormal = 35;
            int danoReal = danoNormal - oponente.getDefesa();
            if(danoReal < 0) danoReal = 0;

            String log = oponente.receberDano(danoReal);
            return this.nome + " atirou uma flecha. " + log;
        }
    }
}
