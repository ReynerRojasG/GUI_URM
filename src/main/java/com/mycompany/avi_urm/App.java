package com.mycompany.avi_urm;

import Objects.User;
import Player.SoundPlayer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.image.Image;
import javafx.stage.StageStyle;

/**
 * JavaFX App
 */
public class App extends Application {

    private static User user;
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("lobby"), 1400, 770);
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/URM.png")));
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        SoundPlayer player = new SoundPlayer("/Title.wav");
        player.loop();
        player.setVolume(-30.0f);
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void setUser(User pUser) {
        user = pUser;
    }

    public static User getUser() {
        return user;
    }
}
