package java;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        { pojos.RepresentativeTest.class, pojos.CountryTest.class,
            pojos.CustomerTest.class, pojos.DataHolderTest.class,
            xml.XML_AccessTest.class, xml.LocalDateAdapterTest.class,
            database.LocalDateConverterTest.class, database.DB_AccessTest.class }
)
public class RPTestSuite {
}
