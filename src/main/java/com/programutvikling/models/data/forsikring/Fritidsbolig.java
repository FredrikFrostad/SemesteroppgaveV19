package com.programutvikling.models.data.forsikring;

import com.programutvikling.models.data.ObjectType;

import java.io.Serializable;

public class Fritidsbolig extends Bolig implements Serializable {


    private static ObjectType type = ObjectType.FRITIDSBOLIG;

    public Fritidsbolig() {
        super();
    }

    @Override
    public ObjectType getType() { return type;}

    @Override
    public String toString() {
        return "type=" + type +
                "," + super.toString();
    }

//    public int calculateForsikringssum(){
//        return (int) (this.getForsikringsbeløpByggning() + this.getForsikringsSum());
//    }
//
//    /**
//     *  Using 300 as a set rate for calculatng price for encurance pr year
//     * @return
//     */
//    public int prisPrÅr(){
//        return (int)((this.getForsikringsSum() + this.getForsikringsbeløpByggning())/300);
//    }

    public static void main(String[] args) {
        Fritidsbolig fritid = new Fritidsbolig();
        String s = fritid.type.toString();
        System.out.println(s);
    }


}
