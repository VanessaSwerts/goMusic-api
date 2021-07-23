package br.inatel.icc.goMusic.util;

public class FormatData {

	public String formatDuration(int duration) {
		String result = "";
		
		int minutes = duration / 60;
		int seconds = duration % 60;
		result += minutes + " min and " + seconds + " sec";

		return result;
	}

}
