package com.programutvikling.models.data.forsikring;

import com.programutvikling.models.data.ObjectType;

import java.io.Serializable;
import java.util.Date;

public abstract class Forsikring implements Serializable {

    private static ObjectType type = ObjectType.FORSIKRING;
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

    @Override
    public String toString() {
        return "premieAnnum=" + premieAnnum +
                ", forsikringsSum=" + forsikringsSum +
                ", avtaleOpprettet=" + avtaleOpprettet +
                ", betingelser=" + betingelser;
    }
}
