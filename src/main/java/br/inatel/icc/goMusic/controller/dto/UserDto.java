package br.inatel.icc.goMusic.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.inatel.icc.goMusic.model.User;

public class UserDto {

	private Long id;
	private String name;
	private String avatar;
	private String country;

	private int totalFollowers;
	private int totalFollowings;
	
	private int totalPlaylists;
	private int totalCreatedPlaylists;
	private int totalLikedPlaylists;

	public UserDto(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.avatar = user.getAvatar();
		this.country = user.getCountry();
		this.totalFollowers = user.getFollowers().size();
		this.totalFollowings = user.getFollowings().size();
		this.totalPlaylists = user.getMyPlaylists().size() + user.getLikedPlaylists().size();
		this.totalCreatedPlaylists = user.getMyPlaylists().size();
		this.totalLikedPlaylists = user.getLikedPlaylists().size();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAvatar() {
		return avatar;
	}

	public String getCountry() {
		return country;
	}

	public int getTotalFollowers() {
		return totalFollowers;
	}

	public int getTotalFollowings() {
		return totalFollowings;
	}

	public int getTotalPlaylists() {
		return totalPlaylists;
	}

	public int getTotalCreatedPlaylists() {
		return totalCreatedPlaylists;
	}

	public int getTotalLikedPlaylists() {
		return totalLikedPlaylists;
	}

	public static List<UserDto> convertToDtoList(List<User> users) {
		List<UserDto> usersDto = users.stream().map(UserDto::new).collect(Collectors.toList());
		return usersDto;
	}

	public static Page<UserDto> convertToDtoPage(Page<User> users) {
		Page<UserDto> usersDto = users.map(UserDto::new);
		return usersDto;
	}

}
