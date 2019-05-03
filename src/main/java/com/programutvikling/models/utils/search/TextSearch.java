package com.programutvikling.models.utils.search;

import com.programutvikling.models.data.kunde.Kunde;

import java.util.ArrayList;

/**
 * @author 811
 */
public class TextSearch {

    /**
     * Searches for match in object datafields
     * @param query String containing searc query
     * @param searchList List containing objects to be searched
     * @return Arraylist containing mathing objects
     */
    public static ArrayList<Kunde> textSearch(String query, ArrayList<Kunde> searchList) {
        ArrayList<Kunde> result = new ArrayList<>();
        query.toLowerCase();

        for (Kunde k : searchList) {
            if (k.getFornavn().toLowerCase().contains(query) ||
                    k.getEtternavn().toLowerCase().contains(query) ||
                        Integer.toString(k.getForsikrNr()).contains(query) ||
                            k.getFakturaadresse().contains(query)) {
                result.add(k);
            }
        }
        return result;
    }
}
