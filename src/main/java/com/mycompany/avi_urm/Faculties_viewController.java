/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.avi_urm;

import Player.SoundPlayer;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author Makin Artavia
 */
public class Faculties_viewController implements Initializable {

    @FXML
    private Label titleLabel;
    @FXML
    private VBox facultiesContainer;
    private JSONArray facultiesList = new JSONArray();
    @FXML
    private AnchorPane faculties_ap;
    @FXML
    private ScrollPane faculties_vbox;
    @FXML
    private Label lbl_minimize1;
    @FXML
    private Label lbl_exit1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        RemoteConnection.GetInstance().getFacultiesInLobby();
        facultiesList = RemoteConnection.GetInstance().getFacultiesList();
        if (facultiesList.length() == 0) {
            System.out.println("No hay facultades disponibles.");
        } else {
            displayFaculties();
        }
    }

    private void displayFaculties() {
        for (int i = 0; i < facultiesList.length(); i++) {
            try {
                JSONObject faculty = facultiesList.getJSONObject(i);

                String facultyName = faculty.getString("faculty_name");
                String facultyInformation = faculty.getString("faculty_information");

                HBox facultyPanel = new HBox(10);
                facultyPanel.getStyleClass().add("faculties-panel"); 
                facultyPanel.setPrefHeight(150);

                Label lblFacultyName = new Label(facultyName);
                lblFacultyName.getStyleClass().add("faculties-title");

                Label lblFacultyInfo = new Label(facultyInformation);
                lblFacultyInfo.getStyleClass().add("faculties-content");
                lblFacultyInfo.setWrapText(true);

                VBox textContainer = new VBox(lblFacultyName, lblFacultyInfo);
                textContainer.setSpacing(5);

                facultyPanel.getChildren().add(textContainer);

                facultiesContainer.getChildren().add(facultyPanel);
            } catch (JSONException e) {
                System.out.println("Error al obtener facultades: " + e.getMessage());
            }
        }
    }

    @FXML
    private void FX(MouseEvent event) {
        SoundPlayer player = new SoundPlayer("/fx.wav");
        player.play();
    }

    @FXML
    private void GoBack(MouseEvent event) throws IOException {
        App.setRoot("lobby");
    }

    @FXML
    private void MinimizeWindow(MouseEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.setIconified(true);
    }

}
