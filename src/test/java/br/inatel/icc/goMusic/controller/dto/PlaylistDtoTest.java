package br.inatel.icc.goMusic.controller.dto;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.inatel.icc.goMusic.model.Like;
import br.inatel.icc.goMusic.model.Playlist;
import br.inatel.icc.goMusic.model.User;

class PlaylistDtoTest {

	private Playlist newPlaylist;
	private User owner;
	private User user;
	private PlaylistDto playlistDto;
	private Like newLike;

	@BeforeEach
	public void beforeEach() {
		this.owner = new User("Ana Silva", "anasilva@email.com", "12345678", "default-avatar.png", "Brasil");
		this.user = new User("José Oliveira", "joseoliveira@email.com", "12345678", "default-avatar.png", "Brasil");
		this.newPlaylist = new Playlist("My first playlist", "This is the first playlist I created",
				"default-playlist.png", owner);
		this.newLike = new Like(newPlaylist, user);
	}

	@Test
	public void verifyPlaylistDtoInfosAfterCreate() {
		this.playlistDto = new PlaylistDto(newPlaylist);

		Assert.assertEquals("My first playlist", playlistDto.getTitle());
		Assert.assertEquals("This is the first playlist I created", playlistDto.getDescription());
		Assert.assertEquals("Ana Silva", playlistDto.getOwner().getName());
		Assert.assertEquals(0, playlistDto.getTotalLikes());
		Assert.assertEquals(0, playlistDto.getTotalSongs());
	}

	@Test
	public void verifyPlaylistTotalLikes() {
		List<Like> likes = new ArrayList<>();
		likes.add(newLike);
		newPlaylist.setLikes(likes);

		this.playlistDto = new PlaylistDto(newPlaylist);

		Assert.assertEquals(1, playlistDto.getTotalLikes());
	}

}
