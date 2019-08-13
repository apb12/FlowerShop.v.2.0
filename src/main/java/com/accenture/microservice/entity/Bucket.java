package com.accenture.microservice.entity;

import javax.persistence.*;

@Entity
public class Bucket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Evidence evidence;

    @OneToOne
    private FlowerEntity flower;

    private Long amount;

    private Double sum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Evidence getEvidence() {
        return evidence;
    }

    public void setEvidence(Evidence evidence) {
        this.evidence = evidence;
    }

    public FlowerEntity getFlower() {
        return flower;
    }

    public void setFlower(FlowerEntity flower) {
        this.flower = flower;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }
}
