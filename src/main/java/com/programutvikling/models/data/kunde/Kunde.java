package com.programutvikling.models.data.kunde;

import com.programutvikling.models.data.forsikring.Forsikring;

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

    private static final DateTimeFormatter dtf = DateTimeFormatter.BASIC_ISO_DATE;


    //TODO: Kommenterer ut disse feltene for å unngå trøbbel med serialisering. Må hånderes senere!!
    //private List<Forsikring> forsikring;
    //private List<Skademelding> skademeldinger;
    //private List<Double> ubetaltErstatning; //TODO: Denne listen har feil type. Mulig vi bør lage en egen klasse for dette også
    public Kunde(String fornavn, String etternavn, String forsikrNr, String fakturaadresse) {
        this.kundeOpprettet = new Date();
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.forsikrNr = forsikrNr;
        this.Fakturaadresse = fakturaadresse;
    }

    public ArrayList<Forsikring> getForsikringer() {
        return forsikringer;
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
