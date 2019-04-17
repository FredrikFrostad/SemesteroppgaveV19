package com.programutvikling.mainapp;

/**
 * This class exists only as a workaround to what appears to be a maven bug, where execution of a built
 * jar file fails if the mainClass defined in mavens pom.xml file is the class extending javafxs Application class
 */
public class MainAppWrapper {

    public static void main(String[] args) {
        MainApp.main(args);
    }
}
