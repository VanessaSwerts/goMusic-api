package br.inatel.icc.goMusic.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UrlConfigs {

	private static final String BASIC_SEARCH_URL = "https://api.deezer.com/search?q=";
	private static final String ARTIST_SEARCH_URL = "https://api.deezer.com/search/artist?q=";
	private static final String TRACK_SEARCH_URL = "https://api.deezer.com/search/track?q=";

	public static String buildSearchQuery(String userInput) {
		return BASIC_SEARCH_URL + userInput;
	}

	public static String buildArtistSearchQuery(String userInput) {
		return getSearchQuery(userInput, ARTIST_SEARCH_URL);
	}

	public static String buildTrackSearchQuery(String userInput) {
		return getSearchQuery(userInput, TRACK_SEARCH_URL);
	}

	private static String getSearchQuery(final String userIn, final String concat) {
		StringBuilder queryBuilder = new StringBuilder(concat);

		try {
			queryBuilder.append(URLEncoder.encode(userIn, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return queryBuilder.toString();
	}

}
