package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Chambre;
import model.Client;
import model.Context;
import model.Reservation;

import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControllerNewReservation implements Initializable {

    @FXML
    private ListView<Client> listClientExistant;

    @FXML
    private DatePicker dateCheckIn;

    @FXML
    private DatePicker dateCheckOut;

    @FXML
    private ComboBox<Chambre> chambreLibre;

    @FXML
    private Button btnSave;

    @FXML
    void save(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sauvegarde");
        alert.setHeaderText("Nouvelle Réservation");
        alert.setContentText("Voulez-vous enregistrer cette nouvelle réservation?" );
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            //ajouter une nouvelle réservation
            Reservation reservation = new Reservation(new Date(dateCheckIn.getValue().toEpochDay()), new Date(dateCheckOut.getValue().toEpochDay()),
                    Context.getInstance().getClient().getIdClient(), Context.getInstance().getChambre().getIdChambre());
            Context.getInstance().setReservation(reservation);
            Context.getInstance().newReservation();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listClientExistant.setItems(FXCollections.observableArrayList(Context.getInstance().getClients()));
        listClientExistant.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> Context.getInstance().setClient(newValue));
        chambreLibre.setItems(FXCollections.observableArrayList(Context.getInstance().getChambres()));
        chambreLibre.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> Context.getInstance().setChambre(newValue));
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

