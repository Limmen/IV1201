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
 * This is a servletfilter that is used for authorization of user requests to /recruit/*.
 * @author kim
 */
@GenericLogger
public class RecruitFilter implements Filter
{
    
    /**
     * This method is called by the container each time a request is issued to 
     * /recruit/* resources.
     * 
     * The method will check the http-session-parameters and then redirect the user
     * depending on it's permissions.
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
                !request.getSession().getAttribute("role").equals("recruit")){            
            response.sendRedirect(request.getContextPath()
                    + "/notallowed.xhtml");
        }
        chain.doFilter(request, response);
    }

    /**
     * This method is called by the web container to indicate to the filter that
     * is it being placed into service.
     * 
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig config) throws ServletException
    {
    }

    /**
     * This method is called by the web container to indicate to a filter that it is being
     * taken out of service.
     */
    @Override
    public void destroy()
    {
    }
    
}
