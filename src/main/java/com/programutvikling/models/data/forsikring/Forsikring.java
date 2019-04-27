package com.programutvikling.models.data.forsikring;

import com.programutvikling.models.data.ObjectType;

import java.io.Serializable;
import java.util.Date;

public abstract class Forsikring implements Serializable {

    private static ObjectType type = ObjectType.FORSIKRING;
    private int forsNr;
    private double premieAnnum;
    private double forsikringsSum;
    private Date avtaleOpprettet;
    private String betingelser;
    private transient String filePath;

    public Forsikring() {
        this.avtaleOpprettet = new Date();
    }

    public Forsikring(int forsNr, double premieAnnum, double forsikringsSum, String betingelser) {
        this.forsNr = forsNr;
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

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return  "forsikringsNummer=" + forsNr +
                ", premieAnnum=" + premieAnnum +
                ", forsikringsSum=" + forsikringsSum +
                ", avtaleOpprettet=" + avtaleOpprettet +
                ", betingelser=" + betingelser;
    }
}
