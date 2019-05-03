package com.programutvikling.models.filehandlers.reader;

import com.programutvikling.models.data.ObjectType;
import com.programutvikling.models.data.forsikring.*;
import com.programutvikling.models.data.kunde.Kunde;
import com.programutvikling.models.data.skademelding.Skademelding;
import com.programutvikling.models.exceptions.InvalidObjectTypeException;
import com.programutvikling.models.utils.helpers.ClientNrHelper;

import java.time.LocalDate;

/**
 * @author 576
 */
public class CsvObjectBuilder {

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
                out = buildHomeOwnerFromCsv(objData);
                break;
            case ("REISE"):
                out = buildReiseFromcsv(objData);
                break;
            case ("VILLAINNBO"):
                out = buildHomeOwnerFromCsv(objData);
                break;
            case ("SKADEMELDING"):
                out = buildSkademeldingFromCSV(objData);
                break;
            default:
                System.out.println("The type is: " + type);
                throw new InvalidObjectTypeException("Object type not found.");
        }

        return out;
    }

    /**
     * Helper method for creating a damagereport object from a string array
     * @param objData String array containing object datafields
     * @return Damagereport object
     */
    private Skademelding buildSkademeldingFromCSV(String[] objData) {
        Skademelding s = new Skademelding();
        s.setForsikrNr(Integer.parseInt(objData[1]));
        s.setSkadeDato(objData[2]);
        s.setSkadeNr(Integer.parseInt(objData[3]));
        s.setTypeSkade(objData[4]);
        s.setSkadeBeskrivelse(objData[5]);
        s.setKontaktinfoVitner(objData[6]);
        s.setKontaktinfoVitner(objData[7]);
        s.setTakseringsBelop(Double.parseDouble(objData[8]));
        return s;
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


    /**
     * Helper method for creating a boatInsurance object from a string array
     * @param objData String array containing object datafields
     * @return Boat policy object
     * @throws IllegalAccessException if new date is set on already existing clientobject
     */
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


    /**
     * Helper method for creating a homeowners Insurance object from a string array
     * @param objData String array containing object datafields
     * @return Homeowner policy object
     * @throws IllegalAccessException if new date is set on already existing clientobject
     */
    private Bolig buildHomeOwnerFromCsv(String[] objData) throws Exception {
        Bolig b = null;
        if (objData[0].equals(ObjectType.VILLAINNBO.toString())) {
            b = new VillaInnbo();
        } else {
            b = new Fritidsbolig();
        }
        b.setForsikrNr(Integer.parseInt(objData[1]));
        b.setPremieAnnum(Double.parseDouble(objData[2]));
        b.setForsikringsSum(Double.parseDouble(objData[3]));
        b.setAvtaleOpprettet(LocalDate.parse(objData[4]));
        b.setBetingelser(objData[5]);
        b.setAdresse(objData[6]);
        b.setByggeaar(Integer.parseInt(objData[7]));
        b.setBoligtype(objData[8]);
        b.setStandard(objData[9]);
        b.setByggemateriale(objData[10]);
        b.setAreal(Integer.parseInt(objData[11]));
        b.setForsikringsbeløpByggning(Double.parseDouble(objData[12]));
        b.setForsikringsbeløpInnbo(Double.parseDouble(objData[13]));

        return b;
    }


    /**
     * Helper method for creating a Travel Insurance object from a string array
     * @param objData String array containing object datafields
     * @return Travel policy object
     * @throws IllegalAccessException if new date is set on already existing clientobject
     */
    private Reise buildReiseFromcsv(String[] objData) throws Exception {
        Reise r = new Reise();
        r.setForsikrNr(Integer.parseInt(objData[1]));
        r.setPremieAnum(Double.parseDouble(objData[2]));
        r.setForsikringsSum(Double.parseDouble(objData[3]));
        r.setAvtaleOpprettet(LocalDate.parse(objData[4]));
        r.setOmraade(objData[5]);

        return r;
    }


}
