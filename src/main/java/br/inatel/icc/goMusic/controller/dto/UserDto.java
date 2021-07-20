package br.inatel.icc.goMusic.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.inatel.icc.goMusic.model.User;

public class UserDto {

	private Long id;
	private String name;
	private String avatar;
	private String country;

	private int totalFollowers;
	private int totalFollowings;

	public UserDto(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.avatar = user.getAvatar();
		this.country = user.getCountry();
		this.totalFollowers = user.getFollowers().size();
		this.totalFollowings = user.getFollowings().size();
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
	
	public static List<UserDto> convertToDtoList(List<User> users){
		List<UserDto> usersDto = users.stream().map(UserDto::new).collect(Collectors.toList());
		return usersDto;
	}

}
