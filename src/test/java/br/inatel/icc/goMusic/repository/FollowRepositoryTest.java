package br.inatel.icc.goMusic.repository;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.inatel.icc.goMusic.model.Follow;
import br.inatel.icc.goMusic.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class FollowRepositoryTest {

	@Autowired
	private FollowRepository followRepository;

	@Autowired
	private UserRepository userRepository;

	private User firstUser;
	private User secondUser;

	@BeforeEach
	public void beforeEach() {
		this.firstUser = userRepository.getById((long) 1);
		this.secondUser = userRepository.getById((long) 2);
	}

	@Test
	public void shouldFindByFollowingAndFollower() {
		Optional<Follow> isFollowing = followRepository.findByFollowingAndFollower(secondUser, firstUser);

		Assert.assertNotNull(isFollowing.get());
	}
	
	@Test
	public void shouldNotFindByFollowingAndFollower() {
		Optional<Follow> isFollowing = followRepository.findByFollowingAndFollower(firstUser, secondUser);

		Assert.assertTrue(isFollowing.isEmpty());
	}

}
