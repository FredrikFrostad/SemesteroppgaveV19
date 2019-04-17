package com.programutvikling.models.data.forsikring;

import com.programutvikling.models.data.ObjectType;

public class Reise extends Forsikring{

    private static ObjectType type = ObjectType.REISE;
    private String forisikringOmraade;
    private double forsikringsSum;

    public Reise() {};

    public Reise(double premieAnnum, double forsikringsSum, String betingelser,
                 String forisikringOmraade, double forsikringsSum1) {
        super(premieAnnum, forsikringsSum, betingelser);
        this.forisikringOmraade = forisikringOmraade;
        this.forsikringsSum = forsikringsSum1;
    }

    public String getForisikringOmraade() {
        return forisikringOmraade;
    }

    public void setForisikringOmraade(String forisikringOmraade) {
        this.forisikringOmraade = forisikringOmraade;
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
                "forisikringOmraade=" + forisikringOmraade +
                ", forsikringsSum=" + forsikringsSum +
                ", type=" + type +
                '}';
    }
}
