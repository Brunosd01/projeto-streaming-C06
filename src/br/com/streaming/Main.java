package br.com.streaming;

import br.com.streaming.modelo.Filme;
import br.com.streaming.modelo.Serie;
import br.com.streaming.modelo.Titulo;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PlataformaStreaming app = new PlataformaStreaming();
        Scanner scanner = new Scanner(System.in);

        // 1. CARREGAR OS DADOS AUTOMATICAMENTE AO INICIAR
        app.iniciarPlataforma();

        int opcao = 0;
        do {
            // 2. EXIBIR O CATÁLOGO EXATAMENTE NO FORMATO SOLICITADO
            exibirPainelCatalogo(app);

            // MENU DE OPÇÕES
            System.out.println("\n=== MENU DE OPÇÕES ===");
            System.out.println("1 - Cadastrar Novo Filme");
            System.out.println("2 - Cadastrar Nova Série");
            System.out.println("3 - Ver Ficha Técnica de um Título");
            System.out.println("4 - Remover um Título do Catálogo");
            System.out.println("5 - Avaliar um Título"); // <--- NOVA OPÇÃO!
            System.out.println("6 - Sair e Salvar");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        try {
                            System.out.print("Nome do Filme: ");
                            String nomeF = scanner.nextLine();
                            System.out.print("Ano de Lançamento: ");
                            int anoF = Integer.parseInt(scanner.nextLine());
                            System.out.print("Incluído no plano? (true ou false): ");
                            boolean planoF = Boolean.parseBoolean(scanner.nextLine());
                            System.out.print("Gênero: ");
                            String generoF = scanner.nextLine();

                            // Agora sim bate com as exigências da sua classe Filme!
                            Filme novoFilme = new Filme(nomeF, anoF, planoF, generoF);
                            app.cadastrarTitulo(novoFilme);
                            System.out.println("[Sucesso] Filme cadastrado!");
                        } catch (Exception e) {
                            System.out.println("[Erro] Dados inválidos inseridos!");
                        }
                        break;

                    case 2:
                        // TRATAMENTO DE ERROS NO CADASTRO DE SÉRIE
                        try {
                            System.out.print("Nome da Série: ");
                            String nomeS = scanner.nextLine();
                            System.out.print("Incluído no plano? (true ou false): ");
                            boolean planoS = Boolean.parseBoolean(scanner.nextLine());
                            System.out.print("Gênero: ");
                            String generoS = scanner.nextLine();
                            System.out.print("Total de Temporadas: ");
                            int temporadas = Integer.parseInt(scanner.nextLine());
                            System.out.print("Episódios por Temporada: ");
                            int episodios = Integer.parseInt(scanner.nextLine());
                            System.out.print("Minutos por Episódio: ");
                            int minutosEp = Integer.parseInt(scanner.nextLine());

                            // Agora passa as 6 informações na ordem exata exigida!
                            Serie novaSerie = new Serie(nomeS, planoS, generoS, temporadas, episodios, minutosEp);
                            app.cadastrarTitulo(novaSerie);
                            System.out.println("[Sucesso] Série cadastrada!");
                        } catch (NumberFormatException e) {
                            System.out.println("[Erro] Valores numéricos inválidos inseridos!");
                        }
                        break;

                    case 3:
                        // BUSCAR E IMPRIMIR UMA FICHA TÉCNICA ESPECÍFICA
                        System.out.print("Digite o nome do título que deseja buscar: ");
                        String nomeBusca = scanner.nextLine();
                        Titulo encontrado = app.buscarTituloPorNome(nomeBusca);

                        if (encontrado != null) {
                            encontrado.exibeFichaTecnica();
                        } else {
                            System.out.println("[Erro] Título '" + nomeBusca + "' não encontrado no catálogo.");
                        }
                        break;

                    case 4:
                        // REMOVER UM TÍTULO
                        System.out.print("Digite o nome do título que deseja remover: ");
                        String nomeRemover = scanner.nextLine();

                        boolean foiRemovido = app.removerTitulo(nomeRemover);
                        if (foiRemovido) {
                            System.out.println("[Sucesso] O título '" + nomeRemover + "' foi excluído do catálogo!");
                        } else {
                            System.out.println("[Erro] Título '" + nomeRemover + "' não foi encontrado.");
                        }
                        break;

                    case 5:
                        // AVALIAR UM TÍTULO
                        System.out.print("Digite o nome do título que deseja avaliar: ");
                        String nomeAvaliar = scanner.nextLine();
                        Titulo tituloAvaliar = app.buscarTituloPorNome(nomeAvaliar);

                        if (tituloAvaliar != null) {
                            try {
                                System.out.print("Dê a sua nota (ex: 1 a 5): ");
                                double nota = Double.parseDouble(scanner.nextLine());

                                // Chama o método que você já deve ter na classe Titulo
                                tituloAvaliar.avalia(nota);

                                // Salva no arquivo na hora para não perder a nota!
                                // app.fecharPlataforma(); // ou gerenciador.salvarDados(app.getCatalogo());
                                System.out.println("[Sucesso] Avaliação registrada com sucesso!");
                            } catch (NumberFormatException e) {
                                System.out.println("[Erro] Digite um número válido para a nota.");
                            }
                        } else {
                            System.out.println("[Erro] Título '" + nomeAvaliar + "' não foi encontrado.");
                        }
                        break;

                    case 6: // SAIR E SALVAR
                        System.out.println("Salvando dados no catalogo.dat e fechando o sistema...");
                        app.fecharPlataforma();
                        break;

                    default:
                        System.out.println("[Aviso] Opção inválida! Escolha de 1 a 6.");
                }
            } catch (NumberFormatException e) {
                System.out.println("[Erro] Digite um número inteiro para selecionar a opção do menu.");
            }

            if (opcao != 5) {
                System.out.println("\nPressione ENTER para continuar...");
                scanner.nextLine();
            }

        } while (opcao != 6);

        scanner.close();
    }

    // MÉTODO AUXILIAR PARA MONTAR A ESTRUTURA VISUAL DO SEU CATÁLOGO
    private static void exibirPainelCatalogo(PlataformaStreaming app) {
        System.out.println("\n{");
        System.out.println("                            ========== CATÁLOGO DE STREAMING ========== ");
        System.out.println("\n");
        // Separação e formatação dos Filmes em grade de até 4 itens por linha
        System.out.println("#Filmes:");
        int contFilmes = 0;
        for (Titulo t : app.getCatalogo()) { // Certifique-se de ter o método getCatalogo() na Plataforma
            if (t instanceof Filme) {
                System.out.printf("%-25s", t.getNome());
                contFilmes++;
                if (contFilmes % 4 == 0) System.out.println();
            }
        }
        if (contFilmes % 4 != 0 || contFilmes == 0) System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------------------");

        // Separação e formatação das Séries em grade de até 4 itens por linha
        System.out.println("#Series:");
        int contSeries = 0;
        for (Titulo t : app.getCatalogo()) {
            if (t instanceof Serie) {
                System.out.printf("%-25s", t.getNome());
                contSeries++;
                if (contSeries % 4 == 0) System.out.println();
            }
        }
        if (contSeries % 4 != 0 || contSeries == 0) System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println("}");
    }
}