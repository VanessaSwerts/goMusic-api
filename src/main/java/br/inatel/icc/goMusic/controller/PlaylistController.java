package br.inatel.icc.goMusic.controller;

import java.net.URI;
import java.util.List;
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
import br.inatel.icc.goMusic.controller.dto.UserDto;
import br.inatel.icc.goMusic.controller.form.PlaylistForm;
import br.inatel.icc.goMusic.controller.form.PlaylistFormUpdate;
import br.inatel.icc.goMusic.model.Like;
import br.inatel.icc.goMusic.model.Playlist;
import br.inatel.icc.goMusic.model.User;
import br.inatel.icc.goMusic.repository.LikeRepository;
import br.inatel.icc.goMusic.repository.PlaylistRepository;
import br.inatel.icc.goMusic.repository.UserRepository;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

	private PlaylistRepository playlistRepository;
	private UserRepository userRepository;
	private LikeRepository likeRepository;

	@Autowired
	public PlaylistController(PlaylistRepository playlistRepository, UserRepository userRepository,
			LikeRepository likeRepository) {
		this.playlistRepository = playlistRepository;
		this.userRepository = userRepository;
		this.likeRepository = likeRepository;
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

	@GetMapping("/{id}/likes")
	@Transactional
	public ResponseEntity<List<UserDto>> listLikes(@PathVariable("id") Long id) {

		Optional<Playlist> optionalPlaylist = playlistRepository.findById(id);

		if (optionalPlaylist.isPresent()) {
			System.out.println(optionalPlaylist.get().getLikes());

			List<UserDto> likesList = UserDto.convertToDtoList(optionalPlaylist.get().getLikes());
			return ResponseEntity.ok(likesList);
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("/liked/{id}")
	@Transactional
	public ResponseEntity<?> addLike(Authentication authentication, @PathVariable("id") Long id) {

		User authenticatedUser = (User) authentication.getPrincipal();
		User userLogged = userRepository.getById(authenticatedUser.getId());

		Optional<Playlist> optionalPlaylistToLike = playlistRepository.findById(id);

		if (optionalPlaylistToLike.isPresent()) {
			Optional<Like> isLiked = likeRepository.findByPlaylistAndUser(optionalPlaylistToLike.get(), userLogged);

			if (isLiked.isPresent()) {
				return ResponseEntity.status(202).build();
			}

			Like newLike = new Like(optionalPlaylistToLike.get(), userLogged);
			likeRepository.save(newLike);

			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/unliked/{id}")
	@Transactional
	public ResponseEntity<?> removeLike(Authentication authentication, @PathVariable("id") Long id) {
		User authenticatedUser = (User) authentication.getPrincipal();
		User userLogged = userRepository.getById(authenticatedUser.getId());

		Optional<Playlist> optionalPlaylistToUnliked = playlistRepository.findById(id);

		if (optionalPlaylistToUnliked.isPresent()) {
			Optional<Like> isLiked = likeRepository.findByPlaylistAndUser(optionalPlaylistToUnliked.get(), userLogged);

			if (isLiked.isEmpty()) {
				return ResponseEntity.status(403).build();
			}

			likeRepository.deleteById(isLiked.get().getId());

			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

}
