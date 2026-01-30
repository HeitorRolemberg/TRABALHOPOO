package Elementos_do_Jogo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    private List<Combatente> timeA;
    private List<Combatente> timeB;
    private Random random;

    public Arena() {
        timeA = new ArrayList<>();
        timeB = new ArrayList<>();
        random = new Random();
    }

    public void adicionarCombatente(Combatente c, char time) {
        if (time == 'A') timeA.add(c);
        else timeB.add(c);
    }

    public boolean temVivos(List<Combatente> time) {
        for(Combatente c : time) if(c.estaVivo()) return true;
        return false;
    }

    public List<String> executarRodada() {
        List<String> log = new ArrayList<>();

        if(!temVivos(timeA)){
            log.add(">>> A HORDA DAS SOMBRAS VENCEU! <<<");
            return log;
        }
        if(!temVivos(timeB)){
            log.add(">>> A ALIANÇA DA LUZ VENCEU! <<<");
            return log;
        }

        log.add("--- NOVA RODADA ---");
        log.addAll(ataqueTime(timeA, timeB));
        log.addAll(ataqueTime(timeB, timeA));

        return log;
    }

    private List<String> ataqueTime(List<Combatente> atacantes, List<Combatente> defensores){
        List<String> acoes = new ArrayList<>();
        
        for(Combatente at : atacantes){
            if(!at.estaVivo()) continue;

            List<Combatente> alvosVivos = new ArrayList<>();
            for(Combatente def : defensores) if(def.estaVivo()) alvosVivos.add(def);

            if(alvosVivos.isEmpty()) break;

            Combatente alvo = alvosVivos.get(random.nextInt(alvosVivos.size()));
            acoes.add(at.atacar(alvo));
        }
        return acoes;
    }

    public List<Combatente> getTimeA() { return timeA; }
    public List<Combatente> getTimeB() { return timeB; }
}
