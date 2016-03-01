package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;
import model.Context;

import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControllerNewReservation implements Initializable{

      @FXML
    private ListView<?> listClientExistant;

    @FXML
    private DatePicker dateCheckIn;

    @FXML
    private DatePicker dateCheckOut;

    @FXML
    private ComboBox<?> chambreLibre;

    @FXML
    private Button btnSave;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        dateCheckIn.setValue(LocalDate.now());
//        final Callback<DatePicker, DateCell> dayCellFactory =
//                new Callback<DatePicker, DateCell>() {
//                    @Override
//                    public DateCell call(final DatePicker datePicker) {
//                        return new DateCell() {
//                            @Override
//                            public void updateItem(LocalDate item, boolean empty) {
//                                super.updateItem(item, empty);
//
//                                if (item.isBefore(
//                                        dateCheckIn.getValue().plusDays(1))
//                                        ) {
//                                    setDisable(true);
//                                    setStyle("-fx-background-color: #ffc0cb;");
//                                }
//                            }
//                        };
//                    }
//                };
//        dateCheckOut.setDayCellFactory(dayCellFactory);
//        dateCheckOut.setValue(dateCheckOut.getValue().plusDays(1));

        }
}

