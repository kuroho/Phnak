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


@WebServlet("/SearchProductServlet")
public class SearchProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            PhnakDB db = Utils.getDB(req);

            String search = Utils.stringParameter(req, "recherche");

            List<SimpleProduct> Produits = db.SearchProduct(search);

            req.setAttribute("SimpleProduct", Produits);

            req.getRequestDispatcher("list_simple_products.jsp").forward(req, resp);

        } catch (SQLException e) {

            throw new ServletException(e);
        }

    }
}
