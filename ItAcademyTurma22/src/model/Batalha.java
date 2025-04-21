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


    public boolean aplicarEvento(Startup s, Evento e) {
        if (!eventosAplicados.get(s).contains(e)) {
            eventosAplicados.get(s).add(e);
            s.aplicarEvento(e);
            return true;
        }
        return false;
    }

    public Startup calcularVencedor() {
        if (finalizada) return a.getPontos() > b.getPontos() ? a : b;

        Startup vencedor;
        if (a.getPontos() > b.getPontos()) {
            vencedor = a;
        } else if (b.getPontos() > a.getPontos()) {
            vencedor = b;
        } else {
            vencedor = new Random().nextBoolean() ? a : b;
            vencedor.ganharSharkFight();
            sharkFightRealizado = true;
        }

        vencedor.ganharBonusVitoria();
        finalizada = true;
        return vencedor;
    }
    
    public Startup getVencedora() {
        if (!finalizada) {
            return calcularVencedor();
        }
        return a.getPontos() > b.getPontos() ? a : b;
    }
    
    public Startup getStartupA() { return a; }
    public Startup getStartupB() { return b; }

    public boolean isFinalizada() {
        return finalizada;
    }

    public boolean teveSharkFight() {
        return sharkFightRealizado;
    }
}
