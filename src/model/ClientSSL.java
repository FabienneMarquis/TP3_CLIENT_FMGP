package model;

/**
 * Created by 0940135 on 2016-03-04.
 */

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class ClientSSL extends Thread {
    private SSLSocket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private boolean fermer;
    private FabriqueObjet fabriqueObjet;


    ClientSSL(String ip) {
        System.setProperty("javax.net.ssl.trustStore", "public.jks");
        SSLSocketFactory ssf = (SSLSocketFactory) SSLSocketFactory.getDefault();
        try {
            socket = (SSLSocket) ssf.createSocket(ip, 8888);
            socket.startHandshake();
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            fermer = false;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void run() {
        while (true) {
            String line = null;
            try {
                fermer = false;
                line = bufferedReader.readLine();
                System.out.println(line);
                String[] inputs = line.split("@");
                String model = inputs[0];
                String[] actionAndArgs = inputs[1].split("\\?");
                String action = actionAndArgs[0];
                String args = actionAndArgs[1];

                switch (model) {
                    case "client": {
                        switch (action) {
                            case "init":
                                fabriqueObjet.constructClient(args);
                                break;
                            case"modify":
                                int id=0;
                                for (String arg: args.split("&")){
                                    switch (arg){
                                        case "id_client":
                                            id = Integer.parseInt(arg.split("=")[1]);
                                            break;
                                    }
                                }
                                Context.getInstance().findAndChange(id, model, args);
                                break;
                            case"new":
                                fabriqueObjet.constructClient(args);
                                break;
                        }
                    }

                    break;
                    case "employee":
                        switch (action) {
                            case "signin":

                                break;
                            case "login":
                                String prenom;
                                String nom;
                                for (String arg: args.split("&")){
                                    switch (arg){
                                        case "prenom":
                                            nom = arg.split("=")[1];
                                            break;
                                        case "nom":
                                            prenom = arg.split("=")[1];
                                            break;
                                    }
                                }
                                Context.getInstance().loginAccepted(prenom,nom);
                                break;
                        }
                        break;
                    case "reservation":
                        switch (action) {
                            case "init":
                                fabriqueObjet.constructReservation(args);
                                break;
                            case"new":
                                fabriqueObjet.constructReservation(args);
                                break;
                            case"modify":
                                int id=0;
                                for (String arg: args.split("&")){
                                    switch (arg){
                                        case "id_reservation":
                                            id = Integer.parseInt(arg.split("=")[1]);
                                            break;
                                    }
                                }
                                Context.getInstance().findAndChange(id, model, args);
                                break;
                        }
                        break;
                    case "chambre":
                        switch (action) {
                            case "all":
                                fabriqueObjet.contructChambre(args);
                                break;
                        }
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
        fermer = true;
        try {
            socket.close();
        } catch (IOException e1) {
            //e1.printStackTrace();
        }
        try {
            bufferedReader.close();
        } catch (IOException e1) {
            // e1.printStackTrace();
        }
        try {
            bufferedWriter.close();
        } catch (IOException e1) {
            //e1.printStackTrace();
        }
        return fermer;
    }

    public void send(String msg) {
        try {
            bufferedWriter.write(msg + "\n");
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
            close();
        }
    }

    public static void main(String args[]) throws Exception {
        ClientSSL clientSSL = new ClientSSL("127.0.0.1");
        clientSSL.start();
        clientSSL.send("Hello");

    }

    public boolean isFermer() {
        return fermer;
    }
}
