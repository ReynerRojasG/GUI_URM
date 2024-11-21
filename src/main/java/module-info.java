module com.mycompany.avi_urm {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.net.http;
    requires org.json;
    requires java.desktop;
    opens com.mycompany.avi_urm to javafx.fxml;
    opens Objects;
    opens Player;
    exports com.mycompany.avi_urm;
}
