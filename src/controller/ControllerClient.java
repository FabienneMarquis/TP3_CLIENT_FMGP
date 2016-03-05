package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerClient implements Initializable{

    @FXML
    private ListView<?> listViewClientTOUT;

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
    private ListView<?> listViewReservationParClient;

    @FXML
    void delete(ActionEvent event) {

    }

    @FXML
    void save(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//liste des clients
    }
}
