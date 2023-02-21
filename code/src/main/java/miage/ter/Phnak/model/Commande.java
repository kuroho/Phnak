package miage.ter.Phnak.model;

public class Commande {
    private int idcommande;
    private int idclient;
    private String idcodepromo;
    private float prixtotalcommande;
    private String statuscommande;
    private float prixavantremise;
    private float remise;

    public Commande(int idcommande, int idclient, String idcodepromo, float prixtotalcommande, String statuscommande, float prixavantremise, float remise){
        this.idcommande = idcommande;
        this.idclient = idclient;
        this.idcodepromo = idcodepromo;
        this.prixtotalcommande = prixtotalcommande;
        this.statuscommande = statuscommande;
        this.prixavantremise = prixavantremise;
        this.remise = remise;
    }

    public int getIdcommande() {
        return idcommande;
    }

    public void setIdcommande(int idcommande) {
        this.idcommande = idcommande;
    }

    public int getIdclient() {
        return idclient;
    }

    public void setIdclient(int idclient) {
        this.idclient = idclient;
    }

    public String getIdcodepromo() {
        return idcodepromo;
    }

    public void setIdcodepromo(String idcodepromo) {
        this.idcodepromo = idcodepromo;
    }

    public float getPrixtotalcommande() {
        return prixtotalcommande;
    }

    public void setPrixtotalcommande(float prixtotalcommande) {
        this.prixtotalcommande = prixtotalcommande;
    }

    public String getStatuscommande() {
        return statuscommande;
    }

    public void setStatuscommande(String statuscommande) {
        this.statuscommande = statuscommande;
    }

    public float getPrixavantremise() {
        return prixavantremise;
    }

    public void setPrixavantremise(float prixavantremise) {
        this.prixavantremise = prixavantremise;
    }

    public float getRemise() {
        return remise;
    }

    public void setRemise(float remise) {
        this.remise = remise;
    }
}
