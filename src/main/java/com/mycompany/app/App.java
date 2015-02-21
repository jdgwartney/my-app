package com.mycompany.app;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Hello world!
 *
 */
public class App {
	
	private String message;
	
	public App(String message) {
		this.message = message;
	}
	
	public void execute(String [] args) throws InterruptedException, URISyntaxException {

		long count = 1;
		Configuration configuration = load("META-INF/json/configuration.json");
		while(true) {
			System.out.printf("%s-%s%n",this.message,count++);
			Thread.sleep(configuration.getSleepDelay());
		}
	}
	public static void main(String[] args) throws InterruptedException, URISyntaxException {
		App app = new App("HelloWorld!");
		app.execute(args);
	}
	
	public Configuration load(String resource) throws URISyntaxException {
		Configuration instance = null;

		ClassLoader classLoader = this.getClass().getClassLoader();
		URL url = classLoader.getResource(resource);
		File file = new File(url.toURI());

		ObjectMapper mapper = new ObjectMapper();

		try {
			instance = mapper.readValue(file,Configuration.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return instance;
	}
}
