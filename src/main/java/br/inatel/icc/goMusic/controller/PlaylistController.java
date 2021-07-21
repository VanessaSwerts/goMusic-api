package br.inatel.icc.goMusic.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.inatel.icc.goMusic.controller.dto.PlaylistDto;
import br.inatel.icc.goMusic.controller.form.PlaylistForm;
import br.inatel.icc.goMusic.controller.form.PlaylistFormUpdate;
import br.inatel.icc.goMusic.model.Playlist;
import br.inatel.icc.goMusic.model.User;
import br.inatel.icc.goMusic.repository.PlaylistRepository;
import br.inatel.icc.goMusic.repository.UserRepository;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

	private PlaylistRepository playlistRepository;
	private UserRepository userRepository;

	@Autowired
	public PlaylistController(PlaylistRepository playlistRepository, UserRepository userRepository) {
		this.playlistRepository = playlistRepository;
		this.userRepository = userRepository;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<PlaylistDto> create(Authentication authentication, @RequestBody @Valid PlaylistForm form,
			UriComponentsBuilder uriBuilder) {

		User authenticatedUser = (User) authentication.getPrincipal();
		User userLogged = userRepository.getById(authenticatedUser.getId());
		form.setOwner(userLogged);

		Playlist newPlaylist = form.convertToPlaylist();
		playlistRepository.save(newPlaylist);

		URI uri = uriBuilder.path("/playlists/{id}").buildAndExpand(newPlaylist.getId()).toUri();
		return ResponseEntity.created(uri).body(new PlaylistDto(newPlaylist));
	}

	@GetMapping("/{id}")
	public ResponseEntity<PlaylistDto> list(@PathVariable("id") Long id) {

		Optional<Playlist> optionalPlaylist = playlistRepository.findById(id);

		if (optionalPlaylist.isPresent()) {
			PlaylistDto playlistDto = new PlaylistDto(optionalPlaylist.get());

			return ResponseEntity.ok().body(playlistDto);
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<PlaylistDto> update(Authentication authentication, @PathVariable("id") Long id,
			@RequestBody @Valid PlaylistFormUpdate form) {

		User authenticatedUser = (User) authentication.getPrincipal();
		Long authenticatedUserId = authenticatedUser.getId();

		Optional<Playlist> optionalPlaylist = playlistRepository.findById(id);

		if (optionalPlaylist.isPresent()) {

			if (authenticatedUserId != optionalPlaylist.get().getOwner().getId()) {
				return ResponseEntity.status(403).build();
			}

			Playlist updatedPlaylist = form.updatePlaylist(optionalPlaylist.get());
			return ResponseEntity.ok(new PlaylistDto(updatedPlaylist));
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(Authentication authentication, @PathVariable("id") Long id) {

		User authenticatedUser = (User) authentication.getPrincipal();
		Long authenticatedUserId = authenticatedUser.getId();

		Optional<Playlist> optionalPlaylist = playlistRepository.findById(id);

		if (optionalPlaylist.isPresent()) {

			if (authenticatedUserId != optionalPlaylist.get().getOwner().getId()) {
				return ResponseEntity.status(403).build();
			}

			playlistRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.status(404).build();
	}

}
