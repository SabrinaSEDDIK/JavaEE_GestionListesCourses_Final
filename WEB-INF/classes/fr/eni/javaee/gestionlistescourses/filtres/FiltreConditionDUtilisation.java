package fr.eni.javaee.gestionlistescourses.filtres;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet Filter implementation class FiltreConditionDUtilisation
 */
@WebFilter(dispatcherTypes = {
				DispatcherType.REQUEST, 
		}
		, urlPatterns = { "/*" })
public class FiltreConditionDUtilisation implements Filter {

    /**
     * Default constructor. 
     */
    public FiltreConditionDUtilisation() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		if(httpRequest.getParameter("accepte")!=null || 
			httpRequest.getServletPath().startsWith("/css")||
			httpRequest.getServletPath().startsWith("/vendor")||
			httpRequest.getSession().getAttribute("ok")!=null)
		{
			httpRequest.getSession().setAttribute("ok", true);
			chain.doFilter(request, response);
			
		}
		else
		{
			httpRequest.setAttribute("urlCible", httpRequest.getContextPath()+httpRequest.getServletPath());
			RequestDispatcher rd = httpRequest.getRequestDispatcher("/WEB-INF/conditionDUtilisation.jsp");
			rd.forward(httpRequest, httpResponse);
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
