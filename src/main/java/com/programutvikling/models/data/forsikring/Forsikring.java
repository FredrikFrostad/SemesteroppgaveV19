package com.programutvikling.models.data.forsikring;

import com.programutvikling.models.data.ObjectType;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public abstract class Forsikring implements Serializable {

    private static ObjectType type = ObjectType.FORSIKRING;
    private int forsNr;
    private double premieAnnum;
    private double forsikringsSum;
    private LocalDate avtaleOpprettet;
    private String betingelser;
    private transient String filePath;

    public Forsikring() {
    }

    public Forsikring(int forsNr, double premieAnnum, double forsikringsSum, String betingelser) {
        this.forsNr = forsNr;
        this.premieAnnum = premieAnnum;
        this.forsikringsSum = forsikringsSum;
        this.avtaleOpprettet = LocalDate.now();
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

    public LocalDate getAvtaleOpprettet() {
        return avtaleOpprettet;
    }

    public void setAvtaleOpprettet(LocalDate avtaleOpprettet) throws IllegalAccessException {
        if (this.avtaleOpprettet != null) {
            throw new IllegalAccessException("The date cannot be changed once it is set");
        }
        this.avtaleOpprettet = avtaleOpprettet;
    }


    public String getBetingelser() {
        return betingelser;
    }

    public void setBetingelser(String betingelser) {
        this.betingelser = betingelser;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setForsNr(int nr) {this.forsNr = nr;}

    @Override
    public String toString() {
        return  "forsikringsNummer=" + forsNr +
                ", premieAnnum=" + premieAnnum +
                ", forsikringsSum=" + forsikringsSum +
                ", avtaleOpprettet=" + avtaleOpprettet +
                ", betingelser=" + betingelser;
    }
}
