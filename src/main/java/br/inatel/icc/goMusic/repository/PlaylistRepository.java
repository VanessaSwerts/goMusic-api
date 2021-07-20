package br.inatel.icc.goMusic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.inatel.icc.goMusic.model.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, Long>{

}
