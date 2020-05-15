package server;

import data.Country;
import data.Customer;
import data.DataHolder;
import data.Representative;
import java.io.File;
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
        return loadLocations(path);
    }

    public DataHolder loadLocations(String path) {
        File file = new File(path);

        if (!file.exists()) {
            System.out.println("Die Datei existiert nicht");
        }

        holder = (DataHolder) JAXB.unmarshal(file, DataHolder.class);

        /*for (Country country : holder.getCountries()) {
            for (Representative representative
                    : country.getRepresentatives()) {
                representative.setCountry(country);
                for (Customer customer : representative.getCustomers()) {
                    customer.setRepresentative(representative);
                }
            }
        }*/
        return holder;

    }

}
