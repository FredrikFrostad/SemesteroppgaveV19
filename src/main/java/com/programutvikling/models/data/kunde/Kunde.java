package com.programutvikling.models.data.kunde;

import com.programutvikling.models.data.forsikring.Forsikring;
import com.programutvikling.models.data.skademelding.Skademelding;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class Kunde implements Serializable {

    private Date kundeOpprettet;
    private String fornavn;
    private String etternavn;
    private String forsikrNr;
    private String Fakturaadresse;
    private ArrayList<Forsikring> forsikringer = new ArrayList<>();
    private ArrayList<Skademelding> skademeldinger = new ArrayList<>();
    private ArrayList<Double> utbetaltErstatn = new ArrayList<>();

    private static final DateTimeFormatter dtf = DateTimeFormatter.BASIC_ISO_DATE;

    public Kunde(String fornavn, String etternavn, String forsikrNr, String fakturaadresse) {
        this.kundeOpprettet = new Date();
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.forsikrNr = forsikrNr;
        this.Fakturaadresse = fakturaadresse;
    }


    public Date getKundeOpprettet() {
        return kundeOpprettet;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public String getForsikrNr() {
        return forsikrNr;
    }

    public void setForsikrNr(String forsikrNr) {
        this.forsikrNr = forsikrNr;
    }

    public String getFakturaadresse() {
        return Fakturaadresse;
    }

    public void setFakturaadresse(String fakturaadresse) {
        Fakturaadresse = fakturaadresse;
    }

    public ArrayList<Forsikring> getForsikringer() {
        return forsikringer;
    }

    public ArrayList<Skademelding> getSkademeldinger() {
        return skademeldinger;
    }

    public ArrayList<Double> getUtbetaltErstatn() {
        return utbetaltErstatn;
    }

    @Override
    public String toString() {
        return "Kunde{" +
                "kundeOpprettet=" + kundeOpprettet +
                ", fornavn='" + fornavn + '\'' +
                ", etternavn='" + etternavn + '\'' +
                ", forsikrNr='" + forsikrNr + '\'' +
                ", Fakturaadresse='" + Fakturaadresse + '\'' +
                '}';
    }
}
