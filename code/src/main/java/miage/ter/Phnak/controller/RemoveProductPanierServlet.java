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

@WebServlet("/RemoveProductPanierServlet")
public class RemoveProductPanierServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            /**
             * Récupération des paramètres
             */
            PhnakDB db = Utils.getDB(req);

            int idproductremove = Utils.productParameter(req, "id_produit");
            Panier panier = (Panier) req.getSession().getAttribute("Panier");
            SimpleProduct produit = db.SearchProduct(idproductremove);

            if (panier == null) {
                panier = new Panier();
            }

            /**
             * Suppression de l'article
             */
            panier.supprimerArticle(produit);
            req.getSession().setAttribute("Panier", panier);

            req.getRequestDispatcher("shoppingcart.jsp").forward(req, resp);

        } catch (SQLException e) {

            throw new ServletException(e);
        }

    }
}
