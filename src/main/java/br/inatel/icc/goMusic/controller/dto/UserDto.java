package br.inatel.icc.goMusic.controller.dto;

import br.inatel.icc.goMusic.model.User;

public class UserDto {

	private Long id;
	private String name;
	private String email;
	private String avatar;

	public UserDto(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.avatar = user.getAvatar();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getAvatar() {
		return avatar;
	}

}
