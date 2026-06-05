package br.com.streaming;

import br.com.streaming.io.GerenciadorArquivos;
import br.com.streaming.modelo.Titulo;
import br.com.streaming.threads.BufferVideoThread;

import java.util.ArrayList;
import java.util.List;

public class PlataformaStreaming {
    private List<Titulo> catalogo;
    private GerenciadorArquivos gerenciador;

    public PlataformaStreaming() {
        this.gerenciador = new GerenciadorArquivos();
        this.catalogo = new ArrayList<>();
    }

    public void iniciarPlataforma() {
        System.out.println("[Sistema] Carregando dados do catálogo...");
        // Lê os dados do arquivo (Critério de Arquivos/Banco de Dados)
        this.catalogo = gerenciador.carregarDados();
    }

    public void cadastrarTitulo(Titulo t) {
        // Verifica se o título já está na lista usando o nome
        if (buscarTituloPorNome(t.getNome()) != null) {
            // Se já existir, ele avisa e sai do método sem duplicar
            System.out.println("[Sistema] Aviso: O título '" + t.getNome() + "' já está no catálogo.");
            return;
        }

        // Se não existir, ele cadastra normalmente e salva
        catalogo.add(t);
        gerenciador.salvarDados(catalogo);
    }

    public Titulo buscarTituloPorNome(String nome) {
        for (Titulo t : catalogo) {
            if (t.getNome().equalsIgnoreCase(nome)) {
                return t;
            }
        }
        return null;
    }

    public void listarCatalogo() {
        System.out.println("\n=== CATÁLOGO DE STREAMING ===");
        if (catalogo.isEmpty()) {
            System.out.println("O catálogo está vazio no momento.");
        } else {
            for (Titulo t : catalogo) {
                // Aqui ocorre o Polimorfismo: o Java decide se chama o método de Filme ou de Serie
                t.exibeFichaTecnica();
                System.out.println("----------------------------");
            }
        }
    }

    public void assistirTitulo(String nome) {
        Titulo titulo = buscarTituloPorNome(nome);

        if (titulo == null) {
            System.out.println("Erro: Título '" + nome + "' não encontrado.");
            return;
        }

        // Inicia a Thread para simular o carregamento do vídeo (Critério de Threads)
        BufferVideoThread buffer = new BufferVideoThread(titulo.getNome());
        buffer.start();

        try {
            // Aguarda a thread terminar para dar o "play" real
            buffer.join();
            System.out.println("Reproduzindo agora: " + titulo.getNome());
        } catch (InterruptedException e) {
            System.err.println("Falha na reprodução do vídeo: " + e.getMessage());
        }
    }

    // Método para remover um título pelo nome
    public boolean removerTitulo(String nome) {
        Titulo tituloParaRemover = buscarTituloPorNome(nome);

        if (tituloParaRemover != null) {
            catalogo.remove(tituloParaRemover);
            gerenciador.salvarDados(this.catalogo);
            return true; // Retorna verdadeiro se conseguiu remover
        }
        return false; // Retorna falso se o título não existia
    }

    // Método para devolver a lista de títulos para a Main conseguir imprimir
    public List<Titulo> getCatalogo() {
        return this.catalogo; // Se a sua lista tiver outro nome, mude "catalogo" aqui
    }

    // Método para fechar e salvar os dados
    public void fecharPlataforma() {
        System.out.println("Salvando o catálogo no arquivo...");
    }

}