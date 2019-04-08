package com.programutvikling.data.tesklasser;

import com.programutvikling.data.tesklasser.forsikringer.Forsikring;
import com.programutvikling.data.tesklasser.forsikringer.Skademelding;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Kunde implements Serializable {

    private Date kundeOpprettet;
    private String fornavn;
    private String mellomnavn;
    private String etternavn;
    private String forsikrNr;
    private List<Forsikring> forsikringer;
    private List<Skademelding> skademeldinger;
    private List<Double> ubetaltErstatning; //TODO: Denne listen har feil type. Mulig vi bør lage en egen klasse for dette også

    public Kunde(Date kundeOpprettet, String fornavn, String mellomnavn, String etternavn, String forsikrNr) {
        this.kundeOpprettet = kundeOpprettet;
        this.fornavn = fornavn;
        this.mellomnavn = mellomnavn;
        this.etternavn = etternavn;
        this.forsikrNr = forsikrNr;
    }

    @Override
    public String toString() {
        return "Kunde{" +
                "kundeOpprettet=" + kundeOpprettet +
                ", fornavn='" + fornavn + '\'' +
                ", mellomnavn='" + mellomnavn + '\'' +
                ", etternavn='" + etternavn + '\'' +
                ", forsikrNr='" + forsikrNr + '\'' +
                ", forsikringer=" + forsikringer +
                ", skademeldinger=" + skademeldinger +
                ", ubetaltErstatning=" + ubetaltErstatning +
                '}';
    }
}
