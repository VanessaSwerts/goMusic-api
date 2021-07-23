package br.inatel.icc.goMusic.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Track {

	private Integer id;
	private String title;
	private String link;
	private Integer duration;
	private Artist artist;
	private Album album;

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
