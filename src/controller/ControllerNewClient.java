package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.util.Optional;

public class ControllerNewClient {

    @FXML
    private TextField nomClient;

    @FXML
    private TextField prenomClient;

    @FXML
    private TextField telephoneClient;

    @FXML
    private Button btnAnnuler;

    @FXML
    private Button btnSave;

    @FXML
    void annuler(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Annuler");
        alert.setContentText("Voulez-vous effac");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {

            //ajouter nouveau client à BD en plus de la réservation


            //ajouter une nouvelle réservation

        }
    }

    @FXML
    void save(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sauvegarde");
        alert.setHeaderText("Nouvelle Réservation");
        alert.setContentText("Voulez-vous enregistrer cette nouvelle réservation?" +
                "");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {

            //ajouter nouveau client à BD en plus de la réservation


            //ajouter une nouvelle réservation

        }
    }

}

