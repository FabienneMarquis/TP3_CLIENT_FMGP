package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

public class ControllerReservation {


        @FXML
        private ListView<?> listViewClientTOUT;

        @FXML
        private TextField nomClient;

        @FXML
        private TextField prenomClient;

        @FXML
        private TextField telephoneClient;

        @FXML
        private DatePicker dateCheckIn;

        @FXML
        private DatePicker dateCheckOut;

        @FXML
        private ComboBox<?> chambreDisponible;

        @FXML
        private Button btnsupprimer;

        @FXML
        private Button btnSave;

        @FXML
        void delete(ActionEvent event) {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Avertissement");
                alert.setHeaderText("Suppression de la réservation");
                alert.setContentText("Voulez-vous vraiment supprimer cette réservation? \n Cette action est finale. ");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.OK) {
                        //supprimer la  réservation
                }
        }

        @FXML
        void save(ActionEvent event) {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Sauvegarde");
                alert.setHeaderText("Modification de la réservation");
                alert.setContentText("Voulez-vous enregistrer les modifications fait sur cette réservation?");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.OK) {
                        //enregistrer les modification de la réservation
                }
        }

    }
