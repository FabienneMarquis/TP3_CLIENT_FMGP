package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import model.Context;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControllerConnection implements Initializable, Observer {

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

        if(textNoEmploye.getText().isEmpty() || textMotPasse.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Connection");
            alert.setHeaderText("Champs invalide");
            alert.setContentText("Vous n'avez pas entrer votre numéro d'employé ou mot de passe correctement. ");
            alert.showAndWait();
        }

    }

    @FXML
    void deconnectionServeur(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Connection");
        alert.setHeaderText("Quitter");
        alert.setContentText("Voulez-vous quitter la connection sécurisée?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            //supprimer la  réservation
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnDeconnection.setDisable(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(Context.getInstance().isConnection()==true){
            btnDeconnection.setDisable(false);
            btnConnection.setDisable(true);
        }else if (Context.getInstance().isConnection()==false){
            btnDeconnection.setDisable(true);
            btnConnection.setDisable(false);
        }

    }
}
