# Stockroom
The warehouse robot to automate placing bins on shelves.




**DATABASE CONNECTIE**
- Run de code in `database.sql` in de je database omgeving naar voorkeur (bijv. MySQL Workbench of PhPMyAdmin)
  - Zorg ervoor dat je verbonden bent met je xampp connectie
- Download `mysql-connector.jar` (pinned in #kbs in discord)
- Voeg deze .jar toe aan external libraries
- Zet jouw eigen gegevens in `Config.java` zoals je deze hebt ingesteld voor je xampp connectie 
  - `ip`, waarschijnlijk localhost (127.0.0.1, staat ie automatisch op)
  - `port`, waarschijnlijk 3306 (staat ie automatisch op)
  - `user`, de username die je gekozen hebt
  - `pass`, het wachtwoord die je gekozen hebt 
    - indien je geen wachtwoord gebruikt, laat deze dan staan als lege string: `pass = ""`
- Klaar

**_!!!`Voorraad.java` nooit instantiëren, dit verneukt de voorraad!!!_**  
**_!!!Config.java nooit mee pushen als je veranderingen pushed naar git!!!_**