package model;

public class Startup {
    private String nome;
    private String slogan;
    private int ano;
    private int pontos = 70;

    // Estatísticas
    private int pitches = 0;
    private int bugs = 0;
    private int tracoes = 0;
    private int investidoresIrritados = 0;
    private int fakeNews = 0;

    public Startup(String nome, String slogan, int ano) {
        this.nome = nome;
        this.slogan = slogan;
        this.ano = ano;
    }

    public void aplicarEvento(Evento evento) {
        pontos += evento.getPontuacao();
        switch (evento) {
            case PITCH -> pitches++;
            case BUG -> bugs++;
            case BOA_TRACAO -> tracoes++;
            case INVESTIDOR_IRRITADO -> investidoresIrritados++;
            case FAKE_NEWS -> fakeNews++;
        }
    }

    public void aplicarEventoGlobal(EventoGlobal eventoGlobal) {
        pontos += eventoGlobal.getImpacto();
    }

    public void ganharBonusVitoria() {
        pontos += 30;
    }

    public void ganharSharkFight() {
        pontos += 2;
    }

    public String getNome() { return nome; }
    public String getSlogan() { return slogan; }
    public int getAno() { return ano; }
    public int getPontos() { return pontos; }

    public int getPitches() { return pitches; }
    public int getBugs() { return bugs; }
    public int getTracoes() { return tracoes; }
    public int getInvestidoresIrritados() { return investidoresIrritados; }
    public int getFakeNews() { return fakeNews; }

    public void setPontos(int i) {
        this.pontos = i;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Startup startup = (Startup) o;
        return nome.equals(startup.nome);
    }

    public int hashCode() {
        return nome.hashCode();
    }
}
