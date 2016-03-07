package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Client;
import model.Context;
import model.Reservation;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControllerClient implements Initializable{

    @FXML
    private ListView<Client> listViewClientTOUT;

    @FXML
    private TextField nomClient;

    @FXML
    private TextField prenomClient;

    @FXML
    private TextField telephoneClient;

    @FXML
    private Button btnSupprimer;

    @FXML
    private Button btnSave;

    @FXML
    private ListView<Reservation> listViewReservationParClient;

    @FXML
    void delete(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression");
        alert.setHeaderText("Suppression de la réservation");
        alert.setContentText("Voulez-vous supprimer cette réservation?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            Context.getInstance().supressionClient();
        }

    }

    @FXML
    void save(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sauvegarde");
        alert.setHeaderText("Modification de la client");
        alert.setContentText("Voulez-vous enregistrer les modifications fait sur ce client?\n" +
                " Cette action est finale.");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            Context.getInstance().getClient().setNom(nomClient.getText());
            Context.getInstance().getClient().setPrenom(prenomClient.getText());
            Context.getInstance().getClient().setTelephone(Integer.parseInt(telephoneClient.getText()));
            Context.getInstance().modificaitonClient();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnSupprimer.setDisable(true);
        listViewClientTOUT.setItems(FXCollections.observableArrayList(Context.getInstance().getClient()));
        listViewClientTOUT.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    btnSupprimer.setDisable(false);
                    Context.getInstance().setClient(newValue);
                    listViewReservationParClient.setItems(FXCollections.observableArrayList(
                            Context.getInstance().listReservationSelonClient()));
                });

    }
}
