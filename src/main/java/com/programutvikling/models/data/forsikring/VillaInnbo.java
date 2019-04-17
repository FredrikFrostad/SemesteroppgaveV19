package com.programutvikling.models.data.forsikring;

import com.programutvikling.models.data.ObjectType;

public class VillaInnbo {

    private static ObjectType type = ObjectType.VILLAINNBO;
    private String adresse;
    private int byggeÅr;
    private String boligType;
    private String byggemateriale;
    private String standard;
    private int areal;
    private int beløpBygning;
    private int beløpInnbo;

    public VillaInnbo(String adresse, int byggeÅr, String boligType, String byggemateriale,
                      String standard, int areal, int beløpBygning, int beløpInnbo) {
        this.adresse = adresse;
        this.byggeÅr = byggeÅr;
        this.boligType = boligType;
        this.byggemateriale = byggemateriale;
        this.standard = standard;
        this.areal = areal;
        this.beløpBygning = beløpBygning;
        this.beløpInnbo = beløpInnbo;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getByggeÅr() {
        return byggeÅr;
    }

    public void setByggeÅr(int byggeÅr) {
        this.byggeÅr = byggeÅr;
    }

    public String getBoligType() {
        return boligType;
    }

    public void setBoligType(String boligType) {
        this.boligType = boligType;
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

    public int getBeløpBygning() {
        return beløpBygning;
    }

    public void setBeløpBygning(int beløpBygning) {
        this.beløpBygning = beløpBygning;
    }

    public int getBeløpInnbo() {
        return beløpInnbo;
    }

    public void setBeløpInnbo(int beløpInnbo) {
        this.beløpInnbo = beløpInnbo;
    }

    @Override
    public String toString() {
        return "VillaInnbo{" +
                "adresse=" + adresse +
                ", byggeÅr=" + byggeÅr +
                ", boligType=" + boligType +
                ", byggemateriale=" + byggemateriale +
                ", standard=" + standard + 
                ", areal=" + areal +
                ", beløpBygning=" + beløpBygning +
                ", beløpInnbo=" + beløpInnbo +
                ", type=" + type +
                '}';
    }
}
