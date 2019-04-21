package com.programutvikling.models.utils.helpers;

import com.programutvikling.mainapp.MainApp;

import java.io.*;

public class ClientNrHelper {

    private static final String CLIENTREGFILE = ".clientNumbers";

    /**
     * Runs at the start of the application to ensure that the needed files for keeping track
     * of ensurance numbers are in place. The files arecreated in the program folder placed in the users home directory.
     */
    public void init() {
        String prjctFldr = MainApp.getPROJECTFOLDER();

        File file = new File(System.getProperty("user.home") + "/" + MainApp.getPROJECTFOLDER() + "/" + CLIENTREGFILE);

        if (!file.exists()) {
            try {
                file.createNewFile();

                FileWriter fw = new FileWriter(file);
                fw.write("1");
                fw.flush();
                fw.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Method for keeping track of and auto-incrementing insurance numbers.
     * @return integer: the next in line insurance number
     * @throws FileNotFoundException if the reference file is not found
     */
    public int appendClient() throws FileNotFoundException {
        int out = -1;

        File file = new File(System.getProperty("user.home") + "/" + MainApp.getPROJECTFOLDER() + "/" + ".clientNumbers");
        if (!file.exists()) throw new FileNotFoundException("Feil: finner ikke kunderegister fil!");

        try {
            // Reading last clientnumber from file
            BufferedReader reader = new BufferedReader(new FileReader(file));
            out = Integer.parseInt(reader.readLine());
            reader.close();

            //writing incremented clientnumber to file
            FileWriter writer = new FileWriter(file);
            String s = Integer.toString(++out);
            writer.write(s);
            writer.flush();
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }
}
