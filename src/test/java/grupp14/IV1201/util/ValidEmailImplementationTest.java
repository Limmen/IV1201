/*
 * Classname: ValidEmailImplementationTest
 * Version: 0.1
 * Date: 20-2-2016
 * Copyright Alexander Lundh, Kim Hammar, Marcel Mattsson 2016
 */

package grupp14.IV1201.util;

import javax.validation.ConstraintValidatorContext;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;

/**
 * This class contains test-cases for the ValidEmailImplementation class
 * @author kim
 */
public class ValidEmailImplementationTest 
{
    private ValidEmailImplementation validator;
    
    /**
     * Class constructor
     */
    public ValidEmailImplementationTest() 
    {
    }
  
    /**
     * This method is called before the tests are executed
     */
    @Before
    public void setUp() 
    {
        validator = new ValidEmailImplementation();
    }
    
    /**
     * This methods is called after the tests have finished
     */
    @After
    public void tearDown() 
    {
        validator = null;
    }

    /**
     * Test of isValid method, of class ValidEmailImplementation.
     */
    @Test
    public void testIsValid() 
    {
        ConstraintValidatorContext mockContext = mock(ConstraintValidatorContext.class);
        Assert.assertEquals(true, validator.isValid("email@domain.com", mockContext));
        Assert.assertEquals(true, validator.isValid("email@subdomain.domain.com", mockContext));
        Assert.assertEquals(true, validator.isValid("firstname+lastname@domain.com", mockContext));
        Assert.assertEquals(true, validator.isValid("1234567890@domain.com", mockContext));
        Assert.assertEquals(false, validator.isValid("plainaddress", mockContext));
        Assert.assertEquals(false, validator.isValid("#@%^%#$@#$@#.com", mockContext));
        Assert.assertEquals(false, validator.isValid("email@domain@domain.com", mockContext));
        Assert.assertEquals(true, validator.isValid(".email@domain.com", mockContext));
        Assert.assertEquals(true, validator.isValid(".@¤%\"&!\"&.com", mockContext));     
    }
    
}
