package com.uttara.bhupendra.FilmyGyaan;

import java.util.Comparator;

/**
 * Represent a comparator to compare MovieBeanIO objects.
 * 
 * @author Bhupendra Singh
 *
 */
public class ProducerNameComparator implements Comparator<MovieBeanIO> {

	/**
	 * Compares two MovieBeanIO objects according to their producerName.
	 */
	@Override
	public int compare(MovieBeanIO o1, MovieBeanIO o2) {
	
		return o1.getProducerName().compareTo(o2.getProducerName());
	}
}
