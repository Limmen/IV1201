/*
* Classname: GenericLoggerInterceptor
* Version: 0.1
* Date: 15-2-2016
* Copyright Alexander Lundh, Kim Hammar, Marcel Mattsson 2016
*/

package grupp14.IV1201.util;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.logging.Level;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * Interceptor for Generic logging.
 *
 * Will cause the following methods to be called in conjunction with the annotated methods in
 * managed beans.
 * @author kim
 */

@GenericLogger
@Interceptor
public class GenericLoggerInterceptor
{
    private static final java.util.logging.Logger
            LOGGER = java.util.logging.Logger.getLogger("grupp14.IV1201");
    private static final Level FINEST = Level.FINEST;
    private static final Level SEVERE = Level.SEVERE;
    
    /**
     * This method is called when the annotated method is invoked.
     *
     * @param ctx context information about the intercepted invocation.
     * @return the invocated method.
     * @throws Exception if a exception is called from the intercepted method.
     */
    @AroundInvoke
    public Object logInvocation(InvocationContext ctx) throws Exception
    {   
        /* Log entry */
        Method targetMethod = logEntry(ctx);
        Object returnValue = null;
        try {
            /* Execute method */
            returnValue = ctx.proceed();
        } catch (Exception e) {
            /* Log exception */
            logException(targetMethod, e);
        }
        /* Log exit */
        logExit(targetMethod, returnValue);
        return returnValue;
    }
    
    private void logExit(Method targetMethod, Object returnValue)
    {
        Object[] args = {targetMethod.getDeclaringClass().getCanonicalName(),
            targetMethod.getName()};
        LOGGER.log(FINEST, "Call to {0}.{1} completed", args);
        if (!isVoid(targetMethod)) {
            LOGGER.log(FINEST, "    Return value: {0}", returnValue);
        }
    }
    
    private void logException(Method targetMethod, Exception e) throws Exception
    {
        Object[] args = {targetMethod.getDeclaringClass().getCanonicalName(),
            targetMethod.getName(), e.getClass().getCanonicalName()};
        LOGGER.log(SEVERE, "{0}.{1} threw {2}", args);
        throw (e);
    }
    
    private Method logEntry(InvocationContext ctx)
    {
        Method targetMethod = ctx.getMethod();
        Object[] params = ctx.getParameters();
        LOGGER.log(FINEST, "Entering: {0}", targetMethod.toGenericString());
        LOGGER.log(FINEST, "    Parameters: {0}", Arrays.toString(params));
        return targetMethod;
    }
    
    private boolean isVoid(Method targetMethod)
    {
        return targetMethod.getReturnType().isAssignableFrom(Void.TYPE);
    }
}
