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
    ClientSSL(String ip){
        System.setProperty("javax.net.ssl.trustStore", "public.jks");
        SSLSocketFactory ssf = (SSLSocketFactory) SSLSocketFactory.getDefault();
        try {
            socket = (SSLSocket)ssf.createSocket(ip, 8888);
            socket.startHandshake();
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void run() {
        while (true) {
            String line = null;
            try {
                line = bufferedReader.readLine();
                System.out.println(line);
                // Traitement

                // wtf
            } catch (IOException e) {
                close();
            }
            if (line == null)
                break;
        }
    }
    private void close() {
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
}
