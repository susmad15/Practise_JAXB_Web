package server;

import data.Country;
import data.Representative;
import java.util.List;

public class DB_Access {

    private static DB_Access theInstance;

    private DB_Access() {
    }

    public static DB_Access getInstance() {
        if (theInstance == null) {
            theInstance = new DB_Access();
        }

        return theInstance;
    }

    public void connect() {}

    public void disconnect() {}

    public void persistDataHolder() {

    }

    public List<Country> getAllCountries() {
        return null;
    }

    public List<Representative> getRepresentativesOfCountry(String countryName) {
        return null;
    }

}
