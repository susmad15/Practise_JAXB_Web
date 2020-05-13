package server;

import data.Country;
import data.Customer;
import data.DataHolder;
import data.Representative;
import java.io.File;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXB;

public class XML_Access {

    private static XML_Access theInstance;

    private String path;

    private DataHolder holder;

    private XML_Access() {

        path = System.getProperty("user.dir") + "/res/countries.xml";
    }

    public static XML_Access getInstance() {
        if (theInstance == null) {
            theInstance = new XML_Access();
        }

        return theInstance;
    }

    public void printPath() {
        System.out.println("Pfad: " + path);
    }

    public DataHolder loadLocations() {
        File file = new File(path);

        if (!file.exists()) {
            System.out.println("Die Datei existiert nicht");
        }

        holder = (DataHolder) JAXB.unmarshal(file, DataHolder.class);

        for (Country country : holder.getCountries()) {
            for (Representative representative
                    : country.getRepresentatives()) {
                representative.setCountry(country);
                for (Customer customer : representative.getCustomers()) {
                    customer.setRepresentative(representative);
                }
            }
        }

        JAXB.marshal(holder, System.out);

        return holder;

    }
    
    
    /*public void writeXML()
    {
        Country c1 = new Country();
        c1.setCapital("Vienna");
        c1.setContinent("Europe");
        c1.setFoundation(LocalDate.of(2000, Month.MARCH, 20));
        c1.setPopulation(10000000);
        c1.setImportance(50);
        
        Representative r1 = new Representative();
        r1.setCountry(c1);
        r1.setName("Kulia");
            
        List representatives = new ArrayList();
        
        representatives.add(r1);
        
        c1.setRepresentatives(representatives);
        
        DataHolder dh = new DataHolder();
        dh.addCountry(c1);
        
        JAXB.marshal(dh, System.out);
    }*/

    public static void main(String[] args) {
        XML_Access xmla = XML_Access.getInstance();

        xmla.loadLocations();
        
        

    }

}
