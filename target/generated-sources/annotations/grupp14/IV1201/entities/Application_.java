package grupp14.IV1201.entities;

import grupp14.IV1201.entities.Expertise;
import grupp14.IV1201.entities.Person;
import java.math.BigInteger;
import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-03-16T08:46:26")
@StaticMetamodel(Application.class)
public class Application_ { 

    public static volatile SingularAttribute<Application, BigInteger> id;
    public static volatile SingularAttribute<Application, Person> person;
    public static volatile SingularAttribute<Application, Expertise> expertise;
    public static volatile SingularAttribute<Application, Date> dateTo;
    public static volatile SingularAttribute<Application, Date> dateFrom;
    public static volatile SingularAttribute<Application, Float> yearsOfExperience;

}