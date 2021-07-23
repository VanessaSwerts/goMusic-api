package br.inatel.icc.goMusic.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.inatel.icc.goMusic.model.Playlist;
import br.inatel.icc.goMusic.model.PlaylistSongs;
import br.inatel.icc.goMusic.model.Songs;

public interface PlaylistSongsRepository extends JpaRepository<PlaylistSongs, Long> {

	Optional<PlaylistSongs> findByPlaylistAndSongs(Playlist playlist, Songs songs);

}
