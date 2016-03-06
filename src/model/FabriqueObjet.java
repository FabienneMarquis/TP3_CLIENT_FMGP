package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Fabiennemarquis on 16-03-05.
 */
public class FabriqueObjet {
    private Client client;
    private List<Client> clients;
    private Reservation reservation;
    private List<Reservation> reservations;
    private Chambre chambre;
    private List<Chambre> chambres;

    public void constructClient(String info){
        int id=0;
        String nom="";
        String prenom="";
        int telephone=0;
        String[] infoC= info.split("&");
        for (int i =0;infoC.length==i;i++)   {
            String [] infoC1 = infoC[i].split("=");
            switch(infoC1[0]){
                case "id":
                    id = Integer.parseInt(infoC1[1]);
                    break;
                case "nom":
                    nom= infoC1[1];
                    break;
                case "prenom":
                    prenom= infoC1[1];
                    break;
                case "telephone":
                    telephone=Integer.parseInt(infoC1[1]);
                    break;
            }
        }
        if(id!=0&&nom!=""&&prenom!=""&&telephone!=0){
            client = new Client(id, nom, prenom, telephone);
            clients.add(client);
        }

    }

    public void contructChambre(String info){
        int id=0;
        String nom="";
        String description="";

        String[] infoC= info.split("&");
        for (int i =0;infoC.length==i;i++)   {
            String [] infoC1 = infoC[i].split("=");
            switch(infoC1[0]){
                case "id":
                    id = Integer.parseInt(infoC1[1]);
                    break;
                case "nom":
                    nom= infoC1[1];
                    break;
                case "description":
                    description= infoC1[1];
                    break;

            }
        }
        if(id!=0&&nom!=""&&description!=""){
            chambre= new Chambre(id, nom, description);
            chambres.add(chambre);
        }
    }
    public void constructReservation(String info){
        int id=0;
        int id_client=0;
        int id_chambre=0;
        Date checkin=null;
        Date checkout=null;
        DateFormat format = new SimpleDateFormat("YYYY-MM-DD", Locale.CANADA);
        String[] infoC= info.split("&");
        for (int i =0;infoC.length==i;i++)   {
            String [] infoC1 = infoC[i].split("=");
            switch(infoC1[0]){
                case "id":
                    id = Integer.parseInt(infoC1[1]);
                    break;
                case "id_client":
                    id_client= Integer.parseInt(infoC1[1]);
                    break;
                case "id_chambre":
                    id_chambre= Integer.parseInt(infoC1[1]);
                    break;
                case "checkin":
                    try {
                        checkin= format.parse(infoC1[1]);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                case "checkout":
                    try {
                        checkout= format.parse(infoC1[1]);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
        if(id!=0&&id_client!=0&&id_chambre!=0){
            reservation= new Reservation(id, checkin, checkout,id_client, id_chambre);
            reservations.add(reservation);
        }
    }

    public void pushListToContext(){
        Context.getInstance().setListChambre(chambres);
        Context.getInstance().setClients(clients);
        Context.getInstance().setReservations(reservations);
    };


}
