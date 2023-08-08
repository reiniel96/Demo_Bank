package com.mindhubbrothers.Mindhub.Brothers.Bank.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//Una clase dentro de la Base de Datos
@Entity
public class Client {

     //Attribute
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;
    private String name;
    private String lastname;
    private String dni;
    private String direction;
    private String birthdate;
    private String cp;



    //Builder

    public Client(Long id,String name, String lastname, String dni, String direction, String birthdate, String cp) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.dni = dni;
        this.direction = direction;
        this.birthdate = birthdate;
        this.cp = cp;


    }

    public Client(String name, String lastname, String dni, String direction, String birthdate, String cp) {

    }

    public Client() {

    }


    //Methods

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String  getDni() {
        return dni;
    }

    public void setDni(String  dni) {
        this.dni = dni;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", dni='" + dni + '\'' +
                ", direction='" + direction + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", cp='" + cp + '\'' +
                '}';
    }
}


