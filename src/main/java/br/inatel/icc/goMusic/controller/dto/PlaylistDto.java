package br.inatel.icc.goMusic.controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.inatel.icc.goMusic.model.Playlist;

public class PlaylistDto {

	private Long id;
	private String title;
	private String description;
	private String avatar;
	private int totalLikes;
	private UserDto owner;
//	private List<SongsDto> songs;
	private int totalSongs;

	public PlaylistDto(Playlist playlist) {
		this.id = playlist.getId();
		this.title = playlist.getTitle();
		this.description = playlist.getDescription();
		this.avatar = playlist.getAvatar();
		this.totalLikes = playlist.getLikes().size();
		this.owner = new UserDto(playlist.getOwner());

//		this.songs = new ArrayList<>();		
//		this.songs = SongsDto.convertToDtoList(playlist.getSongs());
		this.totalSongs = playlist.getSongs().size();
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

	public int getTotalLikes() {
		return totalLikes;
	}

	public boolean isOwner(Long id) {
		return this.owner.getId() == id;
	}

//	public List<SongsDto> getSongs() {
//		return songs;
//	}
	public int getTotalSongs() {
		return totalSongs;
	}

	public static List<PlaylistDto> convertToDtoList(List<Playlist> playlists) {
		List<PlaylistDto> playlistDto = playlists.stream().map(PlaylistDto::new).collect(Collectors.toList());
		return playlistDto;
	}

	public static List<PlaylistDto> concatTwoDtoList(List<Playlist> fisrtList, List<Playlist> secondList) {
		List<Playlist> allPlaylists = new ArrayList<>();
		allPlaylists.addAll(fisrtList);
		allPlaylists.addAll(secondList);

		List<PlaylistDto> playlistDto = convertToDtoList(allPlaylists);

		return playlistDto;
	}

}
