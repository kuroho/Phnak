package miage.ter.Phnak.controller;

import miage.ter.Phnak.model.Client;
import miage.ter.Phnak.model.Panier;
import miage.ter.Phnak.model.PhnakDB;
import miage.ter.Phnak.model.SimpleProduct;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

@WebServlet("/PaiementServlet")
public class PaiementServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            PhnakDB db = Utils.getDB(req);

            Panier panier = (Panier) req.getSession().getAttribute("Panier");
            Client client = (Client) req.getSession().getAttribute("client");

            /**
             *   Si aucun code promo, on set tout à nul
             */
            if(panier.getCodepromo() == null){
                BigDecimal prix = panier.calculerPanier();
                prix = prix.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                panier.setCodepromo(null);
                panier.setPrixtotal(prix);
                panier.setPrixavantremise(prix.floatValue());
                panier.setRemise(0);
            }

            /**
            *   Censé renvoyer sur la page de paiement
            */
            /*
            BigDecimal prix = panier.calculerPanier();
            double id_transaction;
            id_transaction = Math.random();

            String url = "http://localhost:9090/Phnak/PaiementSucceed";

            req.getRequestDispatcher("http://62.210.201.129:9090/bank/PrePaymentServlet?"+url+"&amount="+prix+"&id="+id_transaction).forward(req, resp);
            */

            /**
             *   On crée la commande (On fait mine que le paiement est passé)
             */
            db.createCommande(panier, client);

            /**
             *   On réinitialise le panier
             */
            req.getSession().setAttribute("Panier", null);

            req.getRequestDispatcher("index.jsp?command=true").forward(req, resp);

        } catch (SQLException e) {

            throw new ServletException(e);
        }

    }
}
