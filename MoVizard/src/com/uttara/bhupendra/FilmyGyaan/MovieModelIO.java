package com.uttara.bhupendra.FilmyGyaan;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

/**
 * MovieModelIO implements different methods to perform different operations for the
 * FilmyGyaan application.
 * 
 * @author Bhupendra Singh
 *
 */
public class MovieModelIO {
	
	private String absPath = System.getProperty("user.home") + "/Desktop";
	private String dirPath = absPath + "/FilmyGyaan/";
	
	/**
	 * Constructs a MovieModelIO object with no states.
	 */
	public MovieModelIO() {}
	
	/**
	 * Creates a directory on the desktop of the system.
	 * 
	 * @return	{@code true} if the directory is created, otherwise {@code false}.
	 */
	public boolean makeDirectory() {
		File f = new File(absPath + "/FilmyGyaan");
		if(!f.exists()) {
			f.mkdir();
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Checks whether the name of the movie exists in the file specified by the name 
	 * of the list as abstract path.
	 * 
	 * @param	listName name of the movie wish list.
	 * @param	movieName name of the movie(State of MovieBeanIO object).
	 * @return	{@code true} if the movie name already exists in the file, 
	 * 			{@code false} otherwise.
	 */
	public boolean duplicateName(String listName, String movieName) {
		String path = dirPath  + listName + ".txt";
		File f = new File(path);
		MovieBeanIO bean = null;
		if(f.exists()) {
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(f));
				String str;
				while((str = br.readLine()) != null) {
					String[] s = str.split(":");
					bean = new MovieBeanIO(s[0], s[1], s[2], Integer.parseInt(s[3]), s[4]);
					if(bean.getMovieName().equals(movieName)) {
						return false;
					}
					else {
						continue;
					}
				}
				return true;
			}
			catch(Exception e) {
				
			}
			finally {
				if(br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Checks if the file specified by the name of the wish list as abstract path 
	 * already exists or not.
	 * 
	 * @param	list	name of the movie wish list also specifies the abstract path.
	 * @return	{@code true} if the file exits, and {@code false} otherwise.
	 */
	public boolean checkIfListExists(String list) {
		String path = dirPath + list + ".txt";
		File f = new File(path);
		if(f.exists()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Checks if the file specified by the name of the movie wish list exists, if not
	 * it creates a file in the specified directory.
	 * 
	 * @param	list name of the movie wish list also specifies the abstract path.
	 * @return	{@code true} if the file exits, and {@code false} otherwise.
	 */
	public boolean checkIfMovieWishListExistsToCreate(String list) {
		String path = dirPath + list + ".txt";
		File f = new File(path);
		if(f.exists()) {
			return true;
		}
		else {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		}
	}
	
	/**
	 * Appends the file specified by the abstract pathname which is specified by the name 
	 * of the movie wish list with the MovieBeanIO instance.
	 * 
	 * @param	bean		object of type MovieBeanIO.
	 * @param	listName	name of the movie wish list.
	 * @return	"success" if the object is successfully written to the file, otherwise, 
	 * 			"File not found".
	 */
	public String addMovie(MovieBeanIO bean, String listName) {
		String path = dirPath + listName + ".txt";
		File f = new File(path);
		if(f.exists()) {
			BufferedWriter bw = null;
			try {
				bw = new BufferedWriter(new FileWriter(f,true));
				bw.write(bean.getMovieName()+":"+bean.getDirectorName()+":"+bean.getProducerName()+":"+bean.getRating()+":"+bean.getReview());
				bw.newLine();
				return "Success";
			}
			catch(IOException e) {
				e.printStackTrace();
				return e.getMessage();
			}
			finally {
				if(bw != null) {
					try {
						bw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return "File not found.";
	}
	
	/**
	 * Removes the movie from the file specified by the list name as abstract path,
	 * if the movie exist in the file.
	 * 
	 * @param listName	name of the movie wish list.
	 * @param movieName	movieName name of the movie(State of MovieBeanIO object).
	 * @return	"success" if the movie is removed from the file, else "Movie is not 
	 *			available in the wish list" if the movie is not found in the list,
	 *			otherwise, "File not found" if the file doesn't exist.
	 */
	public String removeMovie(String listName, String movieName) {
		String path = dirPath + listName + ".txt";
		String msg = null;
		List<MovieBeanIO> beanList = new ArrayList<MovieBeanIO>();
		File f = new File(path);
		int count = 0;
		if(f.exists()) {
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(path));
				String s;
				MovieBeanIO bean = null;
				while((s = br.readLine()) != null) {
					String[] str = s.split(":");
					bean = new MovieBeanIO(str[0], str[1],str[2],Integer.parseInt(str[3]),str[4]);
					if(bean.getMovieName().equals(movieName)) {
						count++;
						continue;
					}
					else {
						beanList.add(bean);
					}
				}
				br.close();
				
				if(count == 1) {
					msg = writeBean(beanList, listName);
					return msg;
				}
				else {
					return "Movie is not available in the wish list";
				}
			}
			catch(IOException e) {
				e.printStackTrace();
				return msg;
			}
			finally {
				if(br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		else {
			return "File not found.";
		}
	}
	
	/**
	 * Replaces the old movie name in the file specified by the list name as abstract
	 * path to new movie name, if the movie name exists in the file.
	 * 
	 * @param 	oldMovie	name of the movie to replace.
	 * @param 	newMovie	name of the movie to replace with.
	 * @param 	listName	name of the movie wish list.
	 * @return	"success" if the movie is replaced successfully, else "Movie doesn't
	 * 			exist." if the movie with oldMovie name is not there in the file, otherwise
	 *			"File not found." if the file doesn't exist.
	 */
	public String replaceMovieTitle(String oldMovie, String newMovie, String listName) {
		String path = dirPath + listName + ".txt";
		List<MovieBeanIO> beanList = new ArrayList<MovieBeanIO>();
		File f = new File(path);
		String msg = null;
		int count = 0;
		if(f.exists()) {
			BufferedReader br = null;
			String str = null;
			MovieBeanIO bean = null;
			try {
				br = new BufferedReader(new FileReader(f));
				while((str = br.readLine()) != null) {
					String[] s = str.split(":");
					bean = new MovieBeanIO(s[0], s[1], s[2], Integer.parseInt(s[3]),s[4]);
					if(bean.getMovieName().equals(oldMovie)) {
						bean.setMovieName(newMovie);
						beanList.add(bean);
						count++;
					}
					else {
						beanList.add(bean);
					}
				}
				if(count == 1) {
					msg = writeBean(beanList, listName);
					return msg;
				}
				else {
					return "Movie is not available";
				}
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			finally {
				if(br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return "File not found.";
	}
	
	/**
	 * Replaces the director name of the specified movie in the file specified by the list 
	 * name as abstract path to new director name, if the movie name exists in the file.
	 * 
	 * @param 	movieName	name of the movie to get director name to replace.
	 * @param 	newDirector	name of the director to replace with.
	 * @param 	listName	name of the movie wish list.
	 * @return	"success" if the movie is replaced successfully, else "Movie doesn't
	 * 			exist." if the movie with oldMovie name is not there in the file, otherwise
	 *			"File not found." if the file doesn't exist.
	 */
	public String replaceDirectorTitle(String movieName, String newDirector, String listName) {
		String path = dirPath + listName + ".txt";
		List<MovieBeanIO> beanList = new ArrayList<MovieBeanIO>();
		File f = new File(path);
		String msg = null;
		int count = 0;
		if(f.exists()) {
			BufferedReader br = null;
			String str = null;
			MovieBeanIO bean = null;
			try {
				br = new BufferedReader(new FileReader(f));
				while((str = br.readLine()) != null) {
					String[] s = str.split(":");
					bean = new MovieBeanIO(s[0], s[1], s[2], Integer.parseInt(s[3]),s[4]);
					if(bean.getMovieName().equals(movieName)) {
						bean.setDirectorName(newDirector);
						beanList.add(bean);
						count++;
					}
					else {
						beanList.add(bean);
					}
				}
				if(count == 1) {
					msg = writeBean(beanList, listName);
					return msg;
				}
				else {
					return "Movie is not available.";
				}
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			finally {
				if(br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return "File not found";
	}
	
	/**
	 * Replaces the producer name of the specified movie in the file specified by the list 
	 * name as abstract path to new producer name, if the movie name exists in the file.
	 * 
	 * @param	movieName	name of the movie to get producer name to replace.
	 * @param	newProducer	newDirector	name of the producer to replace with.
	 * @param	listName	name of the movie wish list.
	 * @return	"success" if the movie is replaced successfully, else "Movie doesn't
	 * 			exist." if the movie with oldMovie name is not there in the file, otherwise
	 *			"File not found." if the file doesn't exist.
	 */
	public String replaceProducerTitle(String movieName, String newProducer, String listName) {
		String path = dirPath + listName + ".txt";
		List<MovieBeanIO> beanList = new ArrayList<MovieBeanIO>();
		File f = new File(path);
		String msg = null;
		int count = 0;
		if(f.exists()) {
			BufferedReader br = null;
			String str = null;
			MovieBeanIO bean = null;
			try {
				br = new BufferedReader(new FileReader(f));
				while((str = br.readLine()) != null) {
					String[] s = str.split(":");
					bean = new MovieBeanIO(s[0], s[1], s[2], Integer.parseInt(s[3]),s[4]);
					if(bean.getMovieName().equals(movieName)) {
						bean.setProducerName(newProducer);
						beanList.add(bean);
						count++;
					}
					else {
						beanList.add(bean);
					}
				}
				if(count == 1) {
					msg = writeBean(beanList, listName);
					return msg;
				}
				else {
					return "Movie is not available.";
				}
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			finally {
				if(br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return "File not found";
	}
	
	/**
	 * Writes the list of MovieBeanIO objects to the file specified by the list name
	 * as abstract path.
	 * 
	 * @param 	mbean		list of MeanBeanIO objects.
	 * @param 	listName	name of the movie wish list.
	 * @return "success" if the writing process succeeds, else error message if an 
	 * 			exception occurs while execution, otherwise, "File not found" if the file
	 * 			doesn't exist.
	 */
	private String writeBean(List<MovieBeanIO> mbean, String listName) {
		String path = dirPath + listName + ".txt";
		File f = new File(path);
		if(f.exists()) {
			BufferedWriter bw = null;
			try {
				bw = new BufferedWriter(new FileWriter(path));
				for(MovieBeanIO m : mbean) {
					bw.write(m.getMovieName()+":"+m.getDirectorName()+":"+m.getProducerName()+":"+m.getRating()+":"+m.getReview());
					bw.newLine();
				}
				return "success";
			}
			catch(IOException e) {
				e.printStackTrace();
				return e.getMessage();
			}
			finally {
				if(bw != null) {
					try {
						bw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return "File not found";
	}
	
	/**
	 * Checks the number of occurrences of string in the file specified by the name
	 * of the movie list as abstract path.
	 * 
	 * @param	listName	name of the movie wish list.
	 * @param	word		string whose occurrences are to be calculated.
	 * @return	the number of occurrences of the string in the file if file exists, otherwise 0.
	 */
	public int numOfOccurencesInTheList(String listName, String word) {
		String path = dirPath + listName + ".txt";
		File f = new File(path);
		int count = 0;
		if(f.exists()) {
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(path));
				String s;
				while((s = br.readLine()) != null) {
					String[] str = s.split(":");
					for(String s1 : str) {
						int pos = 0;
						while(pos != -1) {
							pos = s1.indexOf(word, pos);
							if(pos != -1) {
								count++;
								pos++;
							}
						}
					}
				}
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			finally {
				if(br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return count;
		}
		else {
			return 0;
		}
	}
	
	/**
	 * Checks the number of occurrences of the string in the movie names of the MovieBeanIO
	 * objects in the file specified by the movie wish list name as the abstract path.
	 * 
	 * @param 	listName	name of the movie wish list.
	 * @param 	word		string whose occurrences are to be calculated
	 * @return	A set of movie names in which the string is present, otherwise {@code NULL}.
	 */
	public Set<String> numOfOccurenceInMovieName(String listName, String word){
		String path = dirPath + listName + ".txt";
		File f = new File(path);
		Set<String> set = new LinkedHashSet<String>();
		MovieBeanIO bean = null;
		if(f.exists()) {
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(path));
				String s;
				while((s = br.readLine()) != null) {
					String[] str = s.split(":");
					bean = new MovieBeanIO(str[0], str[1], str[2], Integer.parseInt(str[3]), str[4]);
					int pos = 0;
					while(pos != -1) {
						pos = bean.getMovieName().indexOf(word, pos);
						if(pos != -1) {
							pos++;
							set.add(bean.getMovieName());
						}
					}
				}
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			finally {
				if(br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return set;
		}
		return null;
	}
	
	/**
	 * Checks the number of occurrences of the string in the movie names of the MovieBeanIO
	 * objects in the file specified by the movie wish list name as the abstract path.
	 * 
	 * @param	listName	name of the movie wish list.
	 * @param	word		string whose occurrences are to be calculated.
	 * @return	the number of occurrences of the string in the file if file exists, otherwise 0.
	 */
	public int numOfOccurencesInMovies(String listName, String word) {
		String path = dirPath + listName + ".txt";
		File f = new File(path);
		MovieBeanIO bean = null;
		int count = 0;
		if(f.exists()) {
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(path));
				String s;
				while((s = br.readLine()) != null) {
					String[] str = s.split(":");
					bean = new MovieBeanIO(str[0], str[1], str[2], Integer.parseInt(str[3]), str[4]);
					int pos = 0;
					while(pos != -1) {
						pos = bean.getMovieName().indexOf(word, pos);
						if(pos != -1) {
							count++;
							pos++;
						}
					}
				}
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			finally {
				if(br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return count;
		}
		else {
			return 0;
		}
	}
	
	/**
	 * Checks the number of occurrences of the string in the director names of the MovieBeanIO
	 * objects in the file specified by the movie wish list name as the abstract path.
	 * 
	 * @param 	listName	name of the movie wish list.
	 * @param 	word		string whose occurrences are to be calculated.
	 * @return	An entry set of movie names and director names after checking the names 
	 * 			of director in which the string is present, otherwise {@code NULL}.
	 */
	public  Set<Entry<String,String>> numOfOccurenceInDirector(String listName, String word){
		String path = dirPath + listName + ".txt";
		File f = new File(path);
		Map<String,String> map = new LinkedHashMap<String,String>();
		MovieBeanIO bean = null;
		if(f.exists()) {
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(path));
				String s;
				while((s = br.readLine()) != null) {
					String[] str = s.split(":");
					bean = new MovieBeanIO(str[0], str[1], str[2], Integer.parseInt(str[3]), str[4]);
					int pos = 0;
					while(pos != -1) {
						pos = bean.getDirectorName().indexOf(word, pos);
						if(pos != -1) {
							pos++;
							map.put(bean.getMovieName(), bean.getDirectorName());
						}
					}
				}
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			finally {
				if(br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return map.entrySet();
		}
		return null;
	}
	
	/**
	 * Checks the number of occurrences of the string in the director names of the MovieBeanIO
	 * objects in the file specified by the movie wish list name as the abstract path.
	 * 
	 * @param	listName	name of the movie wish list.
	 * @param	word		string whose occurrences are to be calculated.
	 * @return	the number of occurrences of the string in the file if file exists, otherwise 0.
	 */
	public int numOfOccurencesInDirectorName(String listName, String word) {
		String path = dirPath + listName + ".txt";
		File f = new File(path);
		MovieBeanIO bean = null;
		int count = 0;
		if(f.exists()) {
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(path));
				String s;
				while((s = br.readLine()) != null) {
					String[] str = s.split(":");
					bean = new MovieBeanIO(str[0], str[1], str[2], Integer.parseInt(str[3]), str[4]);
					int pos = 0;
					while(pos != -1) {
						pos = bean.getDirectorName().indexOf(word, pos);
						if(pos != -1) {
							count++;
							pos++;
						}
					}
				}
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			finally {
				if(br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return count;
		}
		else {
			return 0;
		}
	}
	
	/**
	 *Checks the number of occurrences of the string in the review of the MovieBeanIO
	 * objects in the file specified by the movie wish list name as the abstract path.
	 * 
	 * @param 	listName	name of the movie wish list.
	 * @param 	word		string whose occurrences are to be calculated
	 * @return	A set of reviews in which the string is present, otherwise {@code NULL}.
	 */
	public Set<String> numOfOccurenceInReview(String listName, String word){
		String path = dirPath + listName + ".txt";
		File f = new File(path);
		Set<String> reviewSet = new LinkedHashSet<String>();
		MovieBeanIO bean = null;
		if(f.exists()) {
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(path));
				String s;
				while((s = br.readLine()) != null) {
					String[] str = s.split(":");
					bean = new MovieBeanIO(str[0], str[1], str[2], Integer.parseInt(str[3]), str[4]);
					int pos = 0;
					while(pos != -1) {
						pos = bean.getReview().indexOf(word, pos);
						if(pos != -1) {
							pos++;
							reviewSet.add(bean.getReview());
						}
					}
				}
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			finally {
				if(br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return reviewSet;
		}
		return null;
	}
	
	/**
	 *Checks the number of occurrences of the string in the review of the MovieBeanIO
	 * objects in the file specified by the movie wish list name as the abstract path.
	 * 
	 * @param	listName	name of the movie wish list.
	 * @param	word		string whose occurrences are to be calculated.
	 * @return	the number of occurrences of the string in the file if file exists, otherwise 0.
	 */
	public int numOfOccurencesInReviewOfMovie(String listName, String word) {
		String path = dirPath + listName + ".txt";
		File f = new File(path);
		MovieBeanIO bean = null;
		int count = 0;
		if(f.exists()) {
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(path));
				String s;
				while((s = br.readLine()) != null) {
					String[] str = s.split(":");
					bean = new MovieBeanIO(str[0], str[1], str[2], Integer.parseInt(str[3]), str[4]);
					int pos = 0;
					while(pos != -1) {
						pos = bean.getReview().indexOf(word, pos);
						if(pos != -1) {
							count++;
							pos++;
						}
					}
				}
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			finally {
				if(br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return count;
		}
		else {
			return 0;
		}
	}
	
	/**
	 * Returns a list of MovieBeanIO objects in ascending order of director names.
	 * 
	 * @param 	listName	name of the movie wish list.
	 * @return	list of MovieBeanIO objects, otherwise {@code NULL}.
	 */
	public List<MovieBeanIO> getMoviesBasedOnDirectorName(String listName){
		String path = dirPath + listName + ".txt";
		File f = new File(path);
		List<MovieBeanIO> list = new ArrayList<MovieBeanIO>();
		MovieBeanIO bean = null;
		if(f.exists()) {
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(f));
				String str;
				while((str = br.readLine()) != null) {
					String[] s = str.split(":");
					bean = new MovieBeanIO(s[0], s[1], s[2], Integer.parseInt(s[3]), s[4]);
					list.add(bean);
				}
				DirectorNameComparator dmc = new DirectorNameComparator();
				Collections.sort(list,dmc);
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			finally {
				if(br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return list;
		}
		return null;
	}
	
	/**
	 * Return a set of MovieBeanIO objects in alphabetical order.
	 * 
	 * @param 	listName	name of the movie wish list.
	 * @return	Set of MovieBeanIO objects, otherwise {@code NULL}.
	 */
	public Set<MovieBeanIO> getMoviesAlphabetically(String listName){
		String path = dirPath + listName + ".txt";
		File f = new File(path);
		Set<MovieBeanIO> beanSet = new TreeSet<MovieBeanIO>();
		MovieBeanIO bean = null;
		if(f.exists()) {
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(f));
				String str;
				while((str = br.readLine()) != null) {
					String[] s = str.split(":");
					bean = new MovieBeanIO(s[0], s[1], s[2], Integer.parseInt(s[3]), s[4]);
					beanSet.add(bean);
				}
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			finally {
				if(br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return beanSet;
		}
		return null;
	}
	
	/**
	 * Returns a set of MovieBeanIO objects in ascending order of producer name.
	 * 
	 * @param 	listName	name of movie wish list.
	 * @return	List of MovieBeanIO objects, otherwise {@code NULL}.
	 */
	public List<MovieBeanIO> getMoviesBasedOnProducerName(String listName){
		String path = dirPath + listName + ".txt";
		File f = new File(path);
		List<MovieBeanIO> list = new ArrayList<MovieBeanIO>();
		MovieBeanIO bean = null;
		if(f.exists()) {
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(f));
				String str;
				while((str = br.readLine()) != null) {
					String[] s = str.split(":");
					bean = new MovieBeanIO(s[0], s[1], s[2], Integer.parseInt(s[3]), s[4]);
					list.add(bean);
				}
				ProducerNameComparator pmc = new ProducerNameComparator();
				Collections.sort(list,pmc);
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			finally {
				if(br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return list;
		}
		return null;
	}
	/**
	 * Return a list of MovieBeanIO objects in descending order of rating.
	 * 
	 * @param 	listName	name of the movie wish list.
	 * @return	List of MovieBeanIO objects, otherwise {@code NULL}.
	 */
	public List<MovieBeanIO> getMoviesBasedOnRating(String listName){
		String path = dirPath + listName + ".txt";
		File f = new File(path);
		List<MovieBeanIO> list = new ArrayList<MovieBeanIO>();
		MovieBeanIO bean = null;
		if(f.exists()) {
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(f));
				String str;
				while((str = br.readLine()) != null) {
					String[] s = str.split(":");
					bean = new MovieBeanIO(s[0], s[1], s[2], Integer.parseInt(s[3]), s[4]);
					list.add(bean);
				}
				RatingComparator rc = new RatingComparator();
				Collections.sort(list,rc);
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			finally {
				if(br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return list;
		}
		return null;
	}
	
	/**
	 * Returns a list of MovieBeanIO objects in ascending order of reviews.
	 * 
	 * @param 	listName	name of the movie wish list.
	 * @return	List of MovieBeanIO objects, otherwise {@code NULL}.
	 */
	public List<MovieBeanIO> getMoviesBasedOnReviews(String listName){
		String path = dirPath + listName + ".txt";
		File f = new File(path);
		List<MovieBeanIO> list = new ArrayList<MovieBeanIO>();
		MovieBeanIO bean = null;
		if(f.exists()) {
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(f));
				String str;
				while((str = br.readLine()) != null) {
					String[] s = str.split(":");
					bean = new MovieBeanIO(s[0], s[1], s[2], Integer.parseInt(s[3]), s[4]);
					list.add(bean);
				}
				ReviewComparator rwc = new ReviewComparator();
				Collections.sort(list,rwc);
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			finally {
				if(br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(bean != null) {
					bean = null;
				}
			}
			return list;
		}
		return null;
	}

	/**
	 * Returns an entry set of movie wish list name and list of MovieBeanIO objects from
	 * the specified directory.
	 * 
	 * @return 	An entry set of movie wish list name and list of MovieBeanIO objects,
	 * 			otherwise {@code NULL}.
	 */
	public Set<Entry<String,List<MovieBeanIO>>> getAllMoviesInAllLists(){
		String path = dirPath;
		File f = new File(path);
		Map<String,List<MovieBeanIO>> beanMap = new LinkedHashMap<String,List<MovieBeanIO>>();
		MovieBeanIO bean = null;
		List<MovieBeanIO> beanList = null;
		if(f.isDirectory()) {
			File[] fa = f.listFiles();
			for(File fi : fa) {
				if(fi.isFile() && fi.getName().endsWith(".txt")) {
					BufferedReader br = null;
					try {
						br = new BufferedReader(new FileReader(fi));
						beanList = new ArrayList<MovieBeanIO>();
						String str;
						while((str = br.readLine()) != null) {
							String[] s = str.split(":");
							bean = new MovieBeanIO(s[0], s[1], s[2], Integer.parseInt(s[3]),s[4]);
							beanList.add(bean);
						}
						beanMap.put(fi.getName().replace(".txt", ""), beanList);
					}
					catch(IOException e) {
						e.printStackTrace();
					}
					finally {
						if(br != null) {
							try {
								br.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
			return beanMap.entrySet();
		}
		return null;
	}
	
	/**
	 * Returns a list of movie names in the file specified by the name of the list as 
	 * abstract path by reading lines from the file and making MovieBeanIO objects.
	 * 
	 * @param 	listName	name of the movie wish list.
	 * @return	List of all the movie names in the file, otherwise {@code NULL}.
	 */
	public List<String> showMovies(String listName){
		String path = dirPath + listName + ".txt";
		File f = new File(path);
		List<String> movieList = new ArrayList<String>();
		MovieBeanIO bean = null;
		if(f.exists()) {
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(f));
				String s;
				while((s = br.readLine())  != null) {
					String[] str = s.split(":");
					bean = new MovieBeanIO(str[0], str[1], str[2], Integer.parseInt(str[3]), str[4]);
					movieList.add(bean.getMovieName());
				}
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			finally {
				if(br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return movieList;
		}
		return null;
	}
	
	/**
	 * Returns a list of all the movie wish list names present in the specified directory.
	 * 
	 * @return List of all movie wish list names, otherwise {@code NULL}.
	 */
	public List<String> getAllLists(){
		String path = dirPath;
		File f = new File(path);
		List<String> list = new ArrayList<String>();
		if(f.isDirectory()) {
			File[] fa = f.listFiles();
			for(File fi : fa) {
				if(fi.isFile() && fi.getName().endsWith(".txt")) {
					list.add(fi.getName().replace(".txt", ""));
				}
			}
			return list;
		}
		return null;
	}
	
	/**
	 * Deletes the file denoted by the pathname of the String passed. If the file
	 * exists in the directory then only it will be deleted.
	 * @param listName abstract pathname of the file.
	 * @return string if the deletion succeed or failed. 
	 */
	public String delete(String listName) {
		String path = dirPath + listName + ".txt";
		File f = new File(path);
		if(f.exists()) {
			f.delete();
			return "success";
		}
		else {
			return "File not found.";
		}
	}
}