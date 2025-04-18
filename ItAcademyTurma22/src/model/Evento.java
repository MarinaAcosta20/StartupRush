package model;

public enum Evento {
    PITCH("Pitch convincente", 6),
    BUG("Produto com bugs", -4),
    BOA_TRACAO("Boa tração de usuários", 3),
    INVESTIDOR_IRRITADO("Investidor irritado", -6),
    FAKE_NEWS("Fake news no pitch", -8);

    private final String nome;
    private final int pontuacao;

    Evento(String nome, int pontuacao) {
        this.nome = nome;
        this.pontuacao = pontuacao;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public String getNomeFormatado() {
        return nome;
    }
}
