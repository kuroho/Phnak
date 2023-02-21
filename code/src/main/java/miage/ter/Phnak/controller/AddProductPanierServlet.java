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

@WebServlet("/AddProductPanierServlet")
public class AddProductPanierServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            PhnakDB db = Utils.getDB(req);

            /**
             * Initialisation des paramètres
             */
            int idproduct = Utils.productParameter(req, "id_produit");
            SimpleProduct produit = db.SearchProduct(idproduct);
            Panier panier = (Panier) req.getSession().getAttribute("Panier");

            if (panier == null) {
                panier = new Panier();
            }

            int quantite = 1;

            /**
             * Ajout du produit dans le panier
             */
            panier.ajouterProduct(produit, quantite);
            req.getSession().setAttribute("Panier", panier);

            for (SimpleProduct product: panier.getPanier().keySet()) {
                System.out.println(product.getIdProduit() + ":" + product.getNomProduit());
            }

            for (SimpleProduct product: panier.getPanier().keySet()) {
                System.out.println("nb items : " + panier.getPanier().get(product));
            }

            req.getRequestDispatcher("ListSimpleProductsServlet").forward(req, resp);

        } catch (SQLException e) {

            throw new ServletException(e);
        }

    }
}
