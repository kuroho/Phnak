package miage.ter.Phnak.controller;

import miage.ter.Phnak.model.PhnakDB;
import miage.ter.Phnak.model.SimpleProduct;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/DescriptifProductServlet")
public class DescriptifProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            /**
             * Récupération des données détaillées du produit
             */
            PhnakDB db = Utils.getDB(req);
            int idproduct = Utils.productParameter(req, "id_produit");
            SimpleProduct produit = db.SearchProduct(idproduct);

            /**
             * Création de l'attribut session produit
             */
            req.setAttribute("Desc_Product", produit);
            req.getRequestDispatcher("descriptifProduct.jsp").forward(req, resp);

        } catch (SQLException e) {

            throw new ServletException(e);
        }

    }
}
