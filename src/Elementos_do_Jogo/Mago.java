package Elementos_do_Jogo;

import java.awt.Color;

public class Mago extends Combatente {
    private int mana;

    public Mago(String nome){
        super(nome, 300, 0, 2);
        this.mana = 75;
        this.cor = new Color(128, 0, 128); // Magos são ROXOS (RGB)
    }

    @Override
    public String atacar(Combatente oponente){
        if(this.mana >= 45){
            this.mana -= 45;
            int danoMagico = 60;
            int danoReal = danoMagico - oponente.getDefesa();
            if(danoReal < 0) danoReal = 0;
            
            String log = oponente.receberDano(danoReal);
            return this.nome + " lançou MAGIA! " + log;
        }
        else{
            this.mana += 20;
            int danoFraco = 15;
            int danoReal = danoFraco - oponente.getDefesa();
            if(danoReal < 0) danoReal = 0;
            
            String log = oponente.receberDano(danoReal);
            return this.nome + " (sem mana) bateu com cajado. " + log;
        }
    }
}
