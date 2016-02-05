/*
 * Course project - IV1201 Design of Global Applications
 * Royal Institute of Technology
 * 2015 (c) Kim Hammar Alexander Lundh Marcel Mattsson
 */
package grupp14.IV1201.view;

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
 * Class to facilitate navigation between pages depending on if the user
 * is logged in or not. This filter intercepts http-requests targeted
 * at the login-page.
 * @author kim
 */
public class LoginFilter implements Filter
{

    /**
     * Called when the filter is loaded for the first time.
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
   {

   }

    /**
     * This method does the interception of the http-request.
     * It will redirect user to the appropriate page.
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,   FilterChain filterChain) throws IOException, ServletException
   {
       HttpServletRequest request = (HttpServletRequest) servletRequest;
       HttpServletResponse response = (HttpServletResponse) servletResponse;

       if(request.getUserPrincipal() != null){ //Check if user is logged in
           if(request.isUserInRole("recruit"))
               response.sendRedirect("/recruit/index.xhtml");//if user is recruiter
           else
               response.sendRedirect("/applicant/index.xhtml");//if user is applicant
       } else
           filterChain.doFilter(servletRequest, servletResponse);//User is not logged in
       
   }

    /**
     * Called before filter is destroyed
     */
    @Override
    public void destroy()
   {

   }
}