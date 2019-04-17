package com.programutvikling.models.filehandlers.reader;

import com.programutvikling.models.data.forsikring.Båt;
import com.programutvikling.models.data.forsikring.Fritidsbolig;
import com.programutvikling.models.data.forsikring.Reise;
import com.programutvikling.models.data.forsikring.VillaInnbo;
import com.programutvikling.models.data.kunde.Kunde;
import com.programutvikling.models.exceptions.InvalidObjectTypeException;

import java.time.LocalDate;

public class CsvObjectBuilder extends CsvReader {

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
        throw new NoSuchMethodException();
    }

    private Fritidsbolig buildFritidsboligFromCsv(String[] objData) throws Exception {
        throw new NoSuchMethodException();
    }

    private Reise buildReiseFromcsv(String[] objData) throws Exception {
        throw new Exception();
    }

    private VillaInnbo buildVillaInnboFromCsv(String[] objData) throws Exception {
        throw new NoSuchMethodException();
    }
}
