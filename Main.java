import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Simulation");
        View root = new View();
        root.drawGrid();

        Scene scene1  = new Scene(root);
        stage.setScene(scene1);

        stage.show();
    }

    public static void main(String[] args){
        launch();
    }
}
