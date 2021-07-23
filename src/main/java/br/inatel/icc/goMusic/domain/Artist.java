package br.inatel.icc.goMusic.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Artist {

	private Integer id;
	private String name;
	private String link;
	private String picture;
	private Integer nb_album;
	private String type;

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getLink() {
		return link;
	}

	public String getPicture() {
		return picture;
	}

	public Integer getNb_album() {
		return nb_album;
	}

	public String getType() {
		return type;
	}

}
