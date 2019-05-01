package com.programutvikling.models.data.forsikring;

import com.programutvikling.models.data.ObjectType;

import java.time.LocalDate;

public class Reise extends Forsikring{

    private static ObjectType type = ObjectType.REISE;
    private String omraade;

    public Reise() {}

    public Reise(int forsNr, double premieAnnum, double forsikringsSum, String betingelser,
                 String omraade) {
        super(type, forsNr,premieAnnum, forsikringsSum, betingelser);
        this.omraade = omraade;
    }

    public String getOmraade() {
        return omraade;
    }

    public void setOmraade(String omraade) {
        this.omraade = omraade;
    }

    @Override
    public double getForsikringsSum() {
        return super.getForsikringsSum();
    }

    @Override
    public void setForsikringsSum(double forsikringsSum) {
        super.setForsikringsSum(forsikringsSum);
    }

    public void setForsikrNr(int nr) {super.setForsikrNr(nr);}

    public void setAvtaleOpprettet(LocalDate avtaleOpprettet) throws IllegalAccessException{
        super.setAvtaleOpprettet(avtaleOpprettet);
    }

    public void setPremieAnum(double premieAnnum) {super.setPremieAnnum(premieAnnum);}


    @Override
    public ObjectType getType() {
        return this.type;
    }

    public int prisPrÅr(){
        return (int) (this.getForsikringsSum()/100);
    }

    @Override
    public String toString() {
        return "type=" + type +
                "," + super.toString() +
                ", omraade=" + omraade;
    }
    public String customStringForSkademelding(){
        return (this.getType() + " " + this.getOmraade());
    }



}
