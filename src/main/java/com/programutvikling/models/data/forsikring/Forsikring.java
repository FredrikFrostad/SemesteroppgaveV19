package com.programutvikling.models.data.forsikring;

import com.programutvikling.models.data.ObjectType;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Forsikring implements Serializable {

    private ObjectType type;
    private int forsikrNr;
    private double premieAnnum;
    private double forsikringsSum;
    private LocalDate avtaleOpprettet;
    private String betingelser;
    private transient String filePath;

    /**
     * @author 576
     */
    public Forsikring() {
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

    public void setForsikrNr(int nr) {this.forsikrNr = nr;}

    public int getForsikrNr() {return this.forsikrNr;}

    public void setType (ObjectType type) {this.type = type;}

    public ObjectType getType() {return this.type;}


    @Override
    public String toString() {
        return  "forsikringsNummer=" + forsikrNr +
                ", premieAnnum=" + premieAnnum +
                ", forsikringsSum=" + forsikringsSum +
                ", avtaleOpprettet=" + avtaleOpprettet +
                ", betingelser=" + betingelser;
    }
    public abstract String customStringForSkademelding();
}
