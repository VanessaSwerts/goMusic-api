package br.inatel.icc.goMusic.config.externalApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.inatel.icc.goMusic.model.Artists;
import br.inatel.icc.goMusic.model.Tracks;
import br.inatel.icc.goMusic.util.UrlConfigs;

@Service("apiService")
public class APIServiceConfigs implements APIService {

	private RestTemplate restTemplate;
	
	@Autowired
	public APIServiceConfigs() {
		this.restTemplate = new RestTemplate();
	}
	
	@Override
    public Artists searchArtist(String userInput) throws Exception {
        String urlQuery = UrlConfigs.buildArtistSearchQuery(userInput);
        
        return restTemplate.getForObject(urlQuery, Artists.class);
    }

	@Override
	public Tracks searchTrack(String userInput) throws Exception {
		String urlQuery = UrlConfigs.buildTrackSearchQuery(userInput);

        return restTemplate.getForObject(urlQuery, Tracks.class);		
	}

}
