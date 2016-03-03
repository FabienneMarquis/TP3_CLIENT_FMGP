package model;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.*;

/**
 * Created by 1494778 on 2016-02-22.
 */
public class GestionLog {
    private static Logger logger= Logger.getLogger("Logging.Semaine_6_logging");

    /**
     * @param args
     */
    public static void main(String[] args) {
        int nomLoggeur = Context.getInstance().getNumEmploye();
        Handler fh;
        try {
            fh = new FileHandler("myLog.log", true);
            logger.addHandler(fh);
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        logger.log(Level.SEVERE, nomLoggeur+" :");
        //ajouter la déconnection?


        String fileName = "temp1.txt";
        String line = null;
        try {
            FileReader fileReader =
                    new FileReader(fileName);

            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            logger.log(Level.SEVERE, "Fichier inexistant :" + ex.toString());
        }
        catch(IOException ex) {
            logger.log(Level.SEVERE, ex.toString());

        }
    }
}
