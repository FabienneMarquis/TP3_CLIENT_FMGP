package model;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Observable;

/**
 * Context est une classe qui sert pour contenir toute les informations specifique pour le fonctionnement de l'
 * application. Conntext est accesssible partout dans l'application par l'usage d'un singleton.
 *
 * @author Gabriel_Fabienne
 */
public class Context extends Observable {
    private static Context context;
    private Client client;
    private Reservation reservation;
    private int port;
    private String ip = "127.0.0.1";
    private boolean connection;
    private int numEmploye;
    private String passwordEmploye;
    private String nomEmpl;
    private String prenomEmp;
    private Chambre chambre;
    private List<Chambre> chambres;
    private List<Client> clients;
    private List<Reservation> reservations;
    private ClientSSL clientSSL;
    private Employee employee;

    private Context() {
        chambres = new ArrayList<>();
        clients = new ArrayList<>();
        reservations = new ArrayList<>();
        clientSSL = new ClientSSL(ip);
        clientSSL.start();
    }

    /**
     * Get instance du singleton
     *
     * @return instance unique
     */
    public static Context getInstance() {
        if (context == null) {
            context = new Context();
        }
        return context;
    }

    //
//    /**
//     * get serveur thread
//     * @return
//     */
//    public ServerThread getServerThread() {
//        return serverThread;
//    }
//
//    /**
//     * set server thread
//     * @param serverThread
//     */
//    public void setServerThread(ServerThread serverThread) {
//        this.serverThread = serverThread;
//    }
//
//    /**
//     * get client thread
//     * @return
//     */
//    public ClientThread getClientThread() {
//        return clientThread;
//    }
//
//    /**
//     * set client Thread
//     * @param clientThread
//     */
//    public void setClientThread(ClientThread clientThread) {
//        this.clientThread = clientThread;
//    }
//
    public List<Client> getClients() {
        return clients;
    }

    public void addClientFromServer() {
        //ajouter les clients du serveur
    }

    public void saveClientServeur() {

    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public String getNomEmpl() {
        return nomEmpl;
    }

    public void setNomEmpl(String nomEmpl) {
        this.nomEmpl = nomEmpl;
    }

    public String getPrenomEmp() {
        return prenomEmp;
    }

    public void setPrenomEmp(String prenomEmp) {
        this.prenomEmp = prenomEmp;
    }

    public Chambre getChambre() {
        return chambre;
    }

    public void setChambre(Chambre chambre) {
        this.chambre = chambre;
    }

    public List<Chambre> getChambres() {
        return chambres;
    }

    private void setListChambre() {
        //connection avec le serveur
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public void setChambres(List<Chambre> chambres) {
        this.chambres = chambres;
    }

    public int getNumEmploye() {
        return numEmploye;
    }

    public void setNumEmploye(int numEmploye) {
        this.numEmploye = numEmploye;
    }

    public String getPasswordEmploye() {
        return passwordEmploye;
    }

    public void setPasswordEmploye(String passwordEmploye) {
        this.passwordEmploye = passwordEmploye;
    }

    public Client getClient() {
        return client;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public boolean isLoggedIn() {
        return numEmploye > 0 && !prenomEmp.isEmpty() && !nomEmpl.isEmpty() && !passwordEmploye.isEmpty();
    }


    /**
     * Return local ip if null or the value set
     *
     * @return
     */
    public String getIp() {
        if (ip == null) {
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
        return ip;
    }

    /*
    * Info pour procédure d'envoit et réception de données
    * model@action?varname1=valeur&varname2=valeur
    * */

    public void modificationReservation() {
        String modRez = "reservation@modify?id=" + reservation.getIdReservation() + "id_client=" + reservation.getIdClient() + "&id_chambre=" + reservation.getIdChambre() + ""
                + "&checkin=" + reservation.getDateCheckIn()
                + "&checkout=" + reservation.getDateCheckOut();
    }

    public void modificaitonClient() {
        String modClient = "client@modify?id=" + client.getIdClient() + "&nom=" + client.getNom() + "&" + client.getPrenom() + ":" + client.getTelephone();
        //envoit au thread
    }

    public void newClient(String nom, String prenom, int phone) {
        String newClient = "client@new:" + nom + ":" + prenom + ":" + phone;
        //retourne idClient
    }

    public void newReservation() {
        String newRez = "reservation@new?id_client=" + reservation.getIdClient() + "&id_chambre=" + reservation.getIdChambre() + ""
                + "&checkin=" + reservation.getDateCheckIn() + "&checkout=" + reservation.getDateCheckOut();


        //création buffer
        //après le retour du serveur ajouter le ID de la réservation puis ajouter la réservation à la liste
        // reservations.add(reservation);

    }

    public void login(int numEmploye, String passWordEmploye) {
        if (!clientSSL.isFermer()) {
            String infoConnection = "employee@login?id=" + numEmploye + "&mot_de_passe=" + passWordEmploye;
            clientSSL.send(infoConnection);
        }
    }

    public void loginAccepted(String prenomEmp, String nomEmpl) {
        this.prenomEmp = prenomEmp;
        this.nomEmpl = nomEmpl;
        notifyObservers();
    }

    public void fermetureConnect() {
        clientSSL.close();
        if (clientSSL.isFermer()) connection = false;
    }

    public void findAndChange(int id, String type, String info) {
        switch (type) {
            case "reservation":
                findReservation(id, info);
                break;
            case "client":
                findClient(id,info);
                break;
        }
    }

    private void findReservation(int id, String info) {
        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getIdReservation() == id) {
                this.reservation = reservations.get(i);
                DateFormat format = new SimpleDateFormat("YYYY-MM-DD", Locale.CANADA);
                String[] infoC = info.split("&");
                for (int a = 0; infoC.length == a; a++) {
                    String[] infoC1 = infoC[a].split("=");
                    switch (infoC1[0]) {
                        case "id_client":
                            reservation.setIdClient(Integer.parseInt(infoC1[1]));
                            break;
                        case "id_chambre":
                            reservation.setIdChambre(Integer.parseInt(infoC1[1]));
                            break;
                        case "checkin":
                            try {
                                reservation.setDateCheckIn(format.parse(infoC1[1]));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            break;
                        case "checkout":
                            try {
                                reservation.setDateCheckOut(format.parse(infoC1[1]));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            break;
                    }
                }
            }
        }

    }

    private void findClient(int id, String info) {
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getIdClient() == id) {
                this.client = clients.get(i);
                String[] infoC= info.split("&");
                for (int a =0;infoC.length==a;a++)   {
                    String [] infoC1 = infoC[a].split("=");
                    switch(infoC1[0]){
                        case "nom":
                            client.setNom(infoC1[1]);
                            break;
                        case "prenom":
                            client.setPrenom(infoC1[1]);
                            break;
                        case "telephone":
                            client.setTelephone(Integer.parseInt(infoC1[1]));
                            break;

                    }
                }
            }
        }
    }

}
