package com.example.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String species;
    @Column
    private String food;
     @Column
    private int amount;
    @Column
    private String assignedEnclosure;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSpecies() {
        return this.species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getNumberOf() {
        return this.amount;
    }

    public void setNumberOf(int amount) {
        this.amount = amount;
    }

    public String getFood() {
        return this.food;
    }

    public void setFood(String Food) {
        this.food = Food;
    }

    public String getAssignedEnclosure() {
        return this.assignedEnclosure;
    }

    public void setAssignedEnclosure(String assignedEnclosure) {
        this.assignedEnclosure = assignedEnclosure;
    }


    

}
