package br.inatel.icc.goMusic.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.inatel.icc.goMusic.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);
}
