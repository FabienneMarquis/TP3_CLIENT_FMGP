package model;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
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
    private ListClients clients;
    private ListReservations reservations;
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

    public ListClients getClients() {
        return clients;
    }

    public ListReservations getReservations() {
        return reservations;
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

    public void newClient(String nom, String prenom, int phone){

    }

    public void newReservation(int idClient){

    }

    public void connectServeur(){
        connectServe.run();
    }

    public void fermetureConnect(){
        if (connectServe.fermetureConnection()==false)connection=false;
    }

}
