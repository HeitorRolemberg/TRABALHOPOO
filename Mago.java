package Elementos_do_Jogo;

public class Mago extends Combatente {

    public Mago(String nome) {
        super(nome, 100, 25, 3);
    }

    @Override
    public int atacar(Combatente inimigo) {
        inimigo.receberDano(dano);
        return dano;
    }

    @Override
    public String getSprite() {
        return "mago.png";
    }
}
