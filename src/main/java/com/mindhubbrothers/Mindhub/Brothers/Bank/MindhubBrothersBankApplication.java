package com.mindhubbrothers.Mindhub.Brothers.Bank;

import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Client;
import com.mindhubbrothers.Mindhub.Brothers.Bank.repositories.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MindhubBrothersBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(MindhubBrothersBankApplication.class, args);

	}

	@Bean
	public CommandLineRunner init(PersonRepository personRepository){
		return args ->{

			Client client = new Client("Reiniel","Garcia","12345678","Piedras 1400","17/08/1996", "1070");

			personRepository.save(client);
		} ;



	}


	}


