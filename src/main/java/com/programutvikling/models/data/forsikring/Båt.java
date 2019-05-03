package com.programutvikling.models.data.forsikring;

import com.programutvikling.models.data.ObjectType;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

/**
 * @author 576
 */
public class Båt extends Forsikring{

    private static ObjectType type = ObjectType.BÅT;
    private String eier;
    private String regNr;
    private String typeBåt;
    private String modell;
    private int lengde;
    private int årsmodell;
    private String motorType;
    private String effekt;

    public Båt() {
        super();
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

    public String getTypeBåt() {
        return typeBåt;
    }

    public void setTypeBåt(String typeBåt) {
        this.typeBåt = typeBåt;
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

    public void setPremieAnum(double premieAnnum) {super.setPremieAnnum(premieAnnum);}

    public void setForsikringsSum(double sum) {super.setForsikringsSum(sum);}

    public void setForsikrNr(int nr) {super.setForsikrNr(nr);}

    public void setAvtaleOpprettet(LocalDate avtaleOpprettet) throws IllegalAccessException{
        super.setAvtaleOpprettet(avtaleOpprettet);
    }

    public ObjectType getType() {return type;}

    @Override
    public String toString() {
        return  "type=" + type +
                "," + super.toString() +
                ", eier=" + eier +
                ", regNr=" + regNr +
                ", typeBåt=" + typeBåt +
                ", modell=" + modell +
                ", lengde=" + lengde +
                ", årsmodell=" + årsmodell +
                ", motorType=" + motorType +
                ", effekt=" + effekt;
    }
    public int prisPrÅr(int rate){
        return (int)((this.getForsikringsSum())/rate);
    }
    public String customStringForSkademelding(){
        return (this.getType() + " " + this.getRegNr());
    }
}
