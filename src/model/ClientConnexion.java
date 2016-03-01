package model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientConnexion implements Runnable {

    private Socket connexion = null;
    private BufferedOutputStream writer = null;
    private BufferedInputStream reader = null;

    private static int count = 0;
    private String name = "Client-";
    private int reponse;
    private int chiffre1;
    private int chiffre2;

    public ClientConnexion(String host, int port) {
        name += ++count;
        try {
            connexion = new Socket(host, port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String menuPrincipale() {
        String commande;
        Scanner scan = new Scanner(System.in);
        System.out.print("Quel calcul voulez-vous faire? \n 1-aditionner \n 2-soustraire \n 3-mutiplier \n 4-Diviser");
        reponse = scan.nextInt();
        System.out.print("Quel est le premier chiffre");
        chiffre1 = scan.nextInt();
        System.out.print("Quel est le deuxieme chiffre");
        chiffre2 = scan.nextInt();
        return commande = reponse + "/" + chiffre1 + "/" + chiffre2;
    }

    public void run() {


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            for (int i = 0; i < 10; i++) {
                writer = new BufferedOutputStream(connexion.getOutputStream());
                reader = new BufferedInputStream(connexion.getInputStream());

                String commande = menuPrincipale();
                writer.write(commande.getBytes());

                writer.flush();

                System.out.println("Commande " + commande + " envoyée au serveur");

                String response = read();
                System.out.println("\t * " + name + " : Réponse reçue " + response);
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // }

        try {
            writer.write("CLOSE".getBytes());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    private String read() throws IOException {
        String response = "";
        int stream;
        byte[] b = new byte[4096];
        stream = reader.read(b);
        response = new String(b, 0, stream);
        return response;
    }
}
