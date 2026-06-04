package br.com.streaming.io;

import br.com.streaming.modelo.Titulo;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorArquivos {
    private Path CAMINHO_CATALOGO = Paths.get("catalogo.dat");

    public void salvarDados(List<Titulo> catalogo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CAMINHO_CATALOGO.toFile()))) {
            oos.writeObject(catalogo);
            System.out.println("Catálogo salvo com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar o arquivo do catálogo: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public List<Titulo> carregarDados() {
        File arquivo = CAMINHO_CATALOGO.toFile();
        if (!arquivo.exists()) {
            return new ArrayList<>(); // Retorna lista vazia se o arquivo não existir
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<Titulo>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar dados salvos: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}