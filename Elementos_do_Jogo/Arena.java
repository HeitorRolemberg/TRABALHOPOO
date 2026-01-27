package Elementos_do_Jogo;

public class Arena {

    private Combatente[] ladoA;
    private Combatente[] ladoB;
    private int qtdA;
    private int qtdB;
    private int rodada;

    public Arena(int maxA, int maxB) {
        ladoA = new Combatente[maxA];
        ladoB = new Combatente[maxB];
        qtdA = 0;
        qtdB = 0;
        rodada = 1;
    }

    public boolean adicionarLadoA(Combatente c) {
        if (qtdA >= ladoA.length) return false;
        ladoA[qtdA++] = c;
        return true;
    }

    public boolean adicionarLadoB(Combatente c) {
        if (qtdB >= ladoB.length) return false;
        ladoB[qtdB++] = c;
        return true;
    }

    public boolean acabou() {
        boolean todosAMortos = true;
        boolean todosBMortos = true;

        for (int i = 0; i < qtdA; i++) {
            if (ladoA[i].estaVivo()) {
                todosAMortos = false;
            }
        }

        for (int i = 0; i < qtdB; i++) {
            if (ladoB[i].estaVivo()) {
                todosBMortos = false;
            }
        }

        return todosAMortos || todosBMortos;
    }

    public void executarRodada() {
        System.out.println("\n--- RODADA " + rodada + " ---");

        for (int i = 0; i < qtdA; i++) {
            atacar(ladoA[i], ladoB, qtdB);
        }

        for (int i = 0; i < qtdB; i++) {
            atacar(ladoB[i], ladoA, qtdA);
        }

        rodada++;
    }

    private void atacar(Combatente atacante, Combatente[] inimigos, int qtd) {
        if (!atacante.estaVivo()) return;

        for (int i = 0; i < qtd; i++) {
            if (inimigos[i].estaVivo()) {
                atacante.atacar(inimigos[i]);
                break;
            }
        }
    }

    
    public int getQtdA() {
        return qtdA;
    }

    public int getQtdB() {
        return qtdB;
    }

    public Combatente[] getLadoA() {
        return ladoA;
    }

    public Combatente[] getLadoB() {
        return ladoB;
    }

    public int getRodada() {
        return rodada;
    }
}
