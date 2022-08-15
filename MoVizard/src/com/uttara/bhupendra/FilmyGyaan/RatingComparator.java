package com.uttara.bhupendra.FilmyGyaan;

import java.util.Comparator;

/**
 * Represent a comparator to compare MovieBeanIO objects.
 * 
 * @author Bhupendra Singh
 *
 */
public class RatingComparator implements Comparator<MovieBeanIO> {

	/**
	 * Compares two MovieBeanIO objects according to their rating.
	 */
	public int compare(MovieBeanIO o1, MovieBeanIO o2) {
		return o2.getRating() - o1.getRating();
	}
	
}
