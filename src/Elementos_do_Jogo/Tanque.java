package Elementos_do_Jogo;

public class Tanque extends Combatente {
    private int vigor;

    public Tanque(String nome){
        super(nome, 400, 45, 5);
        this.vigor = 100;
    }

    @Override
    public int getDefesa(){
        if(this.vigor >= 55) return 9;
        else if(this.vigor >= 30) return 7;
        else return 5;
    }

    @Override
    public String atacar(Combatente oponente){
        int danoCausado = this.dano - oponente.getDefesa();
        if(danoCausado < 0) danoCausado = 0;
        
        String logDano = oponente.receberDano(danoCausado);
        return this.nome + " (Escudo) atacou! " + logDano;
    }

    @Override
    public String receberDano(int dano){
        if(this.vigor >= 75){
            this.vigor -= 75;
            return this.nome + " USOU VIGOR E BLOQUEOU TUDO!";
        }
        else{
            this.vigor += 15;
            return super.receberDano(dano);
        }
    }
}
