# DATA1600 - Programutvikling

### Grupperapport semesteroppgave - Gruppe 1349

Kandidater: 761, 576, 811

### Alternativ 1: Forsikring

### INNLEDNING:

Målet for denne oppgaven har vært å implementere et java-program for et forsikringsselskap. Vi har under arbeidet benyttet oss av git som versjonskontroll og Trello som oppgavehånderingsverktøy. Vi har under implementeringen av prosjektet fokusert på å overholde **OOP**-prinsipper i så stor grad som mulig. 

Kort oppsummert er vi særdeles fornøyd med ferdigstilt produkt da vi mener det overholder alle tekniske krav fastsatt i oppgaveteksten.Vi har opplevd det å jobbe i gruppe som noe uproblematisk og svært lærrerikt da vi har vokst på hverandres innspill og forslag. Semesteroppgaven har stilt høye krav til oss både som enkelpersoner og som gruppe, men vi har håndtert knutepunkt og problemstillinger på en oppriktig og løsningsorientert måte utifra kollektive avgjørelser. 

### EVALUERING AV PROSJEKTARBEIDET:

Etter endt prosjektperiode har vi opparbeidet oss god og relevant erfaring innen teambasert arbeide innen programutvikling. Vi opplever at vi har hatt en god gruppedynamikk og at vi har dratt nytte av de individuelle gruppemedlemmenes styrker i løpet av prosjektperioden.

Vi kom tidlig i gang med planleggingsfasen og la et solid grunnlag for videre arbeid med en gjennomarbeidet prosjektplan. Vi hadde i denne perioden stort fokus på at innspill fra alle gruppens medlemmer skulle «veie like tungt». Dette for å sikre at alle i gruppen fikk et likeverdig eierskap til prosjektet.

Vi sitter igjen med en følelse av at gruppearbeidet har fungert godt, og at verktøyene vi har benyttet for å håndtere kompleksiteten i et "større" programvareprosjekt har fungert svært godt. Spesielt bruken av **Git** for versjonskontroll har fungert meget godt. Vi har også hatt god nytte av å bruke trello for å holde oversikt over oppgaver som må gjøres i prosjektet. Vi opplever at dette er et svært intuitivt verktøy som gjør det enkelt både og ta eierskap til en oppgave, men også å gi oppgaven videre til et annet gruppemedlem. 

Vi har også tillegnet oss erfaring med bruk av **JUnit** biblioteket for enhetstesting. Dette har vært et svært godt verktøy for å sikre codebasens integritet ved større endringer og refaktorering. Vi skulle gjerne ha brukt enhetstesting i enda større grad enn det vi har gjort, og dermed tilnærmet oss prinsippene for testdrevet utvikling, men grunnet noe begrenset tid har ikke dette vært mulig å fult ut gjennomføre i denne omgang. 

Valg av programkode er utviklet på grunnlag av et løsningsorientert ståsted, hvor alternative løsninger har blitt veid opp mot hverandre utifra effektivitet og kvalitet. Vi mener derfor vi har valgt de mest oppriktige løsningene på ulike knutepunkt som dukket opp underveis og generelt kommet opp med en så og si feilfri applikasjon. Koden er skrevet basert på hva vi mener gir mest mening både for oss og andre som skal lese og forstå koden. 



### DELER AV OPPGAVEN VI ER MEST FORNØYD MED:

Vi mener at vi i denne oppgaven i stor grad har oppnådd bruk av objektorienteringsprinsipper, og **MVC**-designmønster. Vi har i stor grad skilt GUI fra backend-kode, og  mener vi har skrevet godt definerte klasser, med tydelig funksjonalitet og gjenbrukbarhet. 

#### Eksempler på kode vi er godt fornøyd med:

**Oppdeling av controllere for å unngå en, uhåndterlig "gudekontroller":**
Vi har delt opp applikasjonens views i flere forskjellige fxml-filer som hver benytter sin egen controller. Dette holder komplekstiteten på et håndterbart nivå og gjør det enkelt å skille komponenter fra hverandre. 

**"OPPBEVARING" AV INSTANTIERTE VIEWS FOR Å BEHOLDE STATE:**

Det kan være ønskelig å ta vare på state i et view som man har byttet bort fra. Dette har vi løst ved å lage en klasse for håndtering av view-changes, der instantierte views lagres i et hashmap slik at de enkelt kan hentes ut igjen.

**TESTER AV FUNKSJONALITET OG BRUKBARHET:**

Testing var en sentral del av utviklings prossesn hvor vi brukte enhets tester, bruker tester og stress tester for å validere funksjonalitet og kapasiteten til programmet.

