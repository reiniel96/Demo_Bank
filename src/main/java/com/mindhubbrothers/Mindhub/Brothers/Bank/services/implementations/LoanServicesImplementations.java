package com.mindhubbrothers.Mindhub.Brothers.Bank.services.implementations;

import com.mindhubbrothers.Mindhub.Brothers.Bank.dto.ClientLoanRecord;
import com.mindhubbrothers.Mindhub.Brothers.Bank.dto.LoanDTO;
import com.mindhubbrothers.Mindhub.Brothers.Bank.models.ClientLoan;
import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Transaction;
import com.mindhubbrothers.Mindhub.Brothers.Bank.enums.TransactionType;
import com.mindhubbrothers.Mindhub.Brothers.Bank.repositories.*;
import com.mindhubbrothers.Mindhub.Brothers.Bank.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;
@Service
public class LoanServicesImplementations implements LoanService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ClientLoanRepository clientLoanRepository;

    @Override
    public List<LoanDTO> getAll() {
        return loanRepository.findAll().stream()
                .map(loan -> new LoanDTO(loan))
                .collect(toList());
    }

    @Override
    public void createLoan(ClientLoanRecord clientLoanRecord, Authentication authentication) {
        Long loanId = clientLoanRecord.getLoanId();
        Double amount = clientLoanRecord.getAmount();
        Integer payments = clientLoanRecord.getPayments();
        String toAccountNumber = clientLoanRecord.getToAccountNumber();


        clientRepository.findByEmail(authentication.getName());
        ClientLoan clientLoan =new ClientLoan(amount,payments);


        clientRepository.findByEmail(authentication.getName()).addClientLoan(clientLoan);
        loanRepository.findById(loanId).get().addClientLoan(clientLoan);

        clientLoanRepository.save(clientLoan);

        //creamos la transaccion
        Transaction transactionLoan = new Transaction(TransactionType.CREDIT,amount, "loan approved");

        //actualizamos el balance de la cuenta destino
        accountRepository.findByNumber(toAccountNumber).setBalance(accountRepository.findByNumber(toAccountNumber).getBalance() + amount);
        accountRepository.findByNumber(toAccountNumber).addTransaction(transactionLoan);
        //guardamos la transaccion
        transactionRepository.save(transactionLoan);
        //guardamos los cambios en las cuentas
        accountRepository.save(accountRepository.findByNumber(toAccountNumber));
    }
}
