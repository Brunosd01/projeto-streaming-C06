package br.com.streaming.modelo;

public class Serie extends Titulo {
    private int temporadas;
    private boolean ativa;
    private int episodiosPorTemporada;
    private int minutosPorEpisodio;

    public Serie(String nome, boolean incluidoNoPlano, String genero, int temporadas, int episodiosPorTemporada, int minutosPorEpisodio) {
        // Inicializa duração com 0, pois será calculada
        super(nome, 0, incluidoNoPlano, genero);
        this.temporadas = temporadas;
        this.episodiosPorTemporada = episodiosPorTemporada;
        this.minutosPorEpisodio = minutosPorEpisodio;
        this.ativa = true;
    }

    //Polimorfismo, A série calcula a duração total dinamicamente
    @Override
    public int getDuracaoEmMinutos() {
        return temporadas * episodiosPorTemporada * minutosPorEpisodio;
    }

    @Override
    public void exibeFichaTecnica() {
        System.out.println("--- FICHA DA SÉRIE ---");
        super.exibeFichaTecnica(); // Reutiliza código da classe mãe
        System.out.println("Total de Temporadas: " + temporadas);
        System.out.println("Duração estimada para maratonar: " + getDuracaoEmMinutos() + " min");
    }
}