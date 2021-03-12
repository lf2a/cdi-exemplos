package com.github.lf2a.interceptadores;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

/**
 * <h1>LoggedInterceptor.java</h1>
 * ---
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 12/03/2021
 */
@Logged
@Interceptor
public class LoggedInterceptor implements Serializable {

    public LoggedInterceptor() {
    }

    @AroundInvoke
    public Object logMethodEntry(InvocationContext invocationContext) throws Exception {
        System.out.println("Entrando no método: "
                + invocationContext.getMethod().getName() + " na classe "
                + invocationContext.getMethod().getDeclaringClass().getName());

        return invocationContext.proceed();
    }
}
