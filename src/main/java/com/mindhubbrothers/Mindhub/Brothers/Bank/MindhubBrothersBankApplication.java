package com.mindhubbrothers.Mindhub.Brothers.Bank;

import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Account;
import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Client;
import com.mindhubbrothers.Mindhub.Brothers.Bank.repositories.AccountRepository;
import com.mindhubbrothers.Mindhub.Brothers.Bank.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class MindhubBrothersBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(MindhubBrothersBankApplication.class, args);

	}

	@Bean
	public CommandLineRunner init(ClientRepository clientRepository, AccountRepository accountRepository){
		return args ->{
            //Se crea el cliente
			Client client1 = new Client("Reiniel","Garcia","12345678","Piedras 1400","17/08/1996", "1070");
			//Se guarda en la Base de datos
			clientRepository.save(client1);

			//Se crea la cuenta
			Account account1 = new Account("12456","784512", LocalDate.now());
			//Se guarda en la bd

			//Agregar la cuenta al cliente
			client1.AddAccount(account1);

			//Guardar en la bs
			accountRepository.save(account1);
		} ;



	}


	}


