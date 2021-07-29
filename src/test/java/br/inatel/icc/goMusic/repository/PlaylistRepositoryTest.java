package br.inatel.icc.goMusic.repository;

import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.inatel.icc.goMusic.model.Playlist;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
class PlaylistRepositoryTest {

	@Autowired
	private PlaylistRepository playlistRepository;

	@Test
	public void shouldFindPlaylistByTitle() {
		String title = "My first playlist";
		Optional<Playlist> playlist = playlistRepository.findByTitle(title);

		Assert.assertNotNull(playlist.get());
		Assert.assertEquals(title, playlist.get().getTitle());
	}
	
	@Test
	public void shouldNotFindPlaylistByTitle() {
		String title = "My test playlist";
		Optional<Playlist> playlist = playlistRepository.findByTitle(title);

		Assert.assertFalse(playlist.isPresent());
	}

}
