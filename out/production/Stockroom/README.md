# Stockroom
The warehouse robot to automate placing bins on shelves.




**DATABASE CONNECTIE**
- Run de code in `database.sql` in de je database omgeving naar voorkeur (bijv. MySQL Workbench of PhPMyAdmin)
  - Zorg ervoor dat je verbonden bent met je xampp connectie
- Download `mysql-connector.jar` (pinned in #kbs in discord)
- Voeg deze .jar toe aan external libraries
- Zet jouw eigen gegevens in `Config.java` 
  - (Standaard is url: `"jdbc:mysql://127.0.0.1:3306/nerdygadgets_robotarm"`, user: `"root"`, pass: `"root"`)
    - `jdbc:mysql://` = syntax van de `jdbc:mysql` driver (jdbc:oracle bestaat bijv. ook maar dat gebruiken we niet)
    - `127.0.0.1` = ip (waarschijnlijk gebruik je localhost dus hoef je dit niet te veranderen)
    - `3306` = port (wederom gebruik je waarschijnlijk de standaard instellingen)
    - `nerdygadgets_robotarm` = naam van database (niet veranderen als je deze ook niet hebt veranderd bij het aanmaken van de database)
    - `root` = username die je gekozen hebt voor je xampp connectie
    - `pass` = password die je gekozen hebt voor je xampp connectie
- Klaar

**_!!!`Voorraad.java` nooit instantiëren, dit verneukt de voorraad!!!_**