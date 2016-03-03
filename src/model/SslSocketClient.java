package model;

import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class SslSocketClient {
	public static void main(String[] args) {
		// System.setProperty("Djavax.net.ssl.trustStore=public.jks","C:\\Program
		// Files (x86)\\Java\\jre7\\bin\\my_home.crt");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintStream out = System.out;

		// Récupération du fabriquant de sockets client SSLs
		SSLSocketFactory f = (SSLSocketFactory) SSLSocketFactory.getDefault();

		try {
			// Création du socket:
			SSLSocket c = (SSLSocket) f.createSocket("localhost", 8888);

			printSocketInfo(c);

			// Démarre une négociation SSL sur cette connexion. Les raisons
			// courantes comprennent la nécessité d'utiliser de nouvelles clés
			// de cryptage, de changer les suites de chiffrement, ou de lancer
			// une nouvelle session
			c.startHandshake();

			// Le reste est comme une communication non sécurisé.
			BufferedWriter w = new BufferedWriter(new OutputStreamWriter(c.getOutputStream()));
			BufferedReader r = new BufferedReader(new InputStreamReader(c.getInputStream()));
			String m = null;
			while ((m = r.readLine()) != null) {
				out.println(m);
				m = in.readLine();
				w.write(m, 0, m.length());
				w.newLine();
				w.flush();
			}
			w.close();
			r.close();
			c.close();
		} catch (IOException e) {
			System.err.println(e.toString());
		}
	}

	private static void printSocketInfo(SSLSocket s) {
		System.out.println("Socket class: " + s.getClass());
		System.out.println("   Remote address = " + s.getInetAddress().toString());
		System.out.println("   Remote port = " + s.getPort());
		System.out.println("   Local socket address = " + s.getLocalSocketAddress().toString());
		System.out.println("   Local address = " + s.getLocalAddress().toString());
		System.out.println("   Local port = " + s.getLocalPort());
		System.out.println("   Need client authentication = " + s.getNeedClientAuth());
		SSLSession ss = s.getSession();
		System.out.println("   Cipher suite = " + ss.getCipherSuite());
		System.out.println("   Protocol = " + ss.getProtocol());
	}
}
