package br.com.streaming.modelo;

public class Filme extends Titulo {

    public Filme(String nome, int duracaoEmMinutos, boolean incluidoNoPlano, String genero) {
        super(nome, duracaoEmMinutos, incluidoNoPlano, genero);
    }

    // Critério 04: Polimorfismo por sobrescrita
    @Override
    public void exibeFichaTecnica() {
        System.out.println("--- FICHA DO FILME ---");
        super.exibeFichaTecnica();
        System.out.println("Classificação: " + getClassificacao() + " estrelas");
    }
}