package br.inatel.icc.goMusic.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.inatel.icc.goMusic.model.Like;
import br.inatel.icc.goMusic.model.Playlist;
import br.inatel.icc.goMusic.model.User;

public interface LikeRepository extends JpaRepository<Like, Long> {

	Optional<Like> findByPlaylistAndUser(Playlist playlist, User user);

}

