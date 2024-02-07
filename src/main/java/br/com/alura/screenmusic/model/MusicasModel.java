package br.com.alura.screenmusic.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "musicas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MusicasModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;

    private String album;

    @ManyToOne
    private ArtistaModel artista;

    public MusicasModel(String nomeMusica, String nomeAlbum) {
        this.nome = nomeMusica;
        this.album = nomeAlbum;
    }
}
