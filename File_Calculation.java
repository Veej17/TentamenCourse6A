
package tentamen;

import java.io.*;
import java.util.*;
import tentamen.PMCApp;

/**
 * Created by Veerle on 14/03/2016.
 *
 * Class FileCalculation leest de bestanden uit en maakt hieruit verscchilleden mappen en sets aan.
 * hierin worden ook de berekeningen uitgevoerd voor oa de overlap van de pubMedID's
 */
public class File_Calculation {

    /**
     * @param args the command line arguments
     */
    public static Map<Integer, String> mapPub2Gene = new HashMap<Integer, String>();    // mapPub2Gene, key=pubmedId als Integer en value = genId als String
    public static Map<Integer, HashSet<Integer>> mapTax2pub = new HashMap<>();          // mapTax2pub, key=TaxID als Integer en Value = pubmedID als HashSet van Integers
    public static Map mapOrganism2Tax = new HashMap();                                  // mapOrganisme2Tax, key = Organisme en Value = TaxID
    public static Set organism = new HashSet<>();                                       // set van organismes
    public static Set tax = new HashSet<>();                                            // Set van TaxID's
    public static String lengte1 = new String();                                        // String voor de lengte van het aantal pubmeds van organisme1
    public static String lengte2 = new String();                                        // String voor de lengte van het aantal pubmeds van organisme2
    public static HashSet<Integer> overlap = new HashSet<>();                           // set van Integers van de overlap van de beide pubmeds
    public static Map <String, ArrayList> mapGene2Info = new HashMap<>();               // mapGene2Info key=GeneID en Value= ArrayList

       /**
     * methode: bestandGEne2Pubmed
     * functie: het uitlezen van het bestand Gene2Pubmed waaruit mapPub2Gene en mapTax2pub gevuld worden. 
     *          uit het laatste wordt taxList gevormd met alle gebruikste taxID in verzameld wordt.
     * parameters: geen
     * retourneerd: taxList
     * exceptions: FileNotFoundException en IOException
     */
    public static ArrayList<Integer> bestandGene2Pubmed() {
        File fileGene2Pubmed = new File("/home/veerle/Documents/bio-informatica/jaar 2/course 2/A/informatica/praktijk/tentamen/gene2pubmed");
        BufferedReader reader = null;
        int count = 0;

        try {
            reader = new BufferedReader(new FileReader(fileGene2Pubmed));
            String text = null;

            // De eerste regel wordt overgeslagen aangezien deze headers bevat.
            // de regel wordt gesplit op de tab waaruit mapPub2Gene gevuld wordt met de derde en tweede rij van het bestand.
            // er wordt een set met de gevonden pubmeds gevormd.
            // Vanuit deze set wordt gekeken of waarde values[0] dat is de TaxID in de set voorkomt.
            // zo ja dan worden de pubmeds die al in de map zitten er uit gehaald en de nieuwe er in gezet.
            // zo niet dan wordt er een nieuwe key als tax en value de pubid toegevoegd.
            while ((text = reader.readLine()) != null) {
                count += 1;
                if (count > 1) {
                    String[] values = text.split("\t");
                    mapPub2Gene.put(Integer.parseInt(values[2]), values[1]);
                    Set setPub = mapTax2pub.keySet();

                    if (setPub.contains(Integer.parseInt(values[0]))) {
                        HashSet<Integer> pubmeds = mapTax2pub.get(Integer.parseInt(values[0]));
                        pubmeds.add(Integer.parseInt(values[2]));
                        mapTax2pub.put(Integer.parseInt(values[0]), pubmeds);

                    } else {
                        HashSet<Integer> pubmeds = new HashSet<Integer>();
                        pubmeds.add(Integer.parseInt(values[2]));
                        mapTax2pub.put(Integer.parseInt(values[0]), pubmeds);
                    }
                    values = null;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
            }
        }
        
        // de lijst met taxID wordt gereturned 
        tax = mapTax2pub.keySet();
        Object[] tempList2 = tax.toArray();
        ArrayList<Integer> taxList = new ArrayList<Integer>();
        for (int i = 0; i < tempList2.length; i++) {
            taxList.add((Integer) tempList2[i]);
        }
        return taxList;
    }

    
     /**
     * methode: bestandTaxnames
     * functie: het uitlezen van het bestand taxnames waaruit mapOrganism2Tax gevuld worden. 
     *          uit het laatste wordt organism gevormd wat uit de keyset van deze map bestaat.
     * parameters: geen
     * retourneerd: Niets
     * exceptions: FileNotFoundException en IOException
     */
    public static void bestandTaxnames() {
        ArrayList<Integer> taxlist = bestandGene2Pubmed();
        File fileTaxnames = new File("/home/veerle/Documents/bio-informatica/jaar 2/course 2/A/informatica/praktijk/tentamen/taxnames.txt");
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(fileTaxnames));
            String text = new String();
            while ((text = reader.readLine()) != null) {
                String[] values = text.split("\t");
                if (taxlist.contains(Integer.parseInt(values[0]))) {
                    mapOrganism2Tax.put(values[1], values[0]);
                    values = null;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
            }
        }

        organism = mapOrganism2Tax.keySet();

    }
    /**
     * methode: bestandGene_info
     * functie: het uitlezen van het bestand gene_info waaruit mapgene2Info gevuld wordt. 
     * parameters: geen
     * retourneerd: gaat een arraylist met de gene info retouneren, nu nog niks
     * exceptions: FileNotFoundException en IOException
     * opmerkingen: Deze functie werkt niet en moet nog uitgewerkt worden
     */    public static void bestandGene_info() {
        
        File fileGeneInfo = new File("/home/veerle/Documents/bio-informatica/jaar 2/course 2/A/informatica/praktijk/tentamen/gene_info");
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(fileGeneInfo));
            String text = new String();
            while ((text = reader.readLine()) != null) {
                String[] values = text.split("\t");
                
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
            }
        }

        organism = mapOrganism2Tax.keySet();

    }


     /**
     * methode: berekening
     * functie: het berekenen van het aantal pubmeds van de geselecteerde organismes uit de comboboxen en hierbij de overlap
     * parameters: de geselecteerde organismes
     * retourneerd: niks
     * exceptions:  geen
     */
     public static void berekening(String org1, String org2) {
        String tax1 = mapOrganism2Tax.get(org1).toString();
        String tax2 = mapOrganism2Tax.get(org2).toString();
        HashSet<Integer> pubsTax1 = mapTax2pub.get(Integer.parseInt(tax1));
        HashSet<Integer> pubsTax2 = mapTax2pub.get(Integer.parseInt(tax2));

        lengte1 = Integer.toString(pubsTax1.size());
        lengte2 = Integer.toString(pubsTax2.size());

        pubsTax1.retainAll(pubsTax2);
        overlap = pubsTax1;
    }

    /**
     * methode: genBerekening
     * functie: het aanroepen en verwerken van de geninfo voor de tabel
     * retourneerd: niks
     * exceptions:  geen
     * opmerking: deze functie moet nog uitgewerkt worden voor het invullen van de tabel
     */
    public static void genBerekening(String gen) {
        //bestandGene_info();
    }
}
