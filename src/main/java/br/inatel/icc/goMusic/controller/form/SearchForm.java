package br.inatel.icc.goMusic.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SearchForm {

	@NotNull
	@NotEmpty
	private String title;

	public String getTitle() {
		return title;
	}

}
