package com.programutvikling.models.data.kunde;

import com.programutvikling.models.data.ObjectType;
import com.programutvikling.models.data.forsikring.Forsikring;
import com.programutvikling.models.data.skademelding.Skademelding;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class Kunde implements Serializable {

    private static ObjectType type = ObjectType.KUNDE;
    private LocalDate kundeOpprettet;
    private String fornavn;
    private String etternavn;
    private int forsikrNr;
    private String Fakturaadresse;
    private transient String filePath;
    private ArrayList<Forsikring> forsikringer = new ArrayList<>();
    private ArrayList<Skademelding> skademeldinger = new ArrayList<>();
    private ArrayList<Double> utbetaltErstatn = new ArrayList<>();

    public static final DateTimeFormatter dtf = DateTimeFormatter.BASIC_ISO_DATE;

    public Kunde() {}

    public Kunde(String fornavn, String etternavn, int forsikrNr, String fakturaadresse) {
        this.kundeOpprettet = LocalDate.now();
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.forsikrNr = forsikrNr;
        this.Fakturaadresse = fakturaadresse;
    }

    public void setKundeOpprettet(LocalDate kundeOpprettet) throws IllegalAccessException {
        if (this.kundeOpprettet != null) {
            throw new IllegalAccessException("The date cannot be changed once it is set");
        }
        this.kundeOpprettet = kundeOpprettet;
    }

    public LocalDate getKundeOpprettet() {
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

    public int getForsikrNr() {
        return forsikrNr;
    }

    public void setForsikrNr(int forsikrNr) {
        this.forsikrNr = forsikrNr;
    }

    public String getFakturaadresse() {
        return Fakturaadresse;
    }

    public void setFakturaadresse(String fakturaadresse) {
        Fakturaadresse = fakturaadresse;
    }

    public ArrayList<Forsikring> getForsikringer() {return forsikringer;}

    public ArrayList<Skademelding> getSkademeldinger() {
        return skademeldinger;
    }

    public ArrayList<Double> getUtbetaltErstatn() {
        return utbetaltErstatn;
    }

    public int getNmbrOfPolicies() {
        return forsikringer.size();
    }

    public double getYearlyPremium() {
        Double out = 0.0;

        for (Forsikring f : forsikringer) {
            out += f.getPremieAnnum();
        }
        return out;
    }

    @Override
    public String toString() {
        return  "type=" + type +
                ", kundeOpprettet=" + kundeOpprettet +
                ", fornavn=" + fornavn +
                ", etternavn=" + etternavn +
                ", forsikrNr=" + forsikrNr +
                ", Fakturaadresse=" + Fakturaadresse;
    }
}
