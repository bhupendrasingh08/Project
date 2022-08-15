package com.uttara.bhupendra.FilmyGyaan;

/**
 * Represents a class with static methods to validate the respective MovieBeanIO object
 * states.
 * 
 * @author Bhupendra Singh
 *
 */
public class MovieUtilIO {

	public static boolean validateName(String name) {
		if(name == null) {
			return false;
		}
		if(name.trim().equals("")) {
			return false;
		}
		if(!Character.isLetter(name.charAt(0))) {
			return false;
		}
		for(int i = 1; i < name.length(); i++) {
			char c = name.charAt(i);
			if(!(Character.isDigit(c) || Character.isLetter(c) || Character.isWhitespace(c))) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean validateRating(int rate) {
		if(rate < 0) {
			return false;
		}
		if(rate > 10) {
			return false;
		}
		return true;
	}
	
	public static boolean validateWord(String word) {
		if(word == null) {
			return false;
		}
		if(word.trim().equals("")) {
			return false;
		}
		if(!Character.isLetter(word.charAt(0))) {
			return false;
		}
		for(int i = 1; i < word.length(); i++) {
			char c = word.charAt(i);
			if(!Character.isLetter(c)) {
				return false;
			}
			else {
				continue;
			}
		}
		return true;
	}
}