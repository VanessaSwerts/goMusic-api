package br.inatel.icc.goMusic.config.externalApi;

import br.inatel.icc.goMusic.domain.Artists;
import br.inatel.icc.goMusic.domain.Tracks;

public interface APIService {

	Artists searchArtist(String artistName) throws Exception;

	Tracks searchTrack(String trackName) throws Exception;

}
