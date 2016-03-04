package model;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ConnexionAuServeur implements Runnable {

    private BufferedWriter writer = null;
    private BufferedReader reader = null;
    private SSLSocket c = null;

    private static int count = 0;
    private String name = "Client-";


    public void run() {
        System.setProperty("Djavax.net.ssl.trustStore=public.jks", "C:\\Program Files (x86)\\Java\\jre7\\bin\\my_home.crt");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintStream out = System.out;

        // Récupération du fabriquant de sockets client SSLs
        SSLSocketFactory f = (SSLSocketFactory) SSLSocketFactory.getDefault();

        try {
            // Création du socket:
            c = (SSLSocket) f.createSocket("localhost", 8888);

            // Démarre une négociation SSL sur cette connexion. Les raisons
            // courantes comprennent la nécessité d'utiliser de nouvelles clés
            // de cryptage, de changer les suites de chiffrement, ou de lancer
            // une nouvelle session
            c.startHandshake();

            // Le reste est comme une communication non sécurisé.
            writer = new BufferedWriter(new OutputStreamWriter(c.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(c.getInputStream()));
            String m = null;
            while ((m = reader.readLine()) != null) {
                out.println(m);
                m = in.readLine();
                writer.write(m, 0, m.length());
                writer.newLine();
                writer.flush();
            }
            writer.close();
            reader.close();
            c.close();
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    public boolean fermetureConnection() {
        boolean connect=true;
        try {
            writer.close();
            reader.close();
            c.close();
            connect=false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connect;
    }

    private String read() throws IOException {
        String response = "";
        int stream;
        byte[] b = new byte[4096];
        stream = reader.read();
        response = new String(b, 0, stream);
        return response;
    }
}
