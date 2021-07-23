package br.inatel.icc.goMusic.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PlaylistSongs {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Playlist playlist;

	@ManyToOne
	private Songs songs;

	public PlaylistSongs() {
	}

	public PlaylistSongs(Playlist playlist, Songs songs) {
		this.playlist = playlist;
		this.songs = songs;
	}

	public Long getId() {
		return id;
	}

	public Playlist getPlaylist() {
		return playlist;
	}

	public Songs getSongs() {
		return songs;
	}

}
