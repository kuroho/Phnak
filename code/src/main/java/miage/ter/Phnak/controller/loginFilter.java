package miage.ter.Phnak.controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = {"/PaiementServlet"})
public class loginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        /**
         * Si le client n'est pas authentifié lors du paiement, on le renvoie sur la page de login
         */
        if(req.getSession().getAttribute("client")==null) {
            System.err.println("Pas authentifié");
            resp.sendRedirect("login.jsp");
        } else {
            filterChain.doFilter(req, resp);
        }

    }

}