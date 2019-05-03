package com.programutvikling.models.data.forsikring;

import com.programutvikling.models.data.ObjectType;

import java.io.Serializable;

/**
 * @author 576
 */
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
}
