package controller;

import model.*;

import java.util.*;

public class TorneioController {
    private List<Startup> startups = new ArrayList<>();
    private List<Batalha> batalhasRodadaAtual = new ArrayList<>();
    private int rodada = 1;
    private Startup campea;


    public void cadastrarStartup(Startup s) {
        startups.add(s);
    }

    public boolean podeIniciar() {
        return startups.size() >= 4 && startups.size() <= 8 && startups.size() % 2 == 0;
    }

    public void iniciarRodada() {
        batalhasRodadaAtual.clear();
        Collections.shuffle(startups);
        for (int i = 0; i < startups.size(); i += 2) {
            batalhasRodadaAtual.add(new Batalha(startups.get(i), startups.get(i + 1)));
        }
    }

    public List<Batalha> getBatalhasRodadaAtual() {
        return batalhasRodadaAtual;
    }

    public boolean todasBatalhasFinalizadas() {
        for (Batalha b : batalhasRodadaAtual) {
            if (!b.isFinalizada()) return false;
        }
        return true;
    }

    public void avancarFase() {
        List<Startup> vencedoras = new ArrayList<>();
        for (Batalha b : batalhasRodadaAtual) {
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
        return startups.size() == 1;
    }

    public Startup getCampea() {
        return startups.get(0);
    }

    public List<Startup> getRankingFinal() {
        List<Startup> ranking = new ArrayList<>(startups);
        ranking.sort(Comparator.comparingInt(Startup::getPontos).reversed());
        return ranking;
    }

    public int getRodada() {
        return rodada;
    }
   
    public List<Batalha> batalhasRestantes() {
        return batalhasRodadaAtual.stream().filter(b -> !b.isFinalizada()).toList();
    }


}
