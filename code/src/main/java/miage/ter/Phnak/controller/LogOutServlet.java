package miage.ter.Phnak.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import javax.servlet.annotation.WebServlet;

@WebServlet("/LogOutServlet")
public class LogOutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        /**
         * On réinitialise l'attribut session client
         */
        req.getSession().setAttribute("client", null);
        resp.sendRedirect("ListSimpleProductsServlet");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /**
         * On réinitialise l'attribut session client
         */
        req.getSession().setAttribute("client", null);
        resp.sendRedirect("ListSimpleProductsServlet");

    }
}
