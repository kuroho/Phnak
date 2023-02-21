package miage.ter.Phnak.controller;

import miage.ter.Phnak.model.Client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/SignInServlet")
public class SignInServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            /**
             * On récupère les paramètres post
             */
            miage.ter.Phnak.model.PhnakDB db = Utils.getDB(req);

            String login = Utils.stringParameter(req, "login");
            String password = Utils.stringParameter(req, "password");

            /**
             * On check si l'utilisateur existe, si oui on log
             */
            if(!db.checkExistingClient(login)){
                req.getRequestDispatcher("login.jsp?login_invalid=true").forward(req, resp);
            }

            Client c = db.signIn(login, password);

            /**
             * Gestion des erreurs
             */
            if(c != null) {
                req.getSession().setAttribute("client", c);
                req.getRequestDispatcher("ListSimpleProductsServlet").forward(req, resp);
            }else{
                req.getRequestDispatcher("login.jsp?password_invalid=true").forward(req, resp);
            }

        } catch (SQLException e) {
            throw new ServletException(e);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
}
