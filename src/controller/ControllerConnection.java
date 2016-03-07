package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import model.Context;

import javax.swing.*;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControllerConnection implements Initializable, Observer {

    @FXML
    private Text textBienvenu;

    @FXML
    private TextField textNoEmploye;

    @FXML
    private TextField textMotPasse;

    @FXML
    private Button btnConnection;

    @FXML
    private Button btnDeconnection;

    @FXML
    void connectionServer(ActionEvent event) {
        //System.out.println("wtf:"+Context.getInstance().getIp());
        if (Context.getInstance().getIp()==null) {
            String ip = "";
            try {
                ip = InetAddress.getLocalHost().getHostAddress();

            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            TextInputDialog dialog =new TextInputDialog(ip);
            dialog.setTitle("Configuration IP serveur");
            dialog.setHeaderText("Aucune IP serveur configurer pour l'application");
            dialog.setContentText("Veuillez entrer l'IP:");

            // Traditional way to get the response value.
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                ip = result.get();
                System.out.println("IP: " + ip);
                Context.getInstance().setIp(ip);
            }
        }
        if (textNoEmploye.getText().isEmpty() || textMotPasse.getText().isEmpty()) {
            displayChampsErreur();
        } else {
            try {
                int numEmp = Integer.parseInt(textNoEmploye.getText());
                // Demande de login au serveur.
                if(!Context.getInstance().login(numEmp, textMotPasse.getText()))
                    displayChampsErreur();
            } catch (Exception e) {
                displayChampsErreur();
            }
            //lancer la connection

        }

    }

    private void displayChampsErreur() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Connection");
        alert.setHeaderText("Champs invalide");
        alert.setContentText("Vous n'avez pas entrer votre numéro d'employé ou mot de passe correctement. ");
        alert.showAndWait();
    }

    @FXML
    void deconnectionServeur(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Connection");
        alert.setHeaderText("Quitter");
        alert.setContentText("Voulez-vous quitter la connection sécurisée?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            Context.getInstance().fermetureConnect();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnDeconnection.setDisable(true);
        Context.getInstance().addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (Context.getInstance().isLoggedIn()) {
            Platform.runLater(() -> {
                btnDeconnection.setDisable(false);
                btnConnection.setDisable(true);
                textBienvenu.setText("Bienvenu " + Context.getInstance().getPrenomEmp() + " " + Context.getInstance().getNomEmpl());
            });

        } else if (!Context.getInstance().isLoggedIn()) {
            Platform.runLater(() -> {
                btnDeconnection.setDisable(true);
                btnConnection.setDisable(false);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Mots de passe ou Id invalide");
                alert.setHeaderText("Champs invalide");
                alert.setContentText("Votre numéro d'employé ou votre mots de passe est incorrect \n ou ne fait pas partie de notre base de données");
                alert.showAndWait();
            });

        }

    }
}
