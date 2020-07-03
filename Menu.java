import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

public class Menu extends ToolBar {
    View view;

    public Menu(View v) {
        view = v;
        Button start = new Button("Start");
        start.setOnAction(this::handleStart);
        Button pause = new Button("Pause");
        pause.setOnAction(this::handlePause);
        Button reset = new Button("Reset");
        reset.setOnAction(this::handleReset);
        Button step = new Button("Step");
        step.setOnAction(this::handleStep);

        getItems().addAll(start,pause,reset,step);
    }

    private void handleStart(ActionEvent actionEvent) {
        System.out.println("start");
        view.setAppState(view.SIMULATION);
        view.start();
    }

    private void handlePause(ActionEvent actionEvent) {
        System.out.println("pause");
        view.stop();
    }

    private void handleReset(ActionEvent actionEvent) {
        System.out.println("reset");
        view.setAppState(view.EDITING);
        view.reset();
        view.drawGrid();
    }

    private void handleStep(ActionEvent actionEvent) {
        System.out.println("step");
        view.setAppState(view.SIMULATION);
        view.step();
        view.drawGrid();
    }
}
