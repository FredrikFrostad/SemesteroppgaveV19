package com.programutvikling.models.osType;

import java.io.IOException;
import java.util.Locale;

/**
 * Klassen finner ut av hvilket os som maskinen kjører på.
 * Klassen har statiske metoder, da det kun er et kjørende OS der spillet kjører.
 */
public class OSType {
    private static OS osType;

    public enum OS {
        WINDOWS,
        MAC,
        LINUX,
        UNIX,
        OTHER;

        private String version;
        private String language;
        private String country;

        private void setVersion(String version) {
            this.version = version;
        }

        private void setLanguage(String language){
            this.language = language;
        }

        private void setCountry(String country){
            this.country = country;
        }

        public String getVersion() {
            return version;
        }

        public String getLanguage(){
            return language;
        }

        public String getCountry(){
            return country;
        }
    }

    //Statisk metode for klassen finner hvilket OS systemet kjører på
    static {
        try {
            String osName = System.getProperty("os.name");

            if (osName == null) throw new IOException("Name of os not found");
            osName = osName.toLowerCase(); //endrer navnet til lowercase pga case sensitive test.
            if (osName.contains("windows")) osType = OS.WINDOWS;
            else if (osName.contains("linux")) osType = OS.LINUX;
            else if (osName.contains("unix")) osType = OS.UNIX;
            else if (osName.contains("mac os")) osType = OS.MAC;
            else osType = OS.OTHER;
        } catch (Exception e) {
            osType = OS.OTHER;
        } finally {
            osType.setVersion(System.getProperty("os.version"));
            Locale locale = Locale.getDefault(); //lokaliserer kjørende os system.(geografisk,språk,land,etc)
            String osLanguage = locale.getLanguage();
            osType.setLanguage(osLanguage);
            String osCountry = locale.getCountry();
            osType.setCountry(osCountry);
        }
    }

    public static OS getOsType() {
        return osType;
    }

}