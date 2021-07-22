package br.inatel.icc.goMusic.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "likes")
public class Like {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Playlist playlist;

	@ManyToOne
	private User user;

	public Like() {
	}

	public Like(Playlist playlist, User user) {
		this.playlist = playlist;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public Playlist getPlaylist() {
		return playlist;
	}

	public User getUser() {
		return user;
	}

}
