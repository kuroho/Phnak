package miage.ter.Phnak.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import miage.ter.Phnak.model.PhnakDB;

/**
 * Petite classe utilitaire pour gérer les sessions et les paramètres de requête
 * HTTP
 */

public final class Utils {
	private Utils() {
	}

	/**
	 * Lit un paramètre dans la requête et renvoie la chaîne vide plutôt que null si
	 * le paramètre est absent.
	 */
	public static String stringParameter(HttpServletRequest req, String param) {
		String s = req.getParameter(param);
		return s == null ? "" : s;
	}

	public static int intParameter(HttpServletRequest req, String param) {
		int nb = Integer.parseInt(req.getParameter(param));
		return nb;
	}


	public static int productParameter(HttpServletRequest req, String param) {
		int idprd = Integer.parseInt(req.getParameter(param));
		return idprd;
	}
	/**
	 * Renvoie une instance d'PhnakDB stocké dans la session et la crée en cas de
	 * besoin.
	 */
	public static PhnakDB getDB(HttpServletRequest req) {
		PhnakDB db = (PhnakDB) req.getSession().getAttribute("db");
		if (db == null) {
			ServletContext ctx = req.getServletContext();
			db = new PhnakDB((String) ctx.getAttribute("db.host"),
							   (String) ctx.getAttribute("db.port"),
							   (String) ctx.getAttribute("db.name"),
							   (String) ctx.getAttribute("db.user"),
							   (String) ctx.getAttribute("db.pass")

			);
			req.getSession().setAttribute("db", db);
		}
		return db;
	}

}
