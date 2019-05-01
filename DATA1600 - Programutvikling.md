# DATA1600 - Programutvikling

### Grupperapport semesteroppgave - Gruppe 1349

Kandidater: 761, 576, 811

### Alternativ 1: Forsikring

### INNLEDNING:

Målet for denne oppgaven har vært å implementere et java-program for et forsikringsselskap. Vi har under arbeidet benyttet oss av git som versjonskontroll og Trello som oppgavehånderingsverktøy. Vi har under implementeringen av prosjektet fokusert på å overholde oop-prinsipper i så stor grad som mulig. 

### EVALUERING AV PROSJEKTARBEIDET:

 Etter endt prosjektperiode har vi opparbeidet oss god og relevant erfaring innen teambasert arbeide innen programutvikling. Vi opplever at vi har hatt en god gruppedynamikk og at vi har dratt nytte av de individuelle gruppemedlemmenes styrker i løpet av prosjektperioden.

Vi kom tidlig i gang med planleggingsfasen og la et solid grunnlag for videre arbeid med en gjennomarbeidet prosjektplan. Vi hadde i denne perioden stort fokus på at innspill fra alle gruppens medlemmer skulle «veie like tungt». Dette for å sikre at alle i gruppen fikk et likeverdig eierskap til prosjektet.

Vi sitter igjen med en følelse av at gruppearbeidet har fungert godt, og at verktøyene vi har benyttet for å håndere kompleksiteten i et "større" programvareprosjekt har fungert svært godt. Spesielt bruken av git for versjonskontroll har fungert meget godt. Vi har også hatt god nytte av å bruke trello for å holde oversikt over oppgaver som må gjøres i prosjektet. Vi opplever at dette er et svært intuitivt verktøy som gjør det enkelt både og ta eierskap til en oppgave, men også å gi oppgaven videre til et annet gruppemedlem. 

Vi har også tillegnet oss erfaring med bruk av JUnit biblioteket for enhetstesting. Dette har vært et svært godt verktøy for å sikre codebasens integritet ved større endringer og refaktorering. Vi skulle gjerne ha brukt enhetstesting i enda større grad enn det vi har gjort, og dermed tilnærmet oss prinsippene for testdrevet utvikling, men grunnet noe begrenset tid har ikke dette vært mulig å fult ut gjennomføre i denne omgang. 



### DELER AV OPPGAVEN VI ER MEST FORNØYD MED:

MERK: Kodeblokkene i rapporten er laget ved bruk av typora. Dette fører til at formatering av kode blir noe annerledes enn i en IDE / teksteditor mtp. linjeskift etc.

Vi mener at vi i denne oppgaven i stor grad har oppnådd bruk av objektorienteringsprinsipper, og MVC-designmønster. Vi har i stor grad skilt GUI fra backend-kode, og  mener vi har skrevet godt definerte klasser, med tydelig funksjonalitet og gjenbrukbarhet. 

#### Eksempler på kode vi er godt fornøyd med:

**Oppdeling av controllere for å unngå en, uhåndterlig "gudekontroller":**
Vi har delt opp applikasjonens views i flere forskjellige fxml-filer som hver benytter sin egen controller. Dette holder komplekstiteten på et håndterbart nivå otg gjør det enkelt å skille komponenter fra hverandre. 

