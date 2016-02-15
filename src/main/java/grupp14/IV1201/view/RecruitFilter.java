/*
 * RecruitFilter
 * Version: 0.1
 * Date: 15-2-2016
 * Copyright Alexander Lundh, Kim Hammar, Marcel Mattsson 2016
 */

package grupp14.IV1201.view;

import grupp14.IV1201.util.GenericLogger;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kim
 */

@GenericLogger
public class RecruitFilter implements Filter
{
    
    /**
     *
     * @param servletRequest
     * @param servletResponse
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse
            , FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (request.getSession().getAttribute("username") == null){
            response.sendRedirect(request.getContextPath() + "/login.xhtml");
        }
        else if(request.getSession().getAttribute("username") != null && 
                request.getSession().getAttribute("role") != "recruit"){
            response.sendRedirect(request.getContextPath()
                    + "/notallowed.xhtml");
        }
        chain.doFilter(request, response);
    }

    /**
     *
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig config) throws ServletException
    {
    }

    /**
     *
     */
    @Override
    public void destroy()
    {
    }
    
}
