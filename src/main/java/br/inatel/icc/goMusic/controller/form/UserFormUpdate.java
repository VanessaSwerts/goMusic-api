package br.inatel.icc.goMusic.controller.form;

import br.inatel.icc.goMusic.model.User;

public class UserFormUpdate {

	private String name;

	private String country;

	public String getName() {
		return name;
	}

	public String getCountry() {
		return country;
	}

	public User updateUser(User user) {
		if (name != null)
			if (name.length() != 0)
				user.setName(name);

		if (country != null)
			if (country.length() != 0)
				user.setCountry(country);

		return user;
	}
}
