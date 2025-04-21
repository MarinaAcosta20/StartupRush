package controller;

import model.*;

import java.util.*;

public class TorneioController {
    private List<Startup> todasStartups = new ArrayList<>();
    private List<Startup> startups = new ArrayList<>();
    private Queue<Batalha> batalhas = new LinkedList<>();
    private int rodada = 1;
    private EventoGlobal eventoGlobalRodadaAtual;
    private Startup campea;
    private Startup byeDaRodada = null;
    private Startup startupComBye;

    public void cadastrarStartup(Startup s) {
        startups.add(s);
        todasStartups.add(s);
    }

    public boolean podeIniciar() {
        return startups.size() >= 4 && startups.size() <= 8 && startups.size() % 2 == 0;
    }

    public void iniciarRodada() {
        aplicarEventoGlobal();
        batalhas.clear();
        Collections.shuffle(startups);
        for (int i = 0; i < startups.size(); i += 2) {
            batalhas.add(new Batalha(startups.get(i), startups.get(i + 1)));
        }
    }

    private void aplicarEventoGlobal() {
        EventoGlobal[] eventos = EventoGlobal.values();
        eventoGlobalRodadaAtual = eventos[new Random().nextInt(eventos.length)];

        for (Startup s : startups) {
            s.aplicarEventoGlobal(eventoGlobalRodadaAtual);
        }
    }
    public void avancarFase() {
        List<Startup> vencedoras = new ArrayList<>();

        while (!batalhas.isEmpty()) {
            Batalha b = batalhas.poll();
            if (!b.isFinalizada()) {
                b.calcularVencedor();
            }
            vencedoras.add(b.calcularVencedor());
        }

        if (byeDaRodada != null) {
            vencedoras.add(byeDaRodada);
            byeDaRodada = null;
        }

        if (vencedoras.size() == 1) {
            campea = vencedoras.get(0);
        } else {
            List<Startup> novaRodada = new ArrayList<>();

            if (vencedoras.size() % 2 != 0) {
                byeDaRodada = Collections.max(vencedoras, Comparator.comparingInt(Startup::getPontos));
                byeDaRodada.ganharBonusVitoria();
                vencedoras.remove(byeDaRodada);
            }

            novaRodada.addAll(vencedoras);
            startups = novaRodada;
            rodada++;
            iniciarRodada();
        }
    }
    
    public List<Startup> getRankingFinal() {
        List<Startup> ranking = new ArrayList<>(todasStartups);
        ranking.sort(Comparator.comparingInt(Startup::getPontos).reversed());
        return ranking;
    }

    public List<Batalha> batalhasRestantes() {
        List<Batalha> restantes = new ArrayList<>();
        for (Batalha b : batalhas) {
            if (!b.isFinalizada()) {
                restantes.add(b);
            }
        }
        return restantes;
    }

    public EventoGlobal getEventoGlobalRodadaAtual() {
        return eventoGlobalRodadaAtual;
    }

    public Batalha getProximaBatalha() {
        return batalhas.poll();
    }

    public boolean temMaisBatalhas() {
        return batalhas.stream().anyMatch(b -> !b.isFinalizada());
    }
    public List<Startup> getTodasStartups() {
        return todasStartups;
    }

    public Startup getByeDaRodada() {
        return byeDaRodada;
    }
    public Startup getStartupComBye() {
        return startupComBye;
    }

    public boolean torneioFinalizado() {
        return campea != null;
    }

    public Startup getCampea() {
        return campea;
    }

    public int getRodada() {
        return rodada;
    }

    
}
