package br.com.alura.screenmusic.repository;

import br.com.alura.screenmusic.model.ArtistaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistaRepository extends JpaRepository<ArtistaModel, Long> {
    Optional<ArtistaModel> findByNomeIgnoreCase(String artistaProcurado);

    @Query(value = """
            SELECT a FROM ArtistaModel a
              JOIN a.musicas m                            
            """)
    List<ArtistaModel> listarMusicasCadastradas();
}
