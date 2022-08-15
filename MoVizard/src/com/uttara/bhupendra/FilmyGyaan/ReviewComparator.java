package com.uttara.bhupendra.FilmyGyaan;

import java.util.Comparator;

/**
 * Represent a comparator to compare MovieBeanIO objects.
 * 
 * @author Bhupendra Singh
 *
 */
public class ReviewComparator implements Comparator<MovieBeanIO> {

	/**
	 * Compares two MovieBeanIO objects according to their review.
	 */
	@Override
	public int compare(MovieBeanIO o1, MovieBeanIO o2) {
		return o1.getReview().compareTo(o2.getReview());
	}
	
}
