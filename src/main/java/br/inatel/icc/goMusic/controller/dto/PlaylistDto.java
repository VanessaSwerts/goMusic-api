package br.inatel.icc.goMusic.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.inatel.icc.goMusic.model.Playlist;

public class PlaylistDto {

	private Long id;
	private String title;
	private String description;
	private String avatar;
	private UserDto owner;

	public PlaylistDto(Playlist playlist) {
		this.id = playlist.getId();
		this.title = playlist.getTitle();
		this.description = playlist.getDescription();
		this.avatar = playlist.getAvatar();
		this.owner = new UserDto(playlist.getOwner());
	}

	public Long getId() {
		return id;
	}

	public UserDto getOwner() {
		return owner;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getAvatar() {
		return avatar;
	}
	
	public boolean isOwner(Long id) {
		return this.owner.getId() == id;
	}

	public static List<PlaylistDto> convertToDtoList(List<Playlist> playlists) {
		List<PlaylistDto> playlistDto = playlists.stream().map(PlaylistDto::new).collect(Collectors.toList());
		return playlistDto;
	}

}
