package br.com.streaming.threads;

public class BufferVideoThread extends Thread {
    private String tituloMidia;

    public BufferVideoThread(String tituloMidia) {
        this.tituloMidia = tituloMidia;
    }

    @Override
    public void run() {
        System.out.print("\n[Sistema] Iniciando o buffer de " + tituloMidia);
        try {
            for (int i = 0; i < 4; i++) {
                Thread.sleep(800); // Simulando tempo de carregamento de rede
                System.out.print(".");
            }
            System.out.println("\n[Sistema] " + tituloMidia + " pronto para reprodução!");
        } catch (InterruptedException e) {
            // Critério 06: Tratamento de exceções em Threads
            System.err.println("Erro durante o carregamento do vídeo: " + e.getMessage());
        }
    }
}