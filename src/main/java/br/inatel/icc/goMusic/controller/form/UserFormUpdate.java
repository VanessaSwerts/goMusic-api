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
	
	public String getName() {
		return name;
	}
	
	public User updateUser(User user) {
		user.setName(name);
		return user;
	}
}
