package br.inatel.icc.goMusic.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.inatel.icc.goMusic.model.Songs;

public interface SongsRepository extends JpaRepository<Songs, Long> {

	Optional<Songs> findBySongId(Integer id);

}
