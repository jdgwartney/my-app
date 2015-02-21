package com.mycompany.app;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws InterruptedException {
		long count = 1;
		while(true) {
			System.out.printf("Hello World!-%s%n",count++);
			Thread.sleep(5000);
		}
	}
}
