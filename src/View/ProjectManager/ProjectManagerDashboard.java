import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class ProjectManagerDashboard extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = (Parent) FXMLLoader.load(getClass().getResource("ProjectManager/projectmanagerDashboard.fxml"));
        Scene scene = new Scene(root);
        primaryStage.getIcons().add(new Image("https://static.thenounproject.com/png/600904-84.png"));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Bug Tracker");
        primaryStage.show();
    }
}