```java
//************** START OF MAINPAGECONTROLLER ******************//

public class mainPageController {

    @FXML
    private BorderPane rootPane;

    @FXML
    private Tab tabForsikring, tabKunder, tabSkademeldinger;

    @FXML
    private TableView<Forsikring> tableOverviewForsikring, tableDetailsForsikring;

    @FXML
    private TableView<Skademelding> tableOverviewSkademeldinger, tableDeatailsSkademelding;

    @FXML
    private TableColumn<Forsikring, String> overviewCol1;

    @FXML
    private TableColumn<Forsikring, ObjectType> overviewCol2;

    @FXML
    private TableView<Kunde> clientTable;

    @FXML
    private TableColumn<Kunde, String> kundeCol1, kundeCol2, kundeCol3;

    @FXML
    private TextField k_fornavn, k_etternavn, k_forsNr, k_adr, k_opDato, policyCountField, yearlyAmountField;

    @FXML
    private TextField selectedKundeField;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Text progressText;

    private File threadfile;


    @FXML
    private void initialize() {
        initClientTable();
        initForsikringsTable();
        initDb();
        refreshTable();
    }

    /**
     * Initialises column names and valueproperties for the Client tableview
     */
    private void initClientTable() {

        kundeCol1.setCellValueFactory(new PropertyValueFactory<>("forsikrNr"));
        kundeCol2.setCellValueFactory(new PropertyValueFactory<>("fornavn"));
        kundeCol3.setCellValueFactory(new PropertyValueFactory<>("etternavn"));
    }

    /**
     * Initialises column names and valueproperties for the insurance overview tableview
     */
    private void initForsikringsTable() {
        overviewCol1.setCellValueFactory(new PropertyValueFactory<>("type"));
        overviewCol2.setCellValueFactory(new PropertyValueFactory<>("premieAnnum"));
    }


    // TODO: fjernes når denne ikke trengs mere - FOR TESTING
    @FXML
    private void CLEAR() {
        MainApp.getClientList().clear();
        refreshTable();
    }

    // TODO: fjernes når denne ikke trengs mere - FOR TESTING
    @FXML
    private void BIG() {
        for (int i = 0; i < 1E6; i++) {
            MainApp.getClientList().add(new Kunde("Test" + Integer.toString(i), "Testesen" + Integer.toString(i), i + 500, "Fakturaadresse"));
        }
        refreshTable();
    }



    /**
     * When a row containing client data is selected, the corresponding client object is set as activive in MainApp.
     * A string describing the selected clien object is also set in the Insurance policies tab.
     */
    @FXML
    private void selectClient() {
        Kunde k = clientTable.getSelectionModel().getSelectedItem();
        if (k != null) {
            MainApp.setSelectedKunde(k);
            populateClientFields(k);
            selectedKundeField.setText(k.getForsikrNr() + ": " + k.getFornavn() + " " + k.getEtternavn());
        }

    }

    /**
     * When a policy is selected, the corrasponding data is displayed in a single row tableview.
     * The columns of the tableview are generated on demand by the FormatPolicyHelper class, based
     * on the type enum of the data object.
     */
    @FXML
    private void selectPolicy() {
        if (tableOverviewForsikring.getSelectionModel().getSelectedItem() != null) {
            Forsikring f = tableOverviewForsikring.getSelectionModel().getSelectedItem();
            tableDetailsForsikring.getColumns().clear();
            FormatPolicyTableHelper.formatCollumns(this, tableDetailsForsikring, f);
            tableDetailsForsikring.getItems().clear();
            tableDetailsForsikring.getItems().add(f);
        }
    }
    @FXML
    private void selectedInjuryReport(){
        if (tableOverviewSkademeldinger.getSelectionModel().getSelectedItem() != null){
            Skademelding skademelding = tableOverviewSkademeldinger.getSelectionModel().getSelectedItem();
            tableDeatailsSkademelding.getColumns().clear();

        }
    }



    
    /**
     * Method for reacting to a selectionchange in this views tabpane. The method response is
     * based on the tab selected
     */
    @FXML
    private void tabChanged() {
        if (tabForsikring.isSelected()) {
            System.out.println("EVENT FORSIKRING TRIGGERED!!");
            refreshTable();
        }
        else if (tabKunder.isSelected()) {
            System.out.println("EVENT KUNDER TRIGGERED");
        }
        else if (tabSkademeldinger.isSelected()) {
            System.out.println("EVENT SKADEMELDINGER TRIGGERED");
        }
    }

    /**
     * Methode for populating textfields with client data
     * @param k The client objects containing the data
     */
    private void populateClientFields(Kunde k) {
        k_fornavn.setText(k.getFornavn());
        k_etternavn.setText(k.getEtternavn());
        k_forsNr.setText(Integer.toString(k.getForsikrNr()));
        k_adr.setText(k.getFakturaadresse());
        k_opDato.setText(k.getKundeOpprettet().toString());
        policyCountField.setText(String.valueOf(k.getNmbrOfPolicies()));
        yearlyAmountField.setText(String.valueOf(k.getYearlyPremium()));
    }

    /**
     * Exports all data objects as a single jobj file or several csv files
     */
    @FXML
    private void exportToFile() {
        try {
            threadfile = FileWriter.getFile();
        } catch (Exception e) {
            e.printStackTrace();
            AlertHelper.createAlert(Alert.AlertType.ERROR, "Export feilet", e.getMessage());
        }

        Service<Void> threadService = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        try
                        {
                            if (ExtensionHandler.getExtension(threadfile).equals(".jobj")) {
                                JobjWriter writer = new JobjWriter();
                                writer.writeObjectDataToFile(threadfile, MainApp.getClientList());
                            } else {
                                DbExportHelperCsv exporter = new DbExportHelperCsv(threadfile.getAbsolutePath());
                                exporter.exportDbAsCsv();
                            }
                        } catch (InvalidFileFormatException e) {
                            AlertHelper.createAlert(Alert.AlertType.ERROR, "Export feilet", e.getMessage());
                            e.printStackTrace();
                        } catch (IOException e) {
                            AlertHelper.createAlert(Alert.AlertType.ERROR, "Export feilet", e.getMessage());
                            e.printStackTrace();
                        } catch (Exception e) {
                            AlertHelper.createAlert(Alert.AlertType.ERROR, "Export feilet", e.getMessage());
                            e.printStackTrace();
                        }
                        return null;
                    }
                };
            }
        };
        threadService.start();
    }

    /**
     * Imports data objects from either jobj or csv files.
     * The file import is threaded  to keep the gui responsive
     */
    @FXML private void importFromFile() {
        try {
            threadfile = FileReader.getFile();
        } catch (Exception e) {
            e.printStackTrace();
        }


        Service<Void> thread = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        try {
                            System.out.println("Starting file import task!");
                            if (ExtensionHandler.getExtension(threadfile).equals(".jobj")) {
                                JobjReader reader = new JobjReader();
                                ArrayList<Kunde> list = (ArrayList<Kunde>) reader.readDataFromFile(threadfile);
                                MainApp.getClientList().addAll(list);
                            } else {
                                // This implementation is fragile, and only works on files eksported using the
                                // exportToFile method, insuring that the naming scheme of the exported csv files
                                // are kept intact.
                                DbImportHelperCsv importer = new DbImportHelperCsv();
                                importer.importDbFromCsv(threadfile.getParent());
                            }
                        } catch (Exception e) {
                            AlertHelper.createAlert(Alert.AlertType.ERROR, "En feil har oppstått", e.getMessage());
                        }
                        System.out.println("File import task completed");
                        refreshTable();
                        return null;
                    }
                };
            }
        };
        thread.start();
    }

    @FXML
    private void nyForsikring(ActionEvent event) {
        Kunde k = clientTable.getSelectionModel().getSelectedItem();
        MainApp.setSelectedKunde(k);
        noCustomerSelected(k);

        ViewChanger vc = new ViewChanger();
        vc.setView(rootPane, "newPolicy", "views/newPolicy.fxml");
    }

    @FXML
    private void nySkademelding(ActionEvent event){
        Kunde k = clientTable.getSelectionModel().getSelectedItem();
        MainApp.setSelectedKunde(k);
        noCustomerSelected(k);

        ViewChanger vc = new ViewChanger();
        vc.setView(rootPane, "newInjuryRepoert", "views/newInjuryReport.fxml");
    }

    /**
     * Checks if a client is selected for operations that rewuire this to be done.
     * @param k selected client object or null if nothing is selected.
     */
    private void noCustomerSelected(Kunde k){
        if (k == null) {
            AlertHelper.createAlert(Alert.AlertType.ERROR, "Kunder ikke valgt", "Vennligst velg en kunde først");
            return;
        }
    }

    /**
     * Changes to the view for adding a client.
     * @param event
     */
    @FXML
    private void newClient(ActionEvent event) {
        ViewChanger vc = new ViewChanger();
        vc.setView(rootPane, "newClient", "views/newClient.fxml");
    }


    @FXML
    private void saveChanges(ActionEvent event) {
        Kunde k = MainApp.getSelectedKunde();
        k.setFornavn(k_fornavn.getText());
        k.setEtternavn(k_etternavn.getText());
        refreshTable();
    }

    @FXML
    private void deleteClient() {
        Kunde k = MainApp.getSelectedKunde();
        Optional<ButtonType> result = AlertHelper.createOptionAlert(Alert.AlertType.WARNING, "Bekreft sletting",
                "Er du sikker på at du vil slette kunde " + k.getFornavn() + " " + k.getEtternavn() + "?",
                "Slett Kunde ", "Avbryt");

        if (result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            MainApp.setSelectedKunde(null);
            MainApp.getClientList().remove(k);
            refreshTable();
        }
    }

    @FXML
    private void deletePolicy() {
        if (tableOverviewForsikring.getSelectionModel().getSelectedItem() != null) {
            Forsikring f = tableOverviewForsikring.getSelectionModel().getSelectedItem();

            Optional<ButtonType> result = AlertHelper.createOptionAlert(Alert.AlertType.WARNING, "Bekreft sletting",
                        "Er du sikker på at du vil slette forsikring " + f.getType() + ", opprettet: " + f.getAvtaleOpprettet() + "?",
                            "Slett Forsikring", "Avbryt");
            if (result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                MainApp.getSelectedKunde().getForsikringer().remove(f);
                tableDetailsForsikring.getColumns().clear();
                tableDetailsForsikring.getItems().clear();
                populateClientFields(MainApp.getSelectedKunde());
                refreshTable();
            }
        }
    }

    @FXML
    private void saveChangesToFile() {

    }

    /**
     * Method for refreshing the kunde tableview. This method is public to enable other screens to
     * refresh the tableview if a change has happened.
     */
    @FXML
    public void refreshTable() {
        clientTable.getItems().clear();
        clientTable.getItems().addAll(MainApp.getClientList());
        tableOverviewForsikring.getItems().clear();
        if (MainApp.getSelectedKunde() != null) {
            Kunde k = MainApp.getSelectedKunde();
            tableOverviewForsikring.getItems().addAll(k.getForsikringer());
        }
    }
    /**
     * Loads db data into program
     */
    private void initDb() {
        DbImportHelperCsv importer = new DbImportHelperCsv();
        try {
            importer.importDbFromCsv(null);
        } catch (Exception e) {
            e.printStackTrace();
            AlertHelper.createAlert(Alert.AlertType.ERROR, "Kritisk feil", "Feil ved importering av database. " +
                    "Kan ikke garantere dataintegritet.");
        }
    }
}

//************* END OF MAINVIEWCONTROLLER *****************//

//***************START OF ADDCLIENTSCONTROLLER***************//

public class AddClientController {


    @FXML
    BorderPane rootPane;

    @FXML private TextField fx_fornavn;
    @FXML private TextField fx_etternavn;
    @FXML private TextField fx_forsikringsnummer;
    @FXML private TextField fx_fakturaadresse;

    @FXML
    public void initialize() {
        fx_forsikringsnummer.setEditable(false);
        try {
            fx_forsikringsnummer.setText(Integer.toString(new ClientNrHelper().appendClient()));

        } catch (FileNotFoundException e) {
            AlertHelper.createAlert(Alert.AlertType.ERROR, "Fatal feil", "Finner ikke registerfil, kan ikke opprette kunde.");
            abort();
        }
    }


    @FXML
    private void saveClient(ActionEvent event) {

        System.out.println("SAVECLIENT");

        Kunde kunde = new Kunde();
        try {
            kunde.setKundeOpprettet(LocalDate.now());

            Inputvalidator.checkValidNameFormat(fx_fornavn.getText());
            kunde.setFornavn(fx_fornavn.getText());

            Inputvalidator.checkValidNameFormat(fx_etternavn.getText());
            kunde.setEtternavn(fx_etternavn.getText());

            kunde.setForsikrNr(Integer.parseInt(fx_forsikringsnummer.getText()));

            kunde.setFakturaadresse(fx_fakturaadresse.getText());


            File file = FileWriter.getFile();
            if (FileWriter.getExtension(file).equals(".csv")) new CsvWriter().writeObjectDataToFile(file, kunde);
            else new JobjWriter().writeObjectDataToFile(file, kunde);


        } catch (InvalidNameFormatException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            AlertHelper.createAlert(Alert.AlertType.ERROR,"Invalid name", "Wrong name, plz try again");
        } catch (InvalidNumberFormatException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (InvalidAddressException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        //Legger til kunde i arrayliste som bor i mainapp

        ArrayList<Kunde> list = MainApp.getClientList();
        for (Kunde k : list) {
            if (k.toString().equals(kunde.toString())) return;
        }
        list.add(kunde);
        abort();
    }


    @FXML
    private void abort() {
        ViewChanger vc = new ViewChanger();
        vc.setView(rootPane, "startpage", "views/mainPage.fxml");
    }

}

//**************** END OF ADDCLIENTSCONTROLLER ******************//

//***************** START OF NEWINJURYREPORTCONTROLLER**************//

public class NewInjuryReportController {

    @FXML
    BorderPane newInjuryRoot;

    @FXML
    DatePicker skadeDato;

    @FXML
    private TextField
            typeSkade,
            name,
            takseringsbeløp;
    @FXML
    private TextArea
            skadeBeskrivelse,
            kontaktinfoVitner;

    @FXML
    private ComboBox
            comboBox;

    Kunde k;

    private int skadeNr;

    @FXML
    private void initialize(){
        k = MainApp.getSelectedKunde();
        skadeNr = k.getForsikrNr();

        name.setText("Kunde: " + k.getFornavn() + " " + k.getEtternavn() + "                  " + "Forsikrings nummer: " + skadeNr);
        name.setDisable(true);

        for (int i = 0; i < k.getForsikringer().size(); i++) {
            comboBox.getItems().add(k.getForsikringer().get(i).customStringForSkademelding());
        }
    }

    public void cancel(){
        ViewChanger viewChanger = new ViewChanger();
        viewChanger.setView(newInjuryRoot,"mainPage", "views/mainPage.fxml");
        viewChanger.resetView("newInjuryReport");
    }


    public void registererSkademelding(){
        Kunde k = MainApp.getSelectedKunde();
        Skademelding skademelding = createSkademelding();
        k.getSkademeldinger().add(skademelding);

        ViewChanger vc = new ViewChanger();
        vc.resetView("newInjuryReport");
        vc.setView(newInjuryRoot, "mainPage", "views/mainPage.fxml");
    }

    /**
     * må lages en bedre håndtering for hvordan man gjør feil testingen
     * @return
     */
    public Skademelding createSkademelding(){

        Skademelding skademelding = new Skademelding();

        try{
            Inputvalidator.checkIfValidNumber(takseringsbeløp.getText());

            skademelding.setKontaktinfoVitner(kontaktinfoVitner.getText());
            skademelding.setSkadeBeskrivelse(skadeBeskrivelse.getText());
            skademelding.setSkadeNr(skadeNr);
            skademelding.setTypeSkade(typeSkade.getText());
            skademelding.setSkadeDato(skadeDato.toString());
            //skademelding.setTakseringsBelop();
            //skademelding.setUtbetaltErstatning();

        } catch (InvalidNumberFormatException e) {
            e.printStackTrace();
            AlertHelper.createAlert(Alert.AlertType.ERROR, "Feil data", "Fyll inn riktig data i riktig datafelt");
        }
        return skademelding;
    }
}
//******************* ENDOFNEWINJURYREPORTCONTROLLER *******************//

//*********************STARTOFNEWPOLICYCONTROLLER***********************//

ublic class NewPolicyController{

    @FXML
    BorderPane newPolicyRoot;
    @FXML
    StackPane stack;
    @FXML
    ToggleGroup choosePolicy;
    @FXML
    AnchorPane togglePage, båtPage, reisePage, bilPage, villaPage, fritidsPage;
    @FXML
    RadioButton båt, bil, reise, fritid, villa;
    @FXML
    Button topRightButton;
    @FXML
    private TextField
            fritid_adresse,
            fritid_byggeår,
            fritid_boligtype,
            fritid_byggemateriale,
            fritid_standard,
            fritid_areal,
            fritid_beløpBygning,
            fritid_beløpInnbo,
            fritid_beløp,
            fritid_prÅr,
            villa_adresse,
            villa_byggeår,
            villa_boligtype,
            villa_byggemateriale,
            villa_standard,
            villa_areal,
            villa_beløpBygning,
            villa_beløpInnbo,
            villa_beløp,
            villa_prÅr,
            båt_eier,
            båt_regNr,
            båt_typebåt,
            båt_modell,
            båt_lengde,
            båt_årsmodell,
            båt_motorType,
            båt_effekt,
            båt_forsikringssum,
            reise_forsikringOmraade,
            reise_forsikringsSum,
            reise_beløp;

    private Node active;


    @FXML
    private void initialize() {
        active = togglePage;

    }

    @FXML
    private void next() {
        if (active.getId().equals("togglePage")) {
            active.setVisible(false);

            if (bil.isSelected()) {
                active = bilPage;
            } else if (båt.isSelected()) {
                active = båtPage;
            } else if (reise.isSelected()) {
                active = reisePage;
            } else if (fritid.isSelected()) {
                active = fritidsPage;
            } else if (villa.isSelected()) {
                active = villaPage;
            } else {
                active.setVisible(true);
                return;
            }
            active.setVisible(true);
            topRightButton.setText("Fullfør");
        }
        else {
            if (registrerForsikring()) {
                ViewChanger vc = new ViewChanger();
                vc.resetView("newPolicy");
                vc.setView(newPolicyRoot, "mainPage", "views/mainPage.fxml");
            }
        }
    }

    @FXML
    private void back() {
        if (active.getId().equals("togglePage")) {
            ViewChanger vc = new ViewChanger();
            vc.setView(newPolicyRoot, "mainPage", "views/mainPage.fxml");
            vc.resetView("newPolicy");
        }
        else {
            active.setVisible(false);
            active = togglePage;
            active.setVisible(true);
            topRightButton.setText("Next");
        }
    }

    private boolean registrerForsikring() {
        Forsikring forsikring = null;

        if (active.getId().equals("fritidsPage")) {
            forsikring = createFritidsBolig();
        }else if(active.getId().equals("båtPage")){
            forsikring = createBåtForsikring();
        }else if(active.getId().equals("reisePage")){
            forsikring = createReiseForsikring();
        }else if(active.getId().equals("villaPage")){
            forsikring = createVillaForsikring();
        }

        if (forsikring != null) {
            Kunde k = MainApp.getSelectedKunde();
            k.getForsikringer().add(forsikring);
            return true;
        }
        return false;
    }

    private Forsikring createVillaForsikring() {
        VillaInnbo villa = new VillaInnbo();

        try{
            Inputvalidator.checkIfValidNumber(villa_byggeår.getText());
            Inputvalidator.checkIfValidNumber(villa_areal.getText());
            Inputvalidator.checkIfValidNumber(villa_beløpInnbo.getText());
            Inputvalidator.checkIfValidNumber(villa_beløpBygning.getText());

            villa.setForsikrNr(MainApp.getSelectedKunde().getForsikrNr());
            villa.setBetingelser("Betingelser shmetingelser");
            villa.setAdresse(villa_adresse.getText());
            villa.setAvtaleOpprettet(LocalDate.now());
            villa.setByggeaar(Integer.parseInt(villa_byggeår.getText()));
            villa.setBoligtype(villa_boligtype.getText());
            villa.setByggemateriale(villa_byggemateriale.getText());
            villa.setStandard(villa_standard.getText());
            villa.setAreal(Integer.parseInt(villa_areal.getText()));
            villa.setForsikringsbeløpByggning(Double.parseDouble(villa_beløpBygning.getText()));
            villa.setForsikringsbeløpInnbo(Double.parseDouble(villa_beløpInnbo.getText()));

            villa.setPremieAnnum(villa.prisPrÅr(200));
            villa.setForsikringsSum(villa.calculateForsikringssum());

        } catch (InvalidNumberFormatException e) {
            AlertHelper.createAlert(Alert.AlertType.ERROR, "Ugyldig byggeår", e.getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return villa;
    }

    private Forsikring createReiseForsikring() {
        Reise reiseForsikring = new Reise();

        try{
            Inputvalidator.checkIfValidNumber(reise_forsikringsSum.getText());

            reiseForsikring.setBetingelser("Betingelser shmetingelser");
            reiseForsikring.setAvtaleOpprettet(LocalDate.now());
            reiseForsikring.setForsikrNr(MainApp.getSelectedKunde().getForsikrNr());
            reiseForsikring.setOmraade(reise_forsikringOmraade.getText());
            reiseForsikring.setForsikringsSum(Double.parseDouble(reise_forsikringsSum.getText()));

            reiseForsikring.setPremieAnnum(reiseForsikring.prisPrÅr());

        } catch (InvalidNumberFormatException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(reiseForsikring.toString());
        return reiseForsikring;
    }

    private Forsikring createBåtForsikring() {
        Båt båtForsikring = new Båt();

        try {
            Inputvalidator.checkIfValidNumber(båt_lengde.getText());
            Inputvalidator.checkIfValidNumber(båt_årsmodell.getText());
            Inputvalidator.checkIfValidNumber(båt_forsikringssum.getText());

            båtForsikring.setBetingelser("Betingelser shmetingelser");
            båtForsikring.setAvtaleOpprettet(LocalDate.now());
            båtForsikring.setForsikrNr(MainApp.getSelectedKunde().getForsikrNr());
            båtForsikring.setEier(båt_eier.getText());
            båtForsikring.setTypeBåt(båt_typebåt.getText());
            båtForsikring.setRegNr(båt_regNr.getText());
            båtForsikring.setEffekt(båt_effekt.getText());
            båtForsikring.setModell(båt_modell.getText());
            båtForsikring.setLengde(Integer.parseInt(båt_lengde.getText()));
            båtForsikring.setÅrsmodell(Integer.parseInt(båt_årsmodell.getText()));
            båtForsikring.setMotorType(båt_motorType.getText());

            båtForsikring.setForsikringsSum(Double.parseDouble(båt_forsikringssum.getText()));
            båtForsikring.setPremieAnum(båtForsikring.prisPrÅr(200));

        } catch (InvalidNumberFormatException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return båtForsikring;
    }


    private Fritidsbolig createFritidsBolig() {

        Fritidsbolig fritidsbolig = new Fritidsbolig();

        try {
            Inputvalidator.checkIfValidNumber(fritid_byggeår.getText());
            Inputvalidator.checkIfValidNumber(fritid_areal.getText());
            Inputvalidator.checkIfValidNumber(fritid_beløpInnbo.getText());
            Inputvalidator.checkIfValidNumber(fritid_beløpBygning.getText());

            fritidsbolig.setForsikrNr(MainApp.getSelectedKunde().getForsikrNr());
            fritidsbolig.setBetingelser("Betingelser shmetingelser");
            fritidsbolig.setAvtaleOpprettet(LocalDate.now());
            fritidsbolig.setAdresse(fritid_adresse.getText());
            fritidsbolig.setByggeaar(Integer.parseInt(fritid_byggeår.getText()));
            fritidsbolig.setBoligtype(fritid_boligtype.getText());
            fritidsbolig.setByggemateriale(fritid_byggemateriale.getText());
            fritidsbolig.setStandard(fritid_standard.getText());
            fritidsbolig.setAreal(Integer.parseInt(fritid_areal.getText()));
            fritidsbolig.setForsikringsbeløpByggning(Double.parseDouble(fritid_beløpBygning.getText()));
            fritidsbolig.setForsikringsbeløpInnbo(Double.parseDouble(fritid_beløpInnbo.getText()));

            fritidsbolig.setPremieAnnum(fritidsbolig.prisPrÅr(200));
            fritidsbolig.setForsikringsSum(fritidsbolig.calculateForsikringssum());

        } catch (InvalidNumberFormatException e) {
            e.printStackTrace();
            AlertHelper.createAlert(Alert.AlertType.INFORMATION, "Ugyldig inndata", "Ett eller flere felter inneholder ugyldige data");
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return fritidsbolig;
    }
}


```

