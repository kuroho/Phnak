package miage.ter.Phnak.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Panier {
    private HashMap<SimpleProduct, Integer> panier;
    private int idcodepromo;
    private BigDecimal prixtotal;
    private float remise;
    private float prixavantremise;
    private String codepromo;

    public Panier(HashMap<SimpleProduct, Integer> panier, int idcodepromo, BigDecimal prixtotal, float remise, float prixavantremise, String codepromo) {
        this.panier = panier;
        this.idcodepromo = idcodepromo;
        this.prixtotal = prixtotal;
        this.remise = remise;
        this.prixavantremise = prixavantremise;
        this.codepromo = codepromo;
    }

    public int getIdcodepromo() {
        return idcodepromo;
    }

    public void setIdcodepromo(int idcodepromo) {
        this.idcodepromo = idcodepromo;
    }

    public void setPrixtotal(BigDecimal prixtotal) {
        this.prixtotal = prixtotal;
    }

    public void setRemise(float remise) {
        this.remise = remise;
    }

    public float getPrixavantremise() {
        return prixavantremise;
    }

    public void setPrixavantremise(float prixavantremise) {
        this.prixavantremise = prixavantremise;
    }

    public void setCodepromo(String codepromo) {
        this.codepromo = codepromo;
    }

    public Panier() {
        this.panier = new HashMap<SimpleProduct, Integer>();
    }

    public BigDecimal getPrixtotal() {
        return prixtotal;
    }

    public float getRemise() {
        return remise;
    }

    public String getCodepromo() {
        return codepromo;
    }

    public HashMap<SimpleProduct, Integer> getPanier() {
        return panier;
    }

    public void setPanier(HashMap<SimpleProduct, Integer> panier) {
        this.panier = panier;
    }

    /**
     * On ajoute l'article dans l'objet panier
     * @param product
     * @param quantite
     */
    public void ajouterProduct(SimpleProduct product, int quantite) {
        Integer quantity = panier.get(product);
        if (quantity == null){
            quantity = Integer.valueOf(0);
        }
        quantity = Integer.valueOf(quantity.intValue()+quantite);
        panier.put(product, quantity);
    }

    /**
     * On supprime l'article de l'objet panier
     * @param product
     */
    public void supprimerArticle(SimpleProduct product) {
        panier.remove(product);
    }

    /**
     * On parcours la map afin de récupérer les prix des articles et calculer le montant
     * @return BigDecimal : le montant total du panier
     */
    public BigDecimal calculerPanier() {
        float total = 0;
        Iterator<Map.Entry<SimpleProduct, Integer>> il = this.panier.entrySet().iterator();
        while (il.hasNext()) {
            Map.Entry<SimpleProduct, Integer> entry = il.next();
            total += entry.getKey().getPrixProduit() * entry.getValue().intValue();
        }
        BigDecimal prixtotalround = new BigDecimal(total);
        prixtotalround = prixtotalround.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        return prixtotalround;
    }
}
