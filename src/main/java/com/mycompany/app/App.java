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
	public static void main(String[] args) throws InterruptedException, URISyntaxException {
		long count = 1;
		Configuration configuration = load("META-INF/json/configuration.json");
		while(true) {
			System.out.printf("Hello World!-%s%n",count++);
			Thread.sleep(configuration.getSleepDelay());
		}
	}
	
	public static Configuration load(String resource) throws URISyntaxException {
		Configuration instance = new Configuration();

		ClassLoader classLoader = instance.getClass().getClassLoader();
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
