package com.uttara.bhupendra.FilmyGyaan;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * Represents a logger.
 * 
 * @author Bhupendra Singh
 *
 */
public class Logger {

	private String path = System.getProperty("user.home") + "/Desktop/FilmyGyaanLogger.txt";
	
	private Logger() {}
	
	private static class BillPughLogger{
		private static final Logger INSTANCE = new Logger();
	}
	
	public static Logger getInstance() {
		return BillPughLogger.INSTANCE;
	}
	
	public void log(final String msg) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				BufferedWriter bw = null;
				try {
					bw = new BufferedWriter(new FileWriter(path, true));
					Date date = new Date();
					bw.write(date + " : " + msg);
					bw.newLine();
				}
				catch(IOException e) {
					e.printStackTrace();
				}
				finally{
					if(bw != null) {
						try {
							bw.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}).start();
	}
}
