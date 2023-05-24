package fr.amu.iut.exercice11;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class PopulationChartController implements Initializable {
    @FXML
    private BarChart<String, Number> chart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private VBox textFieldsContainer;

    private ObservableList<XYChart.Data<String, Number>> data;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        data = FXCollections.observableArrayList();
        chart.setData(FXCollections.singletonObservableList(new XYChart.Series<>("Population", data)));
        xAxis.setLabel("Date");

    }

    @FXML
    private void addTextField() {
        int textFieldCount = textFieldsContainer.getChildren().size();
        String label = "Date " + (textFieldCount ) + "   ";
        TextField textField = new TextField();
        textField.setMaxWidth(100);
        textField.setTranslateY(-6); // J'ai rajouté ça car sinon le texte est légèrement trop haut par rapport au champs
        textField.setPromptText("Valeur");
        textField.setOnKeyPressed(this::handleTextFieldKeyPress);

        HBox textFieldBox = new HBox(new Text(label), textField);
        textFieldsContainer.getChildren().add(textFieldBox);
    }


    private void handleTextFieldKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            TextField textField = (TextField) event.getSource();
            String value = textField.getText();

            if (!value.isEmpty()) {
                String date = ((Text) ((HBox) textField.getParent()).getChildren().get(0)).getText().replace(":", "");
                int parsedValue = parseValue(value);
                if (parsedValue != -1) {
                    updateChartData(date, parsedValue);
                }
            }

            event.consume();
        }
    }

    private int parseValue(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void updateChartData(String date, int value) {
        XYChart.Data<String, Number> newData = new XYChart.Data<>(date, value);
        int existingIndex = data.indexOf(newData);

        if (existingIndex >= 0) {
            data.set(existingIndex, newData);
        } else {
            data.add(newData);

            List<String> categories = new ArrayList<>(xAxis.getCategories());
            if (!categories.contains(date)) {
                categories.add(date);
                xAxis.setCategories(FXCollections.observableArrayList(categories));
            }
        }
    }
}