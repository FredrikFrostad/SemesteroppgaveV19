package com.programutvikling.models.data.forsikring;

import com.programutvikling.models.data.ObjectType;

import java.io.Serializable;

public class Fritidsbolig extends Forsikring implements Serializable {


    private static ObjectType type = ObjectType.FRITIDSBOLIG;
    private String adresse;
    private int byggeaar;
    private String boligtype;
    private String byggemateriale;
    private String standard;
    private int areal;
    private double forsikringsbeløpByggning;
    private double forsikringsbeløpInnbo;

    public Fritidsbolig() {
        super();
    }

    public Fritidsbolig(double premieAnnum, double forsikringsSum, String betingelser, String adresse, int byggeaar,
                        String boligtype, String byggemateriale, String standard, int areal,
                        double forsikringsbeløpByggning, double forsikringsbeløpInnbo) {
        super(premieAnnum, forsikringsSum, betingelser);
        this.adresse = adresse;
        this.byggeaar = byggeaar;
        this.boligtype = boligtype;
        this.byggemateriale = byggemateriale;
        this.standard = standard;
        this.areal = areal;
        this.forsikringsbeløpByggning = forsikringsbeløpByggning;
        this.forsikringsbeløpInnbo = forsikringsbeløpInnbo;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getByggeaar() {
        return byggeaar;
    }

    public void setByggeaar(int byggeaar) {
        this.byggeaar = byggeaar;
    }

    public String getBoligtype() {
        return boligtype;
    }

    public void setBoligtype(String boligtype) {
        this.boligtype = boligtype;
    }

    public String getByggemateriale() {
        return byggemateriale;
    }

    public void setByggemateriale(String byggemateriale) {
        this.byggemateriale = byggemateriale;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public int getAreal() {
        return areal;
    }

    public void setAreal(int areal) {
        this.areal = areal;
    }

    public double getForsikringsbeløpByggning() {
        return forsikringsbeløpByggning;
    }

    public void setForsikringsbeløpByggning(double forsikringsbeløpByggning) {
        this.forsikringsbeløpByggning = forsikringsbeløpByggning;
    }

    public double getForsikringsbeløpInnbo() {
        return forsikringsbeløpInnbo;
    }

    public void setForsikringsbeløpInnbo(double forsikringsbeløpInnbo) {
        this.forsikringsbeløpInnbo = forsikringsbeløpInnbo;
    }

    @Override
    public String toString() {
        return "Fritidsbolig{" +
                super.toString() +
                "adresse=" + adresse +
                ", byggeaar=" + byggeaar +
                ", boligtype=" + boligtype +
                ", byggemateriale=" + byggemateriale +
                ", standard=" + standard +
                ", areal=" + areal +
                ", forsikringsbeløpByggning=" + forsikringsbeløpByggning +
                ", forsikringsbeløpInnbo=" + forsikringsbeløpInnbo +
                ", type=" + type +
                '}';
    }
}
