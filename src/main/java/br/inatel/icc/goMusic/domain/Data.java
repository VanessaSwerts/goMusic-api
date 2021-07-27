package br.inatel.icc.goMusic.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Data<T> {

	private Integer total;
	private List<T> data;

	public Data() {
	}

	public Data(List<T> data) {
		this.data = data;
	}

	public Integer getTotal() {
		return total;
	}

	public List<T> getData() {
		return data;
	}

}
