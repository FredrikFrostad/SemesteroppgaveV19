package com.programutvikling.models.data.forsikring;

import com.programutvikling.models.data.ObjectType;

import java.io.Serializable;

public class VillaInnbo extends Bolig implements Serializable {

    private static ObjectType type = ObjectType.VILLAINNBO;


    public VillaInnbo() {
        super();
    }

    public VillaInnbo(int forsNr, double premieAnnum, double forsikringsSum, String betingelser, String adresse, int byggeaar,
                        String boligtype, String byggemateriale, String standard, int areal,
                        double forsikringsbeløpByggning, double forsikringsbeløpInnbo) {
        super(type, forsNr, premieAnnum, forsikringsSum, betingelser, adresse, byggeaar, boligtype,
                byggemateriale, standard, areal, forsikringsbeløpByggning, forsikringsbeløpInnbo);
    }

    @Override
    public ObjectType getType() { return type;}

    @Override
    public String toString() {
        return "type=" + type +
                "," + super.toString();
    }
}
