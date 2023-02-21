package miage.ter.Phnak.controller;
import miage.ter.Phnak.model.Client;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.lang.Exception;


import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;

@WebServlet("/UpdateInformationClientServlet")
public class UpdateInformationClientServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            /**
             * Récupèration des données post
             */
            miage.ter.Phnak.model.PhnakDB db = Utils.getDB(req);

            Client cl1 = (Client) req.getSession().getAttribute("client");

            int idClient = cl1.getIdClient();
            String mailClient = cl1.getMailClient();

            String nomClient = Utils.stringParameter(req, "nomClientUpdate");
            String prenomClient = Utils.stringParameter(req, "prenomClientUpdate");
            String datenaissanceClient = Utils.stringParameter(req, "dateNaissanceClientUpdate");

            String telephoneClient = Utils.stringParameter(req, "telephoneClientUpdate");
            String civiliteClient = Utils.stringParameter(req, "civiliteClientUpdate");

            /**
             * Mise à jour des données clients
             */
            Client c = db.updateClient(idClient, nomClient, prenomClient, datenaissanceClient, mailClient, telephoneClient, civiliteClient);

            if(c != null) {
                req.getSession().setAttribute("client", c);
                req.getRequestDispatcher("update.jsp").forward(req, resp);
            }

        } catch (SQLException e) {

            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("update.jsp").forward(req, resp);
    }
}
