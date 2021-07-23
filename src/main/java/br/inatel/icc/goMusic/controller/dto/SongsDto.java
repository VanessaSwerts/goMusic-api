package br.inatel.icc.goMusic.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.inatel.icc.goMusic.model.Songs;

public class SongsDto {

	private Integer songId;
	private String title;
	private int duration;
	private String url;
	private String album;
	private String artist;

	public SongsDto(Songs song) {
		this.songId = song.getSongId();
		this.title = song.getTitle();
		this.duration = song.getDuration();
		this.url = song.getUrl();
		this.album = song.getAlbum();
		this.artist = song.getArtist();
	}

	public Integer getSongId() {
		return songId;
	}

	public String getTitle() {
		return title;
	}

	public int getDuration() {
		return duration;
	}

	public String getUrl() {
		return url;
	}

	public String getAlbum() {
		return album;
	}

	public String getArtist() {
		return artist;
	}

	public static List<SongsDto> convertToDtoList(List<Songs> songs) {
		List<SongsDto> songsDto = songs.stream().map(SongsDto::new).collect(Collectors.toList());
		return songsDto;
	}

}
