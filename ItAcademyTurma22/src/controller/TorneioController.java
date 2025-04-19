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

        // Se o número de startups for ímpar, avança uma automaticamente
        if (startups.size() % 2 != 0) {
            // Escolhe a startup com mais pontos para avançar automaticamente
            Startup bye = Collections.max(startups, Comparator.comparingInt(Startup::getPontos));
            startups.remove(bye); // Remove ela da lista de startups para não criar uma batalha com ela
            // Você pode, se preferir, adicionar algum tipo de mensagem informando o avanço automático
            System.out.println("Startup " + bye.getNome() + " avançou automaticamente para a próxima rodada.");

        }

        // Agora cria as batalhas entre as startups restantes
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

        // Coleta os vencedores das batalhas restantes
        while (!batalhas.isEmpty()) {
            Batalha b = batalhas.poll();
            if (!b.isFinalizada()) {
                b.calcularVencedor();
            }
            vencedoras.add(b.getVencedora());
        }

        if (vencedoras.size() == 1) {
            campea = vencedoras.get(0);
        } else {
            List<Startup> novaRodada = new ArrayList<>();

            if (vencedoras.size() % 2 != 0) {
                // Startup com mais pontos avança automaticamente
                Startup bye = Collections.max(vencedoras, Comparator.comparingInt(Startup::getPontos));
                bye.ganharBonusVitoria(); // +30 pontos pela "vitória automática"
                novaRodada.add(bye);

                // Remove usando equals para garantir que a comparação funcione corretamente
                vencedoras.removeIf(s -> s.equals(bye));
            }

            novaRodada.addAll(vencedoras);
            startups = novaRodada;
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
