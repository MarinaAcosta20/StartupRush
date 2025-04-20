package model;

public enum EventoGlobal {
    CRISE_ECONOMICA("Crise Econômica: Todas as startups perdem 10 pontos.", -10),
    FESTIVAL_TECNOLOGIA("Festival de Tecnologia: Todas as startups ganham 10 pontos!", 10),
    GREVE_MUNDIAL("Greve Mundial: Todas as startups perdem 5 pontos.", -5),
    TENDENCIA_INOVACAO("Tendência de Inovação: Todas as startups ganham 15 pontos!", 15),
    LEI_RESTRITIVA("Nova Lei Restritiva: Todas as startups perdem 8 pontos.", -8);

    private final String descricao;
    private final int impacto;

    EventoGlobal(String descricao, int impacto) {
        this.descricao = descricao;
        this.impacto = impacto;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getImpacto() {
        return impacto;
    }
}
