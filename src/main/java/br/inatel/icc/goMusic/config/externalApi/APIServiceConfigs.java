package br.inatel.icc.goMusic.config.externalApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.inatel.icc.goMusic.domain.Artists;
import br.inatel.icc.goMusic.domain.Tracks;
import br.inatel.icc.goMusic.util.UrlConfigs;

@Service("apiService")
public class APIServiceConfigs {

	private RestTemplate restTemplate;
	
	@Autowired
	public APIServiceConfigs() {
		this.restTemplate = new RestTemplate();
	}
	
    public Artists searchArtist(String userInput) throws Exception {
        String urlQuery = UrlConfigs.buildArtistSearchQuery(userInput);
        
        return restTemplate.getForObject(urlQuery, Artists.class);
    }

	public Tracks searchTrack(String userInput) throws Exception {
		String urlQuery = UrlConfigs.buildTrackSearchQuery(userInput);

        return restTemplate.getForObject(urlQuery, Tracks.class);		
	}

}
