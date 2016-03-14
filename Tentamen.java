/**
 * auteur: Veerle van Winden
 * datum: 14-03-2016
 * versie: 2.0
 * functie: Het maken van een applicatie waarbij de overeenkomst in pubmed artikelen tussen 2 organismes kan worden berekend.
 * opmerking: -> De applicatie is niet volledig de tabel wordt niet gevult.
 *            -> De applicatie doet er 1 tot 4 minuten over om op te starten
 * bekende bugs:-> de uitwerking van de tabel is nog niet uitgewerkt. wel is de juiste pubmedID met de juiste genID alwel op te vragen.
 *              -> er kan niet een tweede keer een taxonomy geselecteerd worden omdat de eerste dan geen pubmeds meer bevat.
 *              -> bij het kiezen van sommige organismes komt een nullpointer exception. bij het uitvoeren van de test
 *                 taxonomy1 homo sapien en taxonomy2 Plasmodium falciparum 3D7 komt de volgende overeenkomst:
 *   Testwaarde    homo sapien: 489117    plasmodium falciparum 3DF:1025    overlap: 36
 * 
 */
package tentamen;

import java.io.*;
import java.util.*;

/**
 * Created by Veerle on 14/03/2016.
 *
 * Class Tentamen bevat de main methode van de applicatie.
 */
public class Tentamen extends PMCApp {


    /**
     * methode: Main
     * functie: het aanroepen van de PMCApp waaruit de GUI wordt gevormd
     * parameters: Default String[] args
     * retourneerd: niets
     * exceptions: geen
     */
    public static void main(String[] args) {
        PMCApp frame = new PMCApp();
        frame.setVisible(true);
        frame.setTitle("PubMed Compare - Release Candidate");



    }

   
}
