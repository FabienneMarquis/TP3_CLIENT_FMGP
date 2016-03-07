package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Chambre;
import model.Context;
import model.Reservation;

import java.net.URL;
import java.util.*;

public class ControllerReservation implements Initializable, Observer
{


        @FXML
        private ListView<Reservation> listViewClientTOUT;

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
        private ComboBox<Chambre> chambreDisponible;

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
                        Context.getInstance().supressionReservation();
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
                        Context.getInstance().getReservation().setIdChambre(Context.getInstance().getChambre().getIdChambre());
                        Context.getInstance().getReservation().setDateCheckIn(new Date(dateCheckIn.getValue().toEpochDay()));
                        Context.getInstance().getReservation().setDateCheckOut(new Date(dateCheckOut.getValue().toEpochDay()));
                        Context.getInstance().modificationReservation();
                }
        }

        @Override
        public void initialize(URL location, ResourceBundle resources) {
                btnsupprimer.setDisable(true);
                listViewClientTOUT.setItems(Context.getInstance().getReservationsOb());
                listViewClientTOUT.getSelectionModel().selectedItemProperty().addListener(
                        (observable, oldValue, newValue) -> {
                                btnsupprimer.setDisable(false);
                               Context.getInstance().setReservation(newValue);
                        });
                chambreDisponible.setItems(FXCollections.observableArrayList(Context.getInstance().getChambres()));
                chambreDisponible.getSelectionModel().selectedItemProperty().addListener(
                        (observable, oldValue, newValue) -> Context.getInstance().setChambre(newValue));
        }

        @Override
        public void update(Observable o, Object arg) {
                listViewClientTOUT.setItems(Context.getInstance().getReservationsOb());
        }
}
