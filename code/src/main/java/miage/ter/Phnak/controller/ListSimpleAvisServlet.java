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
/**
 * On a pas eu le temps de finir.
*/

/*
@WebServlet("/ListSimpleAvisServlet")
public class ListSimpleAvisServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            PhnakDB db = Utils.getDB(req);

            List<SimpleAvis> Avis = db.getAllSimpleAvis();

            req.setAttribute("SimpleAvis", Avis);

            req.getRequestDispatcher("WEB-INF/jsp/list_simple_avis.jsp").forward(req, resp);

        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

}

*/