package com.programutvikling.models.filehandlers.writer;

import java.io.File;
import java.io.IOException;

public interface FileWriter {

    /**
     * Metode for å velge en filfra maskinens filsystem
     * Exceptions skal ikke håndteres i denne metoden, men kastes til kallende metode/klasse
     * @return File
     */
    public File getFile() throws IOException;

    /**
     * Metode som skriver data til fil.
     * Exceptions skal ikke håndteres i denne klassen, men kastes til kallende metode/klasse
     * @throws IOException
     */
    public void writeDataToFile(File file, Object obj) throws IOException;
}
