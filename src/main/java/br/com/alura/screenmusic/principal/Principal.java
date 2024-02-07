package br.com.alura.screenmusic.principal;

import br.com.alura.screenmusic.model.ArtistaModel;
import br.com.alura.screenmusic.model.MusicasModel;
import br.com.alura.screenmusic.repository.ArtistaRepository;
import br.com.alura.screenmusic.service.ConsultaChatGPT;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    Optional<ArtistaModel> artista = Optional.of(new ArtistaModel());
    Scanner scanner = new Scanner(System.in);
    private ArtistaRepository repository;

    public Principal(ArtistaRepository repository) {
        this.repository = repository;
    }

    public void exibeMenu() {
        Integer opcaoSelecionada = -1;

        while(opcaoSelecionada != 9) {
            System.out.println("""
                        *** Screen Music ***
                        Digite uma das opções abaixo:
                        
                        1 - Cadastrar artistas
                        2 - Cadastrar músicas
                        3 - Listar músicas
                        4 - Buscar músicas por artistas
                        5 - Pesquisar dados sobre um artista
                        
                        9 - Sair
                    """);

            opcaoSelecionada = scanner.nextInt();
            scanner.nextLine();

            switch (opcaoSelecionada) {
                case 1:
                    cadastrarArtista();
                    break;
                case 2:
                    cadastrarMusicasPorArtista();
                    break;
                case 3:
                    listarMusicas();
                    break;
                case 4:
                    listarMusicasPorArtista();
                    break;
                case 5:
                    listarInformacoesSobreArtista();
                    break;
                case 9:
                    break;
                default:
                    throw new RuntimeException("Opção inválida!");
            }
        }
    }

    private void listarInformacoesSobreArtista() {
        System.out.println("Informe o artista que deseja buscar: ");
        String nomeArtista = scanner.nextLine();

        System.out.println(ConsultaChatGPT.obterInformacoesSobreArtista(nomeArtista).trim());
    }

    private void listarMusicasPorArtista() {
        System.out.println("Informe o artista que deseja buscar: ");
        String nomeArtista = scanner.nextLine();

        artista = repository.findByNomeIgnoreCase(nomeArtista);

        if (artista.isPresent()) {
            artista.get().getMusicas().forEach(m ->
                    System.out.println("Música: " + m.getNome() + ", álbum: " + m.getAlbum()));
        } else {
            System.out.println("Artista não encontrado!");
        }
    }

    private void listarMusicas() {
        List<ArtistaModel> artistasEMusicas = repository.listarMusicasCadastradas();
        artistasEMusicas.forEach(a -> a.getMusicas().forEach(m ->
                System.out.println("Música: " + m.getNome() + ", álbum: " + m.getAlbum() + ", artista: " + a.getNome()))
        );
    }

    private void cadastrarMusicasPorArtista() {
        System.out.println("Informe o artista para o qual deseja cadastrar uma música: ");
        String artistaProcurado = scanner.nextLine();
        Optional<ArtistaModel> artistaRetornado = repository.findByNomeIgnoreCase(artistaProcurado);

        if (artistaRetornado.isPresent()) {
            ArtistaModel artista = artistaRetornado.get();
            System.out.println("Informe o nome da música: ");
            String nomeMusica = scanner.nextLine();

            System.out.println("De qual álbum é esta música?");
            String nomeAlbum = scanner.nextLine();

            MusicasModel musica = new MusicasModel(nomeMusica, nomeAlbum);
            artista.setMusicas(musica);

            repository.save(artista);

        } else {
            System.out.println("Artista não localizado, deseja cadastrar?(S/N)");
            String cadastrarArtista = scanner.nextLine();

            if (cadastrarArtista.equalsIgnoreCase("s")) {
                cadastrarArtista();
            }
        }
    }

    private void cadastrarArtista() {
        System.out.println("Qual o nome do artista?");
        String nomeArtista = scanner.nextLine();

        System.out.println("Qual o gênero musical do artista?");
        String generoMusical = scanner.nextLine();

        ArtistaModel artistaParaCadastro = new ArtistaModel(nomeArtista, generoMusical);

        repository.save(artistaParaCadastro);
    }
}
