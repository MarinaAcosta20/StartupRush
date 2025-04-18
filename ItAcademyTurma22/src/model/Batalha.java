package model;

import java.util.*;

public class Batalha {
    private Startup a;
    private Startup b;
    private boolean finalizada = false;
    private boolean sharkFightRealizado = false;

    private final Map<Startup, Set<Evento>> eventosAplicados = new HashMap<>();

    public Batalha(Startup a, Startup b) {
        this.a = a;
        this.b = b;
        eventosAplicados.put(a, new HashSet<>());
        eventosAplicados.put(b, new HashSet<>());
    }
    
    public String toString() {
        return a.getNome() + " VS " + b.getNome();
    }

    public Startup getStartupA() { return a; }
    public Startup getStartupB() { return b; }

    public boolean aplicarEvento(Startup s, Evento e) {
        if (!eventosAplicados.get(s).contains(e)) {
            eventosAplicados.get(s).add(e);
            s.aplicarEvento(e);
            return true;
        }
        return false;
    }

    public Startup calcularVencedor() {
        if (a.getPontos() > b.getPontos()) {
            a.ganharBonusVitoria();
            finalizada = true;
            sharkFightRealizado = false;
            return a;
        } else if (b.getPontos() > a.getPontos()) {
            b.ganharBonusVitoria();
            finalizada = true;
            sharkFightRealizado = false;
            return b;
        } else {
            // Shark Fight
            Random rand = new Random();
            Startup vencedor = rand.nextBoolean() ? a : b;
            vencedor.ganharSharkFight();
            vencedor.ganharBonusVitoria();
            finalizada = true;
            sharkFightRealizado = true;
            return vencedor;
        }
    }



    public boolean isFinalizada() {
        return finalizada;
    }

    public boolean teveSharkFight() {
        return sharkFightRealizado;
    }

}
