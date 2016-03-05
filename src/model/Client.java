package model;

/**
 * Created by 1494778 on 2016-02-22.
 */
public class Client {
    private int idClient;
    private String nom;
    private String prenom;
    private int telephone;


    public Client(String nom, String prenom, int telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
    }

    public Client(int idClient, String nom, String prenom, int telephone) {
        this.idClient = idClient;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public int getIdClient() {
        return idClient;
    }
}
