package br.inatel.icc.goMusic.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.inatel.icc.goMusic.domain.Track;
import br.inatel.icc.goMusic.util.FormatData;

public class SongsDto {

	private Integer songId;
	private String title;
	private String duration;
	private String url;
	private String album;
	private String artist;

	public SongsDto(Track song) {
		this.songId = song.getId();
		this.title = song.getTitle();
		this.duration = FormatData.formatDuration(song.getDuration());
		this.url = song.getLink();
		this.album = song.getAlbum().getTitle();
		this.artist = song.getArtist().getName();
	}

	public Integer getSongId() {
		return songId;
	}

	public String getTitle() {
		return title;
	}

	public String getDuration() {
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

	public static List<SongsDto> convertToDtoList(List<Track> tracks) {
		List<SongsDto> songsDto = tracks.stream().map(SongsDto::new).collect(Collectors.toList());
		return songsDto;
	}

	public static List<SongsDto> requestTracks(List<Integer> tracksId) {
		Track track = new Track();
		List<Track> tracks = track.requestTrackById(tracksId);

		return convertToDtoList(tracks);
	}

}
