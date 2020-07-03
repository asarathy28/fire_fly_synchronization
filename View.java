import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;

public class View extends VBox {
    public static final int EDITING = 0;
    public static final int SIMULATION = 1;
    private SimulationInterface sim;
    private int simSize = 25;
    private int canvSize = 700;
    private Canvas canvas;
    private GraphicsContext grid;
    private Affine affine;
    private Menu menu;
    private int appState;
    private Clock clock;

    public View() {
        appState = 0;
        sim = new FireflySimMS(simSize,simSize);//ChangeSIM
        affine = new Affine();
        affine.appendScale(canvSize/(float)simSize,canvSize/(float)simSize);
        canvas = new Canvas(canvSize,canvSize);
        menu = new Menu(this);
        setSpacing(5);
        setAlignment(Pos.CENTER);
        getChildren().addAll(menu,canvas);
    }

    public void drawGrid() {
        grid = canvas.getGraphicsContext2D();
        grid.setTransform(affine);

        grid.setFill(Color.LIGHTGREY);
        grid.fillRect(0,0,canvSize,canvSize);

        for (int i = 0; i < simSize; i++) {
            for (int j = 0; j < simSize; j++) {
                int unitState = sim.getUnitState(i,j);
                if (unitState == 1) {
                    setGreen(i, j);
                } else if (unitState == -1) {
                    setPink(i,j);
                } else if (unitState == 2) {
                    setOrange(i,j);
                } else if (unitState == 3) {
                    setBlue(i,j);
                }
                else {
                   setBlack(i,j);
                }
            }
        }

        grid.setStroke(Color.GREY);
        grid.setLineWidth(.01);
        for (int i = 0; i < simSize; i++) {
            grid.strokeLine(i,0,i,simSize);
        }
        for (int i = 0; i < simSize; i++) {
            grid.strokeLine(0,i,simSize,i);
        }
    }
    public void step() {
        sim.step();
    }

    public void stop() {
        clock.stop();
    }

    public void start() {
        clock.start();
    }

    //state -1
    private void setPink(int col, int row) {
        grid.setFill(Color.web("#f0aef5"));
        grid.fillRect(row,col,1,1);
    }

    //state 0 (neutral)
    private void setBlack(int col, int row) {
        grid.setFill(Color.BLACK);
        grid.fillRect(row,col,1,1);
    }

    //state 1
    private void setGreen(int col, int row) {
        grid.setFill(Color.web("#51eda7"));
        grid.fillRect(row,col,1,1);
    }

    //state 2
    private void setOrange(int col, int row) {
        grid.setFill(Color.web("#db9d76"));
        grid.fillRect(row,col,1,1);
    }

    //state 3
    private void setBlue(int col, int row) {
        grid.setFill(Color.web("#2179d1"));
        grid.fillRect(row,col,1,1);
    }

    public void setAppState(int as) {
        if (as == appState) {
            return;
        }
        if (as == SIMULATION) {
            clock = new Clock(this,sim);
        }
        appState = as;
        System.out.println("Application Stat: " + appState);
    }

    public void reset() {
        sim = new FireflySimMS(simSize,simSize); //ChangeSIM
    }

}
