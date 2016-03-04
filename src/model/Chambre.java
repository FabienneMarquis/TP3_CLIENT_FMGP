package model;

/**
 * Created by 1494778 on 2016-03-04.
 */
public class Chambre {
    private int idChambre;
    private String descripChambre;

    public Chambre(int idChambre, String descripChambre) {
        this.idChambre = idChambre;
        this.descripChambre = descripChambre;
    }

    public int getIdChambre() {
        return idChambre;
    }

    public void setIdChambre(int idChambre) {
        this.idChambre = idChambre;
    }

    public String getDescripChambre() {
        return descripChambre;
    }

    public void setDescripChambre(String descripChambre) {
        this.descripChambre = descripChambre;
    }
}
