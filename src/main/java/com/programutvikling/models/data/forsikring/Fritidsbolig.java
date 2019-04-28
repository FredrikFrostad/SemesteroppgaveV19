package com.programutvikling.models.data.forsikring;

import com.programutvikling.models.data.ObjectType;

import java.io.Serializable;

public class Fritidsbolig extends Bolig implements Serializable {


    private static ObjectType type = ObjectType.FRITIDSBOLIG;

    public Fritidsbolig() {
        super();
    }

    public Fritidsbolig(ObjectType type, int forsNr, double premieAnnum, double forsikringsSum, String betingelser, String adresse, int byggeaar,
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

    public static void main(String[] args) {
        Fritidsbolig fritid = new Fritidsbolig();
        String s = fritid.type.toString();
        System.out.println(s);
    }
}
