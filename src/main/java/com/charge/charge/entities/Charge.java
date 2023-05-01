package com.charge.charge.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name = "charges")

public class Charge {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id;
    private String title;
    private double montant;
    private LocalDate dateDernierDelai;
    private LocalDate datePaiement;
    private String observation;
    @ManyToOne
    @JoinColumn(name = "famille_id")
    private Famille famille;

    public Charge(){

    }

    public Charge(int id, String title, double montant, LocalDate dateDernierDelai, LocalDate datePaiement, String observation) {
        this.id = id;
        this.title = title;
        this.montant = montant;
        this.dateDernierDelai = dateDernierDelai;
        this.datePaiement = datePaiement;
        this.observation = observation;
        this.famille = famille;
    }

    public Charge(String title, double montant, LocalDate dateDernierDelai, LocalDate datePaiement, String observation) {

        this.title = title;
        this.montant = montant;
        this.dateDernierDelai = dateDernierDelai;
        this.datePaiement = datePaiement;
        this.observation = observation;
    }

    public Charge(String title, double montant, LocalDate dateDernierDelai, LocalDate datePaiement, String observation, Famille famille) {
        this.title = title;
        this.montant = montant;
        this.dateDernierDelai = dateDernierDelai;
        this.datePaiement = datePaiement;
        this.observation = observation;
        this.famille = famille;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public LocalDate getDateDernierDelai() {
        return dateDernierDelai;
    }

    public void setDateDernierDelai(LocalDate dateDernierDelai) {
        this.dateDernierDelai = dateDernierDelai;
    }

    public LocalDate getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(LocalDate datePaiement) {
        this.datePaiement = datePaiement;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }
}
