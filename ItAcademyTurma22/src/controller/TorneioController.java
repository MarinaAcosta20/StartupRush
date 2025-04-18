package controller;

import model.*;

import java.util.*;

public class TorneioController {
	private List<Startup> todasStartups = new ArrayList<>();
    private List<Startup> startups = new ArrayList<>();
    private Queue<Batalha> batalhas = new LinkedList<>();
    private int rodada = 1;
    private Startup campea;

    public void cadastrarStartup(Startup s) {
        startups.add(s);
        todasStartups.add(s);
    }

    public boolean podeIniciar() {
        return startups.size() >= 4 && startups.size() <= 8 && startups.size() % 2 == 0;
    }

    public void iniciarRodada() {
        batalhas.clear();
        Collections.shuffle(startups);
        for (int i = 0; i < startups.size(); i += 2) {
            batalhas.add(new Batalha(startups.get(i), startups.get(i + 1)));
        }
    }

    public Batalha getProximaBatalha() {
        return batalhas.poll();
    }

    public boolean temMaisBatalhas() {
        return batalhas.stream().anyMatch(b -> !b.isFinalizada());
    }

    public void avancarFase() {
        List<Startup> vencedoras = new ArrayList<>();

        for (Batalha b : batalhas) {
            if (!b.isFinalizada()) {
                b.calcularVencedor();
            }
            vencedoras.add(b.calcularVencedor());
        }

        if (vencedoras.size() == 1) {
            campea = vencedoras.get(0);
        } else {
            startups = vencedoras;
            rodada++;
            iniciarRodada();
        }
    }

    public boolean torneioFinalizado() {
        return campea != null;
    }

    public Startup getCampea() {
        return campea;
    }

    public List<Startup> getRankingFinal() {
        List<Startup> ranking = new ArrayList<>(startups);
        if (campea != null && !ranking.contains(campea)) {
            ranking.add(campea);
        }
        ranking.sort(Comparator.comparingInt(Startup::getPontos).reversed());
        return ranking;
    }

    public int getRodada() {
        return rodada;
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
    
    public List<Startup> getTodasStartups() {
        return todasStartups;
    }

}
