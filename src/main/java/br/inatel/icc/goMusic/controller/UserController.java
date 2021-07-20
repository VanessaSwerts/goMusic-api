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

import br.inatel.icc.goMusic.controller.dto.UserDto;
import br.inatel.icc.goMusic.controller.form.UserForm;
import br.inatel.icc.goMusic.controller.form.UserFormUpdate;
import br.inatel.icc.goMusic.model.User;
import br.inatel.icc.goMusic.repository.FollowRepository;
import br.inatel.icc.goMusic.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserRepository userRepository;
	private FollowRepository followRepository;

	@Autowired
	public UserController(UserRepository userRepository, FollowRepository followRepository) {
		this.userRepository = userRepository;
		this.followRepository = followRepository;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<UserDto> create(@RequestBody @Valid UserForm form, UriComponentsBuilder uriBuilder) {
		User newUser = form.convertToUser();
		userRepository.save(newUser);

		URI uri = uriBuilder.path("/users/{id}").buildAndExpand(newUser.getId()).toUri();
		return ResponseEntity.created(uri).body(new UserDto(newUser));
	}

	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity<UserDto> list(@PathVariable("id") Long id) {
		Optional<User> optionalUser = userRepository.findById(id);

		if (optionalUser.isPresent()) {
			return ResponseEntity.ok(new UserDto(optionalUser.get()));
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<UserDto> update(@PathVariable("id") Long id, @RequestBody @Valid UserFormUpdate form) {
		Optional<User> optionalUser = userRepository.findById(id);

		if (optionalUser.isPresent()) {
			User updatedUser = form.updateUser(optionalUser.get());
			return ResponseEntity.ok(new UserDto(updatedUser));
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		Optional<User> optionalUser = userRepository.findById(id);

		if (optionalUser.isPresent()) {
			userRepository.deleteById(id);
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
			return ResponseEntity.ok(followingsList);
		}

		return ResponseEntity.notFound().build();
	}

}
