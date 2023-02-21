package miage.ter.Phnak.model;

/**
 * Une classe « conteneur »
 * Il faut en créer une pour chaque valeur de BD que l'on veut renvoyer
 * hors du modèle.
 *
 * Les objets de cette classe sont « read-only » : ils servent juste de conteneur.
 * On ne peut pas les modifier car il représente une valeur de BD à modifier avec
 * un update.
 *
 * Pour chaque attribut t, il faut une méthodes *publique* getT() qui renvoie
 * sa valeur. Le framework JSP s'attend a trouver de telles méthodes.
 */

/**
 * Pas eu le temps de finir.
 */
public class SimpleAvis {
    private final int idProduit;
    private final int idMarque;
    private String nomProduit;
    private String descriptifProduit;
    private float prixProduit;
    private String nomMarque;
    private String dateAvis;
    private String descriptifAvis;
    private String noteAvis;

    public SimpleAvis(int idProduit, int idMarque, String nomProduit, String descriptifProduit, float prixProduit, String nomMarque, String dateAvis, String descriptifAvis, String noteAvis)  {
        this.idProduit = idProduit;
        this.idMarque = idMarque;
        this.nomProduit = nomProduit;
        this.descriptifProduit = descriptifProduit;
        this.prixProduit = prixProduit;
        this.nomMarque = nomMarque;
        this.dateAvis = dateAvis;
        this.descriptifAvis = descriptifAvis;
        this.noteAvis = noteAvis;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public int getIdMarque() {
        return idMarque;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public String getDescriptifProduit() {
        return descriptifProduit;
    }

    public void setDescriptifProduit(String descriptifProduit) {
        this.descriptifProduit = descriptifProduit;
    }

    public float getPrixProduit() {
        return prixProduit;
    }

    public void setPrixProduit(float prixProduit) {
        this.prixProduit = prixProduit;
    }

    public String getNomMarque() {
        return nomMarque;
    }

    public void setNomMarque(String nomMarque) {
        this.nomMarque = nomMarque;
    }

    public String getDateAvis() {
        return dateAvis;
    }

    public void setDateAvis(String dateAvis) {
        this.dateAvis = dateAvis;
    }

    public String getDescriptifAvis() {
        return descriptifAvis;
    }

    public void setDescriptifAvis(String descriptifAvis) {
        this.descriptifAvis = descriptifAvis;
    }

    public String getNoteAvis() { return noteAvis; }

    public void setNoteAvis(String noteAvis) {
        this.noteAvis = noteAvis;
    }
}
