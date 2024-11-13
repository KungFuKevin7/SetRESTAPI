package com.example.SetRESTAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SetRestapiApplication{

	public static void main(String[] args) {

		SpringApplication.run(SetRestapiApplication.class, args);
	}


	public void InsertAllCards() {

		char[] shapes = {'W','O','D'};
		char[] textures = {'H','E','F'};
		int[] amounts = {1,2,3};
		char[] colours= {'G','R','B'};

		for (char shape : shapes) {
			for (char texture : textures) {
				for (char colour : colours){
					for (int amount : amounts) {

					}
				}
			}
		}
	}
}
