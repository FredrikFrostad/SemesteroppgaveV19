package com.programutvikling.models.data.forsikring;

import java.util.Date;

public abstract class Forsikring {

    private double premieAnnum;
    private double forsikringsSum;
    private Date avtaleOpprettet;
    private String betingelser;

    public Forsikring() {
        this.avtaleOpprettet = new Date();
    }

    public Forsikring(double premieAnnum, double forsikringsSum, String betingelser) {
        this.premieAnnum = premieAnnum;
        this.forsikringsSum = forsikringsSum;
        this.avtaleOpprettet = new Date();
        this.betingelser = betingelser;
    }

    public double getPremieAnnum() {
        return premieAnnum;
    }

    public void setPremieAnnum(double premieAnnum) {
        this.premieAnnum = premieAnnum;
    }

    public double getForsikringsSum() {
        return forsikringsSum;
    }

    public void setForsikringsSum(double forsikringsSum) {
        this.forsikringsSum = forsikringsSum;
    }

    public Date getAvtaleOpprettet() {
        return avtaleOpprettet;
    }

    public void setAvtaleOpprettet(Date avtaleOpprettet) {
        this.avtaleOpprettet = avtaleOpprettet;
    }

    public String getBetingelser() {
        return betingelser;
    }

    public void setBetingelser(String betingelser) {
        this.betingelser = betingelser;
    }
}
