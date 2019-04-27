package com.programutvikling.models.filehandlers.reader;

import com.programutvikling.models.data.forsikring.Båt;
import com.programutvikling.models.data.forsikring.Fritidsbolig;
import com.programutvikling.models.data.forsikring.Reise;
import com.programutvikling.models.data.forsikring.VillaInnbo;
import com.programutvikling.models.data.kunde.Kunde;
import com.programutvikling.models.exceptions.InvalidObjectTypeException;
import com.programutvikling.models.utils.helpers.ClientNrHelper;

import java.time.LocalDate;

public class CsvObjectBuilder extends CsvReader {

    /**
     * Method for creating a dataobject from an array of strings.
     * The method requires that the first element of the string array is a type identifier, so
     * that the type og object to be created can be determined.
     * @param objData String array containing the objects datafields as separate elements
     * @return Object of the same type as the type identifier @ objData[0].
     * @throws Exception if the type identifier is missing or unknown
     */
    public Object buildObjectFromString(String[] objData) throws Exception{
        String type = objData[0];
        Object out = null;

        switch (type) {
            case("KUNDE"):
                out = buildKundeFromCsv(objData);
                break;
            case("BÅT"):
                out = buildBåtFromCsv(objData);
                break;
            case("FRITIDSBOLIG"):
                out = buildFritidsboligFromCsv(objData);
                break;
            case ("REISE"):
                out = buildReiseFromcsv(objData);
                break;
            case ("VILLAINNBO"):
                out = buildVillaInnboFromCsv(objData);
                break;
            default:
                System.out.println("The type is: " + type);
                throw new InvalidObjectTypeException("Object type not found.");

        }

        return out;
    }

    /**
     * Helper method for creating a client object from a string array
     * @param objData String array containing object datafields
     * @return Client object
     * @throws Exception if new date is set on already existing clientobject
     */
    private Kunde buildKundeFromCsv(String[] objData) throws Exception{

        Kunde k = new Kunde();
        k.setKundeOpprettet(LocalDate.parse(objData[1]));
        k.setFornavn(objData[2]);
        k.setEtternavn(objData[3]);
        if (objData[4].isBlank()) {
            k.setForsikrNr(new ClientNrHelper().appendClient());
        } else {
            k.setForsikrNr(Integer.parseInt(objData[4]));
        }
        k.setFakturaadresse(objData[5]);
        return k;
    }


    private Båt buildBåtFromCsv(String[] objData) throws IllegalAccessException{
        Båt b = new Båt();
        b.setForsikrNr(Integer.parseInt(objData[1]));
        b.setPremieAnum(Double.parseDouble(objData[2]));
        b.setForsikringsSum(Double.parseDouble(objData[3]));
        b.setAvtaleOpprettet(LocalDate.parse(objData[4]));
        b.setBetingelser(objData[5]);
        b.setEier(objData[6]);
        b.setRegNr(objData[7]);
        b.setTypeBåt(objData[8]);
        b.setModell(objData[9]);
        b.setLengde(Integer.parseInt(objData[10]));
        b.setÅrsmodell(Integer.parseInt(objData[11]));
        b.setMotorType(objData[12]);
        b.setEffekt(objData[13]);
        return b;
    }

    private Fritidsbolig buildFritidsboligFromCsv(String[] objData) {
        Fritidsbolig f = new Fritidsbolig();
        f.setAdresse(objData[0]);
        f.setByggeaar(Integer.parseInt(objData[1]));
        f.setBoligtype(objData[2]);
        f.setByggemateriale(objData[3]);
        f.setStandard(objData[4]);
        f.setAreal(Integer.parseInt(objData[5]));
        f.setForsikringsbeløpByggning(Double.parseDouble(objData[6]));
        f.setForsikringsbeløpInnbo(Double.parseDouble(objData[7]));
        return f;
    }

    //TODO: finish this
    private Reise buildReiseFromcsv(String[] objData) throws Exception {
        Reise r = new Reise();
        throw new Exception();
    }

    //TODO: finish this
    private VillaInnbo buildVillaInnboFromCsv(String[] objData) throws Exception {
        throw new NoSuchMethodException();
    }
}
