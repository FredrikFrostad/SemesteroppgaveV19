package com.programutvikling.models.tesklasser.forsikringer;

import java.util.Date;

public class Båt extends Forsikring{

    private String eier;
    private String regNr;
    private String typeBåt; // TODO: Burde dette vært implementert via en indre enum klasse slik at man kan velge mellom predefinerte alternativer...typ daycruiser, robåt, motorseiler etc...
    private String modell;
    private int lengde;
    private int årsmodell;
    private String motorType;
    private String effekt;

    public Båt(String eier, String regNr, String typeBåt, String modell, int lengde, int årsmodell,
                String motorType, String effekt, double premieAnnum, double forsikringsSum, Date avtaleOpprettet,
                    String betingelser) {
        super(premieAnnum, forsikringsSum, avtaleOpprettet, betingelser);
        this.eier = eier;
        this.regNr = regNr;
        this.typeBåt = typeBåt;
        this.modell = modell;
        this.lengde = lengde;
        this.årsmodell = årsmodell;
        this.motorType = motorType;
        this.effekt = effekt;
    }

    public String getEier() {
        return eier;
    }

    public String getRegNr() {
        return regNr;
    }

    public String getTypeBåt() {
        return typeBåt;
    }

    public String getModell() {
        return modell;
    }

    public int getLengde() {
        return lengde;
    }

    public int getÅrsmodell() {
        return årsmodell;
    }

    public String getMotorType() {
        return motorType;
    }

    public String getEffekt() {
        return effekt;
    }


}
