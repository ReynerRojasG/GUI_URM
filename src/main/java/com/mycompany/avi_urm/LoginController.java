/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.avi_urm;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Makin Artavia
 */
public class LoginController implements Initializable {

    @FXML
    private Label lbl_exit;
    @FXML
    private Label lbl_minimize;
    @FXML
    private ImageView imv_shield;
    @FXML
    private ImageView imv_peopleIcon;
    @FXML
    private ImageView imv_PassIcon;
    @FXML
    private Label lbl_info;
    @FXML
    private TextField tf_user;
    @FXML
    private TextField tf_pass;
    @FXML
    private Button btn_enter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        imv_shield.setImage(new Image(getClass().getResourceAsStream("/URM_SHIELD2.png")));
        imv_peopleIcon.setImage(new Image(getClass().getResourceAsStream("/peopleIcon.png")));
        imv_PassIcon.setImage(new Image(getClass().getResourceAsStream("/passIcon.png")));
    }

    @FXML
    private void CloseWindown(MouseEvent event) throws IOException {
        App.setRoot("lobby");
    }

    @FXML
    private void MinimizeWindow(MouseEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void ProvideAcces(ActionEvent event) throws IOException {
        if(RemoteConnection.GetInstance().login(tf_user.getText(), tf_pass.getText())){
            if(App.getUser().getType().equals("Profesor")){
                App.setRoot("ProfessorsPortal");
            }else if(App.getUser().getType().equals("Estudiante")){
                App.setRoot("StudentsPortal");
            }else{
                App.setRoot("adminsPortal");
            }
        }else{
                lbl_info.setText("Usuario no encontrado.");
        }
    }
    
}
