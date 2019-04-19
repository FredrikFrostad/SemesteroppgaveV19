package com.programutvikling.models.filehandlers;

import com.programutvikling.models.exceptions.InvalidFileFormatException;

import java.io.File;

public abstract class FileHandler {

    /**
     * Method for providing the extension of a file.
     * @param file The file we need to get the extension of
     * @return A string containing the extension formatted as: .extension
     * @throws InvalidFileFormatException Error is thrown when the file has an illegal extension
     */
    public static String getExtension(File file) throws InvalidFileFormatException {
        String[] extSplt = file.getName().split("\\.");

        for (String s : extSplt) System.out.println(s);

        if (extSplt.length < 2) {
            throw new InvalidFileFormatException("Files must be in .jobj or .csv format");
        }

        String extension = "."+extSplt[extSplt.length - 1];
        if (!(extension.equals(".jobj") ^ extension.equals(".csv"))) {
            throw new InvalidFileFormatException("Files must be in .jobj or .csv format");
        }
        return extension;
    }
}
