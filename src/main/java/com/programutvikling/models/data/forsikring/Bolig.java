package com.programutvikling.models.data.forsikring;

import com.programutvikling.models.data.ObjectType;

/**
 * @author 576
 */
public class Bolig extends Forsikring {


    private ObjectType type;
    private String adresse;
    private int byggeaar;
    private String boligtype;
    private String byggemateriale;
    private String standard;
    private int areal;
    private double forsikringsbeløpByggning;
    private double forsikringsbeløpInnbo;

    public Bolig() {
        super();
    }


    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getByggeaar() {
        return byggeaar;
    }

    public void setByggeaar(int byggeaar) {
        this.byggeaar = byggeaar;
    }

    public String getBoligtype() {
        return boligtype;
    }

    public void setBoligtype(String boligtype) {
        this.boligtype = boligtype;
    }

    public String getByggemateriale() {
        return byggemateriale;
    }

    public void setByggemateriale(String byggemateriale) {
        this.byggemateriale = byggemateriale;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public int getAreal() {
        return areal;
    }

    public void setAreal(int areal) {
        this.areal = areal;
    }

    public double getForsikringsbeløpByggning() {
        return forsikringsbeløpByggning;
    }

    public void setForsikringsbeløpByggning(double forsikringsbeløpByggning) {
        this.forsikringsbeløpByggning = forsikringsbeløpByggning;
    }

    public double getForsikringsbeløpInnbo() {
        return forsikringsbeløpInnbo;
    }

    public void setForsikringsbeløpInnbo(double forsikringsbeløpInnbo) {
        this.forsikringsbeløpInnbo = forsikringsbeløpInnbo;
    }

    public void setPremieAnnum(double premieAnnum) {super.setPremieAnnum(premieAnnum);}

    public void setForsikringsSum(double sum) {super.setForsikringsSum(sum);}

    public void setForsikrNr(int nr) {super.setForsikrNr(nr);}

    @Override
    public ObjectType getType() { return type;}

    public int calculateForsikringssum(){
        return (int) (this.getForsikringsbeløpByggning() + this.getForsikringsbeløpInnbo());
    }

    /**
     *  Using 200 as a set rate for calculatng price for encurance pr year
     * @return
     */
    public int prisPrÅr(int rate){
        return (int)((this.getForsikringsbeløpInnbo() + this.getForsikringsbeløpByggning())/rate);
    }

    public String customStringForSkademelding(){
        return (this.getType() + " " + this.adresse);
    }


    @Override
    public String toString() {
        return  super.toString() +
                ", adresse=" + adresse +
                ", byggeaar=" + byggeaar +
                ", boligtype=" + boligtype +
                ", byggemateriale=" + byggemateriale +
                ", standard=" + standard +
                ", areal=" + areal +
                ", forsikringsbeløpByggning=" + forsikringsbeløpByggning +
                ", forsikringsbeløpInnbo=" + forsikringsbeløpInnbo;
    }
}
