package br.inatel.icc.goMusic.repository;

import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.inatel.icc.goMusic.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void shouldFindUserByEmail() {
		String email = "anasilva@email.com";
		Optional<User> user = userRepository.findByEmail(email);

		Assert.assertNotNull(user.get());
		Assert.assertEquals(email, user.get().getEmail());
	}
	
	@Test
	public void shouldNotFindUserByEmail() {
		String wrongEmail = "anasilva@email.com.br";
		Optional<User> user = userRepository.findByEmail(wrongEmail);

		Assert.assertTrue(user.isEmpty());
	}

}
