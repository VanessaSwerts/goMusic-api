package br.inatel.icc.goMusic.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.inatel.icc.goMusic.domain.Track;
import br.inatel.icc.goMusic.model.Playlist;
import br.inatel.icc.goMusic.model.Songs;

public class SearchForm {

	@NotNull
	@NotEmpty
	private String title;

	public String getTitle() {
		return title;
	}

	public Songs convertToSong(Track track, Playlist playlist) {
		return new Songs(track.getId(), track.getTitle(), track.getDuration(), track.getLink(),
				track.getAlbum().getTitle(), track.getArtist().getName(), playlist);
	}

}
