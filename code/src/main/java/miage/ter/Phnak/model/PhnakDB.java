package miage.ter.Phnak.model;

//import sun.java2d.pipe.SpanShapeRenderer;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.lang.Exception;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;


/**
 * Cette classe représente une connexion à la base de données. Elle encapsule
 * les méthodes permettant de lire et écrire les données Elle communique avec le
 * monde extérieur en utilisant des objets Java standard (int, String, …) ou des
 * classes du modèle (Point).
 * 
 * Les objets de cette classe seront stockés dans la session HTTP. Chaque client
 * du serveur Web aura donc une connection à la base de donnée le temps de sa
 * session.
 * 
 * La classe est Serializable, elle peut être sauvegardée sur le disque si le
 * serveur Web veut mettre la session en cache. Évidemment la connection doit
 * alors être.
 * 
 * La classe est AutoClosable, sa méthode close() est appelée lorsque java n'a
 * plus besoin de l'objet, permettant de fermer la connexion à la BD proprement.
 * 
 * La classe implémente HttpSessionBindingListener pour pouvoir fermer la
 * connexion lorsque les objets sont retirés de la session HTTP ou que celle ci
 * est détruite.
 * 
 * Cette classe doit être renommée pour avoir un nom plus parlant (MagasinDB,
 * StoreDB, …)
 * 
 * Les premières méthodes privées doivent rester telle qu'elle, les méthodes
 * faisant des SELECT/UPDATE/INSERT doivent évidemment être adaptées.
 */

public class PhnakDB implements Serializable, AutoCloseable, HttpSessionBindingListener {

    // Si l'objet est sauvé sur le disque, cette attribut ne sera pas sauvé et mis à
    // null.
    private transient Connection cnx;
    private String host;
    private String port;
    private String name;
    private String user;
    private String pass;

    // Appelée lorsque l'objet va être mis sur le disque
    private void writeObject(ObjectOutputStream oos) throws IOException {
        close();
        oos.defaultWriteObject(); // Calling the default serialization logic
    }

    /** Crée une connexion si elle n'est pas déjà ouverte */

    private Connection getConnection() throws SQLException {
        if (cnx == null || cnx.isClosed()) {
            cnx = DriverManager.getConnection("jdbc:postgresql://" + host + ":" + port + "/" + name, user, pass);
        }
        return cnx;
    }

