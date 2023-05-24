package fr.amu.iut.exercice7;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class CounterController implements Initializable {
    @FXML
    int counter = 0;

    @FXML
    Label counterLabel = new Label("0");

    @FXML
    public void increment() {
        counter++;
        counterLabel = new Label(String.valueOf(counter));
    }

    @FXML
    public void decrement() {
        counter--;
        counterLabel = new Label(String.valueOf(counter));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initializing CounterController...");
   }


}