**Enhetstester**

Enhetstestene ble skrevet ved hjelp av **JUnit** bibloteket som gjør at vi kan skrive tester for testing av spesifikk funksjonalitet, ved runtime testing av methoder og klasser. Dette var noe vi var svært fornøyd med å gjøre siden det hjalp oss med å sikkre høy kvailtet og et forutsigbart resultat i utviklingsprosessen. Testing av eventuelle side effekter før vi implementerte det i produksjon.

**Brukertester** 

Brukertestene ble gjennomført med tanke på eventuelle problemer en bruker kunne støtet på som feil data i felter, lagring av ingen data, oppretting av skademeldinger og forsikringer uten å selektere en kunde å sette denne informasjonen på, spørsmål om bruker vi lagre endringer ved avsluttning osv. Vi har prøvd å lage et vanntett program som skal kunne håndtere feil bruk av eventuelle brukere gjennom hele programmet med egendefinerte exceptions.

**Stress testing** 

Vi lagde stress tester for å test hvordan programmet håndterte lesing og skriving av store datafiler. Derfor lagde vi en test hvor vi leste inn 1 million objekter for hovedskalig test at trådhåndteringen vår fungerete som forventet. Målet var at vi kunne lese inn en stor mengde data og brukergrensesnittet fortsatt skulle fungere som forventet, noe som vi fikk implementert ved å implementere Task fra javafx.concurrent. 

**DELER AV OPPGAVEN VI ER IKKE LIKE FORNØYD MED:**

Vi er generelt ganske fornøyd med koden vår men vi inser at vi har ting som kunne vært bedre, hovedsaklig har problemene våre vært rundt hvilken oppgave vi har dispornert tiden vår på.

Vi som gruppe har prøvd å balanser arbeidsoppgaver melllom de forskjellige delen av koden (Controllere, backend funksjonalitet, osv) men vi inser at dette er noe vi kunne gjort bedre siden vi primært er "backend" utvikllere og derfor fokuserte mer på funksjonalitet og lesbarhet av koden fremfor GUI' et vårt. Vi inser at vi kunne lagd både et mer intuitivt og estetisk brukergrensesnitt hvis vi fordelte tiden litt bedre. 





### UTDYP HVA DERE HAR LÆRT I HENDHOLD TIL PROSJEKTARBEID OG HVA DERE VIL GJØRE ANNERLEDES FOR DERES NESTE STORE GRUPPE PROSJEKT.

**Hva vi har lært:**

Som gruppe lært hvordan vi skal sammarbeide på kode med flere personer samtidig ved bruk av moderne verktøy. 

Dette inkulderer **Git** versjons kontroll som er det vi har brukt til deling og oppdatering av koden med Github som **Git** host. **Git** versjons kontroll er det mest sentrale verktøyet i utviklings prossesen som har blitt brukt for håndter at flere jobber på koden simultatnt. 

Vi har også lært hvordan vi bruker et ¨kanbann brett¨ ved bruk av **Trello** som verktøy for å få oversikt over hva som må gjøres og hva som er gjort. Dette var med på å strukturere hvordan prosjketet ble gjennomført, vi hadde 4 separate faser som vi brukte til å kvalitetssikkre kode før det ble satt i produksjon. 

Vi gjorde oss en observasjon ved lagringen hvor vi er overasket over hvor hurtig og godt filhåndtering til og fra .csv filer fungerer og hvor tregt serializering av jobj fungerer i forhold. Dette overasket oss i utviklingsprossesen og gjorde oss oppmerksom på tidsforskjellene ved begge løsningene.



**Hva vi kunne gjort bedre:**

Vi kunne gjort en bedre jobb i planleggingsfasen med å sette opp hele programmets struktur med UML diagramer før vi startet å implementere kode slik at alle gruppemedlemmene hadde en formening hvordan strukturen skulle se ut før starten av utviklingsfasen.

Vi kunne vært flinkere til å predefinere spesifikke arbeids områder slik at hvert gruppe medlemm hadde kontroll på hva de skulle gjøre til en hver tid. Dette ville vi gjort at prossesne kunne gått raskere siden vi ville blitt ferdig med deler av koden raskere. Men kunne også skapt et problem på et så "lite" prosjekt som dette siden mye av funksjonaliteten henger sammen.



### **BESKRIVELSE AV ARBEIDET SOM HAR BLITT UTFØRT FOR HVERT MEDLEM.**





| 761  | 576  | 811  |
| ---- | ---- | ---- |
|      |      |      |

