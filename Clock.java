import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;

public class Clock {
    private Timeline timeline;
    private View view;
    private SimulationInterface sim;

    public Clock(View v, SimulationInterface s) {
        view = v;
        sim = s;
        timeline = new Timeline(new KeyFrame(Duration.millis(10),this::nextStep));
        timeline.setCycleCount(Animation.INDEFINITE);
    }

    private void nextStep(ActionEvent actionEvent) {
        sim.step();
        view.drawGrid();
    }

    public void start() {
        timeline.play();
    }

    public void stop() {
        timeline.stop();
    }
}
