package Elementos_do_Jogo;

public class Atirador extends Combatente {

    public Atirador(String nome) {
        super(nome, 90, 20, 2);
    }

    @Override
    public int atacar(Combatente inimigo) {
        inimigo.receberDano(dano);
        return dano;
    }

    @Override
    public String getSprite() {
        return "atirador.png";
    }
}
