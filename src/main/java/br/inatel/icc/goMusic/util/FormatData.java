package br.inatel.icc.goMusic.util;

public class FormatData {

	public static String formatDuration(int duration) {
		String result = "";
		
		int minutes = duration / 60;
		int seconds = duration % 60;
		result += minutes + ":" + seconds;

		return result;
	}

}
