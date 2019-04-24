package com.programutvikling.models.data.forsikring;

import com.programutvikling.models.data.ObjectType;

import java.io.Serializable;
import java.util.Date;

public class Bil extends Forsikring implements Serializable {

    private static ObjectType type = ObjectType.BIL;
    private String eier;
    private String regNr;
    private String typeBil;
    private String modell;
    private int lengde;
    private int årsmodell;
    private String motorType;
    private String effekt;

    public Bil() {
        super();
    }


    public Bil(double premieAnnum, double forsikringsSum, String betingelser, String eier, String regNr,
               String typeBil, String modell, int lengde, int årsmodell, String motorType, String effekt) {
        super(premieAnnum, forsikringsSum, betingelser);
        this.eier = eier;
        this.regNr = regNr;
        this.typeBil = typeBil;
        this.modell = modell;
        this.lengde = lengde;
        this.årsmodell = årsmodell;
        this.motorType = motorType;
        this.effekt = effekt;
    }

    public String getEier() {
        return eier;
    }

    public void setEier(String eier) {
        this.eier = eier;
    }

    public String getRegNr() {
        return regNr;
    }

    public void setRegNr(String regNr) {
        this.regNr = regNr;
    }

    public String getTypeBil() {
        return typeBil;
    }

    public void setTypeBil(String typeBil) {
        this.typeBil = typeBil;
    }

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }

    public int getLengde() {
        return lengde;
    }

    public void setLengde(int lengde) {
        this.lengde = lengde;
    }

    public int getÅrsmodell() {
        return årsmodell;
    }

    public void setÅrsmodell(int årsmodell) {
        this.årsmodell = årsmodell;
    }

    public String getMotorType() {
        return motorType;
    }

    public void setMotorType(String motorType) {
        this.motorType = motorType;
    }

    public String getEffekt() {
        return effekt;
    }

    public void setEffekt(String effekt) {
        this.effekt = effekt;
    }

    @Override
    public String toString() {
        return "Bil{" +
                super.toString() +
                ", eier=" + eier +
                ", regNr=" + regNr +
                ", typeBil=" + typeBil +
                ", modell=" + modell +
                ", lengde=" + lengde +
                ", årsmodell=" + årsmodell +
                ", motorType=" + motorType +
                ", effekt=" + effekt +
                ", type=" + type +
                '}';
    }
}
