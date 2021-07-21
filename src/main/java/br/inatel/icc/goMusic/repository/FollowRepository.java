package br.inatel.icc.goMusic.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.inatel.icc.goMusic.model.Follow;
import br.inatel.icc.goMusic.model.User;

public interface FollowRepository extends JpaRepository<Follow, Long> {

	Optional<Follow> findByFollowingAndFollower(User following, User follower);

}
