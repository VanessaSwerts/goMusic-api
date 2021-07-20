package br.inatel.icc.goMusic.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.inatel.icc.goMusic.model.User;

public class UserFormUpdate {
	
	@NotNull
	@NotEmpty
	@Length(min = 3)
	private String name;
	
	@NotNull
	@NotEmpty
	private String country;
	
	public String getName() {
		return name;
	}
	
	public String getCountry() {
		return country;
	}
	
	public User updateUser(User user) {
		user.setName(name);
		user.setCountry(country);
		return user;
	}
}
