package br.com.streaming;

import br.com.streaming.modelo.Filme;
import br.com.streaming.modelo.Serie;

public class Main {
    public static void main(String[] args) {
        // 1. Instancia o controlador
        PlataformaStreaming app = new PlataformaStreaming();
        System.out.println("=== SISTEMA DE STREAMING INICIADO ===");

        // Inicia carregando os arquivos
        app.iniciarPlataforma();

        // 2. Criando alguns conteúdos de teste
        Filme filme1 = new Filme("O Auto da Compadecida", 104, true, "Comédia");
        filme1.avalia(10);
        filme1.avalia(9.5);

        Serie serie1 = new Serie("Breaking Bad", true, "Drama", 5, 13, 50);
        serie1.avalia(10);
        serie1.avalia(10);

        // 3. Cadastrando na plataforma
        System.out.println("\n[Sistema] Cadastrando novos títulos...");
        app.cadastrarTitulo(filme1);
        app.cadastrarTitulo(serie1);

        // 4. Listando catálogo (Aciona o polimorfismo)
        app.listarCatalogo();

        // 5. Testando reprodução (Aciona as Threads)
        System.out.println("\n=== SIMULANDO REPRODUÇÃO ===");
        app.assistirTitulo("O Auto da Compadecida");

        // 6. Testando erros (Aciona as Exceções)
        System.out.println("\n=== TESTANDO TRATAMENTO DE ERROS ===");
        app.assistirTitulo("Filme Que Não Existe");

        try {
            System.out.println("\n[Sistema] Forçando erro com nota 15...");
            serie1.avalia(15);
        } catch (IllegalArgumentException e) {
            System.err.println("Erro capturado com sucesso: " + e.getMessage());
        }
        // oi
    }
}