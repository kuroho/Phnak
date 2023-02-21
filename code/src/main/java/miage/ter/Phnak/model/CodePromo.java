package miage.ter.Phnak.model;

public class CodePromo {
    private final int idcodepromo;
    private final float valcodepromo;
    private final String nomcodepromo;
    private final String typecodepromo;
    private final String dateexp;

    public CodePromo(int idcodepromo, float valcodepromo, String nomcodepromo, String typecodepromo, String dateexp) {
        this.idcodepromo = idcodepromo;
        this.valcodepromo = valcodepromo;
        this.nomcodepromo = nomcodepromo;
        this.typecodepromo = typecodepromo;
        this.dateexp = dateexp;
    }

    public int getIdcodepromo() {
        return idcodepromo;
    }

    public float getValcodepromo() {
        return valcodepromo;
    }

    public String getNomcodepromo() {
        return nomcodepromo;
    }

    public String getTypecodepromo() {
        return typecodepromo;
    }

    public String getDateexp() {
        return dateexp;
    }
}
