package br.inatel.icc.goMusic.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

	@OneToMany(mappedBy = "playlist", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Like> likes = new ArrayList<>();

	@ElementCollection
	private List<Integer> songsID = new ArrayList<>();

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

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public List<User> getLikes() {
		List<User> users = new ArrayList<>();

		likes.forEach(userLike -> {
			users.add(userLike.getUser());
		});

		return users;
	}

	public void setLikes(List<Like> likes) {
		this.likes = likes;
	}

	public List<Integer> getTracksID() {
		return songsID;
	}

	public void setTracksID(List<Integer> tracksID) {
		this.songsID = tracksID;
	}
	
	public boolean playlistContainsSong(Integer id) {
		return songsID.contains(id);
	}

}
