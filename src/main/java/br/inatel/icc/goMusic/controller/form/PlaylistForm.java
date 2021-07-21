package br.inatel.icc.goMusic.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.inatel.icc.goMusic.model.Playlist;
import br.inatel.icc.goMusic.model.User;

public class PlaylistForm {

	@NotNull
	@NotEmpty
	@Length(min = 3)
	private String title;
	private String description;
	private String avatar = "default-avatar.png";

	private User owner;

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

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Playlist convertToPlaylist() {
		return new Playlist(title, description, avatar, owner);
	}

}
