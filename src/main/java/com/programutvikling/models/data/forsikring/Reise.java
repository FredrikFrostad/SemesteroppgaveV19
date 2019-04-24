package com.programutvikling.models.data.forsikring;

import com.programutvikling.models.data.ObjectType;

import java.io.Serializable;

public class Reise extends Forsikring implements Serializable {

    private static ObjectType type = ObjectType.REISE;
    private String forsikringOmraade;
    private double forsikringsSum;

    public Reise() {};

    public Reise(double premieAnnum, double forsikringsSum, String betingelser,
                 String forsikringOmraade, double forsikringsSum1) {
        super(premieAnnum, forsikringsSum, betingelser);
        this.forsikringOmraade = forsikringOmraade;
        this.forsikringsSum = forsikringsSum1;
    }

    public String getforsikringOmraade() {
        return forsikringOmraade;
    }

    public void setforsikringOmraade(String forsikringOmraade) {
        this.forsikringOmraade = forsikringOmraade;
    }

    @Override
    public double getForsikringsSum() {
        return forsikringsSum;
    }

    @Override
    public void setForsikringsSum(double forsikringsSum) {
        this.forsikringsSum = forsikringsSum;
    }

    @Override
    public String toString() {
        return "Reise{" +
                super.toString() +
                "forsikringOmraade=" + forsikringOmraade +
                ", forsikringsSum=" + forsikringsSum +
                ", type=" + type +
                '}';
    }
}
