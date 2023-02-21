package miage.ter.Phnak.model;

public class Client {
    private final int idClient;
    private final String nomClient;
    private final String prenomClient;
    private final String dateNaissanceClient;
    private final String mailClient;
    private final String telephoneClient;
    private final String civiliteClient;

    public Client(int idClient, String nomClient, String prenomClient, String dateNaissanceClient, String mailClient, String telephoneClient, String civiliteClient) {
        this.idClient = idClient;
        this.nomClient = nomClient;
        this.prenomClient = prenomClient;
        this.dateNaissanceClient = dateNaissanceClient;
        this.mailClient = mailClient;
        this.telephoneClient = telephoneClient;
        this.civiliteClient = civiliteClient;
    }

    public int getIdClient() {
        return idClient;
    }

    public String getNomClient() {
        return nomClient;
    }

    public String getPrenomClient() {
        return prenomClient;
    }

    public String getDateNaissanceClient() {
        return dateNaissanceClient;
    }

    public String getMailClient() {
        return mailClient;
    }

    public String getTelephoneClient() {
        return telephoneClient;
    }

    public String getCiviliteClient() {
        return civiliteClient;
    }
}
