package br.com.alura.screenmusic.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artistas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArtistaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;

    @Enumerated(EnumType.STRING)
    private GeneroMusical generoMusical;

    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<MusicasModel> musicas = new ArrayList<>();

    public ArtistaModel(String nomeArtista, String generoMusical) {
        this.nome = nomeArtista;
        this.generoMusical = GeneroMusical.fromString(generoMusical);
    }
    public void setMusicas(MusicasModel musicas) {
        musicas.setArtista(this);
        this.musicas.add(musicas);
    }
}
