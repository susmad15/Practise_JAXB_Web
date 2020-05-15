package server;

import data.Country;
import data.Customer;
import data.DataHolder;
import data.Representative;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class DB_Access {

    private EntityManagerFactory emf;
    private EntityManager em;

    private static DB_Access theInstance;

    private DB_Access() {
        emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        em = emf.createEntityManager();
    }

    public static DB_Access getInstance() {
        if (theInstance == null) {
            theInstance = new DB_Access();
        }

        return theInstance;
    }

    public void connect() {
    }

    public void disconnect() {
    }

    public void persistDataHolder() {
        persistDataHolder(System.getProperty("user.dir") + "/res/countries.xml");
    }

    public void persistDataHolder(String path) {

        DataHolder holder = XML_Access.getInstance().loadLocations(path);

        em.getTransaction().begin();
        
        setRelationships(holder);

        holder.getCountries().stream()
                .forEach(c -> em.persist(c));

        em.getTransaction().commit();
    }

    public void setRelationships(DataHolder holder) {
        for (Country country : holder.getCountries()) {
            for (Representative representative : country.getRepresentatives()) {
                representative.setCountry(country);

                for (Customer customer : representative.getCustomers()) {
                    customer.setRepresentative(representative);
                }
            }
        }
    }

    public List<Country> getAllCountries() {
        TypedQuery query = em.createNamedQuery("Country.getAll", Country.class);

        return query.getResultList();
    }

    public List<Representative> getRepresentativesOfCountry(String countryName) {
        TypedQuery query = em.createNamedQuery("Representative.getByCountry", Representative.class);

        query.setParameter("countryName", countryName);

        return query.getResultList();
    }

    public List<Customer> getCustomersFromRepresentative(String representativeName) {
        TypedQuery query = em.createNamedQuery("Customer.getByRepresentative", Customer.class);

        query.setParameter("representativeName", representativeName);

        query.getResultList()
                .stream()
                .forEach(System.out::println);

        return query.getResultList();
    }

    /*public List<Customer> getCustomersFromRepresenative(String represenativeName) {
        
    }*/
    public static void main(String[] args) {
        DB_Access.getInstance().persistDataHolder();
    }

}
