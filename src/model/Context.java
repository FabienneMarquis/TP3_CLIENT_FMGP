package model;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Observable;
import java.util.Properties;

/**
 * Context est une classe qui sert pour contenir toute les informations specifique pour le fonctionnement de l'
 * application. Conntext est accesssible partout dans l'application par l'usage d'un singleton.
 * @author Gabriel_Fabienne
 */
public class Context extends Observable {
    private static Context context;
    private Client client;
    private Reservation reservation;
    private int port;
    private String ip= "172.18.10.35";
    private boolean connection;
    private int numEmploye;
    private String passWordEmploye;
    private String nomEmpl;
    private String prenomEmp;
    private Chambre chambre;
    private List<Chambre> listChambre;
    private List<Client> clients;
    private List<Reservation> reservations;
    private ClientSSL clientSSL;

    private Context(){

    }

    /**
     * Get instance du singleton
     * @return instance unique
     */
    public static Context getInstance(){
        if(context == null){
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
    public List<Client> getClients(){
        return clients;
    }

    public void addClientFromServer(){
        //ajouter les clients du serveur
    }
    public void saveClientServeur(){

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

    public List<Chambre> getListChambre() {
        return listChambre;
    }

    private void setListChambre(){
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

    public void setListChambre(List<Chambre> listChambre) {
        this.listChambre = listChambre;
    }

    public int getNumEmploye() {
        return numEmploye;
    }

    public void setNumEmploye(int numEmploye) {
        this.numEmploye = numEmploye;
    }

    public String getPassWordEmploye() {
        return passWordEmploye;
    }

    public void setPassWordEmploye(String passWordEmploye) {
        this.passWordEmploye = passWordEmploye;
    }

    public Client getClient() {
        return client;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public boolean isConnection() {
        return connection;
    }


    /**
     * Return local ip if null or the value set
     * @return
     */
    public String getIp() {
        if(ip == null){
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

    public void modificationReservation(){
        String modRez= "reservation@modify?id="+reservation.getIdReservation()+"id_client="+reservation.getIdClient()+"&id_chambre="+reservation.getIdChambre()+""
                +"&checkin="+reservation.getDateCheckIn()
                +"&checkout="+reservation.getDateCheckOut();
    }

    public void modificaitonClient(){
        String modClient = "client:modify?id="+client.getIdClient()+"&nom="+client.getNom()+"&"+client.getPrenom()+":"+client.getTelephone();
        //envoit au thread
    }

    public void newClient(String nom, String prenom, int phone){
        String newClient= "client:new:"+nom+":"+prenom+":"+phone;
        //retourne idClient
    }

    public void newReservation(){
        String newRez= "reservation:new?id_client="+reservation.getIdClient()+"&id_chambre="+reservation.getIdChambre()+""
                +"&checkin="+reservation.getDateCheckIn()
                +"&checkout="+reservation.getDateCheckOut();


        //création buffer
        //après le retour du serveur ajouter le ID de la réservation puis ajouter la réservation à la liste
        // reservations.add(reservation);

    }

    public void connectServeur(){
        clientSSL= new ClientSSL(ip);
        clientSSL.run();
       clientSSL.isFermer();
        if(!clientSSL.isFermer()){
            String infoConnection = "employee@connection?id="+numEmploye+"&mot_de_passe="+passWordEmploye;
            clientSSL.send(infoConnection);
            //cosntruire liste de Chambres, liste Clients et Liste réservation (selon critère de date) thread
        }
    }

    public void fermetureConnect(){clientSSL.close();
        if (clientSSL.isFermer())connection=false;
    }

    public void findAndChange(int id, String type){
        switch (type){
            case "reservation":
                while(reservation.getIdReservation()!=id)
                break;
            case"client":

                break;
        }
    }


}
