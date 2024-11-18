package com.example.SetRESTAPI;

import com.example.SetRESTAPI.logic.SetLogic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SetRestapiApplication{

	public static void main(String[] args) {
/*
		//Get All Playing Cards
		SetLogic SL = new SetLogic();
		SL.insertPlayingCards();*/

		SpringApplication.run(SetRestapiApplication.class, args);
	}

}
