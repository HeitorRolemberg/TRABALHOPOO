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

    public void adicionarLadoA(Combatente c) {
        ladoA[qtdA] = c;
        qtdA++;
    }

    public void adicionarLadoB(Combatente c) {
        ladoB[qtdB] = c;
        qtdB++;
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
}
