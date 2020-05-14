package data;

import data.Country;
import data.Customer;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2020-05-14T18:41:46")
@StaticMetamodel(Representative.class)
public class Representative_ { 

    public static volatile SingularAttribute<Representative, Country> country;
    public static volatile SingularAttribute<Representative, Long> representativeId;
    public static volatile SingularAttribute<Representative, String> name;
    public static volatile ListAttribute<Representative, Customer> customers;

}