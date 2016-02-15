/*
 * Classname: LoginLoggerInterceptor
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
 *
 * @author kim
 */
@LoginLogger 
@Interceptor
public class LoginLoggerInterceptor
{
    private static final java.util.logging.Logger 
            LOGGER = java.util.logging.Logger.getLogger("grupp14.IV1201");
    private static final Level INFO = Level.INFO;
    private static final Level SEVERE = Level.SEVERE;

    /**
     *
     * @param ctx
     * @return
     * @throws Exception
     */
    @AroundInvoke
    public Object logInvocation(InvocationContext ctx) throws Exception
    {
        Method targetMethod = logEntry(ctx); //Log entry
        Object returnValue = null;
        try {
            returnValue = ctx.proceed(); //execute method
        } catch (Exception e) {
            logException(targetMethod, e); //Log exception
        }
        logExit(targetMethod, returnValue); //Log exit
        return returnValue;
    }

    private void logExit(Method targetMethod, Object returnValue)
    {
        Object[] args = {targetMethod.getDeclaringClass().getCanonicalName()
            , targetMethod.getName()};
        LOGGER.log(INFO, "Call to {0}.{1} completed", args);
        if (!isVoid(targetMethod)) {
            LOGGER.log(INFO, "    Return value: {0}", returnValue);
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
        LOGGER.log(INFO, "FAILED LOGIN ATTEMPT");
        LOGGER.log(INFO, "Entering: {0}", targetMethod.toGenericString());
        LOGGER.log(INFO, "Parameters: {0}", Arrays.toString(params));
        return targetMethod;
    }

    private boolean isVoid(Method targetMethod) 
    {
        return targetMethod.getReturnType().isAssignableFrom(Void.TYPE);
    }
}
