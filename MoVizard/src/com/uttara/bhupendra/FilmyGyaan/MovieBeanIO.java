package com.uttara.bhupendra.FilmyGyaan;

/**
 * Represents a MovieBeanIO.
 * 
 * @author 	Bhupendra Singh
 *
 */
public class MovieBeanIO implements Comparable<MovieBeanIO>{

	/**
	 * State of the MovieBeanIO object.
	 */
	private String movieName;
	
	/**
	 * State of the MovieBeanIO object.
	 */
	private String directorName;
	
	/**
	 * State of the MovieBeanIO object.
	 */
	private String producerName;
	
	/**
	 * State of the MovieBeanIO object.
	 */
	private int rating;
	
	/**
	 * State of the MovieBeanIO object.
	 */
	private String review;

	/**
	 * Initializes a newly created MovieBeanIO object and assigns default values to the respective
	 * states.
	 */
	public MovieBeanIO() {}

	/**
	 * Initializes a newly created MovieBeanIO object and assigns the parameters to respective
	 * states.
	 */
	public MovieBeanIO(String movieName, String directorName, String producerName, int rating, String review) {
		this.movieName = movieName;
		this.directorName = directorName;
		this.producerName = producerName;
		this.rating = rating;
		this.review = review;
	}

	/**
	 * Returns the {@code movieName} state of that MovieBeanIO object.
	 * 
	 * @return	name of the movie.
	 */
	public String getMovieName() {
		return movieName;
	}

	/**
	 * Assigns the passed parameter to the {@literal movieName} state of the MovieBeanIO
	 * object.
	 * 
	 * @param movieName	name of the movie.
	 */
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	
	/**
	 * Returns the {@code directorName} state of that MovieBeanIO object.
	 * 
	 * @return	name of the director.
	 */
	public String getDirectorName() {
		return directorName;
	}

	/**
	 * Assigns the passed parameter to the {@literal directorName} state of the MovieBeanIO
	 * object.
	 * 
	 * @param directorName	name of the director.
	 */
	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}

	/**
	 * Returns {@code producerName} state of that MovieBeanIO object.
	 * 
	 * @return	name of the producer.
	 */
	public String getProducerName() {
		return producerName;
	}
	
	/**
	 * Assigns the passed parameter to the {@literal producerName} state of the MovieBeanIO
	 * object.
	 * 
	 * @param producerName	name of the producer.
	 */
	public void setProducerName(String producerName) {
		this.producerName = producerName;
	}
	
	/**
	 * Returns the {@code rating} state of that MovieBeanIO object.
	 * 
	 * @return	rating of this MovieBeanIO object
	 */
	public int getRating() {
		return rating;
	}
	
	/**
	 * Assigns the passed parameter to the {@literal rating} state of the MovieBeanIO
	 * object.
	 * 
	 * @param rating	rating of the movie.
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	/**
	 * Returns the {@code review} state of that MovieBeanIO object.
	 * 
	 * @return	review state of the MovieBeanIO object.
	 */
	public String getReview() {
		return review;
	}
	
	/**
	 * Assigns the passed parameter to the {@literal review} state of the MovieBeanIO
	 * object.
	 * 
	 * @param review review of the movie.
	 */
	public void setReview(String review) {
		this.review = review;
	}
	
	/**
	 * Returns a hash code value for the MovieBeanIO object.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((directorName == null) ? 0 : directorName.hashCode());
		result = prime * result + ((movieName == null) ? 0 : movieName.hashCode());
		result = prime * result + ((producerName == null) ? 0 : producerName.hashCode());
		result = prime * result + rating;
		result = prime * result + ((review == null) ? 0 : review.hashCode());
		return result;
	}

	/**
	 * Compares all the states of two MovieBeanIO objects.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MovieBeanIO other = (MovieBeanIO) obj;
		if (directorName == null) {
			if (other.directorName != null)
				return false;
		} else if (!directorName.equals(other.directorName))
			return false;
		if (movieName == null) {
			if (other.movieName != null)
				return false;
		} else if (!movieName.equals(other.movieName))
			return false;
		if (producerName == null) {
			if (other.producerName != null)
				return false;
		} else if (!producerName.equals(other.producerName))
			return false;
		if (rating != other.rating)
			return false;
		if (review == null) {
			if (other.review != null)
				return false;
		} else if (!review.equals(other.review))
			return false;
		return true;
	}

	/**
	 * Returns a string representation of this MovieBeanIO object.
	 */
	@Override
	public String toString() {
		return "Movie name - " + movieName + ", Director name - " + directorName + ", Producer name - "
				+ producerName + ", Rating - " + rating + ", review - " + review;
	}

	/**
	 * Compares two MovieBeanIO objects according to their {@code movieName} states.
	 */
	@Override
	public int compareTo(MovieBeanIO m) {
		return this.getMovieName().compareTo(m.getMovieName());
	}
}
