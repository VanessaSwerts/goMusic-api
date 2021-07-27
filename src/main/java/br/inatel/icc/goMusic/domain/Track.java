package br.inatel.icc.goMusic.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.inatel.icc.goMusic.config.externalApi.APIServiceConfigs;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Track {

	private Integer id;
	private String title;
	private String link;
	private Integer duration;
	private Artist artist;
	private Album album;
	
	private APIServiceConfigs apiService = new APIServiceConfigs();

	public List<Track> requestTrackById(List<Integer> myTracksId) {
		List<Track> myTracks = new ArrayList<>();

		myTracksId.forEach(id -> {
			try {
				myTracks.add(apiService.getTrackById(id));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		return myTracks;
	}

	public Integer getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getLink() {
		return link;
	}

	public Integer getDuration() {
		return duration;
	}

	public Artist getArtist() {
		return artist;
	}

	public Album getAlbum() {
		return album;
	}

}
