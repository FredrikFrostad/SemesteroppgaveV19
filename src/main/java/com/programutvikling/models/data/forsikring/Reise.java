package com.programutvikling.models.data.forsikring;

public class Reise extends Forsikring{

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
}
