/*
 * Course project - IV1201 Design of Global Applications
 * Royal Institute of Technology
 * 2015 (c) Kim Hammar Alexander Lundh Marcel Mattsson
 */
package grupp14.IV1201.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 *
 * @author kim
 */

//Says that the use of this annotation will be contained in the JavaDoc of
//elementes with it
@Documented
//Says that methods,fields and annotation declariations  may be annotated
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
//Specifies that annotations of this type will be available at runtime by the
//the means of reflection
@Retention(RetentionPolicy.RUNTIME)
//Specifies the validator to be used to validate elements annotated with this
//annotation.
@Constraint(validatedBy = ValidEmailImplementation.class)
//@Interface to declare a annotation type
public @interface ValidEmail {
    
    //attribute that returns the default key for creating error messages if
    //constraint is violated
    String message() default "The email you entered is not valid";
    
    //Allows specifikation of validation groups to which this constraint belongs
    Class<?>[] groups() default {};
    
    //Can be used by clients of the API to asign custom payload objects to a 
    //constraint
    Class<? extends Payload>[] payload() default {};    
}
