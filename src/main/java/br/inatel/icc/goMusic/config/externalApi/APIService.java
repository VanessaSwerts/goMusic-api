package br.inatel.icc.goMusic.config.externalApi;

import br.inatel.icc.goMusic.model.Artists;
import br.inatel.icc.goMusic.model.Tracks;

public interface APIService {

	Artists searchArtist(String artistName) throws Exception;

	Tracks searchTrack(String trackName) throws Exception;

}
