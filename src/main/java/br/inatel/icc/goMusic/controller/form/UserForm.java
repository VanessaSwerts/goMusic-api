package br.inatel.icc.goMusic.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.inatel.icc.goMusic.model.User;

public class UserForm {

	@NotNull
	@NotEmpty
	@Length(min = 3)
	private String name;

	@NotNull
	@NotEmpty
	@Length(min = 10)
	private String email;

	@NotNull
	@NotEmpty
	@Length(min = 8)
	private String password;

	private String avatar = "default-avatar.png";

	@NotNull
	@NotEmpty
	private String country;

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getAvatar() {
		return avatar;
	}

	public String getCountry() {
		return country;
	}

	public User convertToUser() {
		return new User(name, email, encryptPassword(), avatar, country);
	}

	private String encryptPassword() {
		return new BCryptPasswordEncoder().encode(password);

	}

}