    /** Appelée lorsqu'on retire cet objet de la session. */
    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        close();
    }

    /** Permet de fermer explicitement la connexion */
    public void close() {
        if (cnx != null) {
            try {
                cnx.close();
            } catch (SQLException e) {
                // déjà fermé ?
            }
            cnx = null;
        }
    }

    ///// PARTIE À MODIFIER ///////

    public PhnakDB(String host, String port, String name, String user, String pass) {
        // Renommer le constructeur et
        // initialiser les autres champs si besoin.
        this.host = host;
        this.port = port;
        this.name = name;
        this.user = user;
        this.pass = pass;
    }

    /**
     * Méthode qui récupère l'ensemble des produits
     * @return
     * @throws SQLException
     */
    public List<SimpleProduct> getAllSimpleProduct() throws SQLException {
        List<SimpleProduct> lst = new ArrayList<>();
        try (Statement stmt = getConnection().createStatement()) {
            ResultSet res = stmt.executeQuery("SELECT * FROM produit p, marque m WHERE p.idMarque = m.idMarque");
            while (res.next()) {
                lst.add(new SimpleProduct(res.getInt("idProduit"), res.getInt("idMarque"), res.getString("nomProduit"), res.getString("descriptifProduit"), res.getFloat("prixProduit"), res.getString("nomMarque")));
            }
        }
        return lst;
    }

    /**
     * Une méthode qui permet de retourner tous les produits à l'aide du mot clé de recherche
     * @param recherche
     * @return
     * @throws SQLException
     */
    public List<SimpleProduct> SearchProduct(String recherche) throws SQLException {
        List<SimpleProduct> lst = new ArrayList<>();
        try (Statement stmt = getConnection().createStatement()) {
            Connection con = getConnection();
            PreparedStatement search;
            //search = con.prepareStatement("SELECT * FROM produit p, marque m WHERE p.idMarque = m.idMarque AND p.nomProduit LIKE \"%?%\" OR p.descriptifProduit LIKE \"%?%\" OR m.nomMarque LIKE \"%?%\"");
            search = con.prepareStatement("SELECT * FROM produit p, marque m WHERE p.idMarque = m.idMarque AND UPPER(p.nomProduit) LIKE UPPER('%"+ recherche +"%') UNION SELECT * FROM produit p, marque m WHERE p.idmarque = m.idmarque And UPPER(p.descriptifproduit) LIKE UPPER('%"+ recherche +"%') UNION SELECT * FROM produit p, marque m WHERE p.idmarque = m.idmarque And UPPER(m.nomMarque) LIKE UPPER('%"+ recherche +"%')");

            //search.setString(1, recherche);
            //search.setString(2, recherche);
            //search.setString(3, recherche);
            //ResultSet res = stmt.executeQuery("SELECT * FROM produit p, marque m WHERE p.idMarque = m.idMarque AND p.nomProduit LIKE %?% AND p.descriptifProduit LIKE %?%");
            ResultSet res = search.executeQuery();
            while (res.next()) {
                lst.add(new SimpleProduct(res.getInt("idProduit"), res.getInt("idMarque"), res.getString("nomProduit"), res.getString("descriptifProduit"), res.getFloat("prixProduit"), res.getString("nomMarque")));
            }
        }
        return lst;
    }

    /**
     * Recherche d'un code promo
     * @param codePromo
     * @return
     * @throws SQLException
     */
    public CodePromo SearchPromo(String codePromo) throws SQLException {
        try (Statement stmt = getConnection().createStatement()) {
            Connection con = getConnection();

            String sql = "SELECT * FROM CODEPROMO WHERE nomcodepromo = ? ";
            PreparedStatement search = con.prepareStatement(sql);
            search.setString(1, codePromo);
            ResultSet res = search.executeQuery();
            while(res.next()){
                return new CodePromo(res.getInt("idcodepromo"), res.getFloat("valcodepromo"), res.getString("nomcodepromo"), res.getString("typecodepromo"), res.getString("dateexp"));
            }
        }
        return null;
    }

    /**
     * Recherche d'un produit avec l'id du produit
     * @param idProduct
     * @return
     * @throws SQLException
     */
    public SimpleProduct SearchProduct(int idProduct) throws SQLException {
        SimpleProduct produit = null;
        try (Statement stmt = getConnection().createStatement()) {
            Connection con = getConnection();
            String sql = "SELECT * FROM produit p, marque m WHERE p.idMarque = m.idMarque AND p.idProduit = ?";
            PreparedStatement search = con.prepareStatement(sql);
            search.setInt(1, idProduct);
            ResultSet res = search.executeQuery();
            if(res.next()!=false){
                return new SimpleProduct(res.getInt("idProduit"), res.getInt("idMarque"), res.getString("nomProduit"), res.getString("descriptifProduit"), res.getFloat("prixProduit"), res.getString("nomMarque"));
            }
        }catch (SQLException e) {
            throw e;
        }
        return produit;
    }

    /**
     * Pas eu le temps de finir.
     */
	/*
    public List<AvisProduct> getAllAvisProduct() throws SQLException {
        List<AvisProduct> lst = new ArrayList<>();
        try (Statement stmt = getConnection().createStatement()) {
            ResultSet res = stmt.executeQuery("SELECT * FROM produit p, marque m, avis a WHERE p.idMarque = m.idMarque AND a.idProduit = p.idProduit");
            while (res.next()) {
                lst.add(new AvisProduct(res.getInt("idProduit"), res.getInt("idMarque"), res.getString("nomProduit"), res.getString("descriptifProduit"), res.getFloat("prixProduit"), res.getString("nomMarque"), res.getString("dateAvis"), res.getString("descriptifAvis"), res.getString("noteAvis")));
            }
        }
        return lst;
	}
    */

    /**
     * Une méthode qui vérifie que le client n'existe pas.
     * @param mailClient
     * @return
     * @throws SQLException
     */
    public boolean checkExistingClient(String mailClient) throws SQLException {
        try (Statement stmt = getConnection().createStatement()) {
            Connection con = getConnection();
            String sql = "SELECT * FROM client WHERE mailclient = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, mailClient);
            ResultSet res = preparedStatement.executeQuery();
            if(res.next() == false){
                return false;
            }else{
                return true;
            }
        } catch(SQLException e) {
            throw e;
        }
    }

    /**
     * Une méthode qui vérifie que le client n'existe pas.
     * @param mailClient
     * @param password
     * @return
     * @throws SQLException
     */
    public Client signIn(String mailClient, String password) throws SQLException {
        Client client = null;
        try (Statement stmt = getConnection().createStatement()) {
            Connection con = getConnection();
            String pass_md5 = mailClient+":"+password;
            String sql = "SELECT * FROM client WHERE mailclient = ? and mdpclient=MD5(?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, mailClient);
            preparedStatement.setString(2, pass_md5);
            ResultSet res = preparedStatement.executeQuery();
            if(res.next()!=false){
                return new Client(res.getInt("idclient"), res.getString("nomclient"), res.getString("prenomclient"), res.getString("datenaissanceclient"), res.getString("mailclient"), res.getString("telephoneclient"), res.getString("civiliteclient"));
            }
        } catch(SQLException e) {
            throw e;
        }
        return client;
    }

    /**
     * Méthode d'inscription d'un nouvel utilisateur
     * @param email
     * @param password
     * @param sex
     * @param nom
     * @param prenom
     * @param date_naissance
     * @param telephone
     * @throws SQLException
     */
    public void signUp(String email, String password, String sex, String nom, String prenom, String date_naissance, String telephone) throws SQLException {
        try (Statement stmt = getConnection().createStatement()) {
            Connection con = getConnection();
            String sql = "SELECT MAX(idclient) FROM client";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet res = preparedStatement.executeQuery();
            int idclient = 0;
            while(res.next()){
                idclient = res.getInt(1)+1;
            }

            String pass_md5 = email+":"+password;
            sql = "INSERT INTO client (idclient, nomclient, prenomclient, datenaissanceclient, mdpclient, mailclient, telephoneclient, civiliteclient) VALUES (?,?,?,?,md5(?),?,?,?)";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, idclient);
            preparedStatement.setString(2, nom);
            preparedStatement.setString(3, prenom);
            preparedStatement.setDate(4, java.sql.Date.valueOf(date_naissance));
            preparedStatement.setString(5, pass_md5);
            preparedStatement.setString(6, email);
            preparedStatement.setString(7, telephone);
            preparedStatement.setString(8, sex);
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            throw e;
        }
    }

    /**
     * Une méthode qui met à jour les données client.
     * @param idClient
     * @param nomClient
     * @param prenomClient
     * @param datenaissanceClient
     * @param mailClient
     * @param telephoneClient
     * @param civiliteClient
     * @return
     * @throws SQLException
     */
    public Client updateClient(int idClient, String nomClient, String prenomClient, String datenaissanceClient, String mailClient, String telephoneClient, String civiliteClient) throws SQLException {
    //public Client updateClient(int idClient, String nomClient, String prenomClient, String mailClient, String telephoneClient, String civiliteClient) throws SQLException {
        Client client = null;
        try (Statement stmt = getConnection().createStatement()) {
            Connection con = getConnection();
            //String sql = "UPDATE client set nomclient = ?, prenomclient = ?, datenaissanceclient = ?, mailclient = ?, telephoneclient = ?, civiliteclient = ? WHERE idclient = ?";
            System.out.println(idClient);
            String sql = "UPDATE client set nomclient = ?, prenomclient = ?, datenaissanceclient = '2020-05-10', mailclient = ?, telephoneclient = ?, civiliteclient = 'M' WHERE idclient = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, nomClient);
            preparedStatement.setString(2, prenomClient);

            /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = sdf.parse(datenaissanceClient);
            java.sql.Date sqlDatenaissanceClient_updated = new java.sql.Date(date.getTime());
*/

            //preparedStatement.setDate(3, sqlDatenaissanceClient_updated);
            //preparedStatement.setString(3, datenaissanceClient);
            preparedStatement.setString(3, mailClient);
            preparedStatement.setString(4, telephoneClient);
            preparedStatement.setInt(5, idClient);
            preparedStatement.executeUpdate();

            String sql2 = "SELECT * FROM client where idClient = ?";
            PreparedStatement preparedStatement2 = con.prepareStatement(sql2);
            preparedStatement2.setInt(1, idClient);
            ResultSet res = preparedStatement2.executeQuery();

            if(res.next()!=false){
                //return new Client(res.getInt("idclient"), res.getString("nomclient"), res.getString("prenomclient"), res.getString("mailclient"), res.getString("telephoneclient"), res.getString("civiliteclient"));
               return new Client(res.getInt("idclient"), res.getString("nomclient"), res.getString("prenomclient"), res.getString("datenaissanceClient"), res.getString("mailclient"), res.getString("telephoneclient"), res.getString("civiliteclient"));
            }

        } catch(SQLException e) {
            throw e;
        }
        return client;
    }

    /**
     * Méthode de création de la commande et des lignes commandes
     * @param panier
     * @param client
     * @throws SQLException
     */
    public void createCommande(Panier panier, Client client) throws SQLException {
        try (Statement stmt = getConnection().createStatement()) {
            /**
             * Récupération dernier id commande
             */
            Connection con = getConnection();
            String sql = "SELECT MAX(idcommande) FROM commande";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet res = preparedStatement.executeQuery();
            int idcommande = 0;
            while(res.next()){
                idcommande = res.getInt(1)+1;
            }

            /**
             * Création commande
             */
            sql = "INSERT INTO commande (idcommande, idclient, idcodepromo, prixtotalcommande, statuscommande, prixavantremise, remise) VALUES (?,?,?,?,?,?,?)";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, idcommande);
            preparedStatement.setInt(2, client.getIdClient());
            if(panier.getIdcodepromo()!=0) {
                preparedStatement.setInt(3, panier.getIdcodepromo());
            }else{
                preparedStatement.setNull(3, Types.INTEGER);
            }
            preparedStatement.setBigDecimal(4, panier.getPrixtotal());
            preparedStatement.setString(5, "Paiement accepté");
            preparedStatement.setFloat(6, panier.getPrixavantremise());
            preparedStatement.setFloat(7, panier.getRemise());
            preparedStatement.executeUpdate();

            /**
            * Création lignes commande
             */
            Iterator<Map.Entry<SimpleProduct, Integer>> il = panier.getPanier().entrySet().iterator();
            while (il.hasNext()) {
                /**
                 * Récupération dernier id commande
                 */
                sql = "SELECT MAX(idlignecommande) FROM lignecommande";
                preparedStatement = con.prepareStatement(sql);
                res = preparedStatement.executeQuery();
                int idlignecommande = 0;
                while(res.next()){
                    idlignecommande = res.getInt(1)+1;
                }

                /**
                 * Insertion ligne commande
                 */
                Map.Entry<SimpleProduct, Integer> entry = il.next();
                int idproduit = entry.getKey().getIdProduit();
                int quantite =  entry.getValue().intValue();
                sql = "INSERT INTO lignecommande (idlignecommande, idproduit, idcommande, quantiteajouter) VALUES (?,?,?,?)";
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setInt(1, idlignecommande);
                preparedStatement.setInt(2, idproduit);
                preparedStatement.setInt(3, idcommande);
                preparedStatement.setInt(4, quantite);
                preparedStatement.executeUpdate();
            }
        } catch(SQLException e) {
            throw e;
        }
    }
}
