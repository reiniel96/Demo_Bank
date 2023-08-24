package com.mindhubbrothers.Mindhub.Brothers.Bank;

import com.mindhubbrothers.Mindhub.Brothers.Bank.enums.CardColor;
import com.mindhubbrothers.Mindhub.Brothers.Bank.enums.CardType;
import com.mindhubbrothers.Mindhub.Brothers.Bank.enums.LoanType;
import com.mindhubbrothers.Mindhub.Brothers.Bank.enums.TransactionType;
import com.mindhubbrothers.Mindhub.Brothers.Bank.models.*;
import com.mindhubbrothers.Mindhub.Brothers.Bank.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

import static com.mindhubbrothers.Mindhub.Brothers.Bank.utils.utils.genAccountId;

@SpringBootApplication
public class MindhubBrothersBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(MindhubBrothersBankApplication.class, args);
	}
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Bean
	public CommandLineRunner initData(ClientRepository ClientRepository,
									  AccountRepository AccountRepository,
									  TransactionRepository TransactionRepository,
									  LoanRepository LoanRepository,
									  ClientLoanRepository ClientLoanRepository,
									  CardRepository CardRepository) {
		return (args) -> {

			Client Melba= new Client("Melba", "Morel","MelMor@email.com", passwordEncoder.encode("123mel"));
			Client Chloe= new Client("Chloe", "O'Brian","ChlObri@email.com", passwordEncoder.encode("123cloe"));
			ClientRepository.save(Melba);
			ClientRepository.save(Chloe);

			Account cuenta1= new Account(genAccountId(AccountRepository),28000.0,LocalDate.now());
			Melba.addAccount(cuenta1);
			AccountRepository.save(cuenta1);


			Account cuenta2= new Account(genAccountId(AccountRepository),28000.0,LocalDate.now());
			Melba.addAccount(cuenta2);
			AccountRepository.save(cuenta2);

			Account cuenta3= new Account(genAccountId(AccountRepository),28000.0,LocalDate.now());
			Chloe.addAccount(cuenta3);
			AccountRepository.save(cuenta3);

			Transaction transaction1=new Transaction(TransactionType.CREDIT,125000.0,"Pago de Proyecto");
			Transaction transaction2=new Transaction(TransactionType.DEBIT,500.0,"Carga de sube");

			cuenta1.addTransaction(transaction1);
			cuenta1.addTransaction(transaction2);

			TransactionRepository.save(transaction1);
			TransactionRepository.save(transaction2);

			List<Integer> payments = List.of(2, 4,6,12,24);
			Loan loan1= new Loan(LoanType.mortgage, 300000, payments);
			Loan loan2= new Loan(LoanType.personal, 20000, payments);
			Loan loan3= new Loan(LoanType.automotive, 1500, payments);
			ClientLoan clientLoan1 =new ClientLoan();
			ClientLoan clientLoan2=new ClientLoan();
			ClientLoan clientLoan3=new ClientLoan();
			ClientLoan clientLoan4=new ClientLoan();

			Melba.addClientLoan(clientLoan1);
			Melba.addClientLoan(clientLoan2);
			Melba.addClientLoan(clientLoan3);

			loan1.addClientLoan(clientLoan1);
			loan2.addClientLoan(clientLoan2);
			loan3.addClientLoan(clientLoan3);

			Chloe.addClientLoan(clientLoan4);
			loan2.addClientLoan(clientLoan4);

			LoanRepository.save(loan1);
			LoanRepository.save(loan2);
			LoanRepository.save(loan3);

			ClientLoanRepository.save(clientLoan1);
			ClientLoanRepository.save(clientLoan2);
			ClientLoanRepository.save(clientLoan3);
			ClientLoanRepository.save(clientLoan4);

			Card card1 = new Card(
					CardType.DEBIT,
					CardColor.GOLD,
					LocalDate.now()

			);
			Card card2 = new Card(
					CardType.CREDIT,
					CardColor.TITANIUM,
					LocalDate.now()

			);
			Card card3 = new Card(
					CardType.CREDIT,
					CardColor.SILVER,
					LocalDate.now()

			);

			card1.addCardHolder(Melba);
			card2.addCardHolder(Melba);
			card3.addCardHolder(Chloe);

			CardRepository.save(card1);
			CardRepository.save(card2);
			CardRepository.save(card3);


		};
	}

}
