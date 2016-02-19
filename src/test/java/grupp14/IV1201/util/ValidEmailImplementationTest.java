/*
 * Course project - IV1201 Design of Global Applications
 * Royal Institute of Technology
 * 2015 (c) Kim Hammar Alexander Lundh Marcel Mattsson
 */
package grupp14.IV1201.util;

import javax.validation.ConstraintValidatorContext;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;

/**
 *
 * @author kim
 */
public class ValidEmailImplementationTest {
    ValidEmailImplementation validator;
    public ValidEmailImplementationTest() {
    }
  
    @Before
    public void setUp() {
        validator = new ValidEmailImplementation();
    }
    
    @After
    public void tearDown() {
        validator = null;
    }

    /**
     * Test of isValid method, of class ValidEmailImplementation.
     */
    @Test
    public void testIsValid() {
        ConstraintValidatorContext mockContext = mock(ConstraintValidatorContext.class);
        Assert.assertEquals(true, validator.isValid("email@domain.com", mockContext));
        Assert.assertEquals(true, validator.isValid("email@subdomain.domain.com", mockContext));
        Assert.assertEquals(true, validator.isValid("firstname+lastname@domain.com", mockContext));
        Assert.assertEquals(true, validator.isValid("1234567890@domain.com", mockContext));
        Assert.assertEquals(false, validator.isValid("plainaddress", mockContext));
        Assert.assertEquals(false, validator.isValid("#@%^%#$@#$@#.com", mockContext));
        Assert.assertEquals(false, validator.isValid("email@domain@domain.com", mockContext));
        Assert.assertEquals(true, validator.isValid(".email@domain.com", mockContext));
        //@¤%"&!"&.com
        
    }
    
}
