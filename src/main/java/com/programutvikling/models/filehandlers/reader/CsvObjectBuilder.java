package com.programutvikling.models.filehandlers.reader;

import com.programutvikling.models.data.forsikring.Båt;
import com.programutvikling.models.data.forsikring.Fritidsbolig;
import com.programutvikling.models.data.forsikring.Reise;
import com.programutvikling.models.data.forsikring.VillaInnbo;
import com.programutvikling.models.data.kunde.Kunde;
import com.programutvikling.models.exceptions.InvalidObjectTypeException;

public class CsvObjectBuilder extends CsvReader {

    public Object buildObjectFromString(String[] objData) throws Exception{
        int lastIndex = objData.length - 1;
        String type = objData[lastIndex];

        switch (type) {
            case("KUNDE"):
                buildKundeFromCsv(objData);
                break;
            case("BÅT"):
                buildBåtFromCsv(objData);
                break;
            case("FRITIDSBOLIG"):
                buildFritidsboligFromCsv(objData);
                break;
            case ("REISE"):
                buildReiseFromcsv(objData);
                break;
            case ("VILLAINNBO"):
                buildVillaInnboFromCsv(objData);
                break;
            default:
                throw new InvalidObjectTypeException("Object type not found.");

        }

        return null;
    }

    private Kunde buildKundeFromCsv(String[] objData) throws Exception{
        throw new NoSuchMethodException();
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
