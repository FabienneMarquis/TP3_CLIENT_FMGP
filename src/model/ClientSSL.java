package model;

/**
 * Created by 0940135 on 2016-03-04.
 */

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class ClientSSL extends Thread{
    private SSLSocket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private boolean fermer;
    private FabriqueObjet fabriqueObjet;


    ClientSSL(String ip){
        System.setProperty("javax.net.ssl.trustStore", "public.jks");
        SSLSocketFactory ssf = (SSLSocketFactory) SSLSocketFactory.getDefault();
        try {
            socket = (SSLSocket)ssf.createSocket(ip, 8888);
            socket.startHandshake();
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            fermer=false;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void run() {
        while (true) {
            String line = null;
            try {
                fermer=false;
                line = bufferedReader.readLine();
                System.out.println(line);
                String[] inputs = line.split(":");
                switch (inputs[0]){
                    case "client":
                        switch (inputs[1]){
                            case "init":
                                break;
                        }
                        break;
                    case "employee":

                        break;
                    case "reservation":
                        String[] input2 = inputs[1].split("\\?");
                        switch (input2[0]){
                            case "init":
                                fabriqueObjet.constructReservation(input2[1]);
                                break;
                            case "new":
                                fabriqueObjet.constructReservation(input2[1]);
                                break;
                            case "modify":

                                break;
                        }
                        break;
                    case "chambre":

                        break;
                }
            } catch (IOException e) {
                close();
            }
            if (line == null)
                break;
        }
    }
    public boolean close() {
        fermer=false;
        try {
            socket.close();
            fermer=true;
        } catch (IOException e1) {
            //e1.printStackTrace();
            fermer=false;
        }
        try {
            bufferedReader.close();
            fermer=true;
        } catch (IOException e1) {
            // e1.printStackTrace();
            fermer=false;
        }
        try {
            bufferedWriter.close();
            fermer=true;
        } catch (IOException e1) {
            //e1.printStackTrace();
            fermer=false;
        }
        return fermer;
    }
    public void send(String msg){
        try {
            bufferedWriter.write(msg + "\n");
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
            close();
        }
    }
    public static void main(String args[]) throws Exception {
        ClientSSL clientSSL = new ClientSSL("172.18.10.35");
        clientSSL.start();
        clientSSL.send("Hello");

    }

    public boolean isFermer() {
        return fermer;
    }
}
