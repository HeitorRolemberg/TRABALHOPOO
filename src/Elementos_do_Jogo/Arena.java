package Elementos_do_Jogo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {

    private List<Combatente> timeA;
    private List<Combatente> timeB;
    private int rodada;
    private Random random;

    public Arena() {
        timeA = new ArrayList<>();
        timeB = new ArrayList<>();
        rodada = 1;
        random = new Random();
    }

    public void adicionarLadoA(Combatente c) {
        timeA.add(c);
    }

    public void adicionarLadoB(Combatente c) {
        timeB.add(c);
    }

    public List<Combatente> getTimeA() {
        return timeA;
    }

    public List<Combatente> getTimeB() {
        return timeB;
    }

    public boolean acabou() {
        return todosMortos(timeA) || todosMortos(timeB);
    }

    private boolean todosMortos(List<Combatente> time) {
        for (Combatente c : time) {
            if (c.estaVivo()) return false;
        }
        return true;
    }

    public List<String> executarRodada() {
        List<String> logs = new ArrayList<>();
        logs.add("=== RODADA " + rodada + " ===");

        atacarTime(timeA, timeB, logs);
        atacarTime(timeB, timeA, logs);

        if (todosMortos(timeA)) logs.add("TIME B VENCEU!");
        else if (todosMortos(timeB)) logs.add("TIME A VENCEU!");

        rodada++;
        return logs;
    }

    private void atacarTime(List<Combatente> atacantes, List<Combatente> defensores, List<String> logs) {
        for (Combatente atacante : atacantes) {
            if (!atacante.estaVivo()) continue;

            Combatente alvo = escolherAlvo(defensores);
            if (alvo == null) continue;

            atacante.atacar(alvo);
            logs.add(atacante.getNome() + " atacou " + alvo.getNome());
        }
    }

    private Combatente escolherAlvo(List<Combatente> time) {
        List<Combatente> vivos = new ArrayList<>();
        for (Combatente c : time) {
            if (c.estaVivo()) vivos.add(c);
        }
        if (vivos.isEmpty()) return null;
        return vivos.get(random.nextInt(vivos.size()));
    }
}
