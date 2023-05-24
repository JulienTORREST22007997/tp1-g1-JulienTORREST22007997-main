package fr.amu.iut.exercice11;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;

public class PopulationChartController {
    @FXML
    private BarChart<String, Number> chart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private TextField dateField;

    @FXML
    private TextField valueField;

    private ObservableList<XYChart.Data<String, Number>> data;

    public void initialize() {
        data = FXCollections.observableArrayList();
        xAxis.setCategories(FXCollections.<String>observableArrayList());

        chart.setData(FXCollections.singletonObservableList(
                new XYChart.Series<>(data)
        ));
    }

    @FXML
    private void addData(ActionEvent event) {
        String date = dateField.getText();
        String value = valueField.getText();

        if (!date.isEmpty() && !value.isEmpty()) {
            XYChart.Data<String, Number> newData = new XYChart.Data<>(date, Integer.parseInt(value));
            int existingIndex = data.indexOf(newData);

            if (existingIndex >= 0) {
                data.set(existingIndex, newData);
            } else {
                data.add(newData);
                xAxis.getCategories().add(date);
            }

            dateField.clear();
            valueField.clear();
        }
    }
}