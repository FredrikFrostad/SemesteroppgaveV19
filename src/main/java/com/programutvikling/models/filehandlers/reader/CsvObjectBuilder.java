package com.programutvikling.models.filehandlers.reader;

import com.programutvikling.models.data.forsikring.Båt;
import com.programutvikling.models.data.forsikring.Fritidsbolig;
import com.programutvikling.models.data.forsikring.Reise;
import com.programutvikling.models.data.forsikring.VillaInnbo;
import com.programutvikling.models.data.kunde.Kunde;
import com.programutvikling.models.exceptions.InvalidObjectTypeException;

import java.time.LocalDate;

public class CsvObjectBuilder {

    public Object buildObjectFromString(String[] objData) throws Exception{
        int lastIndex = objData.length - 1;
        String type = objData[lastIndex];
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

    private Kunde buildKundeFromCsv(String[] objData) throws Exception{

        Kunde k = new Kunde();
        k.setKundeOpprettet(LocalDate.parse(objData[0]));
        k.setFornavn(objData[1]);
        k.setEtternavn(objData[2]);
        k.setForsikrNr(objData[3]);
        k.setFakturaadresse(objData[4]);
        return k;
    }

    private Båt buildBåtFromCsv(String[] objData) throws Exception {
        Båt b = new Båt();
        b.setEier(objData[0]);
        b.setRegNr(objData[1]);
        b.setTypeBåt(objData[2]);
        b.setModell(objData[3]);
        b.setLengde(Integer.parseInt(objData[4]));
        b.setÅrsmodell(Integer.parseInt(objData[5]));
        b.setMotorType(objData[6]);
        b.setEffekt(objData[7]);
        return b;
    }

    private Fritidsbolig buildFritidsboligFromCsv(String[] objData) throws Exception {
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

    private Reise buildReiseFromcsv(String[] objData) throws Exception {
        Reise r = new Reise();
        throw new Exception();
    }

    private VillaInnbo buildVillaInnboFromCsv(String[] objData) throws Exception {
        throw new NoSuchMethodException();
    }
}
