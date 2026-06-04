package br.com.streaming.modelo;

import java.io.Serializable;

public abstract class Titulo implements Classificavel, Serializable {
    private String nome;
    private int duracaoEmMinutos;
    private boolean incluidoNoPlano;
    private double somaDasAvaliacoes;
    private int totalDeAvaliacoes;
    private String genero;

    public Titulo(String nome, int duracaoEmMinutos, boolean incluidoNoPlano, String genero) {
        this.nome = nome;
        this.duracaoEmMinutos = duracaoEmMinutos;
        this.incluidoNoPlano = incluidoNoPlano;
        this.genero = genero;
    }

    public void exibeFichaTecnica() {
        System.out.println("Nome: " + nome);
        System.out.println("Duração: " + duracaoEmMinutos + " min");
        System.out.println("Gênero: " + genero);
        System.out.println("Incluído no plano: " + (incluidoNoPlano ? "Sim" : "Não"));
    }

    public void avalia(double nota) {
        if (nota < 0 || nota > 10) {
            // Critério 06: Tratamento de erro para notas inválidas
            throw new IllegalArgumentException("A nota deve ser entre 0 e 10.");
        }
        somaDasAvaliacoes += nota;
        totalDeAvaliacoes++;
    }

    public double obterMediaAvaliacoes() {
        if (totalDeAvaliacoes == 0) return 0;
        return somaDasAvaliacoes / totalDeAvaliacoes;
    }

    @Override
    public int getClassificacao() {
        return (int) obterMediaAvaliacoes() / 2; // Converte escala de 0-10 para 0-5 estrelas
    }

    // Getters
    public String getNome() { return nome; }
    public int getDuracaoEmMinutos() { return duracaoEmMinutos; }
    protected void setDuracaoEmMinutos(int duracaoEmMinutos) { this.duracaoEmMinutos = duracaoEmMinutos; }
}