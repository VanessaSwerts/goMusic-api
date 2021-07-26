package br.inatel.icc.goMusic.controller.dto;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.inatel.icc.goMusic.model.Follow;
import br.inatel.icc.goMusic.model.Like;
import br.inatel.icc.goMusic.model.Playlist;
import br.inatel.icc.goMusic.model.User;

class UserDtoTest {

	private User firstUser;
	private User secondUser;
	private Playlist newPlaylist;
	private Like newLike;
	private UserDto userDto;

	@BeforeEach
	public void beforeEach() {
		this.firstUser = new User("Ana Silva", "anasilva@email.com", "12345678", "default-avatar.png", "Brasil");
		this.secondUser = new User("José Oliveira", "joseoliveira@email.com", "12345678", "default-avatar.png",
				"Brasil");
		this.newPlaylist = new Playlist("My first playlist", "This is the first playlist I created",
				"default-playlist.png", firstUser);
		this.newLike = new Like(newPlaylist, secondUser);
	}

	@Test
	public void verifyUserDtoInfosAfterCreate() {
		this.userDto = new UserDto(firstUser);

		Assert.assertEquals("Ana Silva", userDto.getName());
		Assert.assertEquals("Brasil", userDto.getCountry());
		Assert.assertEquals(0, userDto.getTotalFollowers());
		Assert.assertEquals(0, userDto.getTotalFollowings());
		Assert.assertEquals(0, userDto.getTotalCreatedPlaylists());
		Assert.assertEquals(0, userDto.getTotalLikedPlaylists());
		Assert.assertEquals(0, userDto.getTotalPlaylists());
	}

	@Test
	public void verifyUserTotalFollowings() {
		List<Follow> followings = new ArrayList<>();

		followings.add(new Follow(firstUser, secondUser));
		firstUser.setFollowings(followings);

		this.userDto = new UserDto(firstUser);

		Assert.assertEquals(1, userDto.getTotalFollowings());
	}

	@Test
	public void verifyUserTotalFollowers() {
		List<Follow> followers = new ArrayList<>();

		followers.add(new Follow(secondUser, firstUser));
		firstUser.setFollowers(followers);

		this.userDto = new UserDto(firstUser);

		Assert.assertEquals(1, userDto.getTotalFollowers());
	}

	@Test
	public void verifyUserTotalPlaylistsCreated() {
		List<Playlist> playlists = new ArrayList<>();

		playlists.add(newPlaylist);
		firstUser.setMyPlaylists(playlists);

		this.userDto = new UserDto(firstUser);

		Assert.assertEquals(1, userDto.getTotalCreatedPlaylists());
	}

	@Test
	public void verifyUserTotalPlaylistsLiked() {
		List<Playlist> playlists = new ArrayList<>();

		playlists.add(newPlaylist);
		firstUser.setMyPlaylists(playlists);

		List<Like> likes = new ArrayList<>();
		likes.add(newLike);
		secondUser.setLikedPlaylists(likes);

		this.userDto = new UserDto(secondUser);

		Assert.assertEquals(1, userDto.getTotalLikedPlaylists());
	}

}
