package model;

import java.util.List;

/**
 * Created by 1494778 on 2016-02-23.
 */
public class ListReservations {
    private List<Reservation> reservations;

    public ListReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public void addClientFromServer(Reservation reservations){

        //ajouter les clients du serveur
    }
    public void saveClientServeur(){

    }

}
