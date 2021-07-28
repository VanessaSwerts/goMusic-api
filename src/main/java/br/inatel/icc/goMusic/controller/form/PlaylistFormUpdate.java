package br.inatel.icc.goMusic.controller.form;

import br.inatel.icc.goMusic.model.Playlist;

public class PlaylistFormUpdate {

	private String title;

	private String description;

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Playlist updatePlaylist(Playlist playlist) {
		if (title != null)
			if (title.length() != 0)
				playlist.setTitle(title);

		if (description != null)
			if (description.length() != 0)
				playlist.setDescription(description);

		return playlist;
	}

}
