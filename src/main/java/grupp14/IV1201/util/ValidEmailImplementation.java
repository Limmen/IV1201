/*
 * Course project - IV1201 Design of Global Applications
 * Royal Institute of Technology
 * 2015 (c) Kim Hammar Alexander Lundh Marcel Mattsson
 */
package grupp14.IV1201.util;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author kim
 */
    class ValidEmailImplementation implements ConstraintValidator<ValidEmail, String>{
        
        @Override
        public void initialize(ValidEmail constraintAnnotation) {
        }
        
        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
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