package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import model.Context;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class ControllerTab implements Initializable, Observer {

    @FXML
    private Tab tabReservation;

    @FXML
    private Tab tabNewReservation;

    @FXML
    private Tab tabClient;

    @FXML
    private Tab tabnewClient;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabReservation.setDisable(true);
        tabNewReservation.setDisable(true);
        tabClient.setDisable(true);
        tabnewClient.setDisable(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(Context.getInstance().isLoggedIn()==true){
            tabReservation.setDisable(false);
            tabNewReservation.setDisable(false);
            tabClient.setDisable(false);
            tabnewClient.setDisable(false);
        }
    }
}
