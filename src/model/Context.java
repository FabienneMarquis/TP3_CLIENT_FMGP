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
    private String ip;
    private final String PORT = "port";
    private final String confFile = "conf.txt";
    private boolean connection;
    private int numEmploye;
    private String passWordEmploye;
    private ConnexionAuServeur connectServe;
    private String nomEmpl;
    private String prenomEmp;
    private Chambre chambre;
    private List<Chambre> listChambre;
    private List<Client> clients;
    private List<Reservation> reservations;

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
     * return Port from properties if 0 or else the custom value
     * @return port
     */
    public int getPort() {
        if(port == 0){
            port = getPortFromProperties();
        }
        return port;
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

    /**
     * set the port
     * @param port
     */
    public void setPort(int port) {
        System.out.println(port);
        this.port = port;
    }

    private int getPortFromProperties() {
        Properties properties = new Properties();
        InputStream in = null;
        try {
            in = getClass().getResourceAsStream(confFile);
            properties.load(in);
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return Integer.valueOf(properties.getProperty(PORT));
    }

    public void modificationReservation(){
        String modRez= "modification:Reservation:"+reservation.getDateCheckIn()+":"+reservation.getDateCheckOut()+";"+reservation.getIdChambre()+":"+
                reservation.getIdClient();
    }

    public void modificaitonClient(){
        String modClient = "modifier:client:"+client.getIdClient()+":"+client.getNom()+":"+client.getPrenom()+":"+client.getTelephone();
        //envoit au thread
    }

    public void newClient(String nom, String prenom, int phone){
        String newClient= "new:Client:"+nom+":"+prenom+":"+phone;
        //retourne idClient
    }

    public void newReservation(){
        String newRez= "new:Reservation:"+reservation.getDateCheckIn()+":"+reservation.getDateCheckOut()+";"+reservation.getIdChambre()+":"+
                reservation.getIdClient();
        //création buffer
        //après le retour du serveur ajouter le ID de la réservation puis ajouter la réservation à la liste
        // reservations.add(reservation);

    }

    public void connectServeur(){
        connectServe.run();
        if(connection==true){
            String infoConnection = "employe:"+numEmploye+":"+passWordEmploye;
            //cosntruire liste de Chambres, liste Clients et Liste réservation (selon critère de date) thread
        }
    }

    public void fermetureConnect(){
        if (connectServe.fermetureConnection()==false)connection=false;
    }

}
