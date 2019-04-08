package com.programutvikling.data.tesklasser;

import com.programutvikling.data.tesklasser.forsikringer.Forsikring;
import com.programutvikling.data.tesklasser.forsikringer.Skademelding;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class Kunde implements Serializable {

    private Date kundeOpprettet;
    private String fornavn;
    private String mellomnavn;
    private String etternavn;
    private String forsikrNr;
    private String Fakturaadresse;

    private static final DateTimeFormatter dtf = DateTimeFormatter.BASIC_ISO_DATE;

    //TODO: Kommenterer ut disse feltene for å unngå trøbbel med serialisering. Må hånderes senere!!
    //private List<Forsikring> forsikringer;
    //private List<Skademelding> skademeldinger;
    //private List<Double> ubetaltErstatning; //TODO: Denne listen har feil type. Mulig vi bør lage en egen klasse for dette også

    public Kunde(String fornavn, String mellomnavn, String etternavn, String forsikrNr, String fakturaadresse) {
        this.kundeOpprettet = new Date();
        this.fornavn = fornavn;
        this.mellomnavn = mellomnavn;
        this.etternavn = etternavn;
        this.forsikrNr = forsikrNr;
        this.Fakturaadresse = fakturaadresse;
    }

    @Override
    public String toString() {
        return "Kunde{" +
                "kundeOpprettet=" + kundeOpprettet +
                ", fornavn='" + fornavn + '\'' +
                ", mellomnavn='" + mellomnavn + '\'' +
                ", etternavn='" + etternavn + '\'' +
                ", forsikrNr='" + forsikrNr + '\'' +
                ", Fakturaadresse='" + Fakturaadresse + '\'' +
                '}';
    }
}
