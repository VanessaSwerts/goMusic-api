package br.inatel.icc.goMusic.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.inatel.icc.goMusic.model.Playlist;

public class PlaylistFormUpdate {
	
	@NotNull
	@NotEmpty
	@Length(min = 3)
	private String title;
	private String description;
	private String avatar = "default-avatar.png";
	
	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getAvatar() {
		return avatar;
	}
	
	public Playlist updatePlaylist(Playlist playlist) {
		playlist.setTitle(title);
		playlist.setDescription(description);
		playlist.setAvatar(avatar);
		
		return playlist;
	}

}
