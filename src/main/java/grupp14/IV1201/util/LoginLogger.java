/*
 * Course project - IV1201 Design of Global Applications
 * Royal Institute of Technology
 * 2015 (c) Kim Hammar Alexander Lundh Marcel Mattsson
 */
package grupp14.IV1201.util;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.interceptor.InterceptorBinding;

/**
 *
 * @author kim
 */
@InterceptorBinding
@Target({METHOD, TYPE})
@Retention(RUNTIME)
public @interface LoginLogger {
}
