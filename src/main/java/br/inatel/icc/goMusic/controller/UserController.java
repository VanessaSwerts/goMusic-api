package br.inatel.icc.goMusic.controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import br.inatel.icc.goMusic.config.cloudinary.CloudinaryService;
import br.inatel.icc.goMusic.controller.dto.PlaylistDto;
import br.inatel.icc.goMusic.controller.dto.UserDto;
import br.inatel.icc.goMusic.controller.form.UserForm;
import br.inatel.icc.goMusic.controller.form.UserFormUpdate;
import br.inatel.icc.goMusic.model.Follow;
import br.inatel.icc.goMusic.model.User;
import br.inatel.icc.goMusic.repository.FollowRepository;
import br.inatel.icc.goMusic.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@Slf4j
@AllArgsConstructor(onConstructor = @__({ @Autowired }))
public class UserController {

	private UserRepository userRepository;
	private FollowRepository followRepository;
	private CloudinaryService cloudinaryService;

	@PostMapping
	@Transactional
	public ResponseEntity<UserDto> create(@RequestBody @Valid UserForm form, UriComponentsBuilder uriBuilder)
			throws IOException {

		String avatar = cloudinaryService.getCloudinaryDefault() + "/user/" + "default-avatar.png";
		form.setAvatar(avatar);

		Optional<User> emailsExists = userRepository.findByEmail(form.getEmail());
		if (emailsExists.isPresent()) {
			log.warn("User tried to create an account with an email that already exists!");
			return ResponseEntity.status(403).build();
		}

		User newUser = form.convertToUser();
		userRepository.save(newUser);

		log.info("Created a new user account with ID: " + newUser.getId());

		URI uri = uriBuilder.path("/users/{id}").buildAndExpand(newUser.getId()).toUri();
		return ResponseEntity.created(uri).body(new UserDto(newUser));
	}

	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity<UserDto> list(@PathVariable("id") Long id) {

		Optional<User> optionalUser = userRepository.findById(id);

		if (optionalUser.isPresent()) {
			log.info("List user data with ID: " + id);
			return ResponseEntity.ok(new UserDto(optionalUser.get()));
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping()
	@Transactional
	public ResponseEntity<UserDto> update(Authentication authentication, @RequestBody @Valid UserFormUpdate form) {
		User authenticatedUser = (User) authentication.getPrincipal();
		Long authenticatedUserId = authenticatedUser.getId();

		Optional<User> optionalUser = userRepository.findById(authenticatedUserId);

		if (optionalUser.isPresent()) {
			User updatedUser = form.updateUser(optionalUser.get());

			log.info("Updated user data with ID: " + authenticatedUserId);
			return ResponseEntity.ok(new UserDto(updatedUser));
		}

		return ResponseEntity.notFound().build();
	}

	@SuppressWarnings("rawtypes")
	@PutMapping("/avatar")
	@Transactional
	public ResponseEntity<UserDto> updateAvatar(Authentication authentication, @RequestParam("file") MultipartFile file)
			throws IOException {
		User authenticatedUser = (User) authentication.getPrincipal();
		Long authenticatedUserId = authenticatedUser.getId();

		Optional<User> optionalUser = userRepository.findById(authenticatedUserId);

		if (optionalUser.isPresent()) {
			Map uploadResult = cloudinaryService.upload(file, "user");
			String avatar = uploadResult.get("public_id").toString() + "." + uploadResult.get("format").toString();

			optionalUser.get().setAvatar(avatar);
			log.info("Updated user avatar with ID: " + authenticatedUserId);
			return ResponseEntity.ok(new UserDto(optionalUser.get()));
		}

		return ResponseEntity.status(404).build();
	}

	@DeleteMapping()
	@Transactional
	public ResponseEntity<?> delete(Authentication authentication) {
		User authenticatedUser = (User) authentication.getPrincipal();
		Long authenticatedUserId = authenticatedUser.getId();

		Optional<User> optionalUser = userRepository.findById(authenticatedUserId);

		if (optionalUser.isPresent()) {

			optionalUser.get().getFollowers().clear();
			optionalUser.get().getFollowings().clear();
			optionalUser.get().getLikedPlaylists().clear();

			userRepository.deleteById(authenticatedUserId);

			log.info("Deleted user account with ID: " + authenticatedUserId);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

	@GetMapping("/{id}/followers")
	@Transactional
	public ResponseEntity<List<UserDto>> listFollowers(@PathVariable("id") Long id) {
		Optional<User> optionalUser = userRepository.findById(id);

		if (optionalUser.isPresent()) {
			List<UserDto> followersList = UserDto.convertToDtoList(optionalUser.get().getFollowers());

			log.info("List user's followers with ID: " + id);
			return ResponseEntity.ok(followersList);
		}

		return ResponseEntity.notFound().build();
	}

	@GetMapping("/{id}/followings")
	@Transactional
	public ResponseEntity<List<UserDto>> listFollowings(@PathVariable("id") Long id) {
		Optional<User> optionalUser = userRepository.findById(id);

		if (optionalUser.isPresent()) {
			List<UserDto> followingsList = UserDto.convertToDtoList(optionalUser.get().getFollowings());

			log.info("List user's followings with ID: " + id);
			return ResponseEntity.ok(followingsList);
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("/follow/{id}")
	@Transactional
	public ResponseEntity<?> addFollow(Authentication authentication, @PathVariable("id") Long id) {
		User authenticatedUser = (User) authentication.getPrincipal();
		User userLogged = userRepository.getById(authenticatedUser.getId());

		Optional<User> userToFollow = userRepository.findById(id);

		if (userToFollow.isPresent()) {
			Optional<Follow> isFollowing = followRepository.findByFollowingAndFollower(userLogged, userToFollow.get());

			if (isFollowing.isPresent() || userLogged.getId() == userToFollow.get().getId()) {
				return ResponseEntity.status(403).build();
			}

			Follow newFollow = new Follow(userLogged, userToFollow.get());
			followRepository.save(newFollow);

			log.info("User with ID: " + userLogged.getId() + "follows user with ID: " + userToFollow.get().getId());
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/unfollow/{id}")
	@Transactional
	public ResponseEntity<?> unfollow(Authentication authentication, @PathVariable("id") Long id) {
		User authenticatedUser = (User) authentication.getPrincipal();
		User userLogged = userRepository.getById(authenticatedUser.getId());

		Optional<User> userToUnfollow = userRepository.findById(id);

		if (userToUnfollow.isPresent()) {
			Optional<Follow> isFollowing = followRepository.findByFollowingAndFollower(userLogged,
					userToUnfollow.get());

			if (!isFollowing.isPresent()) {
				return ResponseEntity.status(403).build();
			}

			followRepository.deleteById(isFollowing.get().getId());

			log.info("User with ID: " + userLogged.getId() + "unfollows user with ID: " + userToUnfollow.get().getId());
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

	@GetMapping("/{id}/playlists")
	@Cacheable(value = "userPlaylists")
	public ResponseEntity<List<PlaylistDto>> listUserPlaylists(@PathVariable("id") Long id) {
		Optional<User> optionalUser = userRepository.findById(id);

		if (optionalUser.isPresent()) {
			List<PlaylistDto> playlists = PlaylistDto.concatTwoDtoList(optionalUser.get().getMyPlaylists(),
					optionalUser.get().getLikedPlaylists());

			log.info("List all user playlists");
			return ResponseEntity.ok().body(playlists);
		}

		return ResponseEntity.status(404).build();
	}

	@GetMapping("/{id}/playlistsCreated")
	public ResponseEntity<List<PlaylistDto>> listUserPlaylistsCreated(@PathVariable("id") Long id) {
		Optional<User> optionalUser = userRepository.findById(id);

		if (optionalUser.isPresent()) {
			List<PlaylistDto> playlistsCreated = PlaylistDto.convertToDtoList(optionalUser.get().getMyPlaylists());

			log.info("List all user created playlists");
			return ResponseEntity.ok().body(playlistsCreated);
		}

		return ResponseEntity.status(404).build();
	}

	@GetMapping("/{id}/playlistsLiked")
	public ResponseEntity<List<PlaylistDto>> listUserPlaylistsLiked(@PathVariable("id") Long id) {
		Optional<User> optionalUser = userRepository.findById(id);

		if (optionalUser.isPresent()) {
			List<PlaylistDto> playlistsLiked = PlaylistDto.convertToDtoList(optionalUser.get().getLikedPlaylists());

			log.info("List all user created playlists");
			return ResponseEntity.ok().body(playlistsLiked);
		}

		return ResponseEntity.status(404).build();
	}

}
