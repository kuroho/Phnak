package miage.ter.Phnak.controller;

import miage.ter.Phnak.model.CodePromo;
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
import java.util.List;


@WebServlet("/CodePromoPanierServlet")
public class CodePromoPanierServlet extends HttpServlet {

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
            String codepromo = Utils.stringParameter(req, "code_promo");
            Panier panier = (Panier) req.getSession().getAttribute("Panier");
            BigDecimal prixtotal = panier.calculerPanier();
            CodePromo promo = db.SearchPromo(codepromo);

            /**
             * Si aucune promotion trouvee, on renvoie une erreur
             * Sinon on l'applique sur le prix du panier
             */
            if(promo == null)
            {
                req.getRequestDispatcher("shoppingcart.jsp?invalidcode=true").forward(req, resp);
            }
            else
            {
                float montantreduc = (prixtotal.floatValue() * promo.getValcodepromo()) / 100;
                float prixtotalmaj = prixtotal.floatValue() - montantreduc;

                /**
                 * On transforme le prix en BigDecimal pour afficher 2 décimales
                 */
                BigDecimal prixtotalround = new BigDecimal(prixtotalmaj);
                prixtotalround = prixtotalround.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                BigDecimal remise = new BigDecimal(montantreduc);
                remise = remise.setScale(2, BigDecimal.ROUND_HALF_EVEN);

                /**
                 * On met à jour le panier
                 */
                panier.setIdcodepromo(promo.getIdcodepromo());
                panier.setCodepromo(codepromo);
                panier.setPrixtotal(prixtotalround);
                panier.setPrixavantremise(prixtotal.floatValue());
                panier.setRemise(remise.floatValue());

                req.getSession().setAttribute("Panier", panier);

                req.setAttribute("pourcentagePromo", promo.getValcodepromo());
                req.setAttribute("nomPromo", codepromo);
                req.setAttribute("prixPanierPromo", prixtotalround);

                req.getRequestDispatcher("shoppingcart.jsp").forward(req, resp);
            }

        } catch (SQLException e) {

            throw new ServletException(e);
        }

    }
}
