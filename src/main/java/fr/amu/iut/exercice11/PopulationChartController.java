package fr.amu.iut.exercice11;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class PopulationChartController {
    @FXML
    private BarChart<String, Number> chart;

    @FXML
    private CategoryAxis xAxis;
    @FXML
    private TextFlow textFlow;

    private ObservableList<XYChart.Data<String, Number>> data;

    @FXML
    private VBox textFieldsContainer;

    private int textFieldCounter = 1;

    @FXML
    private void addTextField(ActionEvent event) {
        HBox textFieldBox = new HBox(10);
        TextFlow textFieldTextFlow = new TextFlow(new Text("Date" + textFieldCounter + " "));
        TextField textField = new TextField();

        textFieldBox.getChildren().addAll(textFieldTextFlow, textField);
        textFieldsContainer.getChildren().add(textFieldBox);

        textFieldCounter++;
    }

    public void initialize() {
        data = FXCollections.observableArrayList();
        xAxis.setCategories(FXCollections.<String>observableArrayList());

        chart.setData(FXCollections.singletonObservableList(
                new XYChart.Series<>(data)
        ));
    }

}