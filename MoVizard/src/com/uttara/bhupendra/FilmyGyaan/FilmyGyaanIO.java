package com.uttara.bhupendra.FilmyGyaan;

import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

/**
 * Represents a FilmyGyaan application which gives the user a menu to given inputs and perform
 * tasks.
 * 
 * @author Bhupendra Singh
 *
 */
public class FilmyGyaanIO {
	
	public static void main(String[] args) {

		Scanner sc1 = null;
		Scanner sc2 = null;
		try {
			Logger.getInstance().log("Starting the app");
			sc1 = new Scanner(System.in);
			sc2 = new Scanner(System.in);
			MovieModelIO model = new MovieModelIO();
			model.makeDirectory();
			String listName, movieName, dirName, prodName, review, word;
			int rating, choice = 0, listChoice = 0, editChoice = 0, sortChoice = 0;
			while(listChoice != 9) {
				Logger.getInstance().log("Entering main menu.");
				System.out.println("");
				System.out.println("Press 1 to create a movie wish list");
				System.out.println("Press 2 to edit a movie wish list");
				System.out.println("Press 3 to list all the movies with list names.");
				System.out.println("Press 4 to list all movies in all the list.");
				System.out.println("Press 5 to search in the wish list.");
				System.out.println("Press 6 to remove a movie from a wish list.");
				System.out.println("Press 7 to show all the lists.");
				System.out.println("Press 8 to delete a wish list.");
				System.out.println("Press 9 to exit.");
				listChoice = sc1.nextInt();
				switch(listChoice) {
				case 1 : System.out.println("Enter a wish list name.");
						listName = sc2.nextLine();
						while(!MovieUtilIO.validateName(listName)) {
							System.out.println("The name can start with a letter and have only letters and digits.");
							System.out.println("Enter a new name");
							listName = sc2.nextLine();
						}
						Logger.getInstance().log("Creating a wish list - " + listName);
						if((!model.checkIfMovieWishListExistsToCreate(listName))) {
							while(choice != 6) {
								Logger.getInstance().log("Entering list menu of new list " + listName);
								System.out.println("");
								System.out.println("Press 1 to add a movie in the wish list.");
								System.out.println("Press 2 to edit the wish list.");
								System.out.println("Press 3 to show all the movies in the wish list.");
								System.out.println("Press 4 to search for a word in the wish list.");
								System.out.println("Press 5 to list all the movies in the wish list.");
								System.out.println("Press 6 to go back to main menu menu.");
								choice = sc1.nextInt();
								switch(choice) {
								case 1 : System.out.println("Enter a movie name.");
										movieName = sc2.nextLine();
										while(!MovieUtilIO.validateName(movieName)) {
											System.out.println("Name can only have letter and digits and must start with a letter.");
											System.out.println("Enter another movie name.");
											movieName = sc2.nextLine();
										}
										while(!model.duplicateName(listName, movieName)) {
											System.out.println("Name already exists and cannot be added.");
											System.out.println("Enter another name.");
											movieName = sc2.nextLine();
											while(!MovieUtilIO.validateName(movieName)) {
												System.out.println("Name can only have letter and digits and must start with a letter.");
												System.out.println("Enter another movie name.");
												movieName = sc2.nextLine();
											}
										}
										Logger.getInstance().log("Adding a movie " + movieName + " to the list " + listName);
										System.out.println("Enter director's name.");
										dirName = sc2.nextLine();
										while(!MovieUtilIO.validateName(movieName)) {
											System.out.println("Name can only have letter and digits and must start with a letter.");
											System.out.println("Enter another director name.");
											dirName = sc2.nextLine();
										}
										Logger.getInstance().log("Adding a director " + dirName + " of movie " + movieName);
										System.out.println("Enter producer's name.");
										prodName = sc2.nextLine();
										while(!MovieUtilIO.validateName(prodName)) {
											System.out.println("Name can only have letter and digits and must start with a letter.");
											System.out.println("Enter another producer name.");
											prodName = sc2.nextLine();
										}
										Logger.getInstance().log("Adding a producer " + prodName + " of movie " + movieName);
										System.out.println("Enter rating of the movie 1-10.");
										rating = sc1.nextInt();
										while(!MovieUtilIO.validateRating(rating)) {
											System.out.println("Rating can only be between 1 - 10.");
											System.out.println("Enter another rating.");
											rating = sc1.nextInt();
										}
										Logger.getInstance().log("Adding a rating " + rating + " of movie " + movieName);
										System.out.println("Enter review of the movie.");
										review = sc2.nextLine();
										Logger.getInstance().log("Adding a review " + review + " of movie " + movieName);
										MovieBeanIO bean = new MovieBeanIO(movieName, dirName, prodName, rating, review);
										String msg = model.addMovie(bean, listName);
										if(msg.equalsIgnoreCase("success")) {
											Logger.getInstance().log("Movie " + movieName + " is successfully added to the list." + listName);
											System.out.println("Movie is successfully added to the wish list.");
										}
										else {
											Logger.getInstance().log("Movie " + movieName + " cannot be added because of an error - " + msg);
											System.out.println("The addition failed because of error - " + msg);
										}
									break;
								case 2 : while(editChoice != 5) {
											Logger.getInstance().log("Entered editing menu of the list " + listName);
											System.out.println("");
											System.out.println("Press 1 to replace a movie.");
											System.out.println("Press 2 to edit movie title");
											System.out.println("Press 3 to edit director name.");
											System.out.println("Press 4 to edit producer name.");
											System.out.println("Press 5 to go back to previous menu.");
											editChoice = sc1.nextInt();
											switch(editChoice) {
											case 1 : System.out.println("Enter a movie to replace.");
													movieName = sc2.nextLine();
													while(!MovieUtilIO.validateName(movieName)) {
														System.out.println("Name can only have letter and digits and must start with a letter.");
														System.out.println("Enter another movie name.");
														movieName = sc2.nextLine();
													}
													Logger.getInstance().log("Trying to replace the movie " + movieName + " in list " + listName);
													String s = model.removeMovie(listName, movieName);
													if(s.equalsIgnoreCase("success")) {
														Logger.getInstance().log("Movie " + movieName + " successfully removed.");
														System.out.println("Movie is removed successfully.");
														System.out.println("Enter a new movie name.");
														movieName = sc2.nextLine();
														while(!MovieUtilIO.validateName(movieName)) {
															System.out.println("Name can only have letter and digits and must start with a letter.");
															System.out.println("Enter another movie name.");
															movieName = sc2.nextLine();
														}
														while(!model.duplicateName(listName, movieName)) {
															System.out.println("Name already exists and cannot be added.");
															System.out.println("Enter another name.");
															movieName = sc2.nextLine();
															while(!MovieUtilIO.validateName(movieName)) {
																System.out.println("Name can only have letter and digits and must start with a letter.");
																System.out.println("Enter another movie name.");
																movieName = sc2.nextLine();
															}
														}
														Logger.getInstance().log("Adding a new movie " + movieName + " to the list " + listName);
														System.out.println("Enter director's name.");
														dirName = sc2.nextLine();
														while(!MovieUtilIO.validateName(dirName)) {
															System.out.println("Name can only have letter and digits and must start with a letter.");
															System.out.println("Enter another director name.");
															dirName = sc2.nextLine();
														}
														Logger.getInstance().log("Adding a director " + dirName + " of the movie " + movieName);
														System.out.println("Enter producer's name.");
														prodName = sc2.nextLine();
														while(!MovieUtilIO.validateName(prodName)) {
															System.out.println("Name can only have letter and digits and must start with a letter.");
															System.out.println("Enter another producer name.");
															prodName = sc2.nextLine();
														}
														Logger.getInstance().log("Adding a producer " + prodName + " of the movie " + movieName);
														System.out.println("Enter rating of the movie 1-10.");
														rating = sc1.nextInt();
														while(!MovieUtilIO.validateRating(rating)) {
															System.out.println("Rating can only be between 1 - 10.");
															System.out.println("Enter another rating.");
															rating = sc1.nextInt();
														}
														Logger.getInstance().log("Adding a rating " + rating + " of the movie " + movieName);
														System.out.println("Enter review of the movie.");
														review = sc2.nextLine();
														Logger.getInstance().log("Adding a review " + review + " of the movie " + movieName);
														MovieBeanIO beanObj = new MovieBeanIO(movieName, dirName, prodName, rating, review);
														String msg1 = model.addMovie(beanObj, listName);
														if(msg1.equalsIgnoreCase("success")) {
															Logger.getInstance().log("Movie replaced with " + movieName + " successfully.");
															System.out.println("Movie is successfully added to the wish list.");
														}
														else {
															Logger.getInstance().log("Movie cannot be replaced because of an error - " + msg1);
															System.out.println("The addition failed because of error - " + msg1);
														}
													}
													else {
														Logger.getInstance().log("Movie " + movieName + " cannot be removed cause of an error - " + s);
														System.out.println("Error occured - " + s);
													}
												break;
											case 2 : System.out.println("Enter a movie title name to edit.");
													movieName = sc2.nextLine();
													while(!MovieUtilIO.validateName(movieName)) {
														System.out.println("Name can only have letter and digits and must start with a letter.");
														System.out.println("Enter another movie name.");
														movieName = sc2.nextLine();
													}
													System.out.println("Enter a new movie title");
													String movie = sc2.nextLine();
													while(!MovieUtilIO.validateName(movieName)) {
														System.out.println("Name can only have letter and digits and must start with a letter.");
														System.out.println("Enter another movie name.");
														movie = sc2.nextLine();
													}
													Logger.getInstance().log("Trying to change the movie name "  + movieName + " to " + movie);
													String message = model.replaceMovieTitle(movieName, movie, listName);
													if(message.equalsIgnoreCase("success")) {
														Logger.getInstance().log("Movie name changed successfully");
														System.out.println("Movie title is edited successfully.");
													}
													else {
														Logger.getInstance().log("Movie name cannot be chaned because of an error - " + message);
														System.out.println("Error occured - " + message);
													}
												break;
											case 3 : System.out.println("Enter a movie name to edit director name.");
													movieName = sc2.nextLine();
													while(!MovieUtilIO.validateName(movieName)) {
														System.out.println("Name can only have letter and digits and must start with a letter.");
														System.out.println("Enter another director name.");
														movieName = sc2.nextLine();
													}
													System.out.println("Enter new director name");
													String director = sc2.nextLine();
													while(!MovieUtilIO.validateName(director)) {
														System.out.println("Name can only have letter and digits and must start with a letter.");
														System.out.println("Enter another director name.");
														director = sc2.nextLine();
													}
													Logger.getInstance().log("Trying to change director name of movie " + movieName + " to " + director);
													String str = model.replaceDirectorTitle(movieName, director, listName);
													if(str.equalsIgnoreCase("Success")) {
														Logger.getInstance().log("Director name changed successfully.");
														System.out.println("Director name is edited successfully.");
													}
													else {
														Logger.getInstance().log("Director name cannot be changed because of an error -" + str);
														System.out.println("Error occured - " + str);
													}
												break;
											case 4 : System.out.println("Enter a movie name to edit producer name.");
													movieName = sc2.nextLine();
													while(!MovieUtilIO.validateName(movieName)) {
														System.out.println("Name can only have letter and digits and must start with a letter.");
														System.out.println("Enter another producer name.");
														movieName = sc2.nextLine();
													}
													System.out.println("Enter new producer name");
													String producer = sc2.nextLine();
													while(!MovieUtilIO.validateName(producer)) {
														System.out.println("Name can only have letter and digits and must start with a letter.");
														System.out.println("Enter another producer name.");
														producer = sc2.nextLine();
													}
													Logger.getInstance().log("Trying to change producer name of movie " + movieName);
													String message1 = model.replaceProducerTitle(movieName, producer, listName);
													if(message1.equalsIgnoreCase("Success")) {
														Logger.getInstance().log("Producer name changed successfully.");
														System.out.println("Producer name is edited successfully.");
													}
													else {
														Logger.getInstance().log("Producer name couldn't be changed cause of an error - " + message1);
														System.out.println("Error occured - " + message1);
													}
												break;
											case 5 : System.out.println("Returning back to previous menu.");
												break;
											default : System.out.println("Invalid input. Please choose the input from the menu.");
												break;
											}
										}
									break;
								case 3 : List<String> list = model.showMovies(listName);
										Logger.getInstance().log("Entered show all movies option of the list " + listName);
										System.out.print("All movies in the list = ");
										for(String s : list) {
											System.out.print(s + ", ");
										}
									break;
								case 4 : System.out.println("Enter a word to search.");
										Logger.getInstance().log("Entered search option of the list " + listName);
										word = sc2.nextLine();
										while(!MovieUtilIO.validateWord(word)) {
											System.out.println("Word can only have letters.");
											System.out.println("Enter a new word.");
											word = sc2.nextLine();
										}
										Set<Entry<String,String>> set = model.numOfOccurenceInDirector(listName, word);
										if(set != null) {
											if(set.size() > 0) {
												int a = model.numOfOccurencesInTheList(listName, word);
												System.out.println("Total number of occurence = " + a);
												int b = model.numOfOccurencesInDirectorName(listName, word);
												System.out.println("Total number of occurence in director = " + b);
												for(Entry<String,String> en : set) {
													System.out.println(en.toString().replace("[", "").replace("]",""));
												}
												int c = model.numOfOccurencesInMovies(listName, word);
												System.out.println("Total number of occurecnes in movies = " + c);
												Set<String> movieList = model.numOfOccurenceInMovieName(listName, word);
												for(String s : movieList) {
													System.out.println(s.toString().replace("[", "").replace("]", ""));
												}
												int d = model.numOfOccurencesInReviewOfMovie(listName, word);
												System.out.println("Total number of occurences in review = " + d);
												Set<String> reviewList = model.numOfOccurenceInReview(listName, word);
												for(String s1 : reviewList) {
													System.out.println(s1.toString().replace("[", "").replace("]", ""));
												}
												Logger.getInstance().log("Number of occurence of word " + word + " in the list " + listName + " displayed successfully");
											}
											else {
												Logger.getInstance().log("Number of occurence of word " + word + " cannot be displayed cause the word is not present in the list " + listName);
												System.out.println("Word is not there in the list.");
											}
										}
										else {
											Logger.getInstance().log("The list " + listName + " doesn't exist.");
											System.out.println("File does't exist.");
										}
									break;
								case 5 :while(sortChoice != 6) {
									Logger.getInstance().log("Entering listing menu of new list.");
									System.out.println("");
									System.out.println("Press 1 to list all movies in alphabetical order.");
									System.out.println("Press 2 to list all movies based on director name.");
									System.out.println("Press 3 to list all movies based on producer name.");
									System.out.println("Press 4 to list all movies based on rating.");
									System.out.println("Press 5 to list all movies based on review.");
									System.out.println("Press 6 to go back to main menu.");
									sortChoice = sc1.nextInt();
									switch(sortChoice) {
									case 1 :Set<MovieBeanIO> beanSet = model.getMoviesAlphabetically(listName);
											Logger.getInstance().log("Trying to list all the movies in " + listName + " in alphabetical order.");
											if(beanSet != null) {
												if(beanSet.size() > 0) {
													System.out.println("Movies sorted alphabetically - ");
													for(MovieBeanIO m : beanSet) {
														System.out.println(m);
													}
													Logger.getInstance().log("Movies of list " + listName + " displayed in alphabetical order successfully.");
												}
												else {
													Logger.getInstance().log("Movies of list " + listName + " cannot be displayed in alpabetical order.");
													System.out.println("List is empty.");
												}
											}
											else {
												Logger.getInstance().log("List cannot be displayed as the list doesn't exist.");
												System.out.println("List doesn't exist.");
											}
										break;
									case 2 :List<MovieBeanIO> beanList = model.getMoviesBasedOnDirectorName(listName);
											Logger.getInstance().log("Trying to list all the movies in " + listName + " based on director name.");
											if(beanList != null) {
												if(beanList.size() > 0) {
													System.out.println("Movies sorted by director name - ");
													for(MovieBeanIO m : beanList) {
														System.out.println(m);
													}
													Logger.getInstance().log("Movies of list " + listName + " displayed successfully based on director name.");
												}
												else {
													Logger.getInstance().log("Movies of list " + listName + " cannot be displayed based on director name as the list is empty.");
													System.out.println("List is empty.");
												}
											}
											else {
												Logger.getInstance().log("The list " + listName + " doesn't exist.");
												System.out.println("List doesn't exist.");
											}
										break;
									case 3 :List<MovieBeanIO> prodList = model.getMoviesBasedOnProducerName(listName);
											Logger.getInstance().log("Trying to list all the movies in " + listName + " based on producer name.");
											if(prodList != null) {
												if(prodList.size() > 0) {
													System.out.print("Movies sorted by producer name - ");
													for(MovieBeanIO m : prodList) {
														System.out.println(m);
													}
													Logger.getInstance().log("Movies of list " + listName + " displayed successfully based on producer name.");
												}
												else {
													Logger.getInstance().log("Movies of list " + listName + " cannot be displayed based on producer name as the list is empty.");
													System.out.println("List is empty.");
												}
											}
											else {
												Logger.getInstance().log("The list " + listName + " doesn't exist.");
												System.out.println("List doesn't exist.");
											}
										break;
									case 4 :List<MovieBeanIO> ratingList = model.getMoviesBasedOnRating(listName);
											Logger.getInstance().log("Trying to list all the movies in " + listName + " based on ratings.");
											if(ratingList != null) {
												if(ratingList.size() > 0) {
													System.out.print("Movies sorted by rating - ");
													for(MovieBeanIO m : ratingList) {
														System.out.println(m);
													}
													Logger.getInstance().log("Movies of list " + listName + " displayed successfully based on rating.");
												}
												else {
													Logger.getInstance().log("Movies of list " + listName + " cannot be displayed based on rating as the list is empty.");
													System.out.println("List is empty.");
												}
											}
											else {
												Logger.getInstance().log("The list " + listName + " doesn't exist.");
												System.out.println("List doesn't exist.");
											}
										break;
									case 5 :List<MovieBeanIO> reviewList = model.getMoviesBasedOnReviews(listName);
											Logger.getInstance().log("Trying to list all the movies in " + listName + " based on reviews.");
											if(reviewList != null) {
												if(reviewList.size() > 0) {
													System.out.print("Movies sorted by reviews - ");
													for(MovieBeanIO m : reviewList) {
														System.out.println(m);
													}
													Logger.getInstance().log("Movies of list " + listName + " displayed successfully based on reviews.");
												}
												else {
													Logger.getInstance().log("Movies of list " + listName + " cannot be displayed based on reviews as the list is empty.");
													System.out.println("Word is not there in the list.");
												}
											}
											else {
												Logger.getInstance().log("The list " + listName + " doesn't exist.");
												System.out.println("List doesn't exist.");
											}
										break;
								case 6 : System.out.println("Returning back to previous menu.");
										Logger.getInstance().log("Returning back to new list menu.");
									break;
								default : System.out.println("Invalid input. Please choose the input from the menu.");
									break;
								}
							}
									break;
								case 6 : System.out.println("Going back to main menu.");
										Logger.getInstance().log("Going back to main menu.");
									break;
								default : System.out.println("Invalid input. Please choose the input from the menu.");
									break;
								}
							}
						}
						else {
							Logger.getInstance().log("New list cannot be created as the list already exists.");
							System.out.println("The file already exists.");
						}
					break;
				case 2 :System.out.println("Enter a wish list name.");
						listName = sc2.nextLine();
						while(!MovieUtilIO.validateName(listName)) {
							System.out.println("The name can start with a letter and have only letters and digits.");
							System.out.println("Enter a new name");
							listName = sc2.nextLine();
						}
						Logger.getInstance().log("Creating a wish list - " + listName);
						if(model.checkIfListExists(listName)) {
							while(choice != 4) {
								Logger.getInstance().log("Entering list menu of the list " + listName);
								System.out.println("");
								System.out.println("Press 1 to add a movie in the wish list.");
								System.out.println("Press 2 to edit the wish list.");
								System.out.println("Press 3 to show all the movies in the wish list.");
								System.out.println("Press 4 to go back to main menu menu.");
								choice = sc1.nextInt();
								switch(choice) {
								case 1 : System.out.println("Enter a movie name.");
										movieName = sc2.nextLine();
										while(!MovieUtilIO.validateName(movieName)) {
											System.out.println("Name can only have letter and digits and must start with a letter.");
											System.out.println("Enter another movie name.");
											movieName = sc2.nextLine();
										}
										while(!model.duplicateName(listName, movieName)) {
											System.out.println("Name already exists and cannot be added.");
											System.out.println("Enter another name.");
											movieName = sc2.nextLine();
											while(!MovieUtilIO.validateName(movieName)) {
												System.out.println("Name can only have letter and digits and must start with a letter.");
												System.out.println("Enter another movie name.");
												movieName = sc2.nextLine();
											}
										}
										Logger.getInstance().log("Adding a movie " + movieName + " to the list " + listName);
										System.out.println("Enter director's name.");
										dirName = sc2.nextLine();
										while(!MovieUtilIO.validateName(movieName)) {
											System.out.println("Name can only have letter and digits and must start with a letter.");
											System.out.println("Enter another director name.");
											dirName = sc2.nextLine();
										}
										Logger.getInstance().log("Adding a director " + dirName + " of movie " + movieName);
										System.out.println("Enter producer's name.");
										prodName = sc2.nextLine();
										while(!MovieUtilIO.validateName(prodName)) {
											System.out.println("Name can only have letter and digits and must start with a letter.");
											System.out.println("Enter another producer name.");
											prodName = sc2.nextLine();
										}
										Logger.getInstance().log("Adding a producer " + prodName + " of movie " + movieName);
										System.out.println("Enter rating of the movie 1-10.");
										rating = sc1.nextInt();
										while(!MovieUtilIO.validateRating(rating)) {
											System.out.println("Rating can only be between 1 - 10.");
											System.out.println("Enter another rating.");
											rating = sc1.nextInt();
										}
										Logger.getInstance().log("Adding a rating " + rating + " of movie " + movieName);
										System.out.println("Enter review of the movie.");
										review = sc2.nextLine();
										Logger.getInstance().log("Adding a review " + review + " of movie " + movieName);
										MovieBeanIO bean = new MovieBeanIO(movieName, dirName, prodName, rating, review);
										String msg = model.addMovie(bean, listName);
										if(msg.equalsIgnoreCase("success")) {
											Logger.getInstance().log("Movie " + movieName + " is successfully added to the list." + listName);
											System.out.println("Movie is successfully added to the wish list.");
										}
										else {
											Logger.getInstance().log("Movie " + movieName + " cannot be added because of an error - " + msg);
											System.out.println("The addition failed because of error - " + msg);
										}
									break;
								case 2 : while(editChoice != 5) {
											Logger.getInstance().log("Entered editing menu of the list " + listName);
											System.out.println("");
											System.out.println("Press 1 to replace a movie.");
											System.out.println("Press 2 to edit movie title");
											System.out.println("Press 3 to edit director name.");
											System.out.println("Press 4 to edit producer name.");
											System.out.println("Press 5 to go back to previous menu.");
											editChoice = sc1.nextInt();
											switch(editChoice) {
											case 1 : System.out.println("Enter a movie to replace.");
													movieName = sc2.nextLine();
													while(!MovieUtilIO.validateName(movieName)) {
														System.out.println("Name can only have letter and digits and must start with a letter.");
														System.out.println("Enter another movie name.");
														movieName = sc2.nextLine();
													}
													Logger.getInstance().log("Trying to replace the movie " + movieName + " in list " + listName);
													String s = model.removeMovie(listName, movieName);
													if(s.equalsIgnoreCase("success")) {
														Logger.getInstance().log("Movie " + movieName + " successfully removed.");
														System.out.println("Movie is removed successfully.");
														System.out.println("Enter a new movie name.");
														movieName = sc2.nextLine();
														while(!MovieUtilIO.validateName(movieName)) {
															System.out.println("Name can only have letter and digits and must start with a letter.");
															System.out.println("Enter another movie name.");
															movieName = sc2.nextLine();
														}
														while(!model.duplicateName(listName, movieName)) {
															System.out.println("Name already exists and cannot be added.");
															System.out.println("Enter another name.");
															movieName = sc2.nextLine();
															while(!MovieUtilIO.validateName(movieName)) {
																System.out.println("Name can only have letter and digits and must start with a letter.");
																System.out.println("Enter another movie name.");
																movieName = sc2.nextLine();
															}
														}
														Logger.getInstance().log("Adding a new movie " + movieName + " to the list " + listName);
														System.out.println("Enter director's name.");
														dirName = sc2.nextLine();
														while(!MovieUtilIO.validateName(dirName)) {
															System.out.println("Name can only have letter and digits and must start with a letter.");
															System.out.println("Enter another director name.");
															dirName = sc2.nextLine();
														}
														Logger.getInstance().log("Adding a director " + dirName + " of the movie " + movieName);
														System.out.println("Enter producer's name.");
														prodName = sc2.nextLine();
														while(!MovieUtilIO.validateName(prodName)) {
															System.out.println("Name can only have letter and digits and must start with a letter.");
															System.out.println("Enter another producer name.");
															prodName = sc2.nextLine();
														}
														Logger.getInstance().log("Adding a producer " + prodName + " of the movie " + movieName);
														System.out.println("Enter rating of the movie 1-10.");
														rating = sc1.nextInt();
														while(!MovieUtilIO.validateRating(rating)) {
															System.out.println("Rating can only be between 1 - 10.");
															System.out.println("Enter another rating.");
															rating = sc1.nextInt();
														}
														Logger.getInstance().log("Adding a rating " + rating + " of the movie " + movieName);
														System.out.println("Enter review of the movie.");
														review = sc2.nextLine();
														Logger.getInstance().log("Adding a review " + review + " of the movie " + movieName);
														MovieBeanIO beanObj = new MovieBeanIO(movieName, dirName, prodName, rating, review);
														String msg1 = model.addMovie(beanObj, listName);
														if(msg1.equalsIgnoreCase("success")) {
															Logger.getInstance().log("Movie replaced with " + movieName + " successfully.");
															System.out.println("Movie is successfully added to the wish list.");
														}
														else {
															Logger.getInstance().log("Movie cannot be replaced because of an error - " + msg1);
															System.out.println("The addition failed because of error - " + msg1);
														}
													}
													else {
														Logger.getInstance().log("Movie " + movieName + " cannot be removed cause of an error - " + s);
														System.out.println("Error occured - " + s);
													}
												break;
											case 2 : System.out.println("Enter a movie title name to edit.");
													movieName = sc2.nextLine();
													while(!MovieUtilIO.validateName(movieName)) {
														System.out.println("Name can only have letter and digits and must start with a letter.");
														System.out.println("Enter another movie name.");
														movieName = sc2.nextLine();
													}
													System.out.println("Enter a new movie title");
													String movie = sc2.nextLine();
													while(!MovieUtilIO.validateName(movieName)) {
														System.out.println("Name can only have letter and digits and must start with a letter.");
														System.out.println("Enter another movie name.");
														movie = sc2.nextLine();
													}
													Logger.getInstance().log("Trying to change the movie name "  + movieName + " to " + movie);
													String message = model.replaceMovieTitle(movieName, movie, listName);
													if(message.equalsIgnoreCase("success")) {
														Logger.getInstance().log("Movie name changed successfully");
														System.out.println("Movie title is edited successfully.");
													}
													else {
														Logger.getInstance().log("Movie name cannot be chaned because of an error - " + message);
														System.out.println("Error occured - " + message);
													}
												break;
											case 3 : System.out.println("Enter a movie name to edit director name.");
													movieName = sc2.nextLine();
													while(!MovieUtilIO.validateName(movieName)) {
														System.out.println("Name can only have letter and digits and must start with a letter.");
														System.out.println("Enter another director name.");
														movieName = sc2.nextLine();
													}
													System.out.println("Enter new director name");
													String director = sc2.nextLine();
													while(!MovieUtilIO.validateName(director)) {
														System.out.println("Name can only have letter and digits and must start with a letter.");
														System.out.println("Enter another director name.");
														director = sc2.nextLine();
													}
													Logger.getInstance().log("Trying to change director name of movie " + movieName + " to " + director);
													String str = model.replaceDirectorTitle(movieName, director, listName);
													if(str.equalsIgnoreCase("Success")) {
														Logger.getInstance().log("Director name changed successfully.");
														System.out.println("Director name is edited successfully.");
													}
													else {
														Logger.getInstance().log("Director name cannot be changed because of an error -" + str);
														System.out.println("Error occured - " + str);
													}
												break;
											case 4 : System.out.println("Enter a movie name to edit producer name.");
													movieName = sc2.nextLine();
													while(!MovieUtilIO.validateName(movieName)) {
														System.out.println("Name can only have letter and digits and must start with a letter.");
														System.out.println("Enter another producer name.");
														movieName = sc2.nextLine();
													}
													System.out.println("Enter new producer name");
													String producer = sc2.nextLine();
													while(!MovieUtilIO.validateName(producer)) {
														System.out.println("Name can only have letter and digits and must start with a letter.");
														System.out.println("Enter another producer name.");
														producer = sc2.nextLine();
													}
													Logger.getInstance().log("Trying to change producer name of movie " + movieName);
													String message1 = model.replaceProducerTitle(movieName, producer, listName);
													if(message1.equalsIgnoreCase("Success")) {
														Logger.getInstance().log("Producer name changed successfully.");
														System.out.println("Producer name is edited successfully.");
													}
													else {
														Logger.getInstance().log("Producer name couldn't be changed cause of an error - " + message1);
														System.out.println("Error occured - " + message1);
													}
												break;
											case 5 : System.out.println("Returning back to previous menu.");
												break;
											default : System.out.println("Invalid input. Please choose the input from the menu.");
												break;
											}
										}
									break;
								case 3 : List<String> list = model.showMovies(listName);
										Logger.getInstance().log("Entered show all movies option of the list " + listName);
										System.out.print("All movies in the list = ");
										for(String s : list) {
											System.out.print(s + ", ");
										}
									break;
								case 4 : System.out.println("Going back to main menu.");
										Logger.getInstance().log("Going back to main menu.");
									break;
								default : System.out.println("Invalid input. Please choose the input from the menu.");
									break;
								}
							}
						}
						else {
							Logger.getInstance().log("The list cannot be loaded as the list doesn't exist.");
							System.out.println("The file doesn't exist.");
						}
					break;
				case 3 :while(sortChoice != 6) {
							Logger.getInstance().log("Entering listing menu of files.");
							System.out.println("");
							System.out.println("Press 1 to list all movies in alphabetical order.");
							System.out.println("Press 2 to list all movies based on director name.");
							System.out.println("Press 3 to list all movies based on producer name.");
							System.out.println("Press 4 to list all movies based on rating.");
							System.out.println("Press 5 to list all movies based on review.");
							System.out.println("Press 6 to go back to main menu.");
							sortChoice = sc1.nextInt();
							switch(sortChoice) {
							case 1 :System.out.println("Enter wish list name.");
									listName = sc2.nextLine();
									Logger.getInstance().log("Trying to list all the movies in " + listName + " in alphabetical order.");
									while(!MovieUtilIO.validateName(listName)) {
										System.out.println("The name can start with a letter and have only letters and digits.");
										System.out.println("Enter a new name");
										listName = sc2.nextLine();
									}
									if(model.checkIfListExists(listName)) {
										Set<MovieBeanIO> set = model.getMoviesAlphabetically(listName);
										if(set != null) {
											if(set.size() > 0) {
												System.out.println("Movies sorted alphabetically - ");
												for(MovieBeanIO m : set) {
													System.out.println(m);
												}
												Logger.getInstance().log("Movies of list " + listName + " displayed in alphabetical order successfully.");
											}
											else {
												Logger.getInstance().log("Movies of list cannot be displayed as the list is empty.");
												System.out.println("List is empty.");
											}
										}
										else { 
											Logger.getInstance().log("Movies of list " + listName + " cannot be displayed because the list deosn't exist");
											System.out.println("List doesn't exist.");
										}
									}
									else {
										Logger.getInstance().log("Movies of list " + listName +" cannot be displayed as the list doesn't exits.");
										System.out.println("There is no list with this name.");
									}
								break;
							case 2 :
								break;
							case 3 :System.out.println("Enter wish list name.");
									listName = sc2.nextLine();
									while(!MovieUtilIO.validateName(listName)) {
										System.out.println("The name can start with a letter and have only letters and digits.");
										System.out.println("Enter a new name");
										listName = sc2.nextLine();
									}
									Logger.getInstance().log("Trying to list all the movies in the " + listName + " based on producer name.");
									if(model.checkIfListExists(listName)) {
										List<MovieBeanIO> prodList = model.getMoviesBasedOnProducerName(listName);
										if(prodList != null) {
											if(prodList.size() > 0) {
												System.out.println("Movies sorted by producer name - ");
												for(MovieBeanIO m : prodList) {
													System.out.println(m);
												}
												Logger.getInstance().log("Movies of list " + listName + " displayed successfully based on producer name.");
											}
											else {
												Logger.getInstance().log("Movies of list " + listName + " cannot be displayed based on producer name as the list is empty.");
												System.out.println("List is empty.");
											}
										}
										else {
											Logger.getInstance().log("Movies of list " + listName + " cannot be displayed as the list doesn't exist");
											System.out.println("List doesn't exist.");
										}
									}
									else {
										Logger.getInstance().log("Movies of list " + listName +" cannot be displayed as the list doesn't exits.");
										System.out.println("There is no list with this name.");
									}
								break;
							case 4 :System.out.println("Enter wish list name.");
									listName = sc2.nextLine();
									while(!MovieUtilIO.validateName(listName)) {
										System.out.println("The name can start with a letter and have only letters and digits.");
										System.out.println("Enter a new name");
										listName = sc2.nextLine();
									}
									Logger.getInstance().log("Trying to list all the movies in " + listName + " based on ratings.");
									if(model.checkIfListExists(listName)) {
										List<MovieBeanIO> ratingList = model.getMoviesBasedOnRating(listName);
										if(ratingList != null) {
											if(ratingList.size() > 0) {
												System.out.println("Movies sorted by rating - ");
												for(MovieBeanIO m : ratingList) {
													System.out.println(m);
												}
												Logger.getInstance().log("Movies of list " + listName + " displayed successfully based on rating.");
											}
											else {
												Logger.getInstance().log("Movies of list " + listName + " cannot be displayed based on rating as the list is empty.");
												System.out.println("List is empty.");
											}
										}
										else {
											Logger.getInstance().log("Movies of list " + listName + " cannot be displayed as the list deosn't exist");
											System.out.println("List doesn't exist.");
										}
									}
									else {
										Logger.getInstance().log("Movies of list " + listName +" cannot be displayed as the list doesn't exits.");
										System.out.println("There is no list with this name.");
									}
								break;
							case 5 :System.out.println("Enter wish list name.");
									listName = sc2.nextLine();
									while(!MovieUtilIO.validateName(listName)) {
										System.out.println("The name can start with a letter and have only letters and digits.");
										System.out.println("Enter a new name");
										listName = sc2.nextLine();
									}
									Logger.getInstance().log("Trying to list all the movies in " + listName + " based on reviews.");
									if(model.checkIfListExists(listName)) {
										List<MovieBeanIO> reviewList = model.getMoviesBasedOnReviews(listName);
										if(reviewList != null) {
											if(reviewList.size() > 0) {
												System.out.println("Movies sorted by reviews - ");
												for(MovieBeanIO m : reviewList) {
													System.out.println(m);
												}
												Logger.getInstance().log("Movies of list " + listName + " displayed successfully based on reviews.");
											}
											else {
												Logger.getInstance().log("Movies of list " + listName + " cannot be displayed based on reviews as the list is empty.");
												System.out.println("List is empty.");
											}
										}
										else {
											Logger.getInstance().log("Movies of list " + listName + " cannot be displayed as the list deosn't exist");
											System.out.println("List doesn't exist.");
										}
									}
									else {
										Logger.getInstance().log("Movies of list " + listName +" cannot be displayed as the list doesn't exits.");
										System.out.println("There is no list with this name.");
									}
								break;
							case 6 : System.out.println("Returning to main menu.");
									Logger.getInstance().log("Returning back to main menu.");
								break;
							default : System.out.println("Invalid input. Please choose the input from the menu.");
								break;
							}
						}
					break;
				case 4 :Set<Entry<String,List<MovieBeanIO>>> setBean = model.getAllMoviesInAllLists();
						Logger.getInstance().log("Trying to display all movies in all the lists.");
						if(setBean != null) {
							if(setBean.size() > 0) {
								for(Entry<String, List<MovieBeanIO>> m : setBean) {
									System.out.println("List name - " + m.getKey());
									List<MovieBeanIO> n = m.getValue();
									for(MovieBeanIO bean : n) {
										System.out.println("Details - " +bean);
									}
									System.out.println("");
								}
								Logger.getInstance().log("All movies displayed successfully.");
							}
							else {
								Logger.getInstance().log("Movies cannot be displayed as there is no list.");
								System.out.println("There is no list to show.");
							}
						}
						else {
							Logger.getInstance().log("Movies cannot be displayed as the directory path is wrong.");
							System.out.println("Directory path is wrong contact the designer.");
						}
					break;
				case 5 :System.out.println("Enter wish list name.");
						listName = sc2.nextLine();
						
						while(!MovieUtilIO.validateName(listName)) {
							System.out.println("The name can start with a letter and have only letters and digits.");
							System.out.println("Enter a new name");
							listName = sc2.nextLine();
						}
						Logger.getInstance().log("Trying to check number of occurence of a word in the list " +listName);
						if(model.checkIfListExists(listName)) {
							System.out.println("Enter a word to search in the list.");
							word = sc2.nextLine();
							while(!MovieUtilIO.validateWord(word)) {
								System.out.println("Word can only have letters.");
								System.out.println("Enter another word.");
								word = sc2.nextLine();
							}
							Set<Entry<String,String>> set = model.numOfOccurenceInDirector(listName, word);
							if(set != null) {
								if(set.size() > 0) {
									int a = model.numOfOccurencesInTheList(listName, word);
									System.out.println("Total number of occurences in the list = " + a);
									int b = model.numOfOccurencesInDirectorName(listName, word);
									System.out.println("Total number of occurences in director = " + b);
									for(Entry<String,String> en : set) {
										System.out.println(en.toString().replace("[", "").replace("]",""));
									}
									int c = model.numOfOccurencesInMovies(listName, word);
									System.out.println("Total number of occurences in movies = " + c);
									Set<String> movieList = model.numOfOccurenceInMovieName(listName, word);
									for(String s : movieList) {
										System.out.println(s.toString().replace("[", "").replace("]", ""));
									}
									int d = model.numOfOccurencesInReviewOfMovie(listName, word);
									System.out.println("Total number of occurences in review = " + d);
									Set<String> reviewList = model.numOfOccurenceInReview(listName, word);
									for(String s1 : reviewList) {
										System.out.println(s1.toString().replace("[", "").replace("]", ""));
									}
									Logger.getInstance().log("Number of occurence of word " + word + " in the list " + listName + " displayed successfully");
								}
								else {
									Logger.getInstance().log("Movies of list " + listName +" cannot be displayed as the list doesn't exits.");
									System.out.println("Word is not there in the list.");
								}
							}
							else {
								Logger.getInstance().log("File " + listName +".txt doesn't exist.");
								System.out.println("File does't exist.");
							}
						}
						else {
							Logger.getInstance().log("List " +listName + " doesn't exist.");
							System.out.println("There is no list with this name.");
						}
					break;
				case 6 :System.out.println("Enter wish list name.");
						listName = sc2.nextLine();
						while(!MovieUtilIO.validateName(listName)) {
							System.out.println("The name can start with a letter and have only letters and digits.");
							System.out.println("Enter a new name");
							listName = sc2.nextLine();
						}
						Logger.getInstance().log("Trying to remove a movie from the list " + listName);
						if(model.checkIfListExists(listName)) {
							System.out.println("Enter a movie name you want to remove.");
							movieName = sc2.nextLine();
							while(!MovieUtilIO.validateName(movieName)) {
								System.out.println("The name can start with a letter and have only letters and digits.");
								System.out.println("Enter another movie name");
								movieName = sc2.nextLine();
							}
							Logger.getInstance().log("Reting to remove movie " + movieName + " from the list " + listName);
							String s = model.removeMovie(listName, movieName);
							if(s.equalsIgnoreCase("Success")) {
								Logger.getInstance().log("Movie " + movieName + " removed successfully from the list " + listName);
								System.out.println("The movie has been removed successfully.");
							}
							else {
								Logger.getInstance().log("Movie " + movieName + " cannot be removed from the list " + listName + " cause of an error -" + s);
								System.out.println("Error occured - " + s);
							}
						}
						else {
							Logger.getInstance().log("The list " + listName + " doesn't exist.");
							System.out.println("There is no list with this name.");
						}
					break;
				case 7 :List<String> fileList = model.getAllLists();
				Logger.getInstance().log("Trying to get all the lists.");
						if(fileList != null) {
							if(fileList.size() > 0) {
								System.out.println("Lists - ");
								for(String s : fileList) {
									System.out.println(s);
								}
								Logger.getInstance().log("Name of all the lists displayed succesfully.");
							}
							else {
								Logger.getInstance().log("There is no list.");
								System.out.println("There are no lists present.");
							}
						}
						else {
							Logger.getInstance().log("The directory path is wrong.");
							System.out.println("The directory path is wrong. Contact the desingner.");
						}
					break;
				case 8 :System.out.println("Enter a list name to delete.");
						listName = sc2.nextLine();
						while(!MovieUtilIO.validateName(listName)) {
							System.out.println("The name can start with a letter and have only letters and digits.");
							System.out.println("Enter a new name");
							listName = sc2.nextLine();
						}
						Logger.getInstance().log("Trying to delete a list " + listName);
						if(model.checkIfListExists(listName)) {
							String msg = model.delete(listName);
							if(msg.equalsIgnoreCase("success")) {
								Logger.getInstance().log("List " + listName + " deleted successfully.");
								System.out.println("The list is deleted successfully");
							}
							else {
								Logger.getInstance().log("List " + listName + " cannot be deleted cause of an erro - " + msg);
								System.out.println("Error occured - " + msg);
							}
						}
						else {
							Logger.getInstance().log("List " + listName + " doesn't exist.");
							System.out.println("There is no list with this name.");
						}
					break;
				case 9 : System.out.println("Tata bye bye.");
						Logger.getInstance().log("Exting the app.");
					break;
				default : System.out.println("Invalid input. Please choose the input from the menu.");
					break;
				}
			}
		}
		catch(Exception | Error e) {
			e.printStackTrace();
		}
		finally {
			if(sc1 != null) {
				sc1.close();
			}
			if(sc2 != null) {
				sc2.close();
			}
		}
	}
}