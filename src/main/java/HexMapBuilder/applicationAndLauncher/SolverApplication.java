package HexMapBuilder.applicationAndLauncher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SolverApplication extends Application {


    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/mainScene.fxml"));
        primaryStage.setTitle("Hex Map Builder");
        primaryStage.setScene(new Scene(root, 1200, 700));
        primaryStage.show();
    }




    // must be public to be launched by AppLauncher
    public void launch(){
        launch("anyString");

    }
}
