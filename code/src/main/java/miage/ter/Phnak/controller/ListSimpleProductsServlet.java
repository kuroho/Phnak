package miage.ter.Phnak.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import miage.ter.Phnak.model.PhnakDB;
import miage.ter.Phnak.model.SimpleProduct;

@WebServlet("/ListSimpleProductsServlet")
public class ListSimpleProductsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            /**
             * On récupère la liste des produits
             */
            PhnakDB db = Utils.getDB(req);

            List<SimpleProduct> Produits = db.getAllSimpleProduct();

            req.setAttribute("SimpleProduct", Produits);

            req.getRequestDispatcher("list_simple_products.jsp").forward(req, resp);

        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

}
