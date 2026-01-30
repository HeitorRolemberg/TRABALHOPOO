package Elementos_do_Jogo;

public class Tanque extends Combatente {

    public Tanque(String nome) {
        super(nome, 150, 15, 8);
    }

    @Override
    public int atacar(Combatente inimigo) {
        inimigo.receberDano(dano);
        return dano;
    }

    @Override
    public String getSprite() {
        return "tanque.png";
    }
}
