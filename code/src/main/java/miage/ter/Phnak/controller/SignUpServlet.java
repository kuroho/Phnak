package miage.ter.Phnak.controller;

import miage.ter.Phnak.model.Client;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;

@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            /**
             * Récupèration des paramètres en post
             */
            miage.ter.Phnak.model.PhnakDB db = Utils.getDB(req);

            String email = Utils.stringParameter(req, "email");
            String password = Utils.stringParameter(req, "password");
            String man = Utils.stringParameter(req, "man");
            String woman = Utils.stringParameter(req, "woman");
            String nom = Utils.stringParameter(req, "nom");
            String prenom = Utils.stringParameter(req, "prenom");
            String date_naissance = Utils.stringParameter(req, "date_naissance");
            String telephone = Utils.stringParameter(req, "telephone");

            /**
             * Traitement données
             */
            String sex = "";
            if(man.equals("monsieur")){
                sex = "H";
            }else if(woman.equals("madame")){
                sex="F";
            }

            /**
             * Gestion erreur
             */
            if(db.checkExistingClient(email)){
                req.getRequestDispatcher("signup.jsp?mail_existing=true").forward(req, resp);
            }

            /**
             * On l'inscrit et on le connecte automatiquement
             */
            db.signUp(email, password, sex, nom, prenom, date_naissance, telephone);
            Client c = db.signIn(email, password);

            req.getSession().setAttribute("client", c);
            req.getRequestDispatcher("ListSimpleProductsServlet").forward(req, resp);

        } catch (SQLException e) {
            throw new ServletException(e);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("signup.jsp").forward(req, resp);
    }
}
