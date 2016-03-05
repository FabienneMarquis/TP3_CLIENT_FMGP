package model;

import java.util.List;

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
        String checkin="";
        String checkout="";

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
                    checkin= infoC1[1];
                    break;
                case "checkout":
                    checkout= infoC1[1];
                    break;
            }
        }
        if(id!=0&&id_client!=0&&id_chambre!=0&&checkin!=""&&checkout!=""){
            chambre= new Chambre(id, checkin, checkout,id_client, id_chambre);
            chambres.add(chambre);
        }
    }

    public void pushListToContext(){
        Context.getInstance().setListChambre(chambres);
        Context.getInstance().setClients(clients);
        Context.getInstance().setReservations(reservations);
    };


}
