package com.mindhubbrothers.Mindhub.Brothers.Bank;

import com.mindhubbrothers.Mindhub.Brothers.Bank.Enums.CardColor;
import com.mindhubbrothers.Mindhub.Brothers.Bank.Enums.CardType;
import com.mindhubbrothers.Mindhub.Brothers.Bank.Enums.LoanType;
import com.mindhubbrothers.Mindhub.Brothers.Bank.Enums.TransactionType;
import com.mindhubbrothers.Mindhub.Brothers.Bank.models. *;
import com.mindhubbrothers.Mindhub.Brothers.Bank.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class MindhubBrothersBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(MindhubBrothersBankApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(ClientRepository ClientRepository,
									  AccountRepository AccountRepository,
									  TransactionRepository TransactionRepository,
									  LoanRepository LoanRepository,
									  ClientLoanRepository ClientLoanRepository,
									  com.mindhubbrothers.Mindhub.Brothers.Bank.repositories.CardRepository CardRepository) {
		return (args) -> {

			com.mindhubbrothers.Mindhub.Brothers.Bank.models.Client Melba= new com.mindhubbrothers.Mindhub.Brothers.Bank.models.Client("Gonza", "Legarda","gonzalegarda@gmail.com");
			com.mindhubbrothers.Mindhub.Brothers.Bank.models.Client Chloe= new com.mindhubbrothers.Mindhub.Brothers.Bank.models.Client("Ema", "O'Raspu","emaoraspu@gmail.com");
			ClientRepository.save(Melba);
			ClientRepository.save(Chloe);

			com.mindhubbrothers.Mindhub.Brothers.Bank.models.Account cuenta1= new com.mindhubbrothers.Mindhub.Brothers.Bank.models.Account("VIN"+String.format("%03d",AccountRepository.count()+1 ),35000.0,LocalDate.now());
			Melba.addAccount(cuenta1);
			AccountRepository.save(cuenta1);


			com.mindhubbrothers.Mindhub.Brothers.Bank.models.Account cuenta2= new com.mindhubbrothers.Mindhub.Brothers.Bank.models.Account("VIN"+String.format("%03d", AccountRepository.count()+1),25000.0,LocalDate.now());
			Melba.addAccount(cuenta2);
			AccountRepository.save(cuenta2);

			com.mindhubbrothers.Mindhub.Brothers.Bank.models.Account cuenta3= new com.mindhubbrothers.Mindhub.Brothers.Bank.models.Account("VIN"+String.format("%03d", AccountRepository.count()+1),10000.0,LocalDate.now());
			Chloe.addAccount(cuenta3);
			AccountRepository.save(cuenta3);

			com.mindhubbrothers.Mindhub.Brothers.Bank.models.Transaction transaction1=new com.mindhubbrothers.Mindhub.Brothers.Bank.models.Transaction(TransactionType.CREDIT,125000.0,"Pago de Proyecto");
			com.mindhubbrothers.Mindhub.Brothers.Bank.models.Transaction transaction2=new com.mindhubbrothers.Mindhub.Brothers.Bank.models.Transaction(TransactionType.DEBIT,500.0,"Carga de sube");

			cuenta1.addTransaction(transaction1);
			cuenta1.addTransaction(transaction2);

			TransactionRepository.save(transaction1);
			TransactionRepository.save(transaction2);

			List<Integer> payments = List.of(2, 4,6,12,24);
			com.mindhubbrothers.Mindhub.Brothers.Bank.models.Loan loan1= new com.mindhubbrothers.Mindhub.Brothers.Bank.models.Loan(LoanType.mortgage, 300000, payments);
			com.mindhubbrothers.Mindhub.Brothers.Bank.models.Loan loan2= new com.mindhubbrothers.Mindhub.Brothers.Bank.models.Loan(LoanType.personal, 20000, payments);
			com.mindhubbrothers.Mindhub.Brothers.Bank.models.Loan loan3= new com.mindhubbrothers.Mindhub.Brothers.Bank.models.Loan(LoanType.automotive, 1500, payments);
			com.mindhubbrothers.Mindhub.Brothers.Bank.models.ClientLoan clientLoan1 =new com.mindhubbrothers.Mindhub.Brothers.Bank.models.ClientLoan();
			com.mindhubbrothers.Mindhub.Brothers.Bank.models.ClientLoan clientLoan2=new com.mindhubbrothers.Mindhub.Brothers.Bank.models.ClientLoan();
			com.mindhubbrothers.Mindhub.Brothers.Bank.models.ClientLoan clientLoan3=new com.mindhubbrothers.Mindhub.Brothers.Bank.models.ClientLoan();
			com.mindhubbrothers.Mindhub.Brothers.Bank.models.ClientLoan clientLoan4=new com.mindhubbrothers.Mindhub.Brothers.Bank.models.ClientLoan();			Melba.addClientLoan(clientLoan1);
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
					"4512-4587-6584-5415",
					120,
					LocalDate.now(),
					LocalDate.now().plusYears(6)
			);
			Card card2 = new Card(
					CardType.CREDIT,
					CardColor.TITANIUM,
					"6514-4875-5124-6541",
					320,
					LocalDate.now(),
					LocalDate.now().plusYears(3)
			);
			Card card3 = new Card(
					CardType.CREDIT,
					CardColor.SILVER,
					"8451-8752-6481-6512",
					903,
					LocalDate.now(),
					LocalDate.now().plusYears(5)
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
