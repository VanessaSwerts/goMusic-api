package br.inatel.icc.goMusic.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.inatel.icc.goMusic.model.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, Long>{

	Optional<Playlist> findByTitle(String title);

}
