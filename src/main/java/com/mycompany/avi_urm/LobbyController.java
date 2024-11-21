/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.avi_urm;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Makin Artavia
 */
public class LobbyController implements Initializable {

    @FXML
    private AnchorPane anch_myPane;
    @FXML
    private Label lbl_exit;
    @FXML
    private Label lbl_minimize;
    @FXML
    private ChoiceBox<String> cbx_options;
    @FXML
    private ImageView imv_shield;
    @FXML
    private ImageView imv_newsOne;
    @FXML
    private ImageView imv_newsTwo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cbx_options.getItems().addAll("AVI", "Sedes y facultades", "Info.General");
        cbx_options.setValue("Info.General");
        imv_shield.setImage(new Image(getClass().getResourceAsStream("/URM_shield.png")));
        imv_newsOne.setImage(new Image(getClass().getResourceAsStream("/OIP.jpg")));
        imv_newsTwo.setImage(new Image(getClass().getResourceAsStream("/newsTwo.jpg")));
    }    

    @FXML
    private void CloseWindown(MouseEvent event) {
        Platform.exit();
    }

    @FXML
    private void MinimizeWindow(MouseEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.setIconified(true);
    }
    @FXML
    private void ChangeWindow(MouseEvent event) throws IOException {
        switch (cbx_options.getValue()) {
                case "AVI":
                    App.setRoot("login");
                    break;
                case "Sedes y facultades":
                    App.setRoot("faculties_view");
                    break;
                default:
                    break;
            }
    }
    
}
