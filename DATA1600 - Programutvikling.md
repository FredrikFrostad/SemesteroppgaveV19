# DATA1600 - Programutvikling

### Grupperapport semesteroppgave - Gruppe 1349

Kandidater: 761, 576, 811

### Alternativ 1: Forsikring

### INNLEDNING:

Målet for denne oppgaven har vært å implementere et java-program for et forsikringsselskap. Vi har under arbeidet benyttet oss av git som versjonskontroll og Trello som oppgavehånderingsverktøy. Vi har under implementeringen av prosjektet fokusert på å overholde oop-prinsipper i så stor grad som mulig. 

### EVALUERING AV PROSJEKTARBEIDET:

Etter endt prosjektperiode har vi opparbeidet oss god og relevant erfaring innen teambasert arbeide innen programutvikling. Vi opplever at vi har hatt en god gruppedynamikk og at vi har dratt nytte av de individuelle gruppemedlemmenes styrker i løpet av prosjektperioden.

Vi kom tidlig i gang med planleggingsfasen og la et solid grunnlag for videre arbeid med en gjennomarbeidet prosjektplan. Vi hadde i denne perioden stort fokus på at innspill fra alle gruppens medlemmer skulle «veie like tungt». Dette for å sikre at alle i gruppen fikk et likeverdig eierskap til prosjektet.

Vi sitter igjen med en følelse av at gruppearbeidet har fungert godt, og at verktøyene vi har benyttet for å håndtere kompleksiteten i et "større" programvareprosjekt har fungert svært godt. Spesielt bruken av git for versjonskontroll har fungert meget godt. Vi har også hatt god nytte av å bruke trello for å holde oversikt over oppgaver som må gjøres i prosjektet. Vi opplever at dette er et svært intuitivt verktøy som gjør det enkelt både og ta eierskap til en oppgave, men også å gi oppgaven videre til et annet gruppemedlem. 

Vi har også tillegnet oss erfaring med bruk av JUnit biblioteket for enhetstesting. Dette har vært et svært godt verktøy for å sikre codebasens integritet ved større endringer og refaktorering. Vi skulle gjerne ha brukt enhetstesting i enda større grad enn det vi har gjort, og dermed tilnærmet oss prinsippene for testdrevet utvikling, men grunnet noe begrenset tid har ikke dette vært mulig å fult ut gjennomføre i denne omgang. 

Valg av programkode er utviklet på grunnlag av et løsningsorientert ståsted, hvor alternative løsninger har blitt veid opp mot hverandre utifra effektivitet og kvalitet. Vi mener derfor vi har valgt de mest oppriktige løsningene på ulike knutepunkt som dukket opp underveis og generelt kommet opp med en så og si feilfri applikasjon. Koden er skrevet basert på hva vi mener gir mest mening både for oss og andre som skal lese og forstå koden. 


### DELER AV OPPGAVEN VI ER MEST FORNØYD MED:

MERK: Kodeblokkene i rapporten er laget ved bruk av typora. Dette fører til at formatering av kode blir noe annerledes enn i en IDE / teksteditor mtp. linjeskift etc.

Vi mener at vi i denne oppgaven i stor grad har oppnådd bruk av objektorienteringsprinsipper, og MVC-designmønster. Vi har i stor grad skilt GUI fra backend-kode, og  mener vi har skrevet godt definerte klasser, med tydelig funksjonalitet og gjenbrukbarhet. 

#### Eksempler på kode vi er godt fornøyd med:

**Oppdeling av controllere for å unngå en, uhåndterlig "gudekontroller":**
Vi har delt opp applikasjonens views i flere forskjellige fxml-filer som hver benytter sin egen controller. Dette holder komplekstiteten på et håndterbart nivå otg gjør det enkelt å skille komponenter fra hverandre. 



#### "OPPBEVARING" AV INSTANTIERTE VIEWS FOR Å BEHOLDE STATE:

Det kan være ønskelig å ta vare på state i et view som man har byttet bort fra. Dette har vi løst ved å lage en klasse for håndtering av view-changes, der instantierte views lagres i et hashmap slik at de enkelt kan hentes ut igjen.

Kort oppsummert er vi særdeles fornøyd med ferdigstilt produkt da vi mener det overholder alle tekniske krav fastsatt i oppgaveteksten. I tillegg har vi også implimentert en del tillegseffekter som f.eks (FYLL INN!!!!!)
Vi har opplevd det å jobbe i gruppe som noe uproblematisk og svært lærrerikt da vi har vokst på hverandres innspill og forslag. Semesteroppgaven har stilt høye krav til oss både som enkelpersoner og som gruppe, men vi har håndtert knutepunkt og problemstillinger på en oppriktig og løsningsorientert måte utifra kollektive avgjørelser. 


