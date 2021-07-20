package br.inatel.icc.goMusic.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Playlist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private String description;
	private String avatar;

	@ManyToOne
	private User owner;

	public Playlist() {
	}

	public Playlist(String title, String description, String avatar, User owner) {
		this.title = title;
		this.description = description;
		this.avatar = avatar;
		this.owner = owner;
	}

	public Long getId() {
		return id;
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

	public User getOwner() {
		return owner;
	}

}
