package com.programutvikling.models.data.skademelding;

import java.util.Date;

public class Skademelding {

    private Date skadeDato;
    private int skadeNr;
    private String typeSkade;
    private String skadeBeskrivelse;
    private String kontaktinfoVitner;
    private double takseringsBelop;
    private double utbetaltErstatning;

    public Skademelding(){}

    public Skademelding(Date skadeDato, int skadeNr, String typeSkade, String skadeBeskrivelse, String kontaktinfoVitner, double takseringsBelop, double utbetaltErstatning) {
        this.skadeDato = skadeDato;
        this.skadeNr = skadeNr;
        this.typeSkade = typeSkade;
        this.skadeBeskrivelse = skadeBeskrivelse;
        this.kontaktinfoVitner = kontaktinfoVitner;
        this.takseringsBelop = takseringsBelop;
        this.utbetaltErstatning = utbetaltErstatning;
    }


    public Date getSkadeDato() {
        return skadeDato;
    }

    public void setSkadeDato(Date skadeDato) {
        this.skadeDato = skadeDato;
    }

    public int getSkadeNr() {
        return skadeNr;
    }

    public void setSkadeNr(int skadeNr) {
        this.skadeNr = skadeNr;
    }

    public String getTypeSkade() {
        return typeSkade;
    }

    public void setTypeSkade(String typeSkade) {
        this.typeSkade = typeSkade;
    }

    public String getSkadeBeskrivelse() {
        return skadeBeskrivelse;
    }

    public void setSkadeBeskrivelse(String skadeBeskrivelse) {
        this.skadeBeskrivelse = skadeBeskrivelse;
    }

    public String getKontaktinfoVitner() {
        return kontaktinfoVitner;
    }

    public void setKontaktinfoVitner(String kontaktinfoVitner) {
        this.kontaktinfoVitner = kontaktinfoVitner;
    }

    public double getTakseringsBelop() {
        return takseringsBelop;
    }

    public void setTakseringsBelop(double takseringsBelop) {
        this.takseringsBelop = takseringsBelop;
    }

    public double getUtbetaltErstatning() {
        return utbetaltErstatning;
    }

    public void setUtbetaltErstatning(double utbetaltErstatning) {
        this.utbetaltErstatning = utbetaltErstatning;
    }

    @Override
    public String toString() {
        return "Skademelding{" +
                "skadeDato=" + skadeDato +
                ", skadeNr=" + skadeNr +
                ", typeSkade='" + typeSkade + '\'' +
                ", skadeBeskrivelse='" + skadeBeskrivelse + '\'' +
                ", kontaktinfoVitner='" + kontaktinfoVitner + '\'' +
                ", takseringsBelop=" + takseringsBelop +
                ", utbetaltErstatning=" + utbetaltErstatning +
                '}';
    }
}
