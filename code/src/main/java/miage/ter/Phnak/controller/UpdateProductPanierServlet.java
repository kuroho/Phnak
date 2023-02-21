package miage.ter.Phnak.controller;

import miage.ter.Phnak.model.Panier;
import miage.ter.Phnak.model.PhnakDB;
import miage.ter.Phnak.model.SimpleProduct;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/UpdateProductPanierServlet")
public class UpdateProductPanierServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            /**
             * Récupération des données
             */
            PhnakDB db = Utils.getDB(req);

            int quantitemaj = Utils.intParameter(req, "quantitemaj");
            int idproduct = Utils.productParameter(req, "id_produit");

            SimpleProduct produit = db.SearchProduct(idproduct);

            Panier panier = (Panier) req.getSession().getAttribute("Panier");

            /**
             * Ajout du produit dans le panier
             */
            panier.ajouterProduct(produit, quantitemaj);
            req.getSession().setAttribute("Panier", panier);
            req.setAttribute("prixPanier", panier.calculerPanier());

            req.getRequestDispatcher("shoppingcart.jsp").forward(req, resp);

        } catch (SQLException e) {

            throw new ServletException(e);
        }

    }
}
