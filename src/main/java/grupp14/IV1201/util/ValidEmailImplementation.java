/*
* Classname: ValidEmailImplementation
* Version: 0.1
* Date: 15-2-2016
* Copyright Alexander Lundh, Kim Hammar, Marcel Mattsson 2016
*/

package grupp14.IV1201.util;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * This is a implementation of the ValidEmail validation-constraint.
 * @author kim
 */
class ValidEmailImplementation implements ConstraintValidator <ValidEmail, String>
{
    /* Initialize the validator in preparation for isValid() calls */
    @Override
    public void initialize(ValidEmail constraintAnnotation)
    {
    }
    /* Implement the validation logic */
    @Override
    public boolean isValid(String value,
            ConstraintValidatorContext context)
    {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(value);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
    
}