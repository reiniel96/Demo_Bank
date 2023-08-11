package com.mindhubbrothers.Mindhub.Brothers.Bank.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.awt.*;
import java.time.LocalDate;
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;
    private Enum type;
    private String amount;
    private String description;

    


  enum Level{
      CREDIT,
      DEBIT
  }

    public Transaction (String type, String amount, String description ,LocalDate LocalDate) {
    }

    public Transaction(long id, Enum type, String amount, String description,LocalDate localDate) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.description= description;
    }
    public Transaction (){

    }

    public Long getId() {
        return id;
    }

    public Enum getType() {
        return type;
    }

    public void setType(Enum type) {
        this.type = type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
