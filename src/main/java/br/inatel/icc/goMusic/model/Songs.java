package br.inatel.icc.goMusic.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Songs {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer songId;
	private String title;
	private int duration;
	private String url;
	private String album;
	private String artist;

	@ManyToOne
	private Playlist playlist;

	public Songs() {
	}

	public Songs(Integer songId, String title, int duration, String url, String album, String artist,
			Playlist playlist) {

		this.songId = songId;
		this.title = title;
		this.duration = duration;
		this.url = url;
		this.album = album;
		this.artist = artist;
		this.playlist = playlist;
	}

	public Long getId() {
		return id;
	}

	public Integer getSongId() {
		return songId;
	}

	public String getTitle() {
		return title;
	}

	public String getAlbum() {
		return album;
	}

	public int getDuration() {
		return duration;
	}

	public String getUrl() {
		return url;
	}

	public String getArtist() {
		return artist;
	}

	public Playlist getPlaylist() {
		return playlist;
	}

}
