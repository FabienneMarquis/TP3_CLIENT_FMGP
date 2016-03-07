package model;

import java.util.Date;

/**
 * Created by 1494778 on 2016-02-23.
 */
public class Reservation {
    private int idReservation;
    private Date dateCheckIn;
    private Date dateCheckOut;
    private int idClient;
    private int idChambre;

    public Reservation(Date dateCheckIn, Date dateCheckOut, int idClient, int idChambre) {
        this.dateCheckIn = dateCheckIn;
        this.dateCheckOut = dateCheckOut;
        this.idClient = idClient;
        this.idChambre = idChambre;
    }

    public Reservation(int idReservation, Date dateCheckIn, Date dateCheckOut, int idClient, int idChambre) {
        this.idReservation = idReservation;
        this.dateCheckIn = dateCheckIn;
        this.dateCheckOut = dateCheckOut;
        this.idClient = idClient;
        this.idChambre = idChambre;
    }

    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public Date getDateCheckIn() {
        return dateCheckIn;
    }


    public void setDateCheckIn(Date dateCheckIn) {
        this.dateCheckIn = dateCheckIn;
    }

    public Date getDateCheckOut() {
        return dateCheckOut;
    }

    public void setDateCheckOut(Date dateCheckOut) {
        this.dateCheckOut = dateCheckOut;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdChambre() {
        return idChambre;
    }

    public void setIdChambre(int idChambre) {
        this.idChambre = idChambre;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "idReservation=" + idReservation +
                ", dateCheckIn=" + dateCheckIn +
                ", dateCheckOut=" + dateCheckOut +
                ", idClient=" + idClient +
                ", idChambre=" + idChambre +
                '}';
    }
}
