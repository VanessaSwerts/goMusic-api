package br.inatel.icc.goMusic.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.inatel.icc.goMusic.controller.dto.SongsDto;
import br.inatel.icc.goMusic.model.Songs;
import br.inatel.icc.goMusic.repository.SongsRepository;

@RestController
@RequestMapping("/songs")
public class SongsController {

	private SongsRepository songsrepository;

	@Autowired
	public SongsController(SongsRepository songsrepository) {
		this.songsrepository = songsrepository;
	}

	@GetMapping("/{id}")
	public ResponseEntity<SongsDto> list(@PathVariable("id") Long id) {

		Optional<Songs> optionalSongs = songsrepository.findById(id);

		if (optionalSongs.isPresent()) {
			SongsDto songsDto = new SongsDto(optionalSongs.get());

			return ResponseEntity.ok().body(songsDto);
		}

		return ResponseEntity.notFound().build();
	}

	@GetMapping()
	public ResponseEntity<List<SongsDto>> listAll() {

		List<Songs> songs = songsrepository.findAll();
		return ResponseEntity.ok().body(SongsDto.convertToDtoList(songs));

	}

}
