package com.wasteflow.domain;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "waste_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Waste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "berat_kg")
    private double berat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getBerat() {
        return berat;
    }

    public void setBerat(double berat) {
        this.berat = berat;
    }

    public abstract double calculatePoints(double multiplier);
    public abstract String getWasteType();
}