#### "OPPBEVARING" AV INSTANTIERTE VIEWS FOR Å BEHOLDE STATE:

Det kan være ønskelig å ta vare på state i et view som man har byttet bort fra. Dette har vi løst ved å lage en klasse for håndtering av view-changes, der instantierte views lagres i et hashmap slik at de enkelt kan hentes ut igjen.

```java
public class ViewChanger {

    // Denne listen skal inneholde instanser av views i applikasjonen
    public static HashMap<String, Scene> viewMap = new HashMap<>();

    /**
     * Dette kan gjøres ved å ha en egen klasse for views (denne) og sørge for at alle nyew views legges i
     * et hashmap / arraylist som sjekkes før instaniering av nytt view. Dersom viewet allerede er instansier,
     * skal vi IKKE laste på nytt via FXMLLoader, men hente inn den kjørende instansen fra liste.
     */

    /**
     * Metode som bytter view i appliksjonen. Dersom viewet allerede er instansiert hentes det ut ifra hashmap.
     * Dersom det ikke er tidligere instansiert lastes det via FXMLloader og legges til hashmapet.
     * @param currentParent Dette er parent i viewet vi bytter fra. Denne trenger vi for få tak i scenen
     * @param viewName Key til hashmap
     * @param path Path til nytt view i resourcemappe
     */
    public void setView(Parent currentParent, String viewName, String path) {
        Stage stage = (Stage)currentParent.getScene().getWindow();

        if (viewMap.containsKey(viewName)) {
            stage.setScene(viewMap.get(viewName));
        } else {

            Parent parent = null;
            try {
                parent = FXMLLoader.load(getClass().getClassLoader().getResource(path));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Scene scene = new Scene(parent);
            stage.setScene(scene);
            viewMap.putIfAbsent(viewName, scene);
        }
    }

    public void resetView(String viewName) {
        if (viewMap.containsKey(viewName)) viewMap.remove(viewName);
    }

    public HashMap<String, Scene> getViewMap() {
        return viewMap;
    }

}

```







